package br.com.curiosity.model;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.curiosity.exception.PlateauValueOutsideException;
import br.com.curiosity.helper.builder.WalkBuilder;
import br.com.curiosity.helper.walk.Walk;
import br.com.curiosity.utils.type.ActionProbeEnum;
import br.com.curiosity.utils.type.CompassEnum;

/**
 * 
 * Probe: Vehicle of exploration, set an initial position is responsible for
 * performing navigation instructions and answer your location when prompted.
 * 
 * @author fera
 *
 */
@Component
public class Probe {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private Position position;

	private CompassEnum compass;

	private Plateau plateau;

	@Autowired
	private WalkBuilder walkBuilder;

	public void instruction(List<ActionProbeEnum> actions) throws PlateauValueOutsideException {
		log.debug("Start instruction");
		if (CollectionUtils.isEmpty(actions))
			return;
		log.debug(String.format("Size actions: %s", actions.size()));
		for (ActionProbeEnum action : actions) {
			log.debug(String.format("Position initial of the probe: %s",this.status()));
			this.process(action);
			log.debug(String.format("Position end of the probe: %s",this.status()));
		}

	}

	public String status() {
		String format = String.format("%s %s %s", this.position.getPositionX(), this.position.getPositionY(),
				this.compass);
		log.debug(String.format("Status: %s", format));
		return format;
	}

	public void config(Plateau plateau, Position startProbe, CompassEnum compass) throws PlateauValueOutsideException {
		validateProbeStartPosition(plateau, startProbe);

		this.plateau = plateau;
		this.position = startProbe;
		this.compass = compass;
	}

	public void process(ActionProbeEnum action) throws PlateauValueOutsideException {

		if (ActionProbeEnum.M.equals(action)) {
			this.position = this.move();
		} else if (ActionProbeEnum.L.equals(action)) {
			this.compass = compass.getLeft();
		} else if (ActionProbeEnum.R.equals(action)) {
			this.compass = compass.getRight();
		}

	}

	private Position move() throws PlateauValueOutsideException {
		Walk walk = walkBuilder.direction(compass).Build();

		Position positionFinal = walk.process(position);
		validatePositionNoExceedPlateau(positionFinal);
		return positionFinal;
	}

	private void validatePositionNoExceedPlateau(Position positionFinal) throws PlateauValueOutsideException {
		if (plateau.itNotValidateArea(positionFinal)) {
			String msgError = String.format("Walk in that direction %s exceeds the plateau in position %s,%s.", compass,
					position.getPositionX(), position.getPositionY());
			log.error(msgError);
			throw new PlateauValueOutsideException(msgError);
		}
	}

	private void validateProbeStartPosition(Plateau plateau, Position startProbe) throws PlateauValueOutsideException {
		if (plateau.itNotValidateArea(startProbe)) {
			String msgError = "The starting position exceeds the limits of the plateau.";
			log.error(msgError);
			throw new PlateauValueOutsideException(msgError);
		}
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
