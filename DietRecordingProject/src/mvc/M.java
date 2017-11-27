package mvc;
import dao.Meal;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class M {
	private static final double MAX_CALORIES = 2000;
	private static ArrayList<Meal> menuMeals = new ArrayList<Meal>();
	private static ArrayList<Meal> selectedMeals = new ArrayList<Meal>();

	// Can only be called once at beginning
	// Retrives data from data source
	// Data created locally
	// Can change to persistent file storage
	// DB -> Hibernate
	// This method will be changed
	public static void retrieveMenuMeals() {
		menuMeals.add(new Meal("Beef Casserole", 350.0));
		menuMeals.add(new Meal("Steak and Kidney Pie", 450.0));
		menuMeals.add(new Meal("Lasagne", 500.0));
	}

	public static String getDateString() {
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.YEAR) + "" + calendar.get(Calendar.MONTH) + "" + calendar.get(Calendar.DATE);
	}

	// M.createFolder();// Create folder >> name >> todays date + Meal
	// M.createTextFile();// Create textfile >> name >> todays date +
	// MealDetails 20171123
	public static void createFolder() {
		String folderName = getDateString() + "_Meal";
		Path path = Paths.get(folderName);

		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) { // How to handle this exception // Best recovery behaviour
				e.printStackTrace();
			}
		} else {
			V.display("Folder named: " + folderName + " already exists."); // Overwrite dialog appropriate here?
		}
		V.display("Folder named: " + folderName + " was created.");
	}

	public static void createAndPrintTextFile(String fileName) {

		try (FileWriter writer = new FileWriter(fileName); PrintWriter print = new PrintWriter(writer)) {
			for (int i = 0; i < M.getSelectedMeals().size(); i++) {
				V.display(M.getSelectedMeals().get(i).getName() + " - " + M.getSelectedMeals().get(i).getCalories());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		V.display("File is named: " + fileName);
	}

	public static ArrayList<Meal> getMenuMeals() {
		return menuMeals;
	}

	public static ArrayList<Meal> getSelectedMeals() {
		return selectedMeals;
	}

	public static double getMaxCalories() {
		return MAX_CALORIES;
	}

	public static double getSumOfCalories(ArrayList<Meal> meals) {
		double sum = 0;
		for (Meal meal : meals) {
			sum += meal.getCalories();
		}
		return sum;
	}
}
