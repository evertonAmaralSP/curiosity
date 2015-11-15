package br.com.curiosity.utils.support;

import br.com.curiosity.model.Position;

public class WalkNorth implements Walk {

	@Override
	public Position process(Position position) {
		position.setPositionX(position.getPositionX()+1);
		return position;
	}

}
