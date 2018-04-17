package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
 * 
 * Trieda PretekarInsertController pod¾a zadaných údajov na vstupe vyh¾adá 
 * konkrétny tím a následne pridá údaje o novom pretekárovi do tabu¾ky pretekar.
 *
 */


public class PretekarInsertController {
	
	public void Input (String name, String surname, LocalDate creat, int weight, String timId, String country, String address) {
		
		Connection c = null;
		int i=0;
        
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBS_Race", "postgres", "postgres");
            c.setAutoCommit(false);
            c.createStatement();
            
        		    
            
            java.util.Date date = java.sql.Date.valueOf(creat);
            
            PreparedStatement preparedStatement = 
            		c.prepareStatement("SELECT id_tim, t.krajina, t.adresa FROM pretekar JOIN tim t ON id_tim=t.id WHERE t.nazov= ? AND t.krajina=? AND t.adresa=?");
            		
            		preparedStatement.setString(1, timId);
            		preparedStatement.setString(2, country);
            		preparedStatement.setString(3, address);
            		
            		ResultSet rs = preparedStatement.executeQuery();
            		int id = 0;

            		while(rs.next()){
            			id = rs.getInt("id_tim");
            			i++;
            			break;
            		}
            		
            		rs.close();
            		preparedStatement.close();
            		
            		if((id > 0) && (i > 0))
            		{
            		
        		    preparedStatement = c.prepareStatement("INSERT INTO pretekar (meno, priezvisko, datum_narodenia, vaha, id_tim) VALUES (?, ?, ?, ?, ?)", 
        		    Statement.RETURN_GENERATED_KEYS);
        		    
                        
        		    preparedStatement.setString(1, name);
        		    preparedStatement.setString(2, surname);
        		    preparedStatement.setDate(3, (Date) date);
        		    preparedStatement.setInt(4, weight);
        		    preparedStatement.setInt(5, id);

        		    preparedStatement.executeUpdate();
        		    
        		
        		    System.out.println( "Name = " + name );
        		    System.out.println( "Surname = " + surname );
        		    System.out.println( "Created = " + date );
        		    System.out.println( "Weigth = " + weight );
        		    System.out.println( "Tim ID = " + id );
        		    System.out.println();
        		    
        		    c.commit();
        	        c.close();
        	         
            		}else {
            			
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
