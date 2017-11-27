package main;

import mvc.M;
import mvc.V;
import mvc.C;

/**
 * Title: Duke’s 2000 Calorie Daily Diet
 * 
 * @author William Walsh
 *
 */
public class Main {
	public static void main(String[] args) {

		M.retrieveMenuMeals(); // Initialization

		V.displayTitleMenu();
		V.displayMeals(M.getMenuMeals()); // MVC not
		// followed here V->M directly and not through C -> ASK PETE

		boolean userWantsToPlay = true;
		while (userWantsToPlay) {
			V.captureTypedInput();
			if (C.isTypedInputAnInt(V.getUserTypedInput())) {
				C.processAsInt();
			} else {
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
 * 
 * // Originating Value -> Buggy/ Any Effect?-> ? -> 999 -> To display the menu
 * -> // NB: Must not use this if its not initialized. Potential test before Use
 * // If final must initalize in constructor or must make Class non static an //
 * object // to have constructor....
 * 
 * // could do this in controller instance constructor -> controller as an
 * instance // (Singleton)
 * 
 * 
 * // public boolean isSumOfMealsWithinMax(ArrayList<Meal> selectedMeals, int //
 * indexOfSelectedMeal) { // return isWithinMax; // } // public static boolean
 * isFoodItemSelectable(int mealNumber) { // return false; // }
 * 
 * 
 */
