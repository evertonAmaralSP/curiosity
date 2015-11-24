package br.com.curiosity.helper.walk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.curiosity.model.Position;
import br.com.curiosity.utils.type.CompassEnum;

@Component
public class WalkCompass implements Walk {

	@Autowired
	private Walk walkNorth;

	@Override
	public Walk processResponsability(CompassEnum compass){
		return walkNorth.processResponsability(compass);
	}
	@Override
	public Position process(Position position) {
		position.setPositionX(position.getPositionX()+1);
		return position;
	}

}
