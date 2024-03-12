package Clustering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DataClustering<T> {
    private final ArrayList<Cluster<T>> clusters;

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
        for (Cluster<T> cluster : clusters) {
            for (int j = 0; j < cluster.size(); j++) {
                elements.add(cluster.getItem(j));
            }
        }
        return elements;
    }

    public int numberOfElements(){
        int count = 0;
        for (Cluster<T> cluster : clusters) {
            count += cluster.size();
        }
        return count;
    }

    public HashMap<T, Cluster<T>> clusterMap(){
        HashMap<T, Cluster<T>> clusterMap = new HashMap<>();
        for (Cluster<T> cluster : clusters) {
            for (int j = 0; j < cluster.size(); j++) {
                T item = cluster.getItem(j);
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
        for (Cluster<T> cluster : clusters) {
            ArrayList<T> toBeRemoved = new ArrayList<>();
            for (int j = 0; j < cluster.size(); j++) {
                T item = cluster.getItem(j);
                if (!secondElements.contains(item)) {
                    toBeRemoved.add(item);
                }
            }
            cluster.removeAll(toBeRemoved);
            if (cluster.size() == 0) {
                clustersToBeRemoved.add(cluster);
            }
        }
        clusters.removeAll(clustersToBeRemoved);
    }
}
