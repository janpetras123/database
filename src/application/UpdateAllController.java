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
 * Trieda UpdateAllController zabezpeèuje aktualizáciu záznamu pod¾a toho, 
 * na ktorý záznam uživate¾ klikne a z neho pod¾a id daný záznam aktualizuje.
 *
 */


public class UpdateAllController {
	
public static Integer id;
	
	public void Input(String name, String surname, int weight) {
		Connection c = null;
		int i=0;
		
		int exactly = GUIController.line + GUIController.i*20;
		
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBS_Race", "postgres", "postgres");
            c.setAutoCommit(false);
            c.createStatement();
            
            
            PreparedStatement preparedStatement = 
            		c.prepareStatement("SELECT p.id, meno, priezvisko, t.nazov, t.krajina, count(pp.cas) FROM pretekar p "
            						 + "JOIN tim t ON id_tim=t.id "
            						 + "LEFT JOIN pretekar_v_preteku pp ON p.id=pp.id_pretekar "
            						 + "WHERE vaha='53' "
            						 + "GROUP BY 1, 2, 3, 4, 5 "
            						 + "LIMIT 1 OFFSET ?");
            		
            
            		preparedStatement.setInt(1, exactly);
            		System.out.println(exactly);
            		
            		ResultSet rs = preparedStatement.executeQuery();
            		
            		GUIController.counterAll = 0; 
            		
            		while(rs.next())
            		{
            			id = rs.getInt("id");
            			GUIController.counterAll++;
            			i++;
            		}
            		
            		if(i > 0)
            		{
            		System.out.println(id);
            		rs.close();
            		preparedStatement.close();
            		
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
