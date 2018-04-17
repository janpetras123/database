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
 * Trieda UpdateController zabezpeèuje aktualizáciu záznamu pod¾a toho, 
 * na ktorý záznam uživate¾ klikne a z neho pod¾a id daný záznam aktualizuje.
 *
 */


public class UpdateController {
	
	
	public static Integer id;
	
	public void Input(String name, String surname, int weight) {
		Connection c = null;
		int i=0;
		
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBS_Race", "postgres", "postgres");
            c.setAutoCommit(false);
            c.createStatement();
            
            int exactly = GUIController.line + GUIController.i*20;
            
            PreparedStatement preparedStatement = 
            		c.prepareStatement("SELECT p.id, meno, priezvisko, t.nazov, t.krajina, count(pp.cas) FROM pretekar p "
            						 + "JOIN tim t ON id_tim=t.id "
            						 + "LEFT JOIN pretekar_v_preteku pp ON p.id=pp.id_pretekar "
            						 + "WHERE p.meno=? and p.priezvisko=? "
            						 + "GROUP BY 1, 2, 3, 4, 5 "
            						 + "LIMIT 1 OFFSET ?");
            
            		preparedStatement.setString(1, GUIController.name);
            		preparedStatement.setString(2, GUIController.surname);
            		preparedStatement.setInt(3, exactly);
            		
            		System.out.println(GUIController.name);
            		System.out.println(GUIController.surname);
            		System.out.println(exactly);
            		
            		ResultSet rs = preparedStatement.executeQuery();
            		
            		GUIController.counterAll = 0;
            		
            		while(rs.next())
            		{
            			id = rs.getInt("id");
            			GUIController.counterAll++;
            			i++;
            		}
            		            		
            		rs.close();
            		preparedStatement.close();
            		
            		
            		if(i > 0)
            		{
            		preparedStatement = 
            		c.prepareStatement("UPDATE pretekar SET meno=?, priezvisko=?, vaha=? WHERE id=?");
            		
            		preparedStatement.setString(1, name);
            		preparedStatement.setString(2, surname);
            		preparedStatement.setInt(3, weight);
            		preparedStatement.setInt(4, id);
            		
            		preparedStatement.executeUpdate();
            		
            		preparedStatement.close();
            		
            		
            		System.out.println(name);
            		System.out.println(surname);
            		System.out.println(weight);
            		
            		c.commit();
                    c.close();
                    
            		}else{
            			
            			Alert alert = new Alert(AlertType.ERROR  /*ERROR*/);
        				alert.setTitle("Error Dialog");
        				alert.setHeaderText(null);
        				alert.setContentText("Incorrect value in some field(s).");

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
