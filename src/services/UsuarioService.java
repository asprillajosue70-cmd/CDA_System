package services;

import repositories.UsuarioRepository;
import services.LoginResponse;
import models.Usuario;

public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public LoginResponse login(String username, String password) {

        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            return LoginResponse.error(
                "[NOT FOUND] Usuario no encontrado."
            );
        }

        if (!usuario.getPassword().equals(password)) {
            return LoginResponse.error(
                "[UNAUTHORIZED] Contraseña incorrecta."
            );
        }

        return LoginResponse.success(
            "Inicio de sesión exitoso.",
            usuario
        );
    }

}

