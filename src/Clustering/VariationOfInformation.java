package Clustering;

import DataStructure.CounterHashMap;

import java.util.HashMap;

public class VariationOfInformation<T> implements SimilarityMeasure<T> {

    public double computeSimilarity(DataClustering<T> dataClustering1, DataClustering<T> dataClustering2) {
        if (!dataClustering1.containsSameElements(dataClustering2)){
            System.out.println("Data Clusterings do not have the same elements! Pruning!");
            dataClustering1.pruneToIntersection(dataClustering2);
            dataClustering2.pruneToIntersection(dataClustering1);
        }
        double n, pi, qj, rij, sum = 0.0;
        HashMap<T, Cluster<T>> clusterMap2 = dataClustering2.clusterMap();
        HashMap<Cluster<T>, CounterHashMap<Cluster<T>>> intersections = new HashMap<>();
        n = dataClustering1.numberOfElements();
        for (int i = 0; i < dataClustering1.size(); i++){
            Cluster<T> X = dataClustering1.getCluster(i);
            CounterHashMap<Cluster<T>> hashMap = new CounterHashMap<>();
            intersections.put(X, hashMap);
            for (int j = 0; j < X.size(); j++){
                T item = X.getItem(j);
                Cluster<T> Y = clusterMap2.get(item);
                hashMap.put(Y);
            }
        }
        for (int i = 0; i < dataClustering1.size(); i++){
            Cluster<T> X = dataClustering1.getCluster(i);
            pi = X.size() / n;
            for (Cluster<T> Y : intersections.get(X).keySet()){
                qj = Y.size() / n;
                rij = intersections.get(X).get(Y) / n;
                sum -= rij * (Math.log(rij / pi) + Math.log(rij / qj));
            }
        }
        return sum;
    }
}
