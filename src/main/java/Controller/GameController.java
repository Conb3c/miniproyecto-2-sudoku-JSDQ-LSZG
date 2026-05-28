package Controller;

import Model.dataOfTree;
import Model.node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.util.HashMap;

/**
 * Controller class for the Sudoku game view.
 * <p>
 * This class manages the interaction between the JavaFX interface and
 * the Sudoku data model. It builds the board, handles user input,
 * validates cells, provides help, and starts new games.
 * </p>
 */
public class GameController {
    @FXML private GridPane sudokuGrid;

    private dataOfTree data;
    private HashMap<String, TextField> cellMap = new HashMap<>();
    private TextField selectedCell = null;
    private int selectedRow = -1, selectedCol = -1;

    /**
     * Initializes the controller after the FXML file has been loaded.
     * <p>
     * A new Sudoku data structure is created and the board is rendered.
     * </p>
     */
    @FXML
    public void initialize() {
        data = new dataOfTree();
        buildBoard();
    }

    /**
     * Builds the visual Sudoku board using the generated tree data.
     * <p>
     * Each node is represented as a {@link TextField}. Visible nodes are locked,
     * while hidden nodes remain editable for user input.
     * </p>
     */
    private void buildBoard(){
        sudokuGrid.getChildren().clear();
        cellMap.clear();

        node current = data.myTree.getRoot();
        while(!current.getChildren().isEmpty()){
            current = current.getChildren().get(0);

            int r = current.getRow();
            int c = current.getColumn();
            String key = r + "_" + c;

            TextField tf = new TextField();
            tf.setId("cell_" + key);
            tf.setPrefWidth(60);
            tf.setPrefHeight(60);
            tf.setAlignment(javafx.geometry.Pos.CENTER);

            String borderRight = (c == 2) ? "2px" : "1px";
            String borderBottom = (r == 1 || r == 3) ? "2px" : "1px";
            tf.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;" +
                    "-fx-border-color: #AAAAAA; -fx-border-width: 0 " + borderRight + " " + borderBottom + " 0;" +
                    "-fx-background-color: white;"
            );

            if (current.getIsItVisible()){
                tf.setText(String.valueOf(current.getData()));
                tf.setEditable(false);
                tf.setStyle(tf.getStyle() + "-fx-text-fill: #2C5F8A;");
            } else {
                tf.setText("");
                tf.setEditable(true);

                tf.setOnKeyReleased(this::onCellKeyReleased);

                final int finalR = r;
                final int finalC = c;
                tf.focusedProperty().addListener((observable, oldValue, newValue) ->{
                    if (newValue){
                        selectedCell = tf;
                        selectedRow = finalR;
                        selectedCol = finalC;
                    }
                });
            }
            cellMap.put(key,tf);
            GridPane.setRowIndex(tf, r);
            GridPane.setColumnIndex(tf, c);
            sudokuGrid.getChildren().add(tf);
        }
    }

    /**
     * Handles the number button click event.
     * <p>
     * The selected editable cell receives the clicked number and is validated.
     * </p>
     *
     * @param actionEvent the button click event
     */
    @FXML
    public void onNumberClicked(ActionEvent actionEvent) {
        if (selectedCell == null || !selectedCell.isEditable()) return;
        Button btn = (Button) actionEvent.getSource();
        String number = btn.getText();
        selectedCell.setText(number);
        validateCell(selectedRow, selectedCol, Integer.parseInt(number));
    }

    /**
     * Starts a new Sudoku game.
     *
     * @param actionEvent the button click event
     */
    @FXML
    public void onNewGameClicked(ActionEvent actionEvent) {
        onResetClicked(actionEvent);
    }

    /**
     * Reveals one incorrect or empty hidden cell as a hint.
     *
     * @param actionEvent the button click event
     */
    @FXML
    public void onHelpClicked(ActionEvent actionEvent) {
        node current = data.myTree.getRoot();
        while (!current.getChildren().isEmpty()){
            current = current.getChildren().get(0);
            if (!current.getIsItVisible()
                    && current.getUserValue() != current.getData()) {
                current.setUserValue(current.getData());
                TextField tf = cellMap.get(current.getRow() + "_" + current.getColumn());

                if (tf != null){
                    tf.setText(String.valueOf(current.getData()));
                    tf.setStyle(tf.getStyle() + "-fx-background-color: #D4EDDA; -fx-text-fill: #155724;");
                }
                return;
            }
        }
    }

    /**
     * Resets the current game by creating a new Sudoku board.
     *
     * @param actionEvent the button click event
     */
    @FXML
    public void onResetClicked(ActionEvent actionEvent) {
        data = new dataOfTree();
        cellMap.clear();
        selectedCell = null;
        selectedRow = -1;
        selectedCol = -1;
        buildBoard();
    }

    /**
     * Handles keyboard input inside editable Sudoku cells.
     * <p>
     * Only numbers from 1 to 6 are accepted.
     * </p>
     *
     * @param keyEvent the key release event
     */
    @FXML
    public void onCellKeyReleased(KeyEvent keyEvent) {
        TextField tf = (TextField) keyEvent.getSource();
        String input = tf.getText().trim();

        if (!input.matches("[1-6]")){
            tf.setText("");
            return;
        }

        int [] pos = getPosition(tf);
        if (pos != null){
            validateCell(pos[0], pos[1], Integer.parseInt(input));
        }
    }

    /**
     * Validates the user value entered in a specific cell.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @param userValue the value entered by the user
     */
    private void validateCell(int row, int col, int userValue){
        node target = searchNode(data.myTree.getRoot(), row, col);
        if (target == null) return;

        target.setUserValue(userValue);
        TextField tf = cellMap.get(row + "_" + col);
        if (tf == null) return;

        if (target.getData() == userValue){
            tf.setStyle(tf.getStyle() + "-fx-background-color: #D4EDDA; -fx-text-fill: #155724;");

        } else {
            tf.setStyle(tf.getStyle() + "-fx-background-color: #FDECEA; -fx-text-fill: #E05252;");
        }
    }

    /**
     * Searches for a node by row and column.
     *
     * @param current the current node being searched
     * @param row the target row
     * @param col the target column
     * @return the matching node, or {@code null} if no node is found
     */
    private node searchNode(node current, int row, int col){
        if (current == null) return null;
        if (current.getRow() == row && current.getColumn() == col) return current;
        for (node child : current.getChildren()){
            node found = searchNode(child, row, col);
            if (found != null) return found;
        }
        return null;
    }

    /**
     * Gets the board position associated with a specific text field.
     *
     * @param tf the text field to search for
     * @return an integer array containing row and column, or {@code null} if not found
     */
    private int [] getPosition(TextField tf){
        for (java.util.Map.Entry<String, TextField> e : cellMap.entrySet()){
            if (e.getValue() == tf){
                String[] p = e.getKey().split("_");
                return new int[]{Integer.parseInt(p[0]), Integer.parseInt(p[1])};
            }
        }
        return null;
    }
}