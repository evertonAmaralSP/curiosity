package br.com.curiosity.exception.base;
/**
 * Base to exception Curiosity
 * 
 * @author fera
 *
 */
public class CuriosityException extends Exception {

	private static final long serialVersionUID = 4340094526930946721L;
	
	public CuriosityException() {}
	public CuriosityException(String message) {
		  super(message);
	}

}
