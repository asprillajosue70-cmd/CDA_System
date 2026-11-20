package repositories;

import java.util.ArrayList;
import models.Usuario;

public class UsuarioRepository {

    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public void add(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario findByUsername(String username) {

        for (Usuario usuario : usuarios) {

            if (usuario.getUsername().equals(username)) {
                return usuario;
            }   
        }

        return null;
    }

    public ArrayList<Usuario> findAll() {
        return usuarios;
    }

}
