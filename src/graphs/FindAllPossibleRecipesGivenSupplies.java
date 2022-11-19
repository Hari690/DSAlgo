package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients. The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i]. Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.
 *
 * You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.
 *
 * Return a list of all the recipes that you can create. You may return the answer in any order.
 * Note that two recipes may contain each other in their ingredients.
 *
 * Example 1:
 * Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
 * Output: ["bread"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 *
 * Example 2:
 * Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
 * Output: ["bread","sandwich"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
 *
 * Example 3:
 * Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
 * Output: ["bread","sandwich","burger"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
 * We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".
 *
 */
public class FindAllPossibleRecipesGivenSupplies {

    /*
    Given problem is similar to topological sort if we consider
    Every ingredient as a prerequisite to a recipe.
    Supplies as a leaf node.
     */
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String,List<String>> adjList = new HashMap<>();

        for(int i=0;i<recipes.length;i++) {
            adjList.put(recipes[i], ingredients.get(i));
        }

        // This is preferable to checking a set of supplies as we don't want to check top level of graph which is the root recipe.
        Map<String, Boolean> visited = new HashMap<>();
        for(String supply: supplies)
            visited.put(supply, true);

        List<String> result = new ArrayList<>();

        for(String recipe : recipes) {
            if(dfs(recipe, adjList, visited))
                result.add(recipe);
        }
        return result;
    }


    private boolean dfs(String recipe, Map<String,List<String>> adjList,Map<String, Boolean> visited) {
        if(visited.containsKey(recipe))
            return visited.get(recipe);

        // check cycle.
        visited.put(recipe, false);

        if(!adjList.containsKey(recipe))
            return false;

        for(String value : adjList.get(recipe)) {
            if(!dfs(value,adjList,visited))
                return false;
        }
        visited.put(recipe, true);
        return true;
    }

    public static void main(String[] args) {
        FindAllPossibleRecipesGivenSupplies findAllPossibleRecipesGivenSupplies = new FindAllPossibleRecipesGivenSupplies();

        String[] recipes = new String[]{"bread"};
        List<String> list = List.of("yeast","flour");
        List<List<String>> ingredients = List.of(list);

        String[] supplies = new String[]{"yeast"};
        findAllPossibleRecipesGivenSupplies.findAllRecipes(recipes, ingredients, supplies);
    }
}
