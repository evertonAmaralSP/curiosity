package br.com.curiosity.utils.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.curiosity.model.Position;
import br.com.curiosity.utils.CompassEnum;
import br.com.curiosity.utils.support.Walk;

@Component
public class WalkBuilder {
	
	private CompassEnum compass;
	private Position position;

	@Autowired
	private Walk walkNorth;
	@Autowired
	private Walk walkEast;
	@Autowired
	private Walk walkSouth;
	@Autowired
	private Walk walkWest;

	private WalkBuilder(){	}
	

	
	public static WalkBuilder getInstance() {
		return new WalkBuilder();
	}

	public WalkBuilder direction(CompassEnum compass) {
		this.compass = compass;
		return this;
	}
	public WalkBuilder position(Position position) {
		this.position = position;
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
