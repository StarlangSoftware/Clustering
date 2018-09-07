package Clustering;

import java.util.ArrayList;

public class Cluster<T> {
    private ArrayList<T> elements;

    public Cluster(){
        elements = new ArrayList<>();
    }

    public void addItem(T item){
        elements.add(item);
    }

    public int size(){
        return elements.size();
    }

    public T getItem(int index){
        return elements.get(index);
    }

    public void removeAll(ArrayList<T> toBeRemoved){
        elements.removeAll(toBeRemoved);
    }
}
