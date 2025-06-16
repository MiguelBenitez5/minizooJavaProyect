/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mini.zoo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *Esta clase proporciona una conexion a la base de datos del sitio web del MiniZoo Juan XXIII
 *La finalidad de esta conexion es acceder a los datos para manipularlos en el programa "Que animal eres"
 *Dicho programa tiene el fin de ofrecer un producto entretenido para la Expo Didactica del año 2025
 * @author Estudiantes de tercer semestre de Analisis de Sistemas
 */
public class ConectionDB {
    private static final String DB = "if0_38870682_minizooxxiii";
    private static final String HOST = "127.0.0.1";
    private static final String PORT = "3306";
    private static final String USER = "root";
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB + "?useSSL=false";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    /**
     * Este metodo conecta java con la base de datos MiniZoo
     * 
     * @return El objeto de conexion si se realizo con exito o null en caso contrario 
     */
    public static Connection connect(){
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL,USER,PASSWORD);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Lo sentimos, en este momento no se puede procesar tu solicitud","No se pudo conectar a la base de datos",JOptionPane.ERROR_MESSAGE);
            return null;
        }catch(ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null,"2","No se pudo conectar a la base de datos",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }  
}
