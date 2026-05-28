package Model;

import java.util.ArrayList;
/**
 * Represents a node in the Sudoku tree structure.
 * <p>
 * Each node represents a cell of the Sudoku board and stores its solution value,
 * row, column, user-entered value, visibility state, parent node, and children.
 * </p>
 */

public class node implements Inode{
    Integer data;
    int row;
    int column;
    int userValue;
    boolean isItVisible;
    node father;
    ArrayList<node> children;
    /**
     * Creates a node with the specified data value.
     * <p>
     * The row and column are initialized to zero, the node is visible by default,
     * and the children list is created empty.
     * </p>
     *
     * @param data the value stored in the node
     */

    public node(Integer data) {
        this.data = data;
        this.row = 0;
        this.column = 0;
        this.userValue = 0;
        this.isItVisible = true;
        this.father = null;
        this.children = new ArrayList<>();
    }
    /**
     * Creates a node with a specific value and board position.
     *
     * @param data the value stored in the node
     * @param row the row position of the node
     * @param column the column position of the node
     */

    public node(Integer data, int row, int column){
        this.data = data;
        this.row = row;
        this.column = column;
        this.userValue = 0;
        this.isItVisible = true;
        this.father = null;
        this.children = new ArrayList<>();
    }
    /**
     * Adds a child node and assigns the current node as its parent.
     *
     * @param child the node to add as a child
     */

    public void addChild(node child){
        children.add(child);
        child.father = this;
    }
    /**
     * Returns the solution value stored in the node.
     *
     * @return the node value, or {@code null} if it has not been assigned
     */

    public Integer getData() {
        return data;
    }
    /**
     * Sets the solution value stored in the node.
     *
     * @param data the value to assign
     */

    public void setData(Integer data) {
        this.data = data;
    }
    /**
     * Indicates whether the value of this node is visible to the player.
     *
     * @return {@code true} if the value is visible, otherwise {@code false}
     */

    public boolean getIsItVisible() {
        return isItVisible;
    }
    /**
     * Sets the visibility state of the node value.
     *
     * @param isItVisible {@code true} if the value should be visible,
     * otherwise {@code false}
     */

    public void setIsItVisible(boolean isItVisible) {
        this.isItVisible = isItVisible;
    }
    /**
     * Returns the list of child nodes.
     *
     * @return the children of this node
     */

    public ArrayList<node> getChildren() { return children; }
    /**
     * Returns the row position of this node.
     *
     * @return the row index
     */

    public int getRow() { return row; }
    /**
     * Returns the column position of this node.
     *
     * @return the column index
     */

    public int getColumn() { return column; }
    /**
     * Returns the value entered by the user for this node.
     *
     * @return the user-entered value
     */

    public int getUserValue() { return userValue; }
    /**
     * Sets the value entered by the user for this node.
     *
     * @param userValue the user-entered value
     */

    public void setUserValue(int userValue) { this.userValue = userValue; }
}
