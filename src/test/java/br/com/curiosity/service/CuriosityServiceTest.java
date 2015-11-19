package br.com.curiosity.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.curiosity.config.ApplicationTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationTest.class)
public class CuriosityServiceTest {

	@Autowired
	CuriosityService curiosityService;

	@Test
	public void testStartCuriosity() {

		curiosityService.startPlateau("5 5").startProbePoseidon("0 0 N").intructionPoseidon("MMMR")
				.startProbeAtenas("5 5 S").intructionAtenas("MRMML");
		String statusPoseidon = curiosityService.statusPoseidon();
		String statusAtenas = curiosityService.statusAtenas();

		Assert.assertTrue(statusPoseidon.equals("3 0 E"));
		Assert.assertTrue(statusAtenas.equals("4 3 S"));

	}

}
