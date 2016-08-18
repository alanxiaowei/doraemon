package com.alanma.doraemon.utils.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {

	private static int _1W = 10000;
	private static List<String> list = new ArrayList<String>(100 * _1W);

	static {
		for (int i = 0; i < 10 * _1W; i++) {
			list.add(String.valueOf(i));
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		long start = System.currentTimeMillis();
		batchWrite();
		long end = System.currentTimeMillis();
		System.out.println((end - start) + "ms");
	}

	public static void batchWrite() throws SQLException, ClassNotFoundException {
		// 1108ms
		// Class.forName("oracle.jdbc.OracleDriver");
		// Connection connection =
		// DriverManager.getConnection("jdbc:oracle:thin:127.0.0.1:1521:orcl",
		// "xxx", "xxx");
		Connection connection = DBConnetion.getInstance().getCon();
		connection.setAutoCommit(false);
		PreparedStatement cmd = connection.prepareStatement("insert into t values(?)");
		for (int i = 0; i < list.size(); i++) {
			cmd.setString(1, list.get(i));
			cmd.addBatch();
			if (i % _1W == 0) {
				cmd.executeBatch();
			}
		}
		cmd.executeBatch();
		connection.commit();
	}

	public static void jdbcWrite() throws ClassNotFoundException, SQLException {
		// 28189ms
		Class.forName("oracle.jdbc.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:127.0.0.1:1521:orcl", "xxx", "xxx");
		connection.setAutoCommit(false);
		PreparedStatement cmd = connection.prepareStatement("insert into t values(?)");
		for (String s : list) {
			cmd.setString(1, s);
			cmd.execute();
		}
		connection.commit();
	}

	public static void jdbcRead() throws ClassNotFoundException, SQLException {
		// 3120ms
		Class.forName("oracle.jdbc.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:127.0.0.1:1521:orcl", "xxx", "xxx");
		connection.setAutoCommit(false);
		PreparedStatement cmd = connection.prepareStatement("select * from t");
		ResultSet rs = cmd.executeQuery();
		int i = 0;
		while (rs.next()) {
			rs.getString(1);
			i = i + 1;
		}
		System.out.println("count:" + i);
	}

	public static void fetchRead() throws ClassNotFoundException, SQLException {
		// 764ms
		Class.forName("oracle.jdbc.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:127.0.0.1:1521:orcl", "xxx", "xxx");
		connection.setAutoCommit(false);
		PreparedStatement cmd = connection.prepareStatement("select * from t");
		cmd.setFetchSize(_1W);
		ResultSet rs = cmd.executeQuery();
		int i = 0;
		while (rs.next()) {
			rs.getString(1);
			i = i + 1;
		}
		System.out.println("count:" + i);
	}
}
