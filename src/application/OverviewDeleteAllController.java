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
 * Trieda OverviewDeleteAllController vymazáva vyfiltrovaný údaje z tabu¾ky pretekar,
 * kde je milión záznamov. Ak je vybraný konkrétny záznam, tak sa vymaže pod¾a jeho id.
 *
 */


public class OverviewDeleteAllController {
	
	public void Input() {
		Connection c = null;
		int id=0, i=0;
		int exactly = GUIController.line + GUIController.i*20;
		
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBS_Race", "postgres", "postgres");
            c.setAutoCommit(false);
            c.createStatement();
            
            System.out.println(GUIController.line);
            
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
            		
            		while(rs.next())
            		{
            			id = rs.getInt("id");
            			i++;
            		}
            		            		
            		rs.close();
            		preparedStatement.close();
            		
            		System.out.println(id);
            		
            		if(i > 0)
            		{
            		preparedStatement = 
                    c.prepareStatement("DELETE FROM pretekar_v_preteku WHERE id_pretekar=?");
                            		
                    preparedStatement.setInt(1, id);
                            		
                    preparedStatement.executeUpdate();
                            		
                    preparedStatement.close();
                    
                    
                    
            		preparedStatement = 
            		c.prepareStatement("DELETE FROM pretekar WHERE id=?");
            		
            		preparedStatement.setInt(1, id);
            		
            		preparedStatement.executeUpdate();

            		preparedStatement.close();
            		
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
