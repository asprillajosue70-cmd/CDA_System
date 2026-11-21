package services;

import repositories.UsuarioRepository;
import models.Usuario;

public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public ServiceResponse login(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            return ServiceResponse.error("[NOT FOUND] Usuario no encontrado.");
        }

        if (!usuario.getPassword().equals(password)) {
            return ServiceResponse.error("[UNAUTHORIZED] Contraseña incorrecta.");
        }

        return ServiceResponse.success("Inicio de sesión exitoso.\nBienvenido, " + usuario.getName() + "!");
    }
}

