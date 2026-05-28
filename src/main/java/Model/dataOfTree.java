package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Manages the data generation and validation logic for the Sudoku tree.
 * <p>
 * This class creates the tree, generates a valid 6x6 Sudoku solution,
 * determines which numbers are visible to the player, and validates
 * whether a number can be placed in a specific position.
 * </p>
 */

public class dataOfTree implements IdataOfTree{
    public tree myTree;
    /**
     * Creates a new Sudoku data manager.
     * <p>
     * The constructor initializes the tree, fills it with board nodes,
     * generates a valid Sudoku solution, and selects the visible numbers.
     * </p>
     */

    public dataOfTree(){
        myTree = new tree();
        myTree.fillTree();
        generateSudoku(myTree.root);
        generateVisibleNumbers();
    }
    /**
     * Generates a valid Sudoku solution using recursive backtracking.
     *
     * @param actual the current node being processed
     * @return {@code true} if the Sudoku solution was generated successfully,
     * otherwise {@code false}
     */
    public boolean generateSudoku(node actual){
        if (actual == null) {
            return true;
        }
        if (actual.row == -1) {
            if (!actual.children.isEmpty()) {
                return generateSudoku(actual.children.get(0));
            }
            return false;
        }
        ArrayList<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Collections.shuffle(numbers);
        for (int number : numbers) {
            if (isValid(actual, number, myTree.root)) {
                actual.setData(number);
                node nextChild = actual.children.isEmpty() ? null : actual.children.get(0);
                if (generateSudoku(nextChild)) {
                    return true;
                }
                actual.setData(null);
            }
        }
        return false;
    }
    /**
     * Checks whether a number can be placed in the given node.
     *
     * @param actual the node where the number is being tested
     * @param number the number to validate
     * @param root the root node used to traverse the tree
     * @return {@code true} if the number is valid, otherwise {@code false}
     */

    public boolean isValid(node actual, int number, node root){
        return isValidHelper(actual,number,root);
    }
    /**
     * Recursively validates a number against rows, columns, and quadrants.
     *
     * @param actual the node where the number is being tested
     * @param number the number to validate
     * @param path the current node used during traversal
     * @return {@code true} if the number does not violate Sudoku rules,
     * otherwise {@code false}
     */
    private boolean isValidHelper(node actual, int number,node path){
        if(path.row != -1 && path != actual && path.getData() != null && path.getData() == number){
                if(path.row == actual.row) return false;
                if(path.column == actual.column) return false;

                int quadrantActual = (actual.row / 2) * 2 + (actual.column / 3);
                int quadrantPath = (path.row / 2) * 2 + (path.column / 3);
                if(quadrantActual == quadrantPath) return false;
            }

        for(node son : path.children){
            if(!isValid(actual, number, son)) return false;
        }
        return true;
    }
    /**
     * Selects the numbers that will be visible to the player.
     * <p>
     * For each quadrant, two nodes are randomly selected as visible,
     * while the remaining nodes are hidden.
     * </p>
     */
    public void generateVisibleNumbers() {
        List<List<node>> quadrants = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            quadrants.add(new ArrayList<>());
        }
        groupNodesByQuadrant(myTree.root, quadrants);
        for (List<node> quadrantNodes : quadrants) {
            Collections.shuffle(quadrantNodes);
            quadrantNodes.get(0).isItVisible = true;
            quadrantNodes.get(1).isItVisible = true;
            for (int i = 2; i < quadrantNodes.size(); i++) {
                quadrantNodes.get(i).isItVisible = false;
            }
        }
    }
    /**
     * Returns the Sudoku tree managed by this class.
     *
     * @return the Sudoku tree
     */

    @Override
    public tree getMyTree() {
        return null;
    }
    /**
     * Groups Sudoku nodes by their corresponding 2x3 quadrant.
     *
     * @param actual the current node being processed
     * @param quadrants the list where nodes are grouped by quadrant
     */

    private void groupNodesByQuadrant(node actual, List<List<node>> quadrants) {
        if (actual == null) {
            return;
        }
        if (actual.row != -1) {
            int quadrantIndex = (actual.row / 2) * 2 + (actual.column / 3);
            quadrants.get(quadrantIndex).add(actual);
        }
        for (node son : actual.children) {
            groupNodesByQuadrant(son, quadrants);
        }
    }
}