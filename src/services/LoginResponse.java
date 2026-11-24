package services;

import models.Usuario;

public class LoginResponse {

    private boolean success;
    private String message;
    private Usuario usuario;

    public LoginResponse(boolean success, String message, Usuario usuario) {
        this.success = success;
        this.message = message;
        this.usuario = usuario;
    }

    public static LoginResponse success(String message, Usuario usuario) {
        return new LoginResponse(true, message, usuario);
    }

    public static LoginResponse error(String message) {
        return new LoginResponse(false, message, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
