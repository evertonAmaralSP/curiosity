package br.com.curiosity.exception.base;
/**
 * Base to exception Curiosity
 * 
 * @author fera
 *
 */
public class CuriosityRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 4340094526930946721L;
	
	public CuriosityRuntimeException() {}
	public CuriosityRuntimeException(String message) {
		  super(message);
	}

}
