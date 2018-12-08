
package com.ifpb.edu.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jose Carlos
 */
public class ConexaoBrasilDb {
    
    private String url; 
    private String user; 
    private String password; 
    
    public ConexaoBrasilDb(){
        this.url = "jdbc:postgresql://localhost:5432/brasil";
        this.user = "postgres";
        this.password = "matheus123";
    }
    
    public Connection getConnection(){
        try {
            
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, user, password);
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro na classeForName");
        } catch (SQLException ex) {
            System.out.println("Erro na conexão com banco");
        }
        return null;
        
    }
    
}
