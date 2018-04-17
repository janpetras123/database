package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;


/**
 * 
 * Trieda OverviewAllController vypisuje údaje z tabu¾ky pretekar,
 * kde je milión záznamov. Ak je vybraný konkrétny záznam, tak sa vyberie aj jeho id.
 *
 */


public class OverviewAllController {
	
	public void Input(Integer counter1, TableView<Overview> table) {
		Connection c = null;
		int i=0;
		List<Overview> result = new LinkedList<Overview>();
        
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
            						 + "LIMIT 20 OFFSET ?");
            
            preparedStatement.setInt(1, counter1*20);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            
            while(rs.next())
            {
            	result.add( 
            			new Overview(rs.getInt("id"),
            			rs.getString("meno"),
            			rs.getString("priezvisko"),
            			rs.getString("nazov"),
            			rs.getString("krajina"),
            			rs.getInt("count")
            			));
            	i++;
            }
            		
            
            		table.getItems().addAll(result);
            		
            		
            		rs.close();
            		preparedStatement.close();
            		
            		if(i > 0)
            		{
            		preparedStatement = 
                    		c.prepareStatement("SELECT p.id FROM pretekar p "
                    						 + "WHERE vaha='53'");
            		
                    
                    rs = preparedStatement.executeQuery();
                    
                    GUIController.counterAll = 0;
                    
                    while(rs.next())
                    {
                    	GUIController.counterAll++;
                    }
                    
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
