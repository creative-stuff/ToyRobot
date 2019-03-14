package com.toypacks;

import java.io.File;

import junit.framework.TestCase;

public class FirstPlaceTest extends TestCase {

	public void testFirstPlace() {
	File inputDir = new File("inputfiles");

	String inputTestFile = inputDir.getAbsolutePath() + File.separator + "testfile_2.txt";
	Robot testRobot = new Robot();
	
	testRobot.readAndValidateCommands(inputTestFile);
	testRobot.processActions();
	
	assertEquals(false, testRobot.isRobotOnTheTable());
	
	}
	
}
