import java.util.*;

// Rebecca Brown
// 08/31/15
// Make a calculator that works with parens.

public class Calculator
{
	// Index to go through the string
	private static int index = 0;
	
	// Main for testing
	public static void main(String[] args)
	{
		// Get expression as a string and pass it to the calculate method
		String expression = new String();
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter an expression: ");
		expression = input.next();
		input.close();
		
		double result = calculate(expression);
		// Print result
		System.out.println("Result = " + result);
	}
	
	// Go through the full string and output the answer
	public static double calculate(String given)
	{
		// Result
		double toReturn = 0;
		// Go through string
		// +1 may be giving a problem...?
		while(index + 1 < given.length())
		{
			// Get first 2 characters
			char first = given.charAt(index);
			double one = Character.getNumericValue(first);
			//In case it is more than a 1-digit number
			if(Character.getNumericValue(first)!= -1)
			{
				while(Character.getNumericValue(given.charAt(index+1)) != -1)
				{
					one *= 10;
					one += Character.getNumericValue(given.charAt(++index));
				}
			}
			
			char second = given.charAt(++index);
			double two = Character.getNumericValue(second);
			//In case it is more than a 1-digit number
			if(Character.getNumericValue(second) != -1)
			{
				while(Character.getNumericValue(given.charAt(index+1)) != -1)
				{
					two*=10;
					two += Character.getNumericValue(given.charAt(++index));
				}
			}
			
			// Treat like a different string, recurse
			if(first == '(')
			{
				toReturn = calculate(given);
			}
			// Reached the end of the current recursion
			else if(first == ')')
			{
				return toReturn;
			}
			// Addition
			else if(first == '+')
			{
				index++;
				// If adding something in parens
				if(second == '(')
				{
					toReturn += calculate(given);
				}
				else
				{
					toReturn += two;
				}
			}
			// Subtraction
			else if(first == '-')
			{
				index++;
				// If subtracting something in parens
				if(second == '(')
				{
					toReturn -= calculate(given);
				}
				else
				{
					toReturn -= two;
				}
			}
			else if(one != -1)
			{
				toReturn = one;
			}
		}
		
		return toReturn;
	}
}