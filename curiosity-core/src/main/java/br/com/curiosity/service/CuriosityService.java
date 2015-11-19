package br.com.curiosity.service;

import java.util.HashMap;
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
	
	private Map<String, Probe> probes;

	@Autowired
	private ProbeFactory probeFactory;
	
	public CuriosityService() {
		probes = new HashMap<>();
	}
	
	public CuriosityService startConfigProbe(String name, String config) {
		Probe probe = probeFactory.probe();
		probe.config(config);
		this.probes.put(name, probe); 
		return this;
	}
	public CuriosityService instructionProbe(String name, String instruction) {
		this.probes.get(name).instruction(instruction); 
		return this;
	}
	public String status(String name) {
		return this.probes.get(name).status();
	}
	
	public CuriosityService startPlateau(String position) {
		this.plateau = new Plateau(position);
		return this;
	}

}
