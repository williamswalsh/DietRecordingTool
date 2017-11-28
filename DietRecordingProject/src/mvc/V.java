package mvc;
import dao.Meal;

import java.util.ArrayList;
import java.util.Scanner;

public class V {
	private static String userTypedInput;
	private static Scanner keyboard = new Scanner(System.in);

	public static void display(String message) {
		System.out.println(message);
	}
	public static void displayTitle() {
		System.out.println("*******************Welcome to Duke's 2000 Calorie Daily Diet*******************");
		System.out.println("The diet plan doesn't allow you to exceed 2000 calories.");
		System.out.println();
	}
	public static void displayOptions() {
		System.out.println("Options:");
		System.out.println("********");
		System.out.println("menu:\t\tTo view the menu");
		System.out.println("selected:\tTo view the meals selected");
		System.out.println("total:\t\tTo view Total calories selected");
		System.out.println("options:\tTo view the options menu");
		System.out.println("exit:\t\tTo exit.");
	}
	public static void displayTitleMenu() {
		displayTitle();
		displayOptions();
	}

	public static void displayMealsWithIndex(ArrayList<Meal> selectedMeals) {
		for (int i = 0; i < selectedMeals.size(); i++) {
			System.out
					.println(i + "\t\t" + selectedMeals.get(i).getName() + " - " + selectedMeals.get(i).getCalories());
		}
	}

	public static void displayMeals(ArrayList<Meal> selectedMeals) {
		for (int i = 0; i < selectedMeals.size(); i++) {
			System.out
					.println("\t" + selectedMeals.get(i).getName() + " - " + selectedMeals.get(i).getCalories());
		}
	}
	
	public static void captureTypedInput() {
		System.out.print("Enter Selection: ");
		userTypedInput = keyboard.next();
	}

	public static String getUserTypedInput() {
		return userTypedInput;
	}
}
