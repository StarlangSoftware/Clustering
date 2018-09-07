package Clustering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DataClustering<T> {
    private ArrayList<Cluster<T>> clusters;

    public DataClustering(){
        clusters = new ArrayList<>();
    }

    public void addCluster(Cluster<T> cluster){
        clusters.add(cluster);
    }

    public int size(){
        return clusters.size();
    }

    public Cluster<T> getCluster(int index){
        return clusters.get(index);
    }

    public HashSet<T> elements(){
        HashSet<T> elements = new HashSet<>();
        for (int i = 0; i < clusters.size(); i++){
            for (int j = 0; j < clusters.get(i).size(); j++){
                elements.add(clusters.get(i).getItem(j));
            }
        }
        return elements;
    }

    public int numberOfElements(){
        int count = 0;
        for (int i = 0; i < clusters.size(); i++){
            count += clusters.get(i).size();
        }
        return count;
    }

    public HashMap<T, Cluster<T>> clusterMap(){
        HashMap<T, Cluster<T>> clusterMap = new HashMap<>();
        for (int i = 0; i < clusters.size(); i++){
            Cluster<T> cluster = clusters.get(i);
            for (int j = 0; j < clusters.get(i).size(); j++){
                T item = clusters.get(i).getItem(j);
                clusterMap.put(item, cluster);
            }
        }
        return clusterMap;
    }

    public boolean containsSameElements(DataClustering<T> secondClustering){
        HashSet<T> firstElements = elements();
        HashSet<T> secondElements = secondClustering.elements();
        return firstElements.containsAll(secondElements) && secondElements.containsAll(firstElements);
    }

    public void pruneToIntersection(DataClustering<T> secondClustering){
        HashSet<T> secondElements = secondClustering.elements();
        ArrayList<Cluster<T>> clustersToBeRemoved = new ArrayList<>();
        for (int i = 0; i < clusters.size(); i++){
            Cluster<T> cluster = clusters.get(i);
            ArrayList<T> toBeRemoved = new ArrayList<>();
            for (int j = 0; j < clusters.get(i).size(); j++){
                T item = clusters.get(i).getItem(j);
                if (!secondElements.contains(item)){
                    toBeRemoved.add(item);
                }
            }
            cluster.removeAll(toBeRemoved);
            if (cluster.size() == 0){
                clustersToBeRemoved.add(cluster);
            }
        }
        clusters.removeAll(clustersToBeRemoved);
    }
}
