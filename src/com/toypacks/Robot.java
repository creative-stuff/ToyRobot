package com.toypacks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Robot {

	//private String[] commands  = {"PLACE", "MOVE", "LEFT", "RIGHT", "REPORT"};
	
	private static ArrayList<Pattern> validcommands = new ArrayList<Pattern> ();
	private static final int X_HORIZON = 5;
	private static final int Y_HORIZON = 5;
	
	private ArrayList<Action> inputCommands = new ArrayList<Action> ();
	private int x_position = -1;
	private int y_position = -1;
	private String face = null;
	
	static {
		validcommands.add(Pattern.compile("PLACE(\\s)[0-5](\\s)[0-5](\\s)(EAST|WEST|NORTH|SOUTH)"));
		validcommands.add(Pattern.compile("MOVE"));
		validcommands.add(Pattern.compile("LEFT"));
		validcommands.add(Pattern.compile("RIGHT"));
		validcommands.add(Pattern.compile("REPORT"));
		
	}
	public static void main (String [] args) {
		
	    Robot myRob = new Robot();	
	    String inputFile = null;
	    
	    File inputDir = new File(args[0]);
	    
	    if(inputDir.exists() && inputDir.isDirectory())
	    {
	    	inputFile = args[0]+ File.separator + args[1];
	    }else {
	    	System.out.println("Invalid Input file");
	    	System.exit(1);
	    }
	   
	    myRob.readAndValidateCommands(inputFile);
	    
	    
		myRob.processActions();
	}
	
	public int getValidCommands() {
		return inputCommands.size();
	}
	
	public boolean isRobotOnTheTable() {
		if(this.x_position == -1 && this.y_position == -1) {
			return false;
		}else {
			return true;
		}
	}
	
	public int getXPosition()
	{
		return x_position;
	}
	
	public int getYPosition() {
		return y_position;
	}
	
	public String getDirection() {
		return face;
	}
	private void addAction(String inputComm) {
		Action act = new Action(inputComm);
		inputCommands.add(act);
	}
	
    void readAndValidateCommands(String inputFile) {
		BufferedReader reader = null;
		
		System.out.println("Reading commands from:" + inputFile);
		try {
			
			FileInputStream inStr = new FileInputStream(inputFile);
		    reader = new BufferedReader(new InputStreamReader(inStr));
			
			String s = null;
			int i = 1;
			
			while((s = reader.readLine()) != null) {
				System.out.println("line " + i + "::" + s); 
				i++;
				
				boolean lineMatched = false;
				for(Pattern p: validcommands) {
					
					Matcher m = p.matcher(s);
					if(m != null && m.matches() == true) {
						lineMatched = true;
						System.out.println("Command is a valid one:" + s);
						addAction(s);
						break;
					}
					
				}
				
				if(lineMatched == false) {
					System.out.println("Not a valid command, hence ignoring:" + s);
				}
				
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error in reading input file");
			e.printStackTrace();
				
		} catch(IOException e) {
			
		} 
	   
	}
	
    void processActions() {
		
		if(inputCommands.size() <=0) {
			System.out.println("No valid commands to process");
			return;
		}
		for(Action act:inputCommands) {
			if((x_position == -1 || y_position == -1 || face == null) && !act.getCommand().startsWith("PLACE")) {
				continue;
			}
			switch(act.getCommand()) {
			    
				case "MOVE":
					System.out.println("Processing move for the Robot");
					if(face.equalsIgnoreCase("EAST")) {
						if(x_position != X_HORIZON)
							x_position++;
					}else if(face.equalsIgnoreCase("WEST")) {
						if(x_position != 0) {
							x_position--;
						}
					}else if(face.equalsIgnoreCase("NORTH")) {
						if(y_position != Y_HORIZON) {
							y_position++;
						}
					}else if(face.equalsIgnoreCase("SOUTH")) {
						if(y_position != 0) {
							y_position--;
						}
					}
					break;
				case "LEFT":
					System.out.println("Processing left command for the Robot");
					if(face.equalsIgnoreCase("EAST")) {
						face = "NORTH";
					}else if(face.equalsIgnoreCase("WEST")) {
						face = "SOUTH";
					}else if (face.equalsIgnoreCase("NORTH")) {
						face = "WEST";
					}else if(face.equalsIgnoreCase("SOUTH")) {
						face = "EAST";
					}
					break;
				case "RIGHT":
					System.out.println("Processing right command for the Robot");
					if(face.equalsIgnoreCase("EAST")) {
						face = "SOUTH";
					}else if(face.equalsIgnoreCase("WEST")) {
						face = "NORTH";
					}else if (face.equalsIgnoreCase("NORTH")) {
						face = "EAST";
					}else if(face.equalsIgnoreCase("SOUTH")) {
						face = "WEST";
					}
					break;
				case "REPORT":
					System.out.println("Processing report for the Robot");
					System.out.println(x_position + " " + y_position + " " + face);
					break;
				default:
					if(act.getCommand().startsWith("PLACE")) {
						System.out.println("Processing place for the Robot");
						String [] placeArgs = act.getCommand().split("(\\s)+");
						x_position = Integer.parseInt(placeArgs[1]);
						y_position = Integer.parseInt(placeArgs[2]);
						face = new String(placeArgs[3]);
						
						
					}
			}
		}
	}
	
	private static class Action{
		String s = null;
		public Action(String command) {
			s = command;
		}
		public String getCommand() {
			return s;
		}
	}
	
}
