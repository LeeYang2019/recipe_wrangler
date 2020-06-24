///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            (RecipeBox.java)
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

import java.util.ArrayList;
import java.util.Collections;

/**
 * this RecipeBox class represents a recipe box or list in the program. It 
 * contains an arraylist of sort recipes. Files are read into the program and 
 * created as new recipes and stored into the recipe box.
 * 
 * the RecipeWrangler program instantiates this class to add and store recipes
 */
public class RecipeBox {

	/** An array of RecipeBox recipes **/
	private ArrayList<Recipe> list;
	private int recipeCount;

	/** Creates a recipe list that can have up to as many recipes as possible**/
	public RecipeBox(){
		this.list = new ArrayList<Recipe>();
		this.recipeCount = 0;
	}
	
	/**intializes the recipe list**/
	public RecipeBox(ArrayList<Recipe> recipes){
		this.list = recipes;
		this.recipeCount = recipes.size();
	}
	
	/**adds a recipe to the recipe box**/
	public void add(Recipe r) {
		this.list.add(r);
		this.recipeCount++;
	}
	
	public int getRecipeCount(){
		return this.recipeCount;
	}
	
	/**sorts recipes in the recipe box **/
	public void sortRecipes(){
		Collections.sort(this.list);
	}
	
	public ArrayList<Recipe> getRecipeList(){
		return this.list;
	}
	
	/**asks the user to enter in a recipe name and compares it to existing
	 * recipe. 
	 * @param recipeName
	 * @return false if the recipe does not exist
	 */
	public boolean hasRecipe(String recipeName){
		for (int i = 0; i < this.recipeCount; i++) {
			if (list.get(i).getRecipeName().equals(recipeName)) {
				return true;
			}
		}
	return false;
	}
	
	/**compares the recipes in the text file with existing recipes and if there
	 * exists a duplicate, copies the contents of the new recipes to the 
	 * existing recipes. Otherwise, new recipes are created and added to the
	 * recipe box.
	 **/
	public void findDuplicateRecipe(String recName, String ingredList, 
			String instrctns) 
	{
		boolean doesNotHaveDuplicate = true;
		
		for (int i = 0; i < this.list.size(); i++) 
		{
			if (this.list.get(i).getRecipeName().equals(recName)) 
			{
				this.list.get(i).setIngredientList(ingredList);
				this.list.get(i).setInstructions(instrctns);
				
				doesNotHaveDuplicate = false;
			}
		}
		
		if (doesNotHaveDuplicate) 
		{
			this.list.add(new Recipe(recName, ingredList, instrctns));
			this.recipeCount++;
		}
		
	}
	/**retrieves the recipe name of an existing recipe
	 * 
	 * @param recipeName
	 * @return the name of the existing recipe
	 */
	public Recipe getRecipe(String recipeName) {
		for (int i = 0; i < this.recipeCount; i++) {
			if (list.get(i).getRecipeName().equals(recipeName)) {
				return list.get(i);
			}
		}
	return null;
	
	}
}
