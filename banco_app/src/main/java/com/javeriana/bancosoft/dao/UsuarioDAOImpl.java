package com.javeriana.bancosoft.dao;

import com.javeriana.bancosoft.model.Usuario;
import com.javeriana.bancosoft.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {
    private Connection conexion;

    // Constructor: obtiene la conexión desde DBConnection
    public UsuarioDAOImpl() {
        this.conexion = DBConnection.getInstance();
    }

    @Override
    public void crearUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, correo, clave) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getCorreo());
            stmt.setString(3, usuario.getClave());  // ⚠️ TODO: En el futuro encriptar clave
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // ⚠️ TODO: Manejar errores correctamente
        }
    }

    @Override
    public Usuario obtenerPorCorreoYClave(String correo, String clave) {
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND clave = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, correo);
            stmt.setString(2, clave);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("clave")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Retorna null si no encuentra usuario
    }

    @Override
    public Usuario obtenerPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("clave")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Usuario> obtenerTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("clave")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}
