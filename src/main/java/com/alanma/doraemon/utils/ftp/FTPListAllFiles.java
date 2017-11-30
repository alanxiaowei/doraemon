package com.alanma.doraemon.utils.ftp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;




import java.util.List;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;


public class FTPListAllFiles {

	private static FTPClient ftp ;


	/**
	 * 重载构造函数
	 * 
	 * @param isPrintCommmand
	 *            是否打印与FTPServer的交互命令
	 */
	/*public FTPListAllFiles() {
		ftp = new FTPClient();
		
		if (true) {
			ftp.addProtocolCommandListener(new PrintCommandListener(
					new PrintWriter(System.out)));
		}
	}*/
	
	/**
	 * 登陆FTP服务器
	 * 
	 * @param host
	 *            FTPServer IP地址
	 * @param port
	 *            FTPServer 端口
	 * @param username
	 *            FTPServer 登陆用户名
	 * @param password
	 *            FTPServer 登陆密码
	 * @return 是否登录成功
	 * @throws IOException
	 */
	public static boolean login(String host, int port, String username, String password)
			throws IOException {
		ftp =new FTPClient();
		ftp.connect(host, port);
		if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
			if (ftp.login(username, password)) {
				ftp.setControlEncoding("UTF-8");
				ftp.enterLocalPassiveMode();// ftp启动被动模式
				return true;
			}
		}
		
		return false;
	}

	/**
	 * 关闭数据链接
	 * 
	 * @throws IOException
	 */
	public static void disConnection() throws IOException {
		if (ftp.isConnected()) {
			ftp.disconnect();
		}
	}

	/**
	 * 递归遍历出目录下面所有文件夹
	 * 
	 * @param pathName
	 *            需要遍历的目录，必须以"/"开始和结束
	 * @throws IOException
	 */
	public static java.util.List<String> directoryList(String pathName)
			throws IOException {
		 
		ArrayList<String> directoryList = new ArrayList<String>();
		if (pathName.endsWith("/")) {
			String directory = pathName;
			// 更换目录到当前目录
			ftp.changeWorkingDirectory(directory);
			FTPFile[] files = ftp.listFiles();
			for (FTPFile file : files) {
				if (file.isDirectory()) {
					directoryList.add(file.getName());
				}
			}
		}
		if (ftp.isConnected()) {
			ftp.disconnect();
		}
		return directoryList;
	}

	/**
	 * 递归遍历目录下面指定的文件名
	 * 
	 * @param pathName
	 *            需要遍历的目录，必须以"/"开始和结束
	 * @param ext
	 *            文件的扩展名
	 * @throws IOException
	 */
	public static java.util.List<String> filesList(String pathName, String ext)
			throws IOException {
		
		ArrayList<String> fileList = new ArrayList<String>();
		String directory = pathName;
		// 更换目录到当前目录
		boolean b = ftp.changeWorkingDirectory(directory);
		System.out.println(b);
		FTPFile[] files = ftp.listFiles();

		for (FTPFile file : files) {

			if (file.isFile()) {
				if (file.getName().endsWith(ext)) {
					fileList.add(file.getName());
				}
			}

		}
		if (ftp.isConnected()) {
			ftp.disconnect();
		}
		return fileList;
	}

	public static void main(String[] args) throws IOException {
		
		if(FTPListAllFiles.login("192.168.2.12", 21, "webftp", "webftp")){
			java.util.List<String> listDirectory = FTPListAllFiles.directoryList("importfiles/");
			for (String string : listDirectory) {
				//java.util.List<String> listFile = FTPListAllFiles.filesList(string + "/", "jpg");
				System.out.println(string + "------");
				
			}
		
		}
		if (FTPListAllFiles.login("192.168.2.12", 21, "webftp", "webftp")) {
			List<String> filesList = FTPListAllFiles.filesList("contract/200000000001588"+"/", "jpg");
			for (String string2 : filesList) {
				System.out.println(string2);
			}
		}
		
	}
}
