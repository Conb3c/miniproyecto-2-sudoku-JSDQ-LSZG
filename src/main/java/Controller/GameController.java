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

public class GameController {
    @FXML private GridPane sudokuGrid;

    private dataOfTree data;
    private HashMap<String, TextField> cellMap = new HashMap<>();
    private TextField selectedCell = null;
    private int selectedRow = -1, selectedCol = -1;

    @FXML
    public void initialize() {
        data = new dataOfTree();
        loadBoard();
    }

    private void loadBoard(){
        node current = data.myTree.getRoot();
        while(!current.getChildren().isEmpty()){
            current = current.getChildren().get(0);

            String key = current.getRow() + "_" + current.getColumn();
            TextField tf = (TextField) sudokuGrid.lookup("#cell_" + key);

            if (tf != null){
                cellMap.put(key, tf);

                if (current.getIsItVisible()){
                    tf.setText(String.valueOf(current.getData()));
                    tf.setEditable(false);
                } else {
                    tf.setText("");
                    tf.setEditable(true);

                    tf.setOnKeyReleased(this::onCellKeyReleased);

                    int r = current.getRow();
                    int c = current.getColumn();
                    tf.focusedProperty().addListener((observable, oldValue, newValue) ->{
                        if (newValue){
                            selectedCell = tf;
                            selectedRow = r;
                            selectedCol = c;
                        }
                    });
                }
            }
        }
    }

    @FXML
    public void onNumberClicked(ActionEvent actionEvent) {
        if (selectedCell == null || !selectedCell.isEditable()) return;
        Button btn = (Button) actionEvent.getSource();
        String number = btn.getText();
        selectedCell.setText(number);
        validateCell(selectedRow, selectedCol, Integer.parseInt(number));
    }

    @FXML
    public void onNewGameClicked(ActionEvent actionEvent) {
        onResetClicked(actionEvent);
    }

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

    @FXML
    public void onResetClicked(ActionEvent actionEvent) {
        data = new dataOfTree();
        cellMap.clear();
        selectedCell = null;
        selectedRow = -1;
        selectedCol = -1;
        loadBoard();
    }

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

    private node searchNode(node current, int row, int col){
        if (current == null) return null;
        if (current.getRow() == row && current.getColumn() == col) return current;
        for (node child : current.getChildren()){
            node found = searchNode(child, row, col);
            if (found != null) return found;
        }
        return null;
    }

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
