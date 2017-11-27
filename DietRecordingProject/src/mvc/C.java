package mvc;
import dao.Meal;

public class C {
	private static int userSelectedInt;

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

	public static void exitingApp() {

		// if selection was made
		if (M.getSelectedMeals().size() > 0) {
			boolean userHasNotEnteredCorrectInput = true;
			while (userHasNotEnteredCorrectInput) {
				V.display("Would you like to save your entries to a text file? y/n");
				V.captureTypedInput();
				if (V.getUserTypedInput().trim().toLowerCase().equals("y")) {

					M.createFolder();

					String fileName = M.getDateString() + "_MealDetails";
//					M.createTextFile(fileName);
//					M.printMealsToFile(M.getSelectedMeals(), fileName);
					userHasNotEnteredCorrectInput = false;
				} else if (V.getUserTypedInput().trim().toLowerCase().equals("n")) {
					userHasNotEnteredCorrectInput = false;
				}
			}
		}

		V.display("Goodbye");
		System.exit(0);
	}

	public static void processAsInt() {
		final double maxCalories = M.getMaxCalories();

		// java.lang.IndexOutOfBoundsException C.userSelectedInt

		// if withinRange allow function
		// else skip remainder of function
		// OR
		// try this
		// catch no meal assoc with number
		try {
			Meal selectedMeal = M.getMenuMeals().get(C.userSelectedInt);

			if (M.getSumOfCalories(M.getSelectedMeals()) == maxCalories) {
				V.display("Max Calories Reached - Cannot add another meal.");
				// Finish while loop?
			} else {

				if ((M.getSumOfCalories(M.getSelectedMeals()) + selectedMeal.getCalories()) <= maxCalories) {
					// selectedMeals.add(selectedMeal);
					M.getSelectedMeals().add(selectedMeal);
					V.display(selectedMeal.getName() + " (" + selectedMeal.getCalories() + ") was added.");
				} else {
					V.display(selectedMeal.getName() + " (" + selectedMeal.getCalories()
							+ ") with your current calories: " + M.getSumOfCalories(M.getSelectedMeals())
							+ " exceeds your max calories. It wasn't added.");
				}
			}
			V.display(M.getSumOfCalories(M.getSelectedMeals()) + " calories accumulated.\n");

		} catch (IndexOutOfBoundsException ioobe) {
			V.display("No meal with that selected number.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void processAsInt2() {

		if (M.getSumOfCalories(M.getSelectedMeals()) == M.getMaxCalories()) {
			V.display("Max Calories Reached - Cannot add another meal.");
			// Finish while loop?
		}

		Meal selectedMeal = M.getMenuMeals().get(C.userSelectedInt);
		if ((M.getSumOfCalories(M.getSelectedMeals()) + selectedMeal.getCalories()) <= M.getMaxCalories()) {
			M.getSelectedMeals().add(selectedMeal);
			V.display("The meal \"" + selectedMeal.getName() + "\" was added.");
			selectedMeal = null; // Required???
		} else {
			V.display("The meal " + selectedMeal.getName() + " exceeds your max daily calories.");
		}
	}

	public static void processAsString() {
		if (V.getUserTypedInput().trim().toLowerCase().equals("menu")) {
			V.displayMeals(M.getMenuMeals());
		} else if (V.getUserTypedInput().trim().toLowerCase().equals("selected")) {
			V.displayMeals(M.getSelectedMeals());
		} else if (V.getUserTypedInput().trim().toLowerCase().equals("exit")) {
			C.exitingApp();
		} else {
			V.display("Incorrect string entered. Please try again.");
		}
	}
}
