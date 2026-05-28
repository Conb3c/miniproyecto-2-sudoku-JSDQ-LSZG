package Model;

import java.util.ArrayList;

public interface Inode {
    void addChild(node child);

    Integer getData();

    void setData(Integer data);

    boolean getIsItVisible();

    void setIsItVisible(boolean isItVisible);

    ArrayList<node> getChildren();

    int getRow();

    int getColumn();

    int getUserValue();

    void setUserValue(int userValue);
}