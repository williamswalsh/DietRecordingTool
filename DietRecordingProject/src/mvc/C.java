package mvc;

import java.util.ArrayList;

import dao.Meal;

/**
 * This class is the Controller of the Model-View-Controller paradigm. This
 * class contains the business logic for the program. This class receives and
 * processes user input from the View. This class accesses & modifies data by
 * communicating with the Model.
 * 
 * 
 * @author William Walsh
 * @version 1.0
 * @since 04/12/2017
 * 
 */
public class C {

	// integer used to store a user selected integer
	private static int userSelectedInt;
	
	
	/**
	 * Determines if String argument typedInput can be parsed to an integer. If
	 * String can be parsed to a integer returns true. If String parsed fails
	 * methods returns false.
	 * 
	 * @param typedInput
	 * @return Boolean representing if the passed String typedInput is/isn't an
	 *         integer
	 */
	public static boolean isTypedInputAnInt(String typedInput) {
		// try to parse to int
		// fails -> String
		// succeeds -> int
		try {
			// Storing in static int var
			userSelectedInt = Integer.parseInt(typedInput);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	
	/**
	 * Method used to manage the business logic involved once the user selects to
	 * exit the app. If user selects at least one meal they will be prompted to save their
	 * meal data to a file.
	 * 
	 * If user enters y >> folder and file will be created and meal data will be
	 * saved to file 
	 * 
	 * else if user enters n >> Nothing will happen. 
	 * 
	 * otherwise >> user will be prompted to re-enter a valid input
	 * 
	 * Method then displays exit message and Exits.
	 * 
	 */
	public static void exitingApp() {

		// if selection was made
		if (M.getSelectedMeals().size() > 0) {
			boolean userInputIncorrect = true;
			while (userInputIncorrect) {
				V.display("Would you like to save your entries to a text file? y/n");
				V.captureTypedInput();
				if (V.getUserTypedInput().trim().toLowerCase().equals("y")) {

					// Method generates parts of folder and file name by using todays date
					String todaysDate = M.getDateString();

					String folderName = todaysDate + "_Meal";
					M.createFolderWithName(folderName);

					String fileType = ".txt";
					String fileName = todaysDate + "_MealDetails";
					String directorySeparator = "\\"; // Windows OS (Modify to detect correct separator -OS
														// linux/unix/windows)
					M.createFile(fileName, fileType, folderName, directorySeparator);
					M.saveMealsToFile(fileName, fileType, folderName, directorySeparator);

					userInputIncorrect = false;
				} else if (V.getUserTypedInput().trim().toLowerCase().equals("n")) {
					userInputIncorrect = false;
				} else {
					V.display("Incorrect input, try again.");
				}
			}
		}
		V.display("Goodbye");
		System.exit(0);
	}

	/**
	 * If the user enters an integer this method will handle it.
	 * An integer represents a meal on the menu. 
	 * The integer is the index position of the meal in the menuMeals ArrayList.
	 * 
	 * Method creates reference to Meal and tries to assign this reference to the 
	 * selected meal in the menuMeals arraylist.
	 * Then passes the tryAddMeal method the selectedMeal reference.
	 * If the index is outside the range of the menuMeals ArrayList 
	 * an IndexOutOfBoundsException is thrown.
	 * This is handled by reporting issue and continues in loop. 
	 * 
	 */
	public static void processAsInt() {

		Meal selectedMeal;
		
		try{
			selectedMeal = M.getMenuMeals().get(C.userSelectedInt);
			tryAddMeal(selectedMeal);
		}catch(IndexOutOfBoundsException ioobe) {
			V.display("There is no meal with that selection integer. Please try again.");
		}


	}
	/**
	 * Method takes an Meal object reference.
	 * It checks if your current selection has reached your max daily calories.
	 * if you have it reports this condition back to you
	 * If you haven't 
	 * 		It checks if you have exceeded your max daily calories
	 * 		if you have it does nothing just reports that back to you
	 * 		otherwise it adds the meal and report its back to you.
	 * 
	 * 
	 * @param selectedMeal Meal object reference which user selected from menu using index.
	 */
	public static void tryAddMeal(Meal selectedMeal) {
		
		final double maxCalories = M.getMaxCalories();
		
		if (isEQMaxCalories()) {
			V.display("Max Calories Reached - Cannot add another meal.");
		} else {

			if ((M.getCalories(M.getSelectedMeals()) + selectedMeal.getCalories()) > maxCalories) {
				V.display("The meal " + selectedMeal.getName() + " exceeds your max daily calories.");
			} else {
				M.getSelectedMeals().add(selectedMeal);
				V.display("The meal \"" + selectedMeal.getName() + "\" was added.");
			}
		}
	}

	/**
	 * Method checks if the sum of calories of the user selected meals is equal to
	 * the maximum calories allowed daily.
	 * 
	 * 
	 * @return boolean value wheter or not Max Calories Amount has been reached.
	 * 
	 */
	public static boolean isEQMaxCalories() {
		return (M.getCalories(M.getSelectedMeals()) == M.getMaxCalories()) ? true : false;
	}
	
	/**
	 * Method gets input String from View(V) and stores it locally for reuse.
	 * It compares string with options.
	 * If string value equates to an option String
	 * the associated action of the option is carried out.
	 * If String is not equated with an option an error string is displayed to user.
	 * 
	 */
	public static void processAsString() {
		String processedInput = V.getUserTypedInput().trim().toLowerCase();

		if (processedInput.equals("menu")) {
			V.displayMealsWithIndex(M.getMenuMeals());
		} else if (processedInput.equals("selected")) {
			V.displayMealsWithIndex(M.getSelectedMeals());
		} else if (processedInput.equals("total")) {
			V.display("" + M.getCalories(M.getSelectedMeals()));
		} else if (processedInput.equals("options")) {
			V.displayOptions();
		} else if (processedInput.equals("exit")) {
			C.exitingApp();
		} else {
			V.display("Incorrect string entered. Please try again.");
		}
	}
	
	/**
	 * This is an "indirection" method.
	 * Indirection as described by the GRASP software pattern.
	 * This method is used to decouple the View V from the Model M.
	 * View calls this method which just calls the models method.
	 * Ensures that the View isn't coupled with the Model.
	 * 
	 * @return 
	 */
	public static ArrayList<Meal> getMenuMeals(){
		return M.getMenuMeals();
	}
}
