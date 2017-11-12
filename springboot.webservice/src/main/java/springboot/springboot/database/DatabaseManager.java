package springboot.springboot.database;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DatabaseManager {
	
	private DatabaseConnection mDatabaseConnection;
	private static DatabaseManager mDatabaseHelper = null;

	
	private DatabaseManager() {
		mDatabaseConnection = new DatabaseConnection();
	}
	
	public static synchronized DatabaseManager getInstance() {
        if(mDatabaseHelper == null) {
        	mDatabaseHelper = new DatabaseManager();
        }
        
        return mDatabaseHelper;
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
	
	
	public ResultSet getAllRows(String tablename) {
		if (!mDatabaseConnection.isOpen()) {
			throw new IllegalStateException("database not open");
		}
		
		String sqlQuery = "SELECT * FROM " + tablename;
		
		return mDatabaseConnection.getDatabaseResults(sqlQuery);
	}
	
	public int insert(String table, ContentValues initialValues) {
		System.out.println("Insert");
		if (!mDatabaseConnection.isOpen()) {
			throw new IllegalStateException("database not open");
		}

		String sqlQuery = prepareInsertSqlQuery(table, initialValues);

		return mDatabaseConnection.executeUpdateQuerySQL(sqlQuery);
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
		System.out.println("Values" + values);
		sql.append(" VALUES(");
		sql.append(values);
		sql.append(");");
		
		return sql.toString();
	}
}
