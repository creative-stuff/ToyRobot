package com.toypacks;

import org.junit.runner.*;


public class TestRunner {

	   public static void main(String[] args) {
		     System.out.println("In The Test Runner");
		     
		     JUnitCore junitCore = new JUnitCore();
		     Result res = junitCore.run(ValidateComandsTest.class, FirstPlaceTest.class,ActionMovesTest.class, ActionLeftRotationTest.class, ActionRightRotationTest.class);
		     
		     System.out.println("Total test cases:" + res.getRunCount());
		     
		     System.out.println("Number of failures:" + res.getFailureCount());
		     
		     //ValidateComandsTest test = new ValidateComandsTest();
		     //test.testValidCommands();
	   }
}
 