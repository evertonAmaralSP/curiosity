package br.com.curiosity.service;

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
	private Probe poseidon;

	@Autowired
	private Probe atenas;

	public CuriosityService startPlateau(String position) {
		this.plateau = new Plateau(position);
		return this;
	}

	public CuriosityService startProbePoseidon(String config) {
		poseidon.config(config);
		return this;
	}

	public CuriosityService startProbeAtenas(String config) {
		atenas.config(config);
		return this;
	}

	public CuriosityService intructionPoseidon(String instructions) {
		poseidon.instruction(instructions);
		return this;
	}

	public CuriosityService intructionAtenas(String instructions) {
		atenas.instruction(instructions);
		return this;
	}

	public String statusPoseidon() {
		return poseidon.status();
	}

	public String statusAtenas() {
		return atenas.status();
	}
}
