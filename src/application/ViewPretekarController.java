package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 
 * Trieda ViewPretekarController vytvorí preh¾ad o danom zákaznikovi, 
 * ktorý je vybraný pod¾a zadaných parametrov od uživate¾a. 
 * Tento preh¾ad o pretekárovi sa následne zobrazí.
 *
 */


public class ViewPretekarController{

	public static String nameLab, surnameLab, birthdayLab, weightLab, racesLab;

	public void Input(String name, String surname, String team) {
		Connection c = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBS_Race", "postgres", "postgres");
            c.setAutoCommit(false);
            c.createStatement();
            
            
            PreparedStatement preparedStatement = 
            		c.prepareStatement("SELECT meno, priezvisko, datum_narodenia, vaha, count(pp.cas) FROM pretekar p "
            						 + "JOIN tim t ON id_tim=t.id "
            						 + "LEFT JOIN pretekar_v_preteku pp ON p.id=pp.id_pretekar "
            						 + "WHERE p.meno=? and p.priezvisko=? and t.nazov=? "
            						 + "GROUP BY 1, 2, 3, 4");
            		
            
            		preparedStatement.setString(1, name);
            		preparedStatement.setString(2, surname);
            		preparedStatement.setString(3, team);
            		
            		ResultSet rs = preparedStatement.executeQuery();

            		while(rs.next()){
            			
            			nameLab = rs.getString("meno");
                		surnameLab = rs.getString("priezvisko");
                		birthdayLab = rs.getString("datum_narodenia");
                		weightLab = rs.getString("vaha");
                		racesLab = rs.getString("count");
            			break;
            		}
            		
            		rs.close();
            		preparedStatement.close();
         
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
