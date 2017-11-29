package dao;

/**
 * 
 * @author William Walsh
 * @version %I%, %G%
 * 
 */
public class Meal {
	private String name;
	private double calories;

	public Meal(String name, double calories) {
		this.name = name;
		this.calories = calories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}
}