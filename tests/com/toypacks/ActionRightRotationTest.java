package com.toypacks;

import java.io.File;

import junit.framework.TestCase;

public class ActionRightRotationTest extends TestCase {
	
	public void testRightRotation() {
		File inputDir = new File("inputfiles");

		String inputTestFile = inputDir.getAbsolutePath() + File.separator + "testfile_5.txt";
		Robot testRobot = new Robot();
		
		testRobot.readAndValidateCommands(inputTestFile);
		testRobot.processActions();
		
		int x_pos = testRobot.getXPosition();
		int y_pos = testRobot.getYPosition();
		
		assertEquals(true, (x_pos == 1 && y_pos == 5));
		assertEquals("WEST", testRobot.getDirection());
	}
}
