package springboot.springboot.database;

import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class DatabaseHelper {
	
	private static DatabaseHelper mDatabaseHelper = null;
	private DatabaseConnection mDatabaseConnection;
	
	private DatabaseHelper() {
		mDatabaseConnection = new DatabaseConnection();
	}
	
	public static synchronized DatabaseHelper getInstance() {
        if(mDatabaseHelper == null) {
        	mDatabaseHelper = new DatabaseHelper();
        }
        
        return mDatabaseHelper;
	}
	

	public int insert(String table, ContentValues initialValues) {
		if (!mDatabaseConnection.isOpen()) {
			throw new IllegalStateException("database not open");
		}

		String sqlQuery = prepareInsertSqlQuery(table, initialValues);

		return mDatabaseConnection.executeUpdateQuerySQL(sqlQuery);
	}
	
	public int insert(String table, ContentValues initialValues, String columnName) {
		if (!mDatabaseConnection.isOpen()) {
			throw new IllegalStateException("database not open");
		}

		String sqlQuery = prepareInsertSqlQuery(table, initialValues);
		
		return mDatabaseConnection.executeUpdateQuerySQL(sqlQuery, columnName);
	}
	
	private String prepareInsertSqlQuery(String table, ContentValues initialValues) {
		// Measurements show most sql lengths <= 152
		StringBuilder sql = new StringBuilder(152);
		sql.append("INSERT");
		sql.append(" INTO ");
		sql.append(table);
		// Measurements show most values lengths < 40
		StringBuilder values = new StringBuilder(40);

		Set<Map.Entry<String, Object>> entrySet = null;
		if (initialValues != null && initialValues.size() > 0) {
			entrySet = initialValues.valueSet();
			Iterator<Map.Entry<String, Object>> entriesIter = entrySet
					.iterator();
			sql.append('(');

			boolean needSeparator = false;
			while (entriesIter.hasNext()) {
				if (needSeparator) {
					sql.append(", ");
					values.append(", ");
				}
				needSeparator = true;
				Map.Entry<String, Object> entry = entriesIter.next();
				sql.append(entry.getKey());
				values.append("'" + entry.getValue() + "'");
			}

			sql.append(')');
		}

		sql.append(" VALUES(");
		sql.append(values);
		sql.append(");");
		
		return sql.toString();
	}
	
	public int getCount(String tablename, String columnName) {
		if (!mDatabaseConnection.isOpen()) {
			throw new IllegalStateException("database not open");
		}
		return mDatabaseConnection.getMaxCount(tablename, columnName);
	}
	
	public int getRowCount(String query) {
		if (!mDatabaseConnection.isOpen()) {
			throw new IllegalStateException("database not open");
		}
		return mDatabaseConnection.getRowCount(query);
	}
	
	public ResultSet query(String sqlQuery) {
		if (!mDatabaseConnection.isOpen()) {
			throw new IllegalStateException("database not open");
		}
		
		return mDatabaseConnection.getDatabaseResults(sqlQuery);
	}
	
	public ResultSet getAllRows(String tablename) {
		if (!mDatabaseConnection.isOpen()) {
			throw new IllegalStateException("database not open");
		}
		
		String sqlQuery = "SELECT * FROM " + tablename;
		
		return mDatabaseConnection.getDatabaseResults(sqlQuery);
	}
	
	public boolean executeQuery(String sqlQuery) {
		if (!mDatabaseConnection.isOpen()) {
			throw new IllegalStateException("database not open");
		}
		
		return mDatabaseConnection.executeNonQuerySQL(sqlQuery);
	}
	
	public int delete(String table, String whereClause) {
		if (!mDatabaseConnection.isOpen()) {
			throw new IllegalStateException("database not open");
		}
		
		return mDatabaseConnection.executeUpdateQuerySQL("DELETE FROM " + table + (null != whereClause ? " WHERE " + whereClause : ""));
	}
	
	public int update(String table, ContentValues values, String whereClause) {
		if (!mDatabaseConnection.isOpen()) {
			throw new IllegalStateException("database not open");
		}
		
		if (values == null || values.size() == 0) {
            throw new IllegalArgumentException("Empty values");
        }
		
		StringBuilder sql = new StringBuilder(120);
        sql.append("UPDATE ");
        sql.append(table);
        sql.append(" SET ");
        
        Set<Map.Entry<String, Object>> entrySet = values.valueSet();
        Iterator<Map.Entry<String, Object>> entriesIter = entrySet.iterator();

        while (entriesIter.hasNext()) {
            Map.Entry<String, Object> entry = entriesIter.next();
            sql.append(entry.getKey());
            sql.append("=");
            sql.append("'");
            sql.append(entry.getValue());
            sql.append("'");
            if (entriesIter.hasNext()) {
                sql.append(", ");
            }
        }
        
        if (null != whereClause) {
            sql.append(" WHERE ");
            sql.append(whereClause);
        }
		
        return mDatabaseConnection.executeUpdateQuerySQL(sql.toString());
	}
	
	public int getId(String tableName, String columnName, String idColumnName, String idName) {
		String query = "SELECT * FROM " + tableName + " WHERE " + columnName
				+ "='" + idName + "'";
		ResultSet result = query(query);
		int id = -1;

		try {
			while (result.next()) {
				id = result.getInt(idColumnName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}
	
	public ResultSet getResultSet(String tableName, String columnName, int userid) {
		if (!mDatabaseConnection.isOpen()) {
			throw new IllegalStateException("database not open");
		}
		
		String query = "SELECT * FROM " + tableName + " WHERE " + columnName
				+ "='" + userid + "'";
		return query(query);
	}

}
