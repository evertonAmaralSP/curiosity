package br.com.curiosity.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.curiosity.config.ApplicationTest;
import br.com.curiosity.exception.PlateauValueOutsideException;
import br.com.curiosity.exception.base.CuriosityRuntimeException;
import br.com.curiosity.utils.type.IdentifyProbeEnum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationTest.class)
public class CuriosityServiceTest {

	@Autowired
	CuriosityService curiosityService;

	@Test
	public void testStartCuriosity() {

		curiosityService.startPlateau("5 5")
				.startConfigProbe(IdentifyProbeEnum.POSEIDON, "0 0 N")
				.instructionProbe(IdentifyProbeEnum.POSEIDON, "MMMR")
				.startConfigProbe(IdentifyProbeEnum.ATENAS, "5 5 S")
				.instructionProbe(IdentifyProbeEnum.ATENAS, "MRMML")
				.run();
		String statusPoseidon = curiosityService
				.status(IdentifyProbeEnum.POSEIDON);
		String statusAtenas = curiosityService.status(IdentifyProbeEnum.ATENAS);

		Assert.assertTrue(statusPoseidon.equals("3 0 E"));
		Assert.assertTrue(statusAtenas.equals("4 3 S"));

	}

	@Test(expected = PlateauValueOutsideException.class)
	public void testStartCuriosityStartingPositionExceedPlateau() {
		curiosityService.startPlateau("5 5")
				.startConfigProbe(IdentifyProbeEnum.POSEIDON, "6 0 N")
				.instructionProbe(IdentifyProbeEnum.POSEIDON, "MMMR")
				.startConfigProbe(IdentifyProbeEnum.ATENAS, "5 5 S")
				.instructionProbe(IdentifyProbeEnum.ATENAS, "MRMML").run();
	}

	@Test(expected = PlateauValueOutsideException.class)
	public void testStartCuriosityPositionProbeException() {
		curiosityService.startPlateau("5 5")
				.startConfigProbe(IdentifyProbeEnum.POSEIDON, "0 0 N")
				.instructionProbe(IdentifyProbeEnum.POSEIDON, "MMMMMM")
				.startConfigProbe(IdentifyProbeEnum.ATENAS, "5 5 S")
				.instructionProbe(IdentifyProbeEnum.ATENAS, "MRMML").run();
	}

	@Test(expected = CuriosityRuntimeException.class)
	public void testStartCuriosityWithOneProbeException() {
		curiosityService.startPlateau("5 5")
				.startConfigProbe(IdentifyProbeEnum.POSEIDON, "0 0 N")
				.instructionProbe(IdentifyProbeEnum.POSEIDON, "MMMMMM").run();
	}

	@Test(expected = CuriosityRuntimeException.class)
	public void testStartCuriosityWithOneIntructionException() {
		curiosityService.startPlateau("5 5")
				.startConfigProbe(IdentifyProbeEnum.POSEIDON, "0 0 N")
				.startConfigProbe(IdentifyProbeEnum.ATENAS, "5 5 S")
				.instructionProbe(IdentifyProbeEnum.POSEIDON, "MMMMMM").run();
	}
	
	@Test(expected = CuriosityRuntimeException.class)
	public void testStartCuriosityNoPlateauException() {
		curiosityService
				.startConfigProbe(IdentifyProbeEnum.POSEIDON, "0 0 N")
				.startConfigProbe(IdentifyProbeEnum.ATENAS, "5 5 S")
				.instructionProbe(IdentifyProbeEnum.POSEIDON, "MMMMMM").run();
	}
	
	@Test(expected = CuriosityRuntimeException.class)
	public void testStartCuriositySuperpositionException() {
		curiosityService
				.startConfigProbe(IdentifyProbeEnum.POSEIDON, "5 5 N")
				.startConfigProbe(IdentifyProbeEnum.ATENAS, "5 5 S").run();
	}

}
