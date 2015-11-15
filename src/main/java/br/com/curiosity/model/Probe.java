package br.com.curiosity.model;

import java.beans.ConstructorProperties;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.curiosity.utils.ActionProbeEnum;
import br.com.curiosity.utils.CompassEnum;

@Component
public class Probe {
	
	private Position position;
	
	private CompassEnum compass;
	
	@Autowired
	private Engine engine;
	
	public void instruction(List<ActionProbeEnum> actions){
		if(CollectionUtils.isNotEmpty(actions)) {
			for (ActionProbeEnum action : actions) {
				this.position = engine.process(action,position,compass);
			}
		}
	}
	
	public String status(){
		return String.format("%s %s %s", this.position.getPositionX(),this.position.getPositionY(), this.compass);
	}
	
	public void config(Plateau plateau, Position startProbe,CompassEnum compass){
		this.position = startProbe;
		this.compass = compass;
		engine.setPlateau(plateau);
	}
	
}
