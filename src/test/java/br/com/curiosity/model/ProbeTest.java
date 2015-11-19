package br.com.curiosity.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.curiosity.config.ApplicationTest;
import br.com.curiosity.exception.PlateauValueOutsideException;
import br.com.curiosity.utils.type.ActionProbeEnum;
import br.com.curiosity.utils.type.CompassEnum;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationTest.class)
public class ProbeTest {

	@Autowired
	Probe probe;

	@Before
	public void startPositionProbe() throws PlateauValueOutsideException {
		probe.config("0 1 N");
	}
	
	@Test
	public void createProbe() {
		String statusBase = statusConfig(0, 1, CompassEnum.N);

		assertThat(statusBase).contains(probe.status());
	}
	
	@Test
	public void probeSimpleMoveString() throws PlateauValueOutsideException {
		String statusBase = statusConfig(1, 1, CompassEnum.N);
		probe.instruction("M");
		assertThat(statusBase).contains(probe.status());
	}

	@Test
	public void probeSimpleMove() throws PlateauValueOutsideException {
		String statusBase = statusConfig(1, 1, CompassEnum.N);

		List<ActionProbeEnum> actions = new ArrayList<>();
		actions.add(ActionProbeEnum.M);
		probe.instruction(actions);
		assertThat(statusBase).contains(probe.status());
	}

	@Test
	public void probeComplexMoveString() throws PlateauValueOutsideException {
		String statusBase = statusConfig(1, 0, CompassEnum.W);
		probe.instruction("MLM");
		assertThat(statusBase).contains(probe.status());
	}
	
	@Test
	public void probeComplexMove() throws PlateauValueOutsideException {
		String statusBase = statusConfig(1, 0, CompassEnum.W);

		List<ActionProbeEnum> actions = new ArrayList<>();
		actions.add(ActionProbeEnum.M);
		actions.add(ActionProbeEnum.L);
		actions.add(ActionProbeEnum.M);
		probe.instruction(actions);
		assertThat(statusBase).contains(probe.status());
	}

	private String statusConfig(int x, int y, CompassEnum compass) {
		String statusFormat = String.format("%s %s %s", x, y, compass);
		return statusFormat;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConfigNegativeValues() {
		probe.config("0 -1 N");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConfigWithLetters() {
		probe.config("a 1 N");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConfigEmpty() {
		probe.config("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConfigNull() {
		probe.config(null);
	}

}
