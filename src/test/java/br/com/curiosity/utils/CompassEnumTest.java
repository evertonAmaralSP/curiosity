package br.com.curiosity.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;

public class CompassEnumTest {

	@Test
	public void testNavegation(){
		CompassEnum compass = CompassEnum.N;
		compass = compass.getLeft();
		assertThat(CompassEnum.W).isEqualTo(compass);
		compass = compass.getRight();
		assertThat(CompassEnum.N).isEqualTo(compass);
	}
	
	@Test
	public void testSetDirection(){
		assertThat(CompassEnum.N).isEqualTo(CompassEnum.getEnumByDirection("N"));
		assertThat(CompassEnum.W).isEqualTo(CompassEnum.getEnumByDirection("W"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetDirectionNotValide(){
		CompassEnum.getEnumByDirection("NW");
	}

}
