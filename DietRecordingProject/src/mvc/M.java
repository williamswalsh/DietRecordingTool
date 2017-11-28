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

	/**
	 * Method uses passed folderName string to create a folder within the root
	 * project folder. If the folder exists already it reports this to the user. If
	 * the folder doesn't exist already it create the folder and reports this to the
	 * user.
	 * @author William Walsh
	 * @exception IOException
	 * @param folderName
	 * @return void 
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
	 * @author William Walsh
	 * 
	 * This method creates a file with the name fileName, type fileType, inside the
	 * folder folderName, using the directory separator directorySeperator
	 * 
	 * If the file exists already it reports this to the user and exits. If the file
	 * doesn't exist already it creates the file and reports this to the user and
	 * exits.
	 * 
	 * @exception IOException
	 * If the file cannot be created it prints a stack trace.
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
	 * @return void 
	 */
	public static void createFile(String fileName, String fileType, String folderName, String directorySeparator) {
		String relativePathName = folderName + directorySeparator + fileName + fileType;
		// 20171128_Meal + \ + 20171128_MealDetails + .txt

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
	 * @author William Walsh
	 * 
	 * Saves the meals selected by the user which are saved to the private M.selectedMeals ArrayList
	 * to the File signified by the fileName, fileType, folderName and directoryExtension 
	 * 
	 * 
	 * @exception IOException
	 * Throws Exception if cannot create FileWriter to File or if cannot create PrintWriter to FileWriter
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
	 * @return void 
	 */
	public static void saveMealsToFile(String fileName, String fileType, String folderName, String directorySeparator) {
		String relativePathName = folderName + directorySeparator + fileName + fileType;
		// 20171128_Meal + \\ + 20171128_MealDetails + .txt

		try (FileWriter writer = new FileWriter(relativePathName); PrintWriter print = new PrintWriter(writer)) {
			for (int i = 0; i < M.getSelectedMeals().size(); i++) {
				print.println(
						M.getSelectedMeals().get(i).getName() + " - " + M.getSelectedMeals().get(i).getCalories());
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
