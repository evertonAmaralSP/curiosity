package br.com.curiosity.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Plateau: responsible for simulate a flexible size area and manage position of
 * objects within that area.
 * 
 * @author fera
 *
 */
public class Plateau {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	private Position position;

	/**
	 * Validate that the position is out of the plateau area.
	 * 
	 * @param position
	 * @return
	 */
	public boolean itNotValidateArea(Position position) {
		return position.getPositionX() < 0 || position.getPositionY() < 0
				|| position.getPositionX() > this.getPositionX() || position.getPositionY() > this.getPositionY();
	}

	/**
	 * Parametros iniciais de criacao de planalto.
	 * 
	 * @param positionX
	 * @param positionY
	 */
	public Plateau(int positionX, int positionY) {
		if (positionX < 0 || positionY < 0){
			String msgError = "It is not allowed negative values.";
			log.error(msgError);
			throw new IllegalArgumentException(msgError);
		}
		this.position = new Position(positionX, positionY);
	}

	public int getPositionX() {
		return position.getPositionX();
	}

	public int getPositionY() {
		return position.getPositionY();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(final Object o) {
		return EqualsBuilder.reflectionEquals(this, o);

	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
