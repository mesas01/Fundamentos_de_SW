package com.javeriana.bancosoft.dao;

import com.javeriana.bancosoft.model.Usuario;
import java.util.List;

public interface UsuarioDAO {
    void crearUsuario(Usuario usuario);
    Usuario obtenerPorCorreoYClave(String correo, String clave);
    Usuario obtenerPorId(int id);
    List<Usuario> obtenerTodos();
}
