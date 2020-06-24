///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            (Recipe.java)
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
/**
 * the Recipe class represents a recipe in the program. It contains a recipe 
 * name, ingredient list, and set of instructions.
 *
 * the RecipeWrangler program instantiates this class once for each recipe when
 * a new recipe is created. 
 */
public class Recipe implements Comparable<Recipe> {

	private String recipeName;
	private String ingredientList;
	private String instructions;

	public Recipe(String recipeName, String ingredientList, String instructions) {
		this.recipeName = recipeName;
		this.ingredientList = ingredientList;
		this.instructions = instructions;
	}

	public String getRecipeName() {
		return this.recipeName;
	}

	public String getIngredientList() {
		return this.ingredientList;
	}

	public String getInstructions() {
		return this.instructions;
	}

	public void setIngredientList(String ingredientList) {
		this.ingredientList = ingredientList;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String toString() {
		return "Found recipe for: " + recipeName + "\n"
				+ "Recipe name: " + recipeName + "\n" + 
				"Ingredients: " + ingredientList + "\n" + 
				"Instructions: " + instructions;
	}

	public int compareTo(Recipe other) {
		return this.recipeName.compareTo(other.recipeName);
	}
}
