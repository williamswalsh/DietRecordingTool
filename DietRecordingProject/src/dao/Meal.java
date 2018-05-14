package dao;

/**
 * 
 * This class represents a single meal.
 * This class is encapsulated.
 * Its 2 members can be accessed/mutated by 
 * using the corresponding getters and setters.
 * 
 * @author William Walsh
 * @version 1.0
 * @since 29/11/2017
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
