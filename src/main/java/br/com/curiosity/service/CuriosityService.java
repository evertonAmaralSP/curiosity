package br.com.curiosity.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.curiosity.model.Plateau;
import br.com.curiosity.model.Probe;

/**
 * Curiosity Server is the engine that responsible for simulating the
 * interactions of Probe with Plateaus.
 * 
 * For simplicity we will work with two probes only, but in next release evolve
 * into a list of probes enabling work with one or more probes
 * 
 * @author fera
 *
 */
@Service
public class CuriosityService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private Plateau plateau;

	@Autowired
	private Map<String,Probe> probes;
	
	public CuriosityService startPlateau(String position) {
		
		this.plateau = new Plateau(position);
		return this;
	}

	public Object startProbe(String config, String name) {
		probes.put(name, null);
		return null;
	}
}
