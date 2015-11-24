package br.com.curiosity.helper.walk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.curiosity.model.Position;
import br.com.curiosity.utils.type.CompassEnum;

@Component
public class WalkEast implements Walk{

	@Autowired
	private Walk walkSouth;
	
	@Override
	public Walk processResponsability(CompassEnum compass){
		if (CompassEnum.E.equals(compass)) {
			return this;
		} else {
			return walkSouth.processResponsability(compass);
		}
	}
	
	@Override
	public Position process(Position position) {
		position.setPositionY(position.getPositionY()+1);
		return position;
	}

}
