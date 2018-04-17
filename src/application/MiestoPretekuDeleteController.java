package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
 * 
 * Trieda MiestoPretekuDeleteController vymaže záznam o mieste preteku z tabu¾ky.
 *
 */


public class MiestoPretekuDeleteController {
	
	public void Input (String country, String address) {
        Connection c = null;
        int i=0;
        
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBS_Race", "postgres", "postgres");
            c.setAutoCommit(false);
            c.createStatement();
                        
            
            
            PreparedStatement preparedStatement = 
            		c.prepareStatement("SELECT id FROM pretek where id_miesto_preteku=(SELECT id FROM miesto_preteku WHERE krajina=? and adresa=? LIMIT 1)");

            		preparedStatement.setString(1, country);
            		preparedStatement.setString(2, address);
            		
            		ResultSet rs = preparedStatement.executeQuery();
            		int id = 0;
            		
            		
            		
            		while(rs.next()){
            			id = rs.getInt("id");
            			PreparedStatement preparedStatement2;
            			preparedStatement2 = c.prepareStatement("DELETE FROM pretekar_v_preteku WHERE id_pretek=?");
            			
            			preparedStatement2.setInt(1, id);
                		
                        preparedStatement2.executeUpdate();
                        preparedStatement2.close();
                        i++;
            		}
            		
            		System.out.println(id);
            		rs.close();
            		
            		preparedStatement.close();
            		
            		
            		if(i > 0)
            		{
            		PreparedStatement preparedStatement1 = 
                    		c.prepareStatement("SELECT id FROM miesto_preteku WHERE krajina=? and adresa=?");

                    		preparedStatement1.setString(1, country);
                    		preparedStatement1.setString(2, address);
                    		
                    		ResultSet rs1 = preparedStatement1.executeQuery();

                    		while(rs1.next()){
                    			id = rs1.getInt("id");
                    			break;
                    		}
                    		
                    		System.out.println(id);
                    		rs.close();
                    		preparedStatement1.close();
            		
            		
                    		
            		PreparedStatement preparedStatement3 = 
            		c.prepareStatement("DELETE FROM pretek WHERE id_miesto_preteku=?");
            		
            		preparedStatement3.setInt(1, id);
            		
            		preparedStatement3.executeUpdate();
            		
            		preparedStatement3.close();
            
            		
            		
            		PreparedStatement preparedStatement4 = 
        		    c.prepareStatement("DELETE FROM miesto_preteku WHERE id=?");
            		
            		preparedStatement4.setInt(1, id);
            		
            		preparedStatement4.executeUpdate();
            		
            		preparedStatement4.close();
            		
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
