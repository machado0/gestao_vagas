package br.com.machado.gestaovagas.modules.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("Usuário já existe");
    }
}
