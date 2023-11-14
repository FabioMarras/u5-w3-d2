package fabiomarras.u5w2d5.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(int id) {
        super("Mi dispiace, l'elemento con id: " + id + " non è stato trovato!");
    }
    public NotFoundException(String message) {
        super(message);
    }
}
