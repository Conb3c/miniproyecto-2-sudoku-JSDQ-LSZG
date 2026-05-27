package Model;

public class tree {
    node root;

    public tree(){
        this.root = new node(null, -1, -1);
    }

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
    }
}