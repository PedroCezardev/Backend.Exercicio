package primeira.aula.demo.exception;

public class InvalidLivroException extends RuntimeException {
    public InvalidLivroException (String message){
        super(message);
    }

    public InvalidLivroException () {
        super("Livro procurado nao encontrado.");
    }
}