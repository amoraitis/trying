package sqlitedemo;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * TODO: Fix methods to be reusable
 * @author tasos
 * Simple sqlite demo
 */
public class mydemoDB {
	private static Connection connection=null;
	public static void main(String[] args) {
		connect("test.db");
		createNewTable();
		createNewDatabase("chinook.db");
		createNewDatabase("test.db");
	}
	
	public static void connect(String filename){
		try{
			 // db parameters
            String url = "jdbc:sqlite:C:/Users/τασος/Documents/trying/sqlitedemo/db/"+filename;
            // create a connection to the database
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
	}
	
	public static void createNewTable(){
	        // SQL statement for creating a new table
	        String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
	                + "	id integer PRIMARY KEY,\n"
	                + "	name text NOT NULL,\n"
	                + "	capacity real\n"
	                + ");";
	        
	        try (Statement stmt = connection.createStatement()) {
	            // create a new table
	            stmt.execute(sql);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	}
	
	 /**
     * Connect to a sample database
     *
     * @param fileName the database file name
     */
    public static void createNewDatabase(String fileName) {
 
        String url = "jdbc:sqlite:C:/Users/τασος/Documents/trying/sqlitedemo/db/" + fileName;
        if(new File(url).exists()){
        	System.out.println("exists already");
        	return;
        }
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
