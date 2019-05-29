package com.mxw.doraemon.ftp;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ftp工具类
 * 
 * 
 * @date 2015-6-10
 */
public class FTPUtil {

	/**
	 * 默认端口
	 */
	private final static int DEFAULT_PORT = 21;

	private final static String HOST = "host";

	private final static String PORT = "port";

	private final static String USER_NAME = "userName";

	private final static String PASSWORD = "password";

	/**
	 * 默认配置文件路径
	 */
	private final static String FTP_CONFIG_PATH = "ftpConfig.properties";

	/**
	 * ftpClient
	 */
	private FTPClient ftpClient;

	/**
	 * 服务端保存的文件名
	 */
	private String remote;

	/**
	 * 服务端保存的路径
	 */
	private String remotePath;

	/**
	 * 本地文件
	 */
	private File local;

	/**
	 * 主机地址
	 */
	private String host;

	/**
	 * 端口
	 */
	private int port = DEFAULT_PORT;

	/**
	 * 登录名
	 */
	private String userName;

	/**
	 * 登录密码
	 */
	private String password;

	private static final Map<String, String> replyCodeMap;

	static {
		replyCodeMap = new HashMap<String, String>();
		replyCodeMap.put("530", "登录错误");
		replyCodeMap.put("550", "权限不足");
	}

	public interface FTPAction {
		boolean action(FTPClient ftpClient) throws Exception;
	}

