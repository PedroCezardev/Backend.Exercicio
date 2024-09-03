package primeira.aula.demo.exception;

public class InvalidEditoraException extends RuntimeException{
    public InvalidEditoraException (String message) {
        super(message);
    }

    public InvalidEditoraException () {
        super("Editora procurado nao encontrado.");
    }
}