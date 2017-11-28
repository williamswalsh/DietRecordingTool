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
	public static void createFolderWithName(String folderName) {
		Path path = Paths.get(folderName);

		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
				V.display("Folder named: " + folderName + " was created.");
			} catch (IOException e) { // How to handle this exception // Best recovery behaviour
				e.printStackTrace();
			}
		} else {
			V.display("Folder named: " + folderName + " already exists."); // Overwrite dialog appropriate here?
		}
	}

	public static void createFile(String fileName, String fileType, String folderName, String directorySeparator) {
		String relativePathName = folderName + directorySeparator + fileName + fileType; 
								// 20171128_Meal + \ + 20171128_MealDetails + .txt
		
		try (FileWriter writer = new FileWriter(relativePathName)) {
		} catch (IOException e) {
			e.printStackTrace();
		}
		V.display("File is named: " + fileName);
		V.display("File is created in: " + folderName);
		V.display("File is of type: " + fileType);
		V.display("Using the directory separator: " + directorySeparator);
		V.display("");
	}
	public static void saveMealsToFile(String fileName, String fileType, String folderName, String directorySeparator) {
		String relativePathName = folderName + directorySeparator + fileName + fileType; 
		// 20171128_Meal + \\ + 20171128_MealDetails + .txt

		try (FileWriter writer = new FileWriter(relativePathName); PrintWriter print = new PrintWriter(writer)) {
			for (int i = 0; i < M.getSelectedMeals().size(); i++) {
				print.println(M.getSelectedMeals().get(i).getName() + " - " + M.getSelectedMeals().get(i).getCalories());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		V.display("Meals saved to file: " + relativePathName);
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
