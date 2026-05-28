package Model;

public interface IdataOfTree {
    boolean generateSudoku(node actual);

    boolean isValid(node actual, int number, node root);

    void generateVisibleNumbers();

    tree getMyTree();
}