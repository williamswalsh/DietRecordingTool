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
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This class is the Model of the Model-View-Controller paradigm. This class
 * represents the data layer of the application. This layer interactes with the
 * persistence methods of the application. This layer controls how data is
 * stored and retrieved.
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
public class M {

	// Class level Constant used to set the maximum calories allowable per day.
	private static final double MAX_CALORIES = 2000;

	// Meals contained on the Menu
	private static ArrayList<Meal> menuMeals = new ArrayList<Meal>();

	// Meals selected by user
	private static ArrayList<Meal> selectedMeals = new ArrayList<Meal>();

	
	/**
	 * This method checks if this list contains no Meal Objects.
	 * If this list contains no elements it will adds meal objects to the menuMeals arraylist.
	 * This method is only used once at the beginning of the project.
	 */
	public static void retrieveMenuMeals() {
		if (menuMeals.isEmpty()) {
			menuMeals.add(new Meal("Beef Casserole", 350.0));
			menuMeals.add(new Meal("Steak and Kidney Pie", 450.0));
			menuMeals.add(new Meal("Lasagne", 500.0));
		}
	}

	
	/**
	 * Method used to get date string in specific format for this moment.
	 * 
	 * @return String of Date in form YYYYMMDD e.g. 6-12-2017 -> 20171206
	 */
	public static String getDateString() {
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.YEAR) + "" + calendar.get(Calendar.MONTH) + "" + calendar.get(Calendar.DATE);
	}

	
	/**
	 * 
	 * Method takes a String called folderName and uses it to create a folder within
	 * the root directory project folder. 
	 * If the folder exists already it reports this to the user. 
	 * If the folder doesn't exist already it creates the folder and reports
	 * this to the user.
	 * 
	 * 
	 * @exception IOException
	 * @param folderName
	 */
	public static void createFolderWithName(String folderName) {
		Path path = Paths.get(folderName);

		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
				V.display("Folder named: " + folderName + " was created.");
			} catch (IOException e) { // How to handle this exception // Best recovery behaviour
				// Possible exception -> user enters folderName which is invalid (contains
				// illegal characters)
				e.printStackTrace();
			}
		} else {
			V.display("Folder named: " + folderName + " already exists."); // Overwrite dialog appropriate here?
		}
	}

	
	/**
	 * 
	 * This method creates a file with the name fileName, type fileType,
	 * inside the folder folderName, using the directory separator
	 * directorySeperator
	 * 
	 * If the file exists already it reports this to the user and exits. If
	 * the file doesn't exist already it creates the file and reports this
	 * to the user and exits.
	 * 
	 * @exception IOException
	 *                If the file cannot be created it prints a stack trace.
	 * 
	 * @param fileName
	 *            Name of file to be created without file Extension (not including
	 *            .txt)
	 * @param fileType
	 *            File Extension of file e.g .txt, .c, .java
	 * @param folderName
	 *            Name of folder in which to create file
	 * @param directorySeparator
	 *            Directory Separator which is specific to current OS
	 */
	public static void createFile(String fileName, String fileType, String folderName, String directorySeparator) {
		String relativePathName = folderName + directorySeparator + fileName + fileType;
		// relativePathName = 20171128_Meal + \ + 20171128_MealDetails + .txt

		Path relativePath = Paths.get(relativePathName);

		if (!Files.exists(relativePath)) {
			try (FileWriter writer = new FileWriter(relativePathName)) {
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			V.display("File created: " + relativePathName);

		} else {
			V.display("File already exists: " + relativePathName);
		}
		V.display("");
	}

	
	/**
	 * 
	 * Takes the fileName, fileType, folderName and directoryExtension of a previously created file.
	 * Writes the Meals selected by the user contained in the M.selecteMeals arrayList to the file. 
	 * Reports if the meals were saved to the file.
	 * 
	 * 
	 * @exception IOException
	 *                Throws Exception if cannot create FileWriter to File or if
	 *                cannot create PrintWriter using the FileWriter
	 * @param fileName
	 *            Name of file to be created without file Extension (not including
	 *            .txt)
	 * @param fileType
	 *            File Extension of file e.g .txt, .c, .java
	 * @param folderName
	 *            Name of folder in which to create file
	 * @param directorySeparator
	 *            Directory Separator which is specific to current OS
	 */
	public static void saveMealsToFile(String fileName, String fileType, String folderName, String directorySeparator) {
		String relativePathName = folderName + directorySeparator + fileName + fileType;
		// 20171128_Meal + \\ + 20171128_MealDetails + .txt

		try (FileWriter writer = new FileWriter(relativePathName); PrintWriter print = new PrintWriter(writer)) {
			print.println("Meal Selection");
			print.println(new Date());
			print.println();

			for (int i = 0; i < M.getSelectedMeals().size(); i++) {
				print.println(
						M.getSelectedMeals().get(i).getName() + " - " + M.getSelectedMeals().get(i).getCalories());
			}
			print.println();
			print.println(M.getCalories(M.getSelectedMeals()));
			print.println("*********************Have a nice day**************************");
		} catch (IOException e) {
			e.printStackTrace();
		}
		V.display("Meals saved to file: " + relativePathName);
	}

	
	/**
	 * Method used to get the meals contained in the meals menu.
	 * 
	 * @return Reference to an arrayList of meals which are contained in the menu.
	 */
	public static ArrayList<Meal> getMenuMeals() {
		return menuMeals;
	}

	
	/**
	 * Method used to get the meals selected by the user today.
	 * 
	 * @return Reference to an arrayList of meals which are selected by the user today.
	 */	
	public static ArrayList<Meal> getSelectedMeals() {
		return selectedMeals;
	}

	
	/**
	 * 
	 * @return Double which is the max allowable calories consumed per day.
	 */
	public static double getMaxCalories() {
		return MAX_CALORIES;
	}

	
	/**
	 * Returns Sum of calories of an ArrayList of meals. 
	 * 
	 * @param meals Reference to an arrayList of meals which you want to calculate the sum of calories of. 
	 * @return Sum of calories of all meals in passed arraylist meals.
	 */
	public static double getCalories(ArrayList<Meal> meals) {
		double sum = 0;
		for (Meal meal : meals) {
			sum += meal.getCalories();
		}
		return sum;
	}
}