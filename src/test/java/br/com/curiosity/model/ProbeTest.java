package br.com.curiosity.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.curiosity.utils.ActionProbeEnum;
import br.com.curiosity.utils.CompassEnum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class ProbeTest {

	@Autowired
	Probe probe;

	@Test
	public void createProbe() {
		String basePosition = String.format("%s %s %s", 0, 1, CompassEnum.N);

		Plateau plateau = new Plateau(5, 5);
		Position startProbe = new Position(0, 1);
		probe.config(plateau, startProbe, CompassEnum.N);

		assertThat(basePosition).contains(probe.status());
	}

	@Test
	public void probeMove() {
		String possicaoBase = String.format("%s %s %s", 1, 1, CompassEnum.N);

		Plateau plateau = new Plateau(5, 5);
		Position startProbe = new Position(0, 1);
		probe.config(plateau, startProbe, CompassEnum.N);
		List<ActionProbeEnum> actions = new ArrayList<>();
		actions.add(ActionProbeEnum.M);
		probe.instruction(actions);
		assertThat(possicaoBase).contains(probe.status());
	}

}
