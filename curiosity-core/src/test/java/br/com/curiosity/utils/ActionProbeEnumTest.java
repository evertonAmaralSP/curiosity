package br.com.curiosity.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;

import br.com.curiosity.utils.type.ActionProbeEnum;

public class ActionProbeEnumTest {

	@Test
	public void testSetDirection(){
		assertThat(ActionProbeEnum.M).isEqualTo(ActionProbeEnum.getEnumByAction("M"));
		assertThat(ActionProbeEnum.L).isEqualTo(ActionProbeEnum.getEnumByAction("L"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetDirectionNotValide(){
		ActionProbeEnum.getEnumByAction("B");
	}

}
