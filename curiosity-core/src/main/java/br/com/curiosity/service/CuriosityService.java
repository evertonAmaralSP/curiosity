package br.com.curiosity.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.curiosity.exception.PlateauValueOutsideException;
import br.com.curiosity.exception.base.CuriosityRuntimeException;
import br.com.curiosity.helper.factory.ProbeFactory;
import br.com.curiosity.model.Plateau;
import br.com.curiosity.model.Probe;
import br.com.curiosity.utils.type.IdentifyProbeEnum;

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

	private Map<String, String> instructions;

	@Autowired
	private ProbeFactory probeFactory;

	public CuriosityService() {
		this.probes = new HashMap<>();
		this.instructions = new HashMap<>();
	}

	public CuriosityService startConfigProbe(IdentifyProbeEnum id, String config) {
		Probe probe = probeFactory.probe();
		probe.config(config);
		validateSuperPosition(probe, id.getName());
		this.probes.put(id.getName(), probe);
		return this;
	}

	public CuriosityService instructionProbe(IdentifyProbeEnum id,
			String instruction) {
		this.instructions.put(id.getName(), instruction);
		return this;
	}

	public String status(IdentifyProbeEnum id) {
		return this.probes.get(id.getName()).status();
	}

	public CuriosityService startPlateau(String position) {
		this.plateau = new Plateau(position);
		return this;
	}

	public void run() {
		validatePlateauNotValide();
		validateProbesNotValide();
		validateIntructionsNotNull();
		validateProbeStartPosition();

		for (IdentifyProbeEnum id : IdentifyProbeEnum.values()) {
			String name = id.getName();
			Probe probe = probes.get(name);
			String instruction = instructions.get(name);
			probe.instruction(instruction);
			validatePositionNoExceedPlateau(name, probe);

		}

	}

	private void validatePlateauNotValide() {
		if (null == plateau)
			throw new CuriosityRuntimeException(
					"The method startPlateau must be called before the run.");
	}

	private void validateProbesNotValide() {
		if (MapUtils.isEmpty(probes) && probes.size() < 2)
			throw new CuriosityRuntimeException(
					"Two probes are necessary to start operation.");
	}

	private void validateIntructionsNotNull() {
		if (MapUtils.isEmpty(instructions) && instructions.size() < 2)
			throw new CuriosityRuntimeException(
					"Two instructions are necessary to start operation.");
	}

	private void validateProbeStartPosition()
			throws PlateauValueOutsideException {
		for (Map.Entry<String, Probe> probe : probes.entrySet()) {
			if (plateau.itNotValidateArea(probe.getValue().getPosition())) {
				String msgError = String
						.format("The starting position %s: %s exceeds the limits of the plateau: %s.",
								probe.getKey(), probe.getValue().status(),
								plateau.status());
				log.error(msgError);
				throw new PlateauValueOutsideException(msgError);
			}
		}

	}

	private void validatePositionNoExceedPlateau(String id, Probe probe)
			throws PlateauValueOutsideException {
		if (plateau.itNotValidateArea(probe.getPosition())) {
			String msgError = String.format(
					"Probe %s: %s exceeds the plateau in position", id,
					probe.status());
			log.error(msgError);
			throw new PlateauValueOutsideException(msgError);
		}
	}

	private void validateSuperPosition(Probe probe, String name) {
		if (probes.size() > 0) {
			for (Map.Entry<String, Probe> p : probes.entrySet()) {
				if (!p.getKey().equals(name)
						&& p.getValue().getPosition()
								.equals(probe.getPosition())) {
					throw new CuriosityRuntimeException(
							"Starting position can not be the same as the other probe.");
				}
			}
		}
	}

}
