package com.javeriana.bancosoft;

import com.javeriana.bancosoft.util.DBConnection;
import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        Connection conexion = DBConnection.getInstance();
        if (conexion != null) {
            System.out.println("🎉 Conexión exitosa a H2.");
        } else {
            System.out.println("❌ No se pudo conectar.");
        }
    }
}
