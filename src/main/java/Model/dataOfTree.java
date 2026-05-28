package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class dataOfTree {
    public tree myTree;

    public dataOfTree(){
        myTree = new tree();
        myTree.fillTree();
        generateSudoku(myTree.root);
        generateVisibleNumbers();
    }
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
    public boolean isValid(node actual, int number, node path){
        if(path.row != -1 && path != actual){
            if(path.getData() != null && path.getData() == number){
                if(path.row == actual.row) return false;
                if(path.column == actual.column) return false;

                int quadrantActual = (actual.row / 2) * 2 + (actual.column / 3);
                int quadrantPath = (path.row / 2) * 2 + (path.column / 3);
                if(quadrantActual == quadrantPath) return false;
            }
        }
        for(node son : path.children){
            if(!isValid(actual, number, son)) return false;
        }
        return true;
    }
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