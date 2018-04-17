package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
 * 
 * Trieda SponzorDeleteController vykonáva vymazávanie sponzora z databázy 
 * cez dve tabu¾ky, pretože sa musí najskor vykona� vymazanie z tabu¾ky 
 * sponzor_timu, na ktorý sa neviaže žiadny FK a následne vymaže aj konkrétného sponzora.
 *
 */


public class SponzorDeleteController {
	
	public void Input (String name, LocalDate creat, String link) {
        Connection c = null;
        int i=0;
        
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBS_Race", "postgres", "postgres");
            c.setAutoCommit(false);
            c.createStatement();
            
            java.util.Date date = java.sql.Date.valueOf(creat);
            
            PreparedStatement preparedStatement = 
            		c.prepareStatement("SELECT id FROM sponzor WHERE meno=? and vznik=? and link=?");

            		preparedStatement.setString(1, name);
            		preparedStatement.setDate(2, (Date) date);
            		preparedStatement.setString(3, link);
            		
            		ResultSet rs = preparedStatement.executeQuery();
            		int id = 0;

            		while(rs.next()){
            			id = rs.getInt("id");
            			i++;
            			break;
            		}
            		
            		rs.close();
            		preparedStatement.close();
            		
            		if(i > 0)
            		{
            			PreparedStatement preparedStatement1 = 
            			c.prepareStatement("DELETE FROM sponzor_timu WHERE id_sponzor=?");
            		
            			preparedStatement1.setInt(1, id);
            		
            			preparedStatement1.executeUpdate();
            		
            			preparedStatement1.close();
            
            		
            			PreparedStatement preparedStatement2 = 
            			c.prepareStatement("DELETE FROM sponzor WHERE id=?");
            		
            			preparedStatement2.setInt(1, id);

            			preparedStatement2.executeUpdate();
        		
            			preparedStatement2.close();
        		
        		
            			System.out.println( "Name = " + name );
            			System.out.println( "Created = " + date );
            			System.out.println( "Link = " + link );
            			System.out.println();
                
            			c.commit();
            			c.close();
            		}else{
            			
            			Alert alert = new Alert(AlertType.ERROR  /*ERROR*/);
        				alert.setTitle("Error Dialog");
        				alert.setHeaderText(null);
        				alert.setContentText("Error: Not added");

        				alert.showAndWait();
            		}

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