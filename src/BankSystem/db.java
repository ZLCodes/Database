package BankSystem;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class db {
	private Connection dbConn;
	private Statement stateMent;
	public db()
	{
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=BankSystem";
		String userName = "sa";
		String userPwd = "zhulin1996";
		try {
			Class.forName(driverName);
			dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
			System.out.println("Connection Successful!");
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	public db(String s)
	{
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName="+s;
		String userName = "sa";
		String userPwd = "zhulin1996";
		try {
			Class.forName(driverName);
			dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
			System.out.println("Connection Successful!");
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	public int executeUpdate(String sql) throws SQLException{
		
		stateMent = dbConn.createStatement();
		return stateMent.executeUpdate(sql);
		
	}
	public ResultSet executeQuery(String sql) throws SQLException{
		stateMent = dbConn.createStatement();
		return stateMent.executeQuery(sql);
		
	}
	public void closeConn() throws SQLException{
		stateMent.close();
		dbConn.close();
	}
	public PreparedStatement PreparedStatement(String sql) throws SQLException
	{
		return dbConn.prepareStatement(sql);
	}
	public CallableStatement AddCard(String Proc_CardInfo)throws SQLException
	{
		return dbConn.prepareCall(Proc_CardInfo);
	}
	public CallableStatement AddUser(String Proc_UserInfo)throws SQLException
	{
		return dbConn.prepareCall(Proc_UserInfo);
	}
	public CallableStatement Store(String Proc_Store)throws SQLException
	{
		return dbConn.prepareCall(Proc_Store);
	}
	public CallableStatement Prozh(String Proc_Zh)throws SQLException
	{
		return dbConn.prepareCall(Proc_Zh);
	}
}
