package com.javeriana.bancosoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:h2:~/bancosoft_db;AUTO_SERVER=TRUE"; // Crea una BD en tu directorio de usuario
    private static final String USER = "sa";  // Usuario por defecto
    private static final String PASSWORD = "";  // Sin contraseña

    private static Connection conexion;

    public static Connection getInstance() {
        if (conexion == null) {
            try {
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Conexión a la BD establecida en " + URL);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("❌ Error al conectar a la BD.");
            }
        }
        return conexion;
    }
}
