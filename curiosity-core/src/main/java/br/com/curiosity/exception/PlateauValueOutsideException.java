package br.com.curiosity.exception;

import br.com.curiosity.exception.base.CuriosityRuntimeException;
/**
 * 
 * Exception to values outside of the plateau
 *
 * @author fera
 *
 */
public class PlateauValueOutsideException extends CuriosityRuntimeException {

	private static final long serialVersionUID = -1030407088355341231L;
	public PlateauValueOutsideException() {}
	public PlateauValueOutsideException(String message) {
		  super(message);
	}
}
