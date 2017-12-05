package main;

import mvc.M;
import mvc.V;
import mvc.C;
/**
 * Title: Duke’s 2000 Calorie Daily Diet
 * 
 * This is the Main Class of the DietRecording project.
 * This class contains the main program flow.
 * 
 * @author William Walsh
 *
 */
public class Main {
	public static void main(String[] args) {

		M.retrieveMenuMeals(); // Initialization of the menuMeals ArrayList

		V.displayTitleMenu();
		
		// Displays Menu Meals which are retrieved from Model by communicating with Controller.
		V.displayMealsWithIndex(C.getMenuMeals()); 

		// boolean variable to base a while loop on.
		// if user doesn't want to play, this boolean will be changed to false 
		// when the user enters the string exit, exiting the while loop.
		boolean userWantsToPlay = true;
		while (userWantsToPlay) {
			V.captureTypedInput();

			// Checks if input is an int
			if (C.isTypedInputAnInt(V.getUserTypedInput())) {
				C.processAsInt();
			} else { // If input isn't an int
				C.processAsString();
			}
		}
	}
}

/*
 * private boolean setMenuMeals() { // Can implement hibernate solution here to
 * persist data to database using
 * 
 * 
 * class Meals{
 * 
 * //Model.getSelectedMeals().sumOfCalories(); } // Potentially build class
 * which holds arraylist of Meals and processes them // Finds sum of calories //
 * Data Access Object
 * 
 * // could do this in controller instance constructor -> controller as an
 * instance // (Singleton)
 * 
 * // public boolean isSumOfMealsWithinMax(ArrayList<Meal> selectedMeals, int //
 * indexOfSelectedMeal) { // return isWithinMax; // } // public static boolean
 * isFoodItemSelectable(int mealNumber) { // return false; // }
 * 
 * 
 */
