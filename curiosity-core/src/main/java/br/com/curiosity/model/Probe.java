package br.com.curiosity.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
@Scope(value = "prototype")
public class Probe {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private Position position;

	private CompassEnum compass;

	@Autowired
	private WalkBuilder walkBuilder;

	private static final String REGEX_CONFIG = "^(-)?(\\d)+\\s(-)?(\\d)+\\s[NESW]$";

	private static final String REGEX_MOVE = "^[LRM]+$";

	private static final String REGEX_CARACTER = "(?!^)";

	public void config(String config) {

		notNull(config);
		notFormatConfig(config);

		String[] list = config.split("\\s");
		int positionX = Integer.parseInt(list[0]);
		int positionY = Integer.parseInt(list[1]);
		compass = CompassEnum.getEnumByDirection(list[2]);

		notNegativeNumber(positionX, positionY);
		this.position = new Position(positionX, positionY);

	}

	public void instruction(String instructions) {
		notNull(instructions);
		notFormatMove(instructions);
		String[] characters = instructions.split(REGEX_CARACTER);
		List<ActionProbeEnum> listActions = new ArrayList<>();
		for (String character : characters) {
			listActions.add(ActionProbeEnum.getEnumByAction(character));
		}
		instruction(listActions);
	}

	public void instruction(List<ActionProbeEnum> actions) {
		log.debug("Start instruction");
		if (CollectionUtils.isEmpty(actions))
			return;
		log.debug(String.format("Size actions: %s", actions.size()));
		for (ActionProbeEnum action : actions) {
			log.debug(String.format("Position initial of the probe: %s", this.status()));
			this.process(action);
			log.debug(String.format("Position end of the probe: %s", this.status()));
		}

	}

	public String status() {
		String format = String.format("%s %s %s", this.position.getPositionX(), this.position.getPositionY(),
				this.compass);
		log.debug(String.format("Status: %s", format));
		return format;
	}

	public void process(ActionProbeEnum action) {

		if (ActionProbeEnum.M.equals(action)) {
			this.position = this.move();
		} else if (ActionProbeEnum.L.equals(action)) {
			this.compass = compass.getLeft();
		} else if (ActionProbeEnum.R.equals(action)) {
			this.compass = compass.getRight();
		}

	}


	public Position getPosition() {
		return position;
	}

	private Position move() {
		Walk walk = walkBuilder.direction(compass).build();
		Position positionFinal = walk.process(position);
		return positionFinal;
	}

	private void notFormatConfig(String config) {
		if (!Pattern.matches(REGEX_CONFIG, config)) {
			throw new IllegalArgumentException(String.format(
					"The value of %s is non-standard. the expected format is \"n° n° [NWSE]\" ex .: \"3 3 N\" or \"0 1 W\"",
					config));
		}
	}

	private void notFormatMove(String instruction) {
		if (!Pattern.matches(REGEX_MOVE, instruction)) {
			throw new IllegalArgumentException(String.format(
					"The value of %s is non-standard. the expected format is \"[MLR]\" ex .: \"M\" or \"MMRMMRMRRM\"",
					instruction));
		}
	}

	private void notNull(String config) {
		if (StringUtils.isEmpty(config)) {
			throw new IllegalArgumentException("Value can not be null");
		}
	}

	private void notNegativeNumber(int positionX, int positionY) {
		if (positionX < 0 || positionY < 0) {
			String msgError = "It is not allowed negative values.";
			log.error(msgError);
			throw new IllegalArgumentException(msgError);
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

	public void setWalkBuilder(WalkBuilder walkBuilder) {
		this.walkBuilder = walkBuilder;
	}
}
