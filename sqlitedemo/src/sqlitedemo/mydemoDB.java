package sqlitedemo;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Download the SQLite package: https://bitbucket.org/xerial/sqlite-jdbc/downloads/sqlite-jdbc-3.16.1.jar
 * TODO: Fix methods to be reusable
 * @author tasos
 * Simple sqlite demo
 */
public class mydemoDB {
	
	private static Connection connection=null;
	private final static File currentFilePath = new File(mydemoDB.class.getProtectionDomain()
			.getCodeSource().getLocation().getPath());
	
	private static final String DBFOLDER = currentFilePath.getParentFile()+ File.separator+"db"+File.separator;
	
	public static void main(String[] args) {
		System.out.println(DBFOLDER);
		connect("test.db");
		createNewTable("test.db");
	}
	
	private static void connect(String filename){
		if(connection!=null)return;
		try{
			 // db parameters
            String url = "jdbc:sqlite:"+DBFOLDER+filename;
            // create a connection to the database
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		} 
	}
	private static void close(){
		try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	}
	
	/**
	 * Create a new table in given db
	 * @param db: the database to create table
	 */
	public static void createNewTable(String db){
	        // SQL statement for creating a new table
	        String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
	                + "	id integer PRIMARY KEY,\n"
	                + "	name text NOT NULL,\n"
	                + "	capacity real\n"
	                + ");";
	        connect(db);
	        try (Statement stmt = connection.createStatement()) {
	            // create a new table
	            stmt.execute(sql);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }finally {
	            close();
	        }
	}
	
	 /**
     * Create a new database
     *
     * @param fileName: the database file name
     */
    public static void createNewDatabase(String fileName) {
 
    		connect(fileName);
    		try{
	            if (connection != null) {
	                DatabaseMetaData meta = connection.getMetaData();
	                System.out.println("The driver name is " + meta.getDriverName());
	                System.out.println("A new database has been created.");
	            }
    		}catch(SQLException e){
    			System.out.println(e.getMessage());
    		}finally {
                close();
            }
    }
}
