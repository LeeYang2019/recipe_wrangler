///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            (RecipeWrangler.java)
// Files:            (list of source files)
// Semester:         (Introduction to Computer Programming) Fall 2015
//
// Author:           (Nhialee Yang)
// Email:            (nyang5@wisc.edu)
// CS Login:         (nhialee)
// Lecturer's Name:  (Deb Deppeler)
// Lab Section:      (302)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     (name of your pair programming partner)
// Email:            (email address of your programming partner)
// CS Login:         (partner's login name)
// Lecturer's Name:  (name of your partner's lecturer)
// Lab Section:      (your partner's lab section number)
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   must fully acknowledge and credit those sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but tutors, room mates, relatives, strangers, etc do.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The RecipeWrangler is an application that manages and keeps track of recipe
 * files. It displays to the console a menu asking the user to either enter in a
 * recipe, its ingredient lists and instructions, or read into the program a 
 * text file containing recipes. The program also allows for the user to write 
 * to file, existing recipes on the program. Upon completing each option, the
 * program will loop until the user chooses to terminate it. 
 * 
 * Bugs: none known
 * 
 * @author nhialeeyang
 */

public class RecipeWrangler {
	
	/**The user is asked to select from one of five choices from the menu. A 
    * "1." will display the names of recipes on file. A "2." will retrieve an 
    * existing recipe for editing or will create a new recipe; a "3." 
    * will read a file text and add its recipes to the program. A "4." 
    * will write to file existing files on the program. The "5." will exit the 
    * menu and quit the program.
	**/
	public static void main(String[] args) {

		//create Scanner
		Scanner input = new Scanner(System.in);

		//object classes 
		RecipeBox recipeBox = new RecipeBox();
		Recipe recipe = null;

		//local variables
		String recName = null;
		String fileName = null;

		int choice = 0;
		
		//initial message output 
		System.out.println("Recipe Wrangler 2015");
		System.out.println("Let us help you keep track of "
				+ "your favorite recipes.");

		do 
		{
			displayMainMenu();
			try 
			{

				choice = getUserInput(input);

				if (choice == 1) 
				{
					//displays the list of recipes currently on the program
					displayRecipeNames(recipeBox);
				} 
				else if (choice == 2) 
				{
					//the user can retrieve, edit, or add a recipe
					displayEditAddRecipe(input, recipeBox, recipe);
				} 
				else if (choice == 3) 
				{
					//reads from file a text file and uploads the recipes onto
					//the program
					readFromFile(input,recipeBox, recipe);
				} 
				else if (choice == 4) 
				{
					//writes to file the recipes on the program
					writeToFile(fileName, recName, input, recipeBox);
				} 
				else if (choice < 1 || choice > 5) 
				{
					System.out.println("Enter integer choice between 1-5: ");
				}
			} 
			catch (NumberFormatException exception) 
			{
				System.out.println("Enter integer choice between 1-5: ");
			} 
		} 
		while (choice != 5); 
		System.out.println("Thanks for using RecipeWrangler!");
		input.close();
	}
/**
 * gets the user's input 
 * 
 * @param input
 * @return user input
 */
	private static int getUserInput(Scanner input) 
	{
		int choice = Integer.parseInt(input.nextLine());
		
		while (choice < 1 || choice > 5) {

			try {
				System.out.print("Enter integer choice between 1-5: ");
				choice = Integer.parseInt(input.nextLine());
			}
			catch(NumberFormatException exception) {
			}
		}
		return choice;
	}
/**
 *displays the main menu of the recipe wrangler program
 */
	public static void displayMainMenu() 
	{
		System.out.println("Main Menu");
		System.out.println("---------");
		System.out.println("1. Display recipe names (sorted)");
		System.out.println("2. Display/Edit/Add a recipe");
		System.out.println("3. Load recipes from a file");
		System.out.println("4. Save recipes to a file");
		System.out.println("5. Exit");
		System.out.print("Enter choice: ");
	}
/**
 * displays the a recipe wrangler sub menu
 */
	public static void displaySubMenu() 
	{
		System.out.println("1. Edit ingredient list");
		System.out.println("2. Edit instructions");
		System.out.println("3. Done editing");
		System.out.print("Enter choice: ");
	}
/**
 * asks if the user wants to edit the recipe ingredient list, its instructions, 
 * or exit to the main menu.
 * @param input
 * @param recipe
 */
	public static void editSubMenu(Scanner input, Recipe recipe) 
	{
		int subChoice = 0; 
		
		do 
		{	
			displaySubMenu();

			try 
			{
				subChoice = Integer.parseInt(input.nextLine());
				
				//edits the recipe ingredient list
				if (subChoice == 1) 
				{
					System.out.println("Ingredients: ");
					String newIngredList = input.nextLine();
					recipe.setIngredientList(newIngredList);
				}
				//edits the recipe instructions
				else if (subChoice == 2) 
				{
					System.out.println("Instructions: ");
					String newInstructions = input.nextLine();
					recipe.setInstructions(newInstructions);
				} 
				else if (subChoice < 1 || subChoice > 5) 
				{
					System.out.println("Invalid choice");
					System.out.println(subChoice);
				}
			} 
			catch(NumberFormatException exception) 
			{
				System.out.println("Invalid choice");
			}
		} while (subChoice != 3);
	}
/**
 * asks the user to enter in a recipe name. If it exists in the recipe box,
 * it will be displayed to the console; otherwise, a new recipe is created
 * 
 * @param recipeBox
 * @param recipe
 */
	public static void displayEditAddRecipe(Scanner input, RecipeBox recipeBox, 
			Recipe recipe) 
	{
		String ingredList = null;
		String instrctns = null;
		String recName= null;
		
		System.out.print("What is the name of the recipe? ");
		recName = input.nextLine().toUpperCase();
		
		//retrieves existing recipe file from the recipe box
		if (recipeBox.hasRecipe(recName)) 
		{	
			recipe = recipeBox.getRecipe(recName);
			System.out.println(recipe);
			editSubMenu(input, recipe);
		} 
		else 
		{
			//adds new recipe file to the recipe box
			addToRecipeBox(ingredList, instrctns, recName, 
					recipeBox, recipe, input);
		}
	}
/**
 * displays the names of existing recipes; otherwise, outputs
 * a "no recipes" statement to the console.
 * 
 * @param recipeBox
 */
	private static void displayRecipeNames(RecipeBox recipeBox) 
	{
		if (recipeBox.getRecipeCount() == 0) 
		{
			System.out.println("No recipes");
		} 
		else 
		{
			// Display Recipe Names
			for (int i = 0; i < recipeBox.getRecipeList().size(); i++) 
			{
				System.out.println(recipeBox.getRecipeList().
						get(i).getRecipeName());
			}
		}
	}
/**
 * asks the user to enter in a recipe name. If it exists, it will be displayed 
 * to the console. The name of the recipe name, its ingredient list and 
 * instructions will be displayed.
 * 
 * @param input
 * @param recipeBox
 * @param recipe
 */
	public static void addEditRecipes(Scanner input, RecipeBox recipeBox, Recipe recipe) 
	{
		String recName = null;
		
		System.out.print("What is the name of the recipe? ");
		recName = input.nextLine().toUpperCase();

		if (recipeBox.hasRecipe(recName)) 
		{	
			recipe = recipeBox.getRecipe(recName);
			System.out.println("Found recipe for: " + 
					recipeBox.getRecipe(recName));
			System.out.println(recipe);
		}
	}
/**
 * adds the recipe, which the user has named, along with its ingredient list 
 * and instructions to the recipe box.
 * 
 * @param ingredList
 * @param instrctns
 * @param recName
 * @param recipeBox
 * @param recipe
 * @param input
 */
	private static void addToRecipeBox(String ingredList, String instrctns, 
			String recName, RecipeBox recipeBox,
			Recipe recipe, Scanner input) 
	{
		System.out.println("Adding recipe for: " + recName);
		System.out.print("Enter the ingredients: ");
		ingredList = input.nextLine();
		System.out.print("Enter the instructions: ");
		instrctns = input.nextLine();

		//creates a new recipe with the information provided and adds 
		//it to the recipe box, after which the recipes in the recipe box are
		//sorted
		recipe = new Recipe(recName, ingredList, instrctns);
		recipeBox.add(recipe);
		recipeBox.sortRecipes();
	}
/**
 * asks the user to enter in a file name. The program will read the file and its
 * recipes and add them into the recipe box.
 * 
 * @param readFileName
 * @param recName
 * @param ingredList
 * @param instrctns
 * @param input
 * @param recipeBox
 */
	public static void readFromFile(Scanner input, 
			RecipeBox recipeBox, Recipe recipe) 
	{
		String readFileName = null;
		String recName;
		String ingredList;
		String instrctns;
		
		int loadedRecipes = 0;
		System.out.print("Enter a file name: ");

		try 
		{
			readFileName = input.nextLine(); 

			File fileTwo = new File(readFileName);
			Scanner scnr = new Scanner(fileTwo);

			try 
			{
				//reads in the integer which tells the program how many recipes
				//are on the text file
				int numRecipes = scnr.nextInt();
				scnr.nextLine();

				//a for loop for the number of recipes to read
				for (int i = 0; i < numRecipes; i++) 
				{
					recName = scnr.nextLine().toUpperCase();
					ingredList = scnr.nextLine();
					instrctns = scnr.nextLine();

					//compares read in recipe file to existing recipe file and
					//updates the existing file if there is a duplicate
					recipeBox.findDuplicateRecipe(recName,ingredList,instrctns);
				
					//displays which recipes are added
					System.out.println("Added " + recName);
					loadedRecipes++;
				}

				//displays the number of recipes added
				System.out.println("Added " + loadedRecipes 
						+ " recipes from " + readFileName);

				scnr.close();
			}
			catch (NoSuchElementException e) 
			{
				System.out.println("Unable to read from " + readFileName);
			}	
		}
		catch (FileNotFoundException exception) 
		{
			System.out.println("Unable to read from file: " + readFileName);
			System.out.println("Added " + loadedRecipes 
					+ " recipes from " + readFileName);
		}

		recipeBox.sortRecipes();
	}
/**
 * writes to file the number of existing recipes. The recipes are formatted to
 * show the number of recipes being written, followed by their recipe names, 
 * ingredient lists, and instructions.
 * 
 * @param fileName
 * @param recName
 * @param input
 * @param recipeBox
 */
	public static void writeToFile(String fileName, String recName, 
			Scanner input, RecipeBox recipeBox) 
	{
		int writtenRecipes = 0;

		if (recipeBox.getRecipeCount() == 0)
		{
			System.out.println("No recipes");
		}
		else
		{
			System.out.print("Enter a file name: ");

			try 
			{
				fileName = input.nextLine(); 
				File file = new File(fileName);

				if (file.exists()) 
				{
					System.out.println("File already exists");
				}

				else 
				{
					PrintWriter output = new PrintWriter(file);
					output.println(recipeBox.getRecipeCount());

					for (int i = 0; i < recipeBox.getRecipeList().size(); i++) 
					{
						output.println(recipeBox.getRecipeList().
								get(i).getRecipeName());
						output.println(recipeBox.getRecipeList().
								get(i).getIngredientList());
						output.println(recipeBox.getRecipeList().
								get(i).getInstructions());
						writtenRecipes++;
					}

					if (writtenRecipes == 1) 
					{
						System.out.println("Saved " + writtenRecipes + 
								" recipe to file: " + fileName);
					}
					else 
					{
						System.out.println("Saved " + writtenRecipes + 
								" recipes to file: " + fileName);
					}
					output.close();
				}
			} 
			catch (IOException e) 
			{
				System.out.println("Unable to write to file: " + fileName);
				System.out.println("Saved " + writtenRecipes + 
						" recipes to file: " + fileName);
			}
		}
	}
}

