package br.com.curiosity.utils.support;

import br.com.curiosity.model.Position;

public class WalkEast implements Walk{

	@Override
	public Position process(Position position) {
		position.setPositionY(position.getPositionY()+1);
		return position;
	}

}
