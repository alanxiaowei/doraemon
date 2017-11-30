package com.alanma.doraemon.utils.sftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import com.alanma.doraemon.utils.string.StringUtil;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * SFTPUtil
 * 
 * 
 * @date 2015-6-23
 */
public class SFTPUtil {

	/**
	 * 默认端口
	 */
	private final static int DEFAULT_PORT = 22;

	private final static String HOST = "host";

	private final static String PORT = "port";

	private final static String USER_NAME = "userName";

	private final static String PASSWORD = "password";

	/**
	 * 默认配置文件路径
	 */
	private final static String FTP_CONFIG_PATH = "sftpConfig.properties";

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

	private ChannelSftp sftp;

	public SFTPUtil() {
		super();
		// init(FTP_CONFIG_PATH);
	}

	public SFTPUtil(String host, int port, String userName, String password) {
		this.init(host, port, userName, password);
	}

	public SFTPUtil(Properties prop) {
		this.init(prop);
	}

	/**
	 * 
	 * @param configPath
	 *            classpath路径
	 */
	public SFTPUtil(String configPath) {
		this.init(configPath);
	}

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

	private void init(String configPath) {
		// this.init(PropertiesUtil.getProperties(configPath));
	}

	/**
	 * 连接sftp
	 * 
	 * 
	 * @throws JSchException
	 * @date 2015-6-23
	 */
	private void connect() throws JSchException {
		JSch jsch = new JSch();
		// 取得一个SFTP服务器的会话
		Session session = jsch.getSession(userName, host, port);
		// 设置连接服务器密码
		session.setPassword(password);
		Properties sessionConfig = new Properties();
		// StrictHostKeyChecking
		// "如果设为"yes"，ssh将不会自动把计算机的密匙加入"$HOME/.ssh/known_hosts"文件，
		// 且一旦计算机的密匙发生了变化，就拒绝连接。
		sessionConfig.setProperty("StrictHostKeyChecking", "no");
		// 设置会话参数
		session.setConfig(sessionConfig);
		// 连接
		session.connect();
		// 打开一个sftp渠道
		Channel channel = session.openChannel("sftp");
		channel.connect();
		sftp = (ChannelSftp) channel;
	}

	/**
	 * 上传文件
	 * 
	 * 
	 * @date 2015-6-23
	 */
	public void uploadFile() {
		FileInputStream inputStream = null;
		try {
			connect();
			if (StringUtil.isEmpty(remote)) {
				remote = local.getName();
			}
			if (!StringUtil.isEmpty(remotePath)) {
				sftp.cd(remotePath);
			}
			inputStream = new FileInputStream(local);
			sftp.put(inputStream, remote);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sftp.disconnect();
			// ResourceUtil.close(inputStream);
		}
	}

	/**
	 * 下载
	 * 
	 * 
	 * @date 2015-6-23
	 */
	public void download() {
		FileOutputStream output = null;
		try {
			this.connect();
			if (!StringUtil.isEmpty(remotePath)) {
				sftp.cd(remotePath);
			}
			output = new FileOutputStream(local);
			sftp.get(remote, output);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sftp.disconnect();
			// ResourceUtil.close(output);
		}
	}

	public void setRemote(String remote) {
		this.remote = remote;
	}

	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}

	public void setLocal(File local) {
		this.local = local;
	}

	public static void main(String[] args) {
		// ===上传文件===
		SFTPUtil sftp = new SFTPUtil("192.168.2.18", 22, "root", "shpt810");
		// 本地文件
		File local = new File("D:\\testfile.txt");
		// 保存在服务端的文件名，如果不设置，将默认为本地文件名
		sftp.setLocal(local);
		// 保存在服务端的路径，如果不设置，将为用户登录之后的当前路径
		sftp.setRemotePath("/home/11/30");
		sftp.uploadFile();

		// ===下载文件===
		// File local = new File("C:\\home\\testfile.txt");
		// sftp.setLocal(local);
		// sftp.setRemote("testfile.txt");
		// sftp.setRemotePath("/root");
		// sftp.download();
	}

}
