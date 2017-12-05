package mvc;

import dao.Meal;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is the View of the Model-View-Controller paradigm. 
 * This class represents the GUI of the application.
 * All the interaction with the user is contained in this class. 
 * This class communicates the user input to the Controller.
 * The Controller passes data to this class to be presented to user.
 * 
 * This class contains all the methods to show data to the application user and
 * it contains all the methods to capture data from the user.
 * 
 * 
 * @author William Walsh
 * @version 1.0
 * @since 29/11/2017
 * 
 */
public class V {

	// The String that the user enters to control the tool.
	private static String userTypedInput;

	// This is a Scanner which is passed the standard input stream.
	// This will be used to capture user input from the standard input stream which
	// is the keyboard in this case.
	private static Scanner keyboard = new Scanner(System.in);

	/**
	 * This method prints the argument String "message" to the standard output
	 * stream.
	 * 
	 * @param message
	 *            The String to be printed to the standard output stream.
	 */
	public static void display(String message) {
		System.out.println(message);
	}

	/**
	 * This method prints the title to the standard output stream.
	 * 
	 */
	public static void displayTitle() {
		System.out.println("*******************Welcome to Duke's 2000 Calorie Daily Diet*******************");
		System.out.println("The diet plan doesn't allow you to exceed 2000 calories.");
		System.out.println();
	}

	/**
	 * This method prints the options that can be chosen to the standard output
	 * stream. The options are Strings that the user can entered at the console to
	 * interact with the tool.
	 * 
	 */
	public static void displayOptions() {
		System.out.println("Options:");
		System.out.println("********");
		System.out.println("menu:\t\tTo view the menu");
		System.out.println("selected:\tTo view the meals selected");
		System.out.println("total:\t\tTo view Total calories selected");
		System.out.println("options:\tTo view the options menu");
		System.out.println("exit:\t\tTo exit.");
	}

	/**
	 * This method prints the title and options by calling the methods that print to
	 * the standard output stream.
	 * 
	 */
	public static void displayTitleMenu() {
		displayTitle();
		displayOptions();
	}

	/**
	 * This method accepts an ArrayList of Meal objects. The method iterates through
	 * the ArrayList and prints an index value that can be used to select a meal,
	 * the meals name and the amount of calories of the meal to the standard output
	 * stream.
	 * 
	 * @param meals
	 *            The arraylist of meals that you want to print the data of.
	 */
	public static void displayMealsWithIndex(ArrayList<Meal> meals) {
		for (int i = 0; i < meals.size(); i++) {
			System.out.println(i + "\t\t" + meals.get(i).getName() + " - " + meals.get(i).getCalories());
		}
	}

	/**
	 * This method accepts an ArrayList of Meal objects. The method iterates through
	 * the ArrayList and prints each meals name and calories to the standard output
	 * stream.
	 * 
	 * @param meals
	 *            The arraylist of meals that you want to print the data of.
	 */
	public static void displayMeals(ArrayList<Meal> meals) {
		for (int i = 0; i < meals.size(); i++) {
			System.out.println("\t" + meals.get(i).getName() + " - " + meals.get(i).getCalories());
		}
	}

	/**
	 * This method prints a prompt to the standard output stream. Then it captures
	 * the user input using a static Scanner. The Scanner captures the next complete
	 * token from the standard input stream. The standard input stream is normally
	 * the keyboard. The String data is stored as a static String called
	 * userTypedInput.
	 * 
	 */
	public static void captureTypedInput() {
		System.out.print("Enter Selection: ");
		userTypedInput = keyboard.next();
	}

	/**
	 * This method returns the private String userTypedInput. This method allows
	 * access to the private userTypedInput String outside this class.
	 * 
	 * @return The string that was typed by user and captured by the
	 *         captureTypedInput method.
	 */
	public static String getUserTypedInput() {
		return userTypedInput;
	}
}
