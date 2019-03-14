package com.toypacks;

import java.io.File;

import junit.framework.TestCase;

public class ValidateComandsTest extends TestCase {

	public void testValidCommands() {
		File inputDir = new File("inputfiles");

		String inputTestFile = inputDir.getAbsolutePath() + File.separator + "testfile_1.txt";
		Robot testRobot = new Robot();
		
		testRobot.readAndValidateCommands(inputTestFile);
		int validCommands = testRobot.getValidCommands();
		
		assertEquals(5, validCommands);
		
	}
	
}
