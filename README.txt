Title: Duke’s 2000 Calorie Daily Diet
*******************************************************************************
This is a Diet recording tool that allows you to select meals from a menu.
The menu of meals is enumerated at runtime.
The user can select a tool option(menu,selected,total,options & exit)
OR
The user can select a meal option from the meal menu.(0 Beef Casserole - 350.0)

When the user selects a meal, 
1) A meal object reference is created to the selected meal in the menuMeals ArrayList<Meal>. 
2) The program checks if the user hasn't already reached their max daily calory intake.
3) The program checks if the users calories and the selected meal doesn't exceed their max daily calory intake.
4) If both cases are true then the meal reference is added to a second arrayList called selectedMeals.
5) All other cases report an message to the user using the View Class.

This logic is contained and rerun in a while loop, until the user selects exit.
At which point these events occur:
1) User is prompted if they would like to save their meal data(Dialog only occurs if the user selects at least one meal)

       Would you like to save your entries to a text file? y/n   

2a) If the user selects "n" then the program breaks out of the main program flow while loop.
2b) If the user selects "y" then a folder is created within project with current date as part of title.
    Then a text file is created within the folder which also has the current date as part of title.
    Then the users selected meals name and calories is written to the file.
    Then the program breaks out of the main program flow while loop.
    
3) Then an exit message is displayed to user and the program exits.

*******************************************************************************

This is sample console output from the tool:

*******************Welcome to Duke's 2000 Calorie Daily Diet*******************
The diet plan doesn't allow you to exceed 2000 calories.

Options:
********
menu:		To view the menu
selected:	To view the meals selected
total:		To view Total calories selected
options:	To view the options menu
exit:		To exit.

Menu:
********
0		Beef Casserole - 350.0
1		Steak and Kidney Pie - 450.0
2		Lasagne - 500.0
Enter Selection: 
 
*******************************************************************************



I/O:
Sample Input with generated output.
********************************************************************************
INPUT: 0
OUTPUT: The meal "Beef Casserole" was added.


INPUT: 1
OUTPUT: The meal "Steak and Kidney Pie" was added.


INPUT: 2
OUTPUT: The meal "Lasagne" was added.


INPUT: menu
OUTPUT: 
Menu:
********
0		Beef Casserole - 350.0
1		Steak and Kidney Pie - 450.0
2		Lasagne - 500.0
Enter Selection: 
 
 
INPUT: selected
OUTPUT: 
Beef Casserole - 350.0
Steak and Kidney Pie - 450.0
Lasagne - 500.0


INPUT: total
OUTPUT:
Total: 
700.0


INPUT: options
OUTPUT:
Options:
********
menu:		To view the menu
selected:	To view the meals selected
total:		To view Total calories selected
options:	To view the options menu
exit:		To exit.


INPUT: exit
OUTPUT: Would you like to save your entries to a text file? y/n
   
INPUT: y
OUTPUT:
Folder named: 2017115_Meal was created.
File created: 2017115_Meal\2017115_MealDetails.txt

Meals saved to file: 2017115_Meal\2017115_MealDetails.txt
Goodbye
********************************************************************************
