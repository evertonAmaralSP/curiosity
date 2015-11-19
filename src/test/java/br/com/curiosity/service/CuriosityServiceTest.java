package br.com.curiosity.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.curiosity.config.BaseTest;


public class CuriosityServiceTest extends BaseTest {
	
	@Autowired
	CuriosityService curiosityService;
	
	@Test
	public void testStartCuriosity() {
		
		//curiosityService.startPlateau("5 5").startProbePoseidon("0 0 N").intructionPoseidon("MMMR").startProbeAtenas("5 5 S").intructionAtenas("MLMMR").execult();
		
	}

}
