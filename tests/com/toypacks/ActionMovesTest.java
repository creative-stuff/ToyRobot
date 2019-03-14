package com.toypacks;

import java.io.File;

import junit.framework.TestCase;

public class ActionMovesTest extends TestCase {

	public void testValidMoves() {
		File inputDir = new File("inputfiles");

		String inputTestFile = inputDir.getAbsolutePath() + File.separator + "testfile_3.txt";
		Robot testRobot = new Robot();
		
		testRobot.readAndValidateCommands(inputTestFile);
		testRobot.processActions();
		
		int x_pos = testRobot.getXPosition();
		int y_pos = testRobot.getYPosition();
		
		assertEquals(true, (x_pos == 5 && y_pos == 1));
	}
}
