package springboot.springboot.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

	private Connection dbCon;
	
	public DatabaseConnection() {
		// TODO Auto-generated constructor stub
		
		dbCon = null;
		activateConnection();
	}
	
	boolean isOpen() {
		return (dbCon != null);
	}
	
	String getSqlServerDBName()
	{
		String dbName = "jdbc:postgresql://localhost:5432/student";
		return dbName;
	}
	
	String getSqlServerLoginId()
	{
		String dbUserName = "postgres";
		return dbUserName;
	}
	
	
	String getSqlServerUserPWD()
	{
		String dbPwd = "admin";
		return dbPwd;
	}
	
	Connection getDBConnection()
	{
		return dbCon;
	}
	
	int activateConnection(){
		if(dbCon != null)
			return 1;
		else{
			try{
				dbCon = DriverManager.getConnection(getSqlServerDBName(),
						getSqlServerLoginId(),
						getSqlServerUserPWD());
				System.out.println(dbCon);
				return 1;
			}catch(Exception e){
				throw new IllegalStateException("Something Wrong");
			}
		}
	}
	
	int getRowCount(String query) {
		Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    int rowCount = -1;
	    try {
	      conn = getDBConnection();
	      stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

	      rs = stmt.executeQuery(query);

	      // move to the end of the result set
	      rs.last();
	      // get the row number of the last row which is also the row count
	      rowCount = rs.getRow();
	      System.out.println("rowCount=" + rowCount);
	    } catch (Exception e) {
	      e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    
	    return rowCount;
	  }
	
	int getMaxCount(String tablename, String columnName) {
		try {
			Statement statement = getDBConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT MAX(" + columnName + ") AS " + columnName + " FROM " + tablename);
			rs.next();
			int rowCount = rs.getInt(columnName);
			rs.close();
			statement.close();
			return rowCount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
	
	ResultSet getDatabaseResults(String sqlQuery) {
		try {

			System.out.println("query       " + sqlQuery); // console message
			ResultSet rs = getDBConnection().createStatement().executeQuery(sqlQuery); // executing our query
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	int executeUpdateQuerySQL(String sqlQuery) {
		int row = -1;
		try {
			System.out.println("query       " + sqlQuery); // console message
			row = getDBConnection().createStatement().executeUpdate(sqlQuery); // executing our query
			return row;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}
	
}
