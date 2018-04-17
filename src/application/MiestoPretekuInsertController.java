package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * Trieda MiestoPretekuInsertController vkladá záznam o novom mieste preteku do tabu¾ky.
 *
 */

public class MiestoPretekuInsertController {
	
	
	public void Input (String country, String address) {
        Connection c = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBS_Race", "postgres", "postgres");
            c.setAutoCommit(false);
            c.createStatement();
            
            PreparedStatement preparedStatement = 
        		    c.prepareStatement("INSERT INTO miesto_preteku (krajina, adresa) VALUES (?, ?)", 
        		    Statement.RETURN_GENERATED_KEYS);

        		                          
        		preparedStatement.setString(1, country);
        		preparedStatement.setString(2, address);

        		preparedStatement.executeUpdate();
            
            
            
            System.out.println( "Country = " + country );
            System.out.println( "Address = " + address );
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
