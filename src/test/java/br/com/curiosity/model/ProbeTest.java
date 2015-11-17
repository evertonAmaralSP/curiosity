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
		Plateau plateau = new Plateau(5, 5);
		Position startProbe = new Position(0, 1);
		probe.config(plateau, startProbe, CompassEnum.N);
	}
	
	@Test(expected = PlateauValueOutsideException.class)
	public void startingPositionExceedPlateau() throws PlateauValueOutsideException {
		Plateau plateau = new Plateau(3, 3);
		Position startProbe = new Position(0, 4);
		probe.config(plateau, startProbe, CompassEnum.N);
	}

	@Test
	public void createProbe() {
		String statusBase = statusConfig(0, 1, CompassEnum.N);

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
	public void probeComplexMove() throws PlateauValueOutsideException {
		String statusBase = statusConfig(1, 0, CompassEnum.W);

		List<ActionProbeEnum> actions = new ArrayList<>();
		actions.add(ActionProbeEnum.M);
		actions.add(ActionProbeEnum.L);
		actions.add(ActionProbeEnum.M);
		probe.instruction(actions);
		assertThat(statusBase).contains(probe.status());
	}

	@Test(expected = PlateauValueOutsideException.class)
	public void probeMoveException() throws PlateauValueOutsideException {
		List<ActionProbeEnum> actions = new ArrayList<>();
		actions.add(ActionProbeEnum.M);
		actions.add(ActionProbeEnum.L);
		actions.add(ActionProbeEnum.M);
		actions.add(ActionProbeEnum.M);
		probe.instruction(actions);
	}

	private String statusConfig(int x, int y, CompassEnum compass) {
		String statusFormat = String.format("%s %s %s", x, y, compass);
		return statusFormat;
	}

}
