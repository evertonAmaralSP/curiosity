package br.com.curiosity.helper.walk;

import br.com.curiosity.model.Position;
import br.com.curiosity.utils.type.CompassEnum;

public interface Walk {

	public Position process(Position position);

	public Walk processResponsabilit(CompassEnum compass);
}
