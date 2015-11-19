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
	public void testStartCuriosityGeneric() {

		curiosityService.startPlateau("5 5").startConfigProbe("Poseidon","0 0 N").instructionProbe("Poseidon","MMMR")
				.startConfigProbe("Atenas","5 5 S").instructionProbe("Atenas","MRMML");
		String statusPoseidon = curiosityService.status("Poseidon");
		String statusAtenas = curiosityService.status("Atenas");

		Assert.assertTrue(statusPoseidon.equals("3 0 E"));
		Assert.assertTrue(statusAtenas.equals("4 3 S"));

	}

}
