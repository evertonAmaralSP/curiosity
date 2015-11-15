package br.com.curiosity.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.curiosity.utils.ActionProbeEnum;
import br.com.curiosity.utils.CompassEnum;
import br.com.curiosity.utils.builder.WalkBuilder;
import br.com.curiosity.utils.support.Walk;

@Component
public class Engine {

	private Plateau plateau;
	
	@Autowired
	private WalkBuilder walkBuilder;

	public Position process(ActionProbeEnum action, Position position,CompassEnum compass) {
		
		if (ActionProbeEnum.M.equals(action)) {
			this.move(position,compass);
		} else if (ActionProbeEnum.L.equals(action)) {
			compass = compass.getLeft();
		} else if (ActionProbeEnum.R.equals(action)) {
			compass = compass.getRight();
		}

		return position;
	}

	private Position move(Position position, CompassEnum compass) {
		Walk walk = walkBuilder.position(position).direction(compass).Build();
		Position positionFinal = walk.process(position);
		
		return positionFinal;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

}
