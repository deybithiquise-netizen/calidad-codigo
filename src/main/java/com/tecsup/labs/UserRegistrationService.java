package com.tecsup.labs;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Servicio de registro de usuarios con calidad mejorada.
 */
public class UserRegistrationService {

    // Variable privada con getter
    private String lastErrorMessage = "";

    // Lista de usuarios usando genéricos
    private List<String> users = new ArrayList<>();

    private static final int MIN_PASSWORD_LENGTH = 8;

    // Constructor limpio, sin lógica innecesaria
    public UserRegistrationService() {
        // Constructor simple, sin lógica redundante
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    /**
     * Registra un nuevo usuario.
     *
     * @param username Nombre de usuario (no null, no vacío)
     * @param password Contraseña (no null, mínimo 8 caracteres)
     * @param email    Correo electrónico válido
     * @return true si se registra correctamente, false en caso contrario
     */
    public boolean registerUser(final String username, final String password, final String email) {
        // Validaciones
        if (username == null || username.trim().isEmpty()) {
            lastErrorMessage = "El nombre de usuario está vacío o es null.";
            return false;
        }

        if (password == null) {
            lastErrorMessage = "La contraseña es null.";
            return false;
        }

        if (password.length() < MIN_PASSWORD_LENGTH) {
            lastErrorMessage = "La contraseña es muy corta.";
            return false;
        }

        if (email == null || !isValidEmail(email)) {
            lastErrorMessage = "El correo electrónico no parece válido.";
            return false;
        }

        // Manejo de excepciones más específico
        try {
            saveUser(username, password, email);
        } catch (IllegalArgumentException e) {
            lastErrorMessage = e.getMessage();
            return false;
        } catch (Exception e) {
            lastErrorMessage = "Error desconocido al guardar el usuario.";
            return false;
        }

        System.out.println("Usuario registrado: " + username);
        return true;
    }

    // Guardar usuario con validación
    private void saveUser(final String username, final String password, final String email) {
        if ("error".equals(username)) {
            throw new IllegalArgumentException("Nombre de usuario no permitido.");
        }
        if (users.contains(username)) {
            throw new IllegalArgumentException("Usuario duplicado.");
        }
        users.add(username);
    }

    // Validación de email usando expresión regular
    private boolean isValidEmail(final String email) {
        String regex = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,}$";
        return Pattern.matches(regex, email);
    }

    // Método optimizado y más claro
    public int countCharacters(final String s) {
        return s != null ? s.length() : -1;
    }
}
