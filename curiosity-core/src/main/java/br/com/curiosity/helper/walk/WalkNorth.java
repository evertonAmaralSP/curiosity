package br.com.curiosity.helper.walk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.curiosity.model.Position;
import br.com.curiosity.utils.type.CompassEnum;

@Component
public class WalkNorth implements Walk {

	@Autowired
	private Walk walkEast;

	@Override
	public Walk processResponsability(CompassEnum compass){
		if (CompassEnum.N.equals(compass)) {
			return this;
		} else {
			return walkEast.processResponsability(compass);
		}
	}
	@Override
	public Position process(Position position) {
		position.setPositionX(position.getPositionX()+1);
		return position;
	}

}
