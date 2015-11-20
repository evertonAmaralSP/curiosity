package br.com.curiosity.model;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
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
	
	private static final String REGEX_POSITION = "^(-)?(\\d)+\\s(-)?(\\d)+$";

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
		notNegativeNumber(positionX, positionY);
		this.position = new Position(positionX, positionY);
	}
	
	public Plateau(String position) {
		notNull(position);
		notFormat(position);
		
		String[] list = position.split("\\s");
		int positionX = Integer.parseInt(list[0]);
		int positionY = Integer.parseInt(list[1]);
		
		notNegativeNumber(positionX, positionY);
		this.position = new Position(positionX, positionY);
	}
	
	public String status() {
		String format = String.format("%s %s", this.position.getPositionX(), this.position.getPositionY());
		log.debug(String.format("Status: %s", format));
		return format;
	}

	private void notFormat(String position) {
		if(!Pattern.matches(REGEX_POSITION, position)){
			throw new IllegalArgumentException(String.format("The value of %s is non-standard. the expected format is \"n° n°\" ex .: \"3 3\" or \"30 3\"", position));
		}
	}

	private void notNull(String position) {
		if(StringUtils.isEmpty(position)){
			throw new IllegalArgumentException("Value can not be null");
		}
	}
	private void notNegativeNumber(int positionX, int positionY) {
		if (positionX < 0 || positionY < 0){
			String msgError = "It is not allowed negative values.";
			log.error(msgError);
			throw new IllegalArgumentException(msgError);
		}
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
