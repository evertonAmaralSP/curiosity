package br.com.curiosity.helper.walk;

import org.springframework.stereotype.Component;

import br.com.curiosity.model.Position;
import br.com.curiosity.utils.type.CompassEnum;

@Component
public class WalkWest implements Walk {

	@Override
	public Walk processResponsability(CompassEnum compass){
		if (CompassEnum.W.equals(compass)) {
			return this;
		} else {
			return null;
		}
	}
	
	@Override
	public Position process(Position position) {
		position.setPositionY(position.getPositionY() - 1);
		return position;
	}

}
