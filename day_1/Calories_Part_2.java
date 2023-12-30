package _2022_day_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Calories_Part_2 {
	static final int INVALID_FILENAME = -1;
	static final int FILE_NOT_FOUND = -2;
	
	public static String get_filename(Scanner keyboardScanner)
	{
		System.out.print("Enter file name: ");
		String filename = keyboardScanner.nextLine();
		
		if (filename.isEmpty() || filename.isBlank() || filename == null)
		{
			System.out.println("Invalid filename entered.");
			System.exit(INVALID_FILENAME);;
		}
		
		return filename;
	}
	
	public static void main(String[] args)
	{
		Scanner keyboardScanner = new Scanner(System.in);
		String filename = get_filename(keyboardScanner);
		String line = null;
		File file = new File(filename);
		ArrayList<Integer> elfTotalCalories = new ArrayList<Integer>();
		int first = 0;
		int second = 0;
		int third = 0;
		
		try 
		{
			Scanner fileScanner = new Scanner(file);
			int currentElfCalories = 0;
			while (fileScanner.hasNextLine())
			{
				line = fileScanner.nextLine();
				if (line.isBlank())
				{
					elfTotalCalories.add(currentElfCalories);
					currentElfCalories = 0;
				}
				else
				{
					currentElfCalories += Integer.parseInt(line);
				}
			}
			elfTotalCalories.add(currentElfCalories);
			fileScanner.close();
		}
		catch (FileNotFoundException exception)
		{
			System.out.println("File not found.");
			System.exit(FILE_NOT_FOUND);
		}
		
		for (Integer calories : elfTotalCalories)
		{
			if (calories > first)
			{
				third = second;
				second = first;
				first = calories;
				continue;
			}
			
			if (calories > second)
			{
				third = second;
				second = calories;
				continue;
			}
			
			if (calories > third)
			{
				third = calories;
				continue;
			}
		}
		
		System.out.println("First: " + first + " Second: " + second + " Third: " + third);
		System.out.println("Total = " + (first + second + third));
	}
}
