package exceptions;

public class ClienteException extends Exception {

	private static final long serialVersionUID = -2163896499541091156L;

	public ClienteException(String mensaje){
		super(mensaje);
	}
}
