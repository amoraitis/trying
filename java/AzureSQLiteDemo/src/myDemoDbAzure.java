import java.sql.*;

public class myDemoDbAzure {

  public static void main(String[] args) {
	  // Connect to database
      String hostName = "demodbs";
      String dbName = "demojava";
      String user = "amoraitis";
      String password = "Tasosmor#7";
      String url = String.format("jdbc:sqlserver://%s.database.windows.net:1433;database=%s;user=%s;password=%s;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
      Connection connection = null;

      try {
              connection = DriverManager.getConnection(url);
              System.out.println("Query data example:");
              System.out.println("=========================================");
              String tableName = "warehouses";
              // Create and execute a SELECT SQL statement.
              String newtableSql = "CREATE TABLE"
                  + " " +tableName +" (\n"
  	                + "	id integer PRIMARY KEY,\n"
  	                + "	name text NOT NULL,\n"
  	                + "	capacity real\n"
  	                + ");";
              
              String insertDataSql = "INSERT INTO "+tableName+"(id,name,capacity) VALUES(?,?,?)";

              try (//Statement statement = connection.createStatement();
            	PreparedStatement pstmt = connection.prepareStatement(insertDataSql);) {
            	  //statement.execute(newtableSql);
            	  pstmt.setInt(1, 2);
            	  pstmt.setString(2, "me");
                  pstmt.setDouble(3, 21.0);
                  pstmt.executeUpdate();
              }
          }
      catch (Exception e) {
              System.out.println(e.getMessage());
      }finally {
    	  try {
              if (connection != null) {
                  connection.close();
                  connection=null;
              }
          } catch (SQLException ex) {
              System.out.println(ex.getMessage());
          }
      }
  }
}