	public void execute(FTPAction ftpAction) {
		try {
			boolean success = ftpAction.action(ftpClient);
			if (!success) {
				int replyCode = ftpClient.getReplyCode();
				String reply = ftpClient.getReplyString();
				if (!FTPReply.isPositiveCompletion(replyCode)) {
					throw new Exception(reply);
				}
			}
		} catch (ConnectException e) {
			e.printStackTrace();
			System.out.println("连接到服务器" + host + ":" + port + "失败," + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <pre>
	 * 默认读取classpath:ftpconfig.propertis
	 * 的信息
	 * 用户自行配置
	 * host:host
	 * port:port
	 * userName:userName
	 * password:password
	 * </pre>
	 */
	public FTPUtil() {
		super();
		// this.init(FTP_CONFIG_PATH);TODO
	}

	public FTPUtil(String host, int port, String userName, String password) {
		this.init(host, port, userName, password);
	}

	public FTPUtil(Properties prop) {
		this.init(prop);
	}

	/**
	 * 
	 * @param configPath
	 *            classpath路径
	 */
	// public FTPUtil(String configPath) {
	// this.init(configPath);
	// }

	/**
	 * 初始化
	 * 
	 * @param prop
	 * 
	 * @date 2015-6-23
	 */
	private void init(Properties prop) {
		init(prop.getProperty(HOST), Integer.parseInt(prop.getProperty(PORT)), prop.getProperty(USER_NAME),
				prop.getProperty(PASSWORD));
	}

	/**
	 * 初始化
	 * 
	 * @param host
	 * @param port
	 * @param userName
	 * @param password
	 * 
	 * @date 2015-6-23
	 */
	private void init(String host, int port, String userName, String password) {
		this.host = host;
		this.port = port;
		this.userName = userName;
		this.password = password;
	}

	// private void init(String configPath) {
	// this.init(PropertiesUtil.getProperties(configPath));
	// }

	/**
	 * 连接ftp
	 * 
	 *
	 * @date 2015-6-23
	 */
	private void ftpConnect() {
		execute(new FTPAction() {
			@Override
			public boolean action(FTPClient ftpClient) throws Exception {
				ftpClient.connect(host, port);
				return true;
			}
		});
	}

	/**
	 * 登录
	 * 
	 *
	 * @date 2015-6-23
	 */
	private void ftpLogin() {
		execute(new FTPAction() {
			@Override
			public boolean action(FTPClient ftpClient) throws Exception {
				return ftpClient.login(userName, password);
			}
		});
	}

	private void ftpSetFileType() {
		execute(new FTPAction() {
			@Override
			public boolean action(FTPClient ftpClient) throws Exception {
				return ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			}
		});
	}

	private void ftpChangeWorkingDirectory() {
		execute(new FTPAction() {
			@Override
			public boolean action(FTPClient ftpClient) throws Exception {
				return ftpClient.changeWorkingDirectory(remotePath);
			}
		});
	}

	private void ftpRetrieveFile(final FileOutputStream output) {
		execute(new FTPAction() {
			@Override
			public boolean action(FTPClient ftpClient) throws Exception {
				return ftpClient.retrieveFile(remote, output);
			}
		});
	}

	private void ftpStoreFile(final FileInputStream fis) {
		execute(new FTPAction() {
			@Override
			public boolean action(FTPClient ftpClient) throws Exception {
				return ftpClient.storeFile(remote, fis);
			}
		});
	}

	/**
	 * 连接ftp服务器
	 *
	 * @throws Exception
	 * 
	 * @throws IOException
	 * @throws SocketException
	 * @date 2015-6-10
	 */
	private void connect() throws SocketException, IOException {
		validateConnectInfo();
		ftpClient = new FTPClient();
		ftpConnect();
		ftpLogin();
		ftpClient.enterLocalPassiveMode();
		ftpSetFileType();
	}

	/**
	 * 验证连接信息
	 * 
	 * 
	 * @date 2015-6-23
	 */
	private void validateConnectInfo() {
		if (StringUtils.isEmpty(host)) {
			System.out.println("host不能为空");
		}
		if (StringUtils.isEmpty(userName)) {
			System.out.println("userName不能为空");
		}
		if (StringUtils.isEmpty(password)) {
			System.out.println("password不能为空");
		}
	}

	/**
	 * 断开连接
	 * 
	 * 
	 * @date 2015-6-23
	 */
	private void disconnect() {
		try {
			ftpClient.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 下载文件
	 * 
	 * 
	 * @date 2015-6-23
	 */
	public void download() {
		FileOutputStream output = null;
		try {
			this.connect();
			if (!StringUtils.isEmpty(remotePath)) {
				ftpChangeWorkingDirectory();
			}
			output = new FileOutputStream(local);
			ftpRetrieveFile(output);
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// TODO ResourceUtil.close(output);
			this.disconnect();
		}
	}

	/**
	 * 上传文件
	 * 
	 * 
	 * @date 2015-6-10
	 */
	public void upload() {
		FileInputStream fis = null;
		try {
			this.connect();
			fis = new FileInputStream(local);
			if (!StringUtils.isEmpty(remotePath)) {
				ftpChangeWorkingDirectory();
			}
			if (StringUtils.isEmpty(remote)) {
				remote = local.getName();
			}
			ftpStoreFile(fis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
			// TODO ResourceUtil.close(fis);
		}
	}

	/**
	 * 服务端保存的路径 如果不设置此值，将会默认为用户登录之后的路径
	 * 
	 * @param remotePath
	 * 
	 * @date 2015-6-23
	 */
	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}

	/**
	 * 服务端保存的文件名 如果不设置此值，将会默认为本地文件的文件名
	 * 
	 * @param remote
	 * 
	 * @date 2015-6-23
	 */
	public void setRemote(String remote) {
		this.remote = remote;
	}

	/**
	 * 本地文件
	 * 
	 * @param local
	 * 
	 * @date 2015-6-23
	 */
	public void setLocal(File local) {
		this.local = local;
	}

	public static void main(String[] args) {
		FTPUtil ftpUtil = new FTPUtil("192.168.2.12", 21, "webftp", "webftp");
		// // 本地文件
		// File local = new File("D:\\testfile.txt");
		// // 保存在服务端的文件名，如果不设置，将默认为本地文件名
		// // ftpUtil.setRemote("upload.txt");
		// // 保存在服务端的路径，如果不设置，将为用户登录之后的当前路径
		// // ftpUtil.setRemotePath("/user/");
		// ftpUtil.setLocal(local);
		// ftpUtil.upload();

		// 文件下载
		// 下载之后，保存到本地的文件
		File file = new File("D:\\FTP\\testfile.txt");
		// 服务端的文件名
		ftpUtil.setRemote("testfile.txt");
		// 服务端文件的路径，如果不设置，将为用户登录之后的当前路径
		// ftpUtil.setRemotePath("/user/");
		ftpUtil.setLocal(file);
		ftpUtil.download();
	}

}
