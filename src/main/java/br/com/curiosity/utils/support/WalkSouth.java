package br.com.curiosity.utils.support;

import org.springframework.stereotype.Component;

import br.com.curiosity.model.Position;

@Component
public class WalkSouth implements Walk {

	@Override
	public Position process(Position position) {
		position.setPositionX(position.getPositionX() - 1);
		return position;
	}

}
