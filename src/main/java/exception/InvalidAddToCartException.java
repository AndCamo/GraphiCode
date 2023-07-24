package exception;

public class InvalidAddToCartException extends IllegalStateException{
    public InvalidAddToCartException(){
        super("Aggiunta al carrello non valida");
    }

    public InvalidAddToCartException(String msg){
        super(msg);
    }
}
