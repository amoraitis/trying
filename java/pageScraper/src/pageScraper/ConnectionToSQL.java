package pageScraper;

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
 * SQLite viewer@ https://sqliteonline.com/
 * Tutorial: 
 * TODO: Fix methods to be reusable
 * @author tasos
 * Simple sqlite demo
 */
public class ConnectionToSQL extends Thread{
	
	private static String name, links, images;
	
	private static Connection connection=null;
	private final static File currentFilePath = new File(ConnectionToSQL.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	private static String DBFOLDER = currentFilePath.getParentFile().getParentFile()+ File.separator+"db"+File.separator;
	
	public ConnectionToSQL(String name, String links, String images) {
		this.name= name;
		this.links=links;
		this.images=images;
	}
	
	public ConnectionToSQL(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		if(DBFOLDER.contains("%cf"))
			fixPath();
		System.out.println(DBFOLDER);
		//queryAll("extracted_sites.db", "links");
		querySite("extracted_sites.db", "links", name);
		//insertData("extracted_sites.db", "links", name, links, images);
		/*for(double i=0; i<7.0; i++){
			insertData("test.db", "warehouses", "mywarehouses", 34.0+i);
		}*/
		//deleteBetween("extracted_sites.db",3,4,"links");
		//queryAll("test.db", "warehouses");		
	}
	
	private static void fixPath() {
		int start=0,end=0;
		for(int i=0; i<DBFOLDER.length(); i++){
			if(DBFOLDER.substring(i, i+6).equals("%cf%84")){
				start=i;
			}else if (DBFOLDER.substring(i, i+1).equals("2")) {
				end=i+1;
				break;
			}
		}
		String substr= DBFOLDER.substring(start,end);
		DBFOLDER=DBFOLDER.replace(substr, "τασος");
		//System.out.println(DBFOLDER);
	}

	private static void connect(String filename){
		if(connection!=null)return;
		String url = "jdbc:sqlite:"+DBFOLDER+filename;
		try{
			 // db parameters
            Class.forName("org.sqlite.JDBC");
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
	
	/**
	 * Deletes table rows by id starting from idmin ending to idmax
	 * @param db to edit
	 * @param idmin minid to delete
	 * @param idmax maxid to delete
	 * @param table to edit
	 */
	public static void deleteBetween(String db, int idmin, int idmax, String table){
		String sql = "DELETE FROM " + table+ " WHERE id >= ? AND id <= ?";
		connect(db);
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
	
	public static Site querySite(String db,String table, String site) {
		String sql = "SELECT id, name, links, photos FROM "+table + " WHERE name = ?";
        connect(db);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);){
        	preparedStatement.setString(1, site);
            ResultSet rs    = preparedStatement.executeQuery();
            // loop through the result set
            while (rs.next()) {
            	int id = rs.getInt("id");
            	String name = rs.getString("name");
            	String links = rs.getString("links");
            	String photos = rs.getString("photos");
                System.out.println(id +  "\t" + 
                                   name + "\t" +
                                   links + "\t" +
                                   photos);
             return   new Site(name,links,photos);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }finally{
        	close();
        }
        return null;
	}
	
	/**
	 * Prints a tab
	 * @param db to query
	 * @param table for query
	 */
	public static void queryAll(String db, String table){
		 String sql = "SELECT id, name, links, photos FROM "+table;
	        connect(db);
	        try (Statement stmt  = connection.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            
	            // loop through the result set
	            while (rs.next()) {
	            	int id = rs.getInt("id");
	            	String name = rs.getString("name");
	            	String links = rs.getString("links");
	            	String pictures = rs.getString("photos");
	                System.out.println(id +  "\t" + 
	                                   name + "\t" +
	                                   links + "\t" +
	                                   pictures);
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
	public static void insertData(String db, String table, String name, String links, String picturesLinks){
		String sql = "INSERT INTO "+table+"(name,links,photos) VALUES(?,?,?)";
		connect(db);
        try (
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, links);
            pstmt.setString(3, picturesLinks);
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
	                + "	links text NOT NULL,\n"
	                + " photos text NOT NULL"
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
