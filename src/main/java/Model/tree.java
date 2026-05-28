package Model;

/**
 * Represents the tree structure used to store the Sudoku board.
 * <p>
 * The tree contains a root node and a linear chain of child nodes,
 * where each node represents a cell in the 6x6 Sudoku board.
 * </p>
 */

public class tree {
    node root;
    /**
     * Creates an empty Sudoku tree with a root node.
     * <p>
     * The root node does not represent a real board cell and uses
     * row and column values of {@code -1}.
     * </p>
     */

    public tree(){
        this.root = new node(null, -1, -1);
    }
    /**
     * Fills the tree with nodes representing each cell of a 6x6 Sudoku board.
     * <p>
     * Nodes are created from row {@code 0} to {@code 5} and from column
     * {@code 0} to {@code 5}.
     * </p>
     */

    public void fillTree(){
        node actual = root;
        for(int row = 0; row < 6; row++){
            for(int column = 0; column < 6; column++){
                node newSon = new node(null,row,column);
                actual.addChild(newSon);
                actual = newSon;
            }
        }
    }
    /**
     * Returns the root node of the tree.
     *
     * @return the root node
     */

    public node getRoot() {
        return root;
    }

    /* Just for test.
    public void printTree(node actual){
        if(actual.row != -1){
            System.out.println("Node: [" + actual.row + "," + actual.column + "] " +
                    "data=" + actual.getData() + " | visible=" + actual.isItVisible);
        }
        for(node son : actual.children){
            printTree(son);
        }
    }

    public node getRoot() {
        return root;
    }*/
}