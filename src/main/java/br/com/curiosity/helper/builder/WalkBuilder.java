package br.com.curiosity.helper.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.curiosity.helper.walk.Walk;
import br.com.curiosity.utils.type.CompassEnum;

@Component
@Scope(value="singleton")
public class WalkBuilder {
	
	private CompassEnum compass;
		
	@Autowired
	private Walk walkNorth;
	@Autowired
	private Walk walkEast;
	@Autowired
	private Walk walkSouth;
	@Autowired
	private Walk walkWest;

	
	public WalkBuilder direction(CompassEnum compass) {
		this.compass = compass;
		return this;
	}

	public Walk Build() {
		if (CompassEnum.N.equals(compass)) {
			return walkNorth;
		} else if (CompassEnum.E.equals(compass)) {
			return walkEast;
		} else if (CompassEnum.S.equals(compass)) {
			return walkSouth;
		} else if (CompassEnum.W.equals(compass)) {
			return walkWest;
		}

        throw new IllegalArgumentException("Compass not map: " + compass);
	}

	
}
