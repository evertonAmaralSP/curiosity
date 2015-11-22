package br.com.curiosity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.curiosity.model.ProbesResult;
import br.com.curiosity.service.CuriosityService;
import br.com.curiosity.utils.type.IdentifyProbeEnum;

@RestController
public class CuriosityController {

	@Autowired
	private CuriosityService curiosityService;

	@RequestMapping("/explorer")
	public ProbesResult explorer(
			@RequestParam(value = "plateaus") String sizePlateaus,
			@RequestParam(value = "probe1") String positionProbe1,
			@RequestParam(value = "intruction1") String intructionProbe1,
			@RequestParam(value = "probe2") String positionProbe2,
			@RequestParam(value = "intruction2") String intructionProbe2) {
		curiosityService.startPlateau(sizePlateaus)
				.startConfigProbe(IdentifyProbeEnum.POSEIDON, positionProbe1)
				.startConfigProbe(IdentifyProbeEnum.ATENAS, positionProbe2)
				.instructionProbe(IdentifyProbeEnum.POSEIDON, intructionProbe1)
				.instructionProbe(IdentifyProbeEnum.ATENAS, intructionProbe2)
				.run();

		String statusPoseidon = curiosityService.status(IdentifyProbeEnum.POSEIDON);
		String statusAtenas = curiosityService.status(IdentifyProbeEnum.ATENAS);
		ProbesResult probesResult = new ProbesResult(statusPoseidon, statusAtenas);
		return probesResult;
	}
}