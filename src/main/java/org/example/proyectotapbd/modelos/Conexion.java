package org.example.proyectotapbd.modelos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    static private String DB = "restaurante";
    static private String USER = "admin";
    static private String PWD = "admin2124";
    static private String HOST = " localhost";//127.0.0.1(loopback)
    static private String PORT = "3306";
    public static Connection connection;

    public static void create_connection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB, USER, PWD);
            System.out.println("Conexion establecida :)");

        } catch (Exception e) {
            e.printStackTrace();//taza de ejecucion

        }

    }

}
