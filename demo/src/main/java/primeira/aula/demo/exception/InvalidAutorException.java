package primeira.aula.demo.exception;

public class InvalidAutorException extends RuntimeException {
     public InvalidAutorException (String message){
        super(message);
     }

     public InvalidAutorException () {
        super("Autor procurado nao encontrado.");
    }
}