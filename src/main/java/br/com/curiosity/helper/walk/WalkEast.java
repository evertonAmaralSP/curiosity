package br.com.curiosity.helper.walk;

import org.springframework.stereotype.Component;

import br.com.curiosity.model.Position;

@Component
public class WalkEast implements Walk{

	@Override
	public Position process(Position position) {
		position.setPositionY(position.getPositionY()+1);
		return position;
	}

}