package Model;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        dataOfTree myData = new dataOfTree();
        myData.myTree.printTree(myData.myTree.root);
    }
}
