package br.com.curiosity.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PlateauTest {

	@Test(expected=IllegalArgumentException.class)
	public void testNegativeValues() {
		new Plateau(-1, -1);
	}
	
	@Test
	public void testAreaValidate() {
		Plateau plateau = new Plateau(2, 2);
		Position position = new Position(0,0);
		assertFalse(plateau.itNotValidateArea(position));
		position = new Position(3,3);
		assertTrue(plateau.itNotValidateArea(position));
		position = new Position(-1,-1);
		assertTrue(plateau.itNotValidateArea(position));
		position = new Position(0,-1);
		assertTrue(plateau.itNotValidateArea(position));
		position = new Position(3,-1);
		assertTrue(plateau.itNotValidateArea(position));
		
	}
	
	@Test
	public void testConstrutorString() {
		Plateau plateau = new Plateau("2 2");
		assertNotNull(plateau);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstrutorStringtNegativeValues() {
		new Plateau("-2 2");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstrutorStringtWithLetters() {
		new Plateau("a 2");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstrutorStringtEmpty() {
		new Plateau("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstrutorStringtNull() {
		new Plateau(null);
	}

}
