package application;

import java.sql.*;
import java.time.LocalDate;


/**
 * Trieda SponzorInsertController pridáva nový záznam sponzora do tabu¾ky sponzor.
 * 
 */


public class SponzorInsertController {


    public void Input (String name, LocalDate creat, String link) {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBS_Race", "postgres", "postgres");
            c.setAutoCommit(false);
            c.createStatement();
            
            
            
            java.util.Date date = java.sql.Date.valueOf(creat);
            
            PreparedStatement preparedStatement = 
        		    c.prepareStatement("INSERT INTO sponzor (meno, vznik, link) VALUES (?, ?, ?)", 
        		    Statement.RETURN_GENERATED_KEYS);

        		                          
        		preparedStatement.setString(1, name);
        		preparedStatement.setDate(2, (Date) date);
        		preparedStatement.setString(3, link);

        		preparedStatement.executeUpdate();
        		
        		
        		System.out.println( "Name = " + name );
                System.out.println( "Created = " + date );
                System.out.println( "Link = " + link );
                System.out.println();
                
                
                
         c.commit();
         c.close();
         

        }catch(SQLException se){
        	try {
				c.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
            se.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
}