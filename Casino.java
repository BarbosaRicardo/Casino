/***********************************************************
 * 
 * @author Ricardo Barbosa
 * @version 10/30/2019
 * 
 ***********************************************************/

import java.util.*;
import java.lang.Math;

public class Casino
{
   
	private static Scanner keyboard = new Scanner(System.in);
	
	/*
	 * @input valid bet
	 * @return bet amount as functional return 
	 */
	public static int getBet()
	{
		int userBet = -1;
		
		//Prompts until the bet is valid
		while (userBet < 0 || userBet >100)
		{
			System.out.println("How much would you like to bet? (1 - 100) " +
										"or quit: ");
			userBet = keyboard.nextInt();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
//Constructor for ThreeString class
class ThreeString
{
	//Maximum length of any string 
	public static final int MAX_LEN = 20;
	public static final int Max_Pulls = 40;
	
	//Array of outcome of pull winnings
	private static int[] pullWinnings = new int[Max_Pulls];
	
	//Outcomes of pull
	private String string1;
	private String string2;
	private String string3;
	
	ThreeString()
	{
		string1 = "";
		string2 = "";
		string3 = "";
	}
	
	//Accessors to string values 
	
	public String getString1()
	{
		return string1;
	}
	public String getString2()
	{
		return string2;
	}
	public String getString3()
	{
		return string3;
	}
	
	//Mutators of string values
	
	public boolean setString1(String value)
	{
		//Validates the string 
		if(validString(value))
		{
			string1 = value;
			return true;
		}
		return false;
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		

	}

}
