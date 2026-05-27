package Model;

import java.util.ArrayList;

public class node {
    Integer data;
    int row;
    int column;
    int userValue;
    boolean isItVisible;
    node father;
    ArrayList<node> children;

    public node(Integer data) {
        this.data = data;
        this.row = 0;
        this.column = 0;
        this.userValue = 0;
        this.isItVisible = true;
        this.father = null;
        this.children = new ArrayList<>();
    }

    public node(Integer data, int row, int column){
        this.data = data;
        this.row = row;
        this.column = column;
        this.userValue = 0;
        this.isItVisible = true;
        this.father = null;
        this.children = new ArrayList<>();
    }

    public void addChild(node child){
        children.add(child);
        child.father = this;
    }
}
