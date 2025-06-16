/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mini.zoo;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 2-LASI-2025
 */
public class Species {
    /**
     * Conecta con la base de datos con el fin de obtener el id de todas las especies
     * selecciona un id de forma aleatoria y recoge los datos de esa especie especifica
     * @return Arreglo con los datos [0]Nombre [1]Imagen [3]QR
     */
    public static String[] getRandomSpecie(){
        //conexion a la base de datos
        Connection con = ConectionDB.connect();
        //daeclaracion de ArrayList para alamacenar todos los id
        ArrayList<Integer> species = new ArrayList();
        //verificar la conexion
        if(con!=null){
            try{
                //realizar consulta a la base de datos
                String sql = "SELECT id FROM especies";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                //recorrer todo el resultado obtenido y almacenar cada id en el ArrayList
                while(rs.next()){
                    int id = rs.getInt("id");
                    species.add(id);
                }
                //limpiar recursos al terminar
                rs.close();
                stmt.close();                    
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Ha ocurrido un error en la primera consulta","Error al leer en la base de datos",JOptionPane.ERROR_MESSAGE);
            }
            if(!species.isEmpty()){
                //obtener longitud del arraylist
                int length = species.size();
                //creacion de numero aleatorio desde 0 hasta length-1
                int index = (int)Math.floor(Math.random()*length);
                //obetener el id ganador
                int winnerID = species.get(index);
                try{
                    //consultar el id ganador en la base de datos para obtener sus datos
                    String sql = "SELECT name,img FROM especies WHERE id = ?";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.setInt(1, winnerID);
                    ResultSet rs = stmt.executeQuery();
                    
                    String specieName ="";
                    String imgLocation = "";
                    
                    if(rs.next()){
                        specieName = rs.getString("name");
                        imgLocation = rs.getString("img");
                    }
                    
                    String serverURL = "https://juanxxiiizoo.infinityfreeapp.com/";
                    
                    //url que lleva al sitio web con el boton exclusivo de compartir (unicamente disponible si se escanea este qr
                    String qr = "https://api.qrserver.com/v1/create-qr-code/?size=600x600&data=https://juanxxiiizoo.infinityfreeapp.com/specie_info.php?id="+winnerID+"%26ref=java";
                    String imgUrlEncoded = encodeURL(imgLocation);
                    String fixedImgUrl = serverURL + imgUrlEncoded;
                    System.out.println(fixedImgUrl);
                    
                    //limpieza de recursos y cerrar conexion
                    stmt.close();
                    rs.close();
                    con.close();
                    return new String[]{specieName,fixedImgUrl,qr};
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Ha ocurrido un error en la segunda consulta","Error al leer en la base de datos",JOptionPane.ERROR_MESSAGE);
                }
            }
        
        }
        return null;
    }
      

    
    private static final Map<String, String> ENCODE_MAP = new HashMap<>();

    static {
        // Mapeo de caracteres conflictivos y sus respectivas codificaciones
        String[][] mappings = {
            {" ", "%20"}, {"\"", "%22"}, {"#", "%23"}, {"$", "%24"}, {"%", "%25"}, {"&", "%26"}, {"'", "%27"},
            {"(", "%28"}, {")", "%29"}, {"*", "%2A"}, {"+", "%2B"}, {",", "%2C"}, {":", "%3A"}, {";", "%3B"},
            {"<", "%3C"}, {"=", "%3D"}, {">", "%3E"}, {"?", "%3F"}, {"@", "%40"}, {"[", "%5B"}, {"\\", "%5C"},
            {"]", "%5D"}, {"^", "%5E"}, {"`", "%60"}, {"{", "%7B"}, {"|", "%7C"}, {"}", "%7D"}, {"~", "%7E"},
            {"á", "%C3%A1"}, {"é", "%C3%A9"}, {"í", "%C3%AD"}, {"ó", "%C3%B3"}, {"ú", "%C3%BA"}, {"ñ", "%C3%B1"},
            {"¿", "%C2%BF"}, {"¡", "%C2%A1"}, {"Á", "%C3%81"}, {"É", "%C3%89"}, {"Í", "%C3%8D"}, {"Ó", "%C3%93"},
            {"Ú", "%C3%9A"}, {"Ñ", "%C3%91"}, {"Ü", "%C3%9C"}, {"ü", "%C3%BC"}
        };
        
        for (String[] mapping : mappings) {
            ENCODE_MAP.put(mapping[0], mapping[1]);
        }
    }
    
    /**
     * Convierte una url conflictiva en una valida
     * @param url -La url que se desea verificar
     * @return La url en correcto formato
     */
    public static String encodeURL(String url) {
        StringBuilder encodedURL = new StringBuilder();
        for (char c : url.toCharArray()) {
            String charStr = String.valueOf(c);
            encodedURL.append(ENCODE_MAP.getOrDefault(charStr, charStr));
        }
        return encodedURL.toString();
    }
    }

