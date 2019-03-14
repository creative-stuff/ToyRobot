package com.toypacks;

import java.io.File;

import junit.framework.TestCase;

public class ActionLeftRotationTest extends TestCase {

	public void testLeftRotation() {
		File inputDir = new File("inputfiles");

		String inputTestFile = inputDir.getAbsolutePath() + File.separator + "testfile_4.txt";
		Robot testRobot = new Robot();
		
		testRobot.readAndValidateCommands(inputTestFile);
		testRobot.processActions();
		
		int x_pos = testRobot.getXPosition();
		int y_pos = testRobot.getYPosition();
		
		assertEquals(true, (x_pos == 5 && y_pos == 1));
		assertEquals("SOUTH", testRobot.getDirection());
	}
}
