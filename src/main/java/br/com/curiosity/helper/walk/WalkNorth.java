package br.com.curiosity.helper.walk;

import org.springframework.stereotype.Component;

import br.com.curiosity.model.Position;

@Component
public class WalkNorth implements Walk {

	@Override
	public Position process(Position position) {
		position.setPositionX(position.getPositionX()+1);
		return position;
	}

}
