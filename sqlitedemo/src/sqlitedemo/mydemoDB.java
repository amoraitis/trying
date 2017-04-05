package sqlitedemo;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Download the SQLite package: https://bitbucket.org/xerial/sqlite-jdbc/downloads/sqlite-jdbc-3.16.1.jar
 * Tutorial: 
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
		/*for(double i=0; i<7.0; i++){
			insertData("mywarehouses",34.0+i);
		}*/
		delete(3,25);
		//queryAll();
		
		
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
                connection=null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	}
	
	public static void delete(int idmin, int idmax){
		String sql = "DELETE FROM warehouses WHERE id > ? AND id < ?";
		connect("test.db");
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setInt(1, idmin);
            pstmt.setInt(2, idmax);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
        	close();
        }
	}
	
	public static void queryAll(){
		 String sql = "SELECT id, name, capacity FROM warehouses";
	        connect("test.db");
	        try (Statement stmt  = connection.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            
	            // loop through the result set
	            while (rs.next()) {
	            	int id = rs.getInt("id");
	            	String name = rs.getString("name");
	            	double capacity = rs.getDouble("capacity");
	                System.out.println(id +  "\t" + 
	                                   name + "\t" +
	                                   capacity);
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }finally{
	        	close();
	        }
	}
	
	/**
	 * Insert a new row into the warehouses table
	 * @param firstParam
	 * @param secondParam
	 */
	public static void insertData(String firstParam, double secondParam){
		String sql = "INSERT INTO warehouses(name,capacity) VALUES(?,?)";
		connect("test.db");
        try (
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, firstParam);
            pstmt.setDouble(2, secondParam);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
        	close();
        }
	}
	
	/**
	 * Create a new table in given db
	 * @param db: the database to create table
	 */
	public static void createNewTable(String db, String tableName){
	        // SQL statement for creating a new table
	        String sql = "CREATE TABLE IF NOT EXISTS " +tableName +" (\n"
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
