package XML;

import java.sql.*;

public class SQLiteJDBC{
	
	Connection c = null;
	Statement stmt = null;
	String sql = "";
    
    public void createDatabase(){
    	try {
    	      Class.forName("org.sqlite.JDBC");
    	      c = DriverManager.getConnection("jdbc:sqlite:test.db");
    	      c.setAutoCommit(false);
    	      System.out.println("Opened database successfully");
    	      
    	      stmt = c.createStatement();
    	      String sql = "DROP TABLE IF EXISTS NEWS, LINKS";
    	      stmt.executeUpdate(sql);
    	      
    	      stmt = c.createStatement();
    	      sql = "CREATE TABLE NEWS " +
    	    		  "(ID INT PRIMARY KEY     NOT NULL," +
    	              " NAME           TEXT    NOT NULL, " + 
    	    		  " LINK           TEXT     NOT NULL )";
    	      stmt.executeUpdate(sql);
    	      stmt.close();
    	      c.close();
    	 } catch ( Exception e ) {
    	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    	      System.exit(0);
    	    }
    	    System.out.println("Table created successfully");
    }
    
    public void insertData(){
    	try {
    			Class.forName("org.sqlite.JDBC");
    			c = DriverManager.getConnection("jdbc:sqlite:test.db");
    			c.setAutoCommit(false);
    			System.out.println("Opened database successfully");
      
    			for(int i = 0; i < newsList.size(); i++){
    			stmt = c.createStatement();
    			sql = "INSERT INTO NEWS (ID,NAME) " +
    					"VALUES (i, newsList);"; 
    			stmt.executeUpdate(sql);
    			}
    			
    			for(int i = 0; i < newsLink.size(); i++){
        			stmt = c.createStatement();
        			sql = "INSERT INTO NEWS (LINK) " +
        					"VALUES (, newsLink);"; 
        			stmt.executeUpdate(sql);
        			}
          
      
		        stmt.close();
		        c.commit();
		        c.close();
    	 } catch ( Exception e ) {
   	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
   	      System.exit(0);
   	    }
    	System.out.println("Records created successfully");
   }
    
      public void grabData(){
    	  
    	  try {
    	      Class.forName("org.sqlite.JDBC");
    	      c = DriverManager.getConnection("jdbc:sqlite:test.db");
    	      c.setAutoCommit(false);
    	      System.out.println("Opened database successfully");
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM NEWS;" );
		      while ( rs.next() ) {
		         int id = rs.getInt("ID");
		         String  name = rs.getString("NAME");
		         String link  = rs.getString("LINK");
		         System.out.println( "ID = " + id );
		         System.out.println( "NAME = " + name );
		         System.out.println( "LINK = " + link );
		         System.out.println();
		      }
		      
		      rs.close();
		      stmt.close();
		      c.close();
      	} catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Operation done successfully");
      }
}
