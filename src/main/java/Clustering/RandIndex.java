package Clustering;

import java.util.HashMap;

public class RandIndex<T> implements SimilarityMeasure<T>{

    public double computeSimilarity(DataClustering<T> dataClustering1, DataClustering<T> dataClustering2) {
        if (!dataClustering1.containsSameElements(dataClustering2)){
            System.out.println("Data Clusterings do not have the same elements! Pruning!");
            dataClustering1.pruneToIntersection(dataClustering2);
            dataClustering2.pruneToIntersection(dataClustering1);
        }
        double a = 0, b, c = 0, d = 0, n;
        HashMap<T, Cluster<T>> clusterMap1 = dataClustering1.clusterMap();
        HashMap<T, Cluster<T>> clusterMap2 = dataClustering2.clusterMap();
        n = dataClustering1.numberOfElements();
        for (int i = 0; i < dataClustering1.size(); i++) {
            for (int j = 0; j < dataClustering1.getCluster(i).size(); j++){
                T firstItem = dataClustering1.getCluster(i).getItem(j);
                for (int k = j + 1; k < dataClustering1.getCluster(i).size(); k++){
                    T secondItem = dataClustering1.getCluster(i).getItem(k);
                    Cluster<T> cluster1 = clusterMap2.get(firstItem);
                    Cluster<T> cluster2 = clusterMap2.get(secondItem);
                    if (cluster1.equals(cluster2)){
                        a++;
                    } else {
                        c++;
                    }
                }
            }
        }
        for (int i = 0; i < dataClustering2.size(); i++) {
            for (int j = 0; j < dataClustering2.getCluster(i).size(); j++){
                T firstItem = dataClustering2.getCluster(i).getItem(j);
                for (int k = j + 1; k < dataClustering2.getCluster(i).size(); k++){
                    T secondItem = dataClustering2.getCluster(i).getItem(k);
                    Cluster<T> cluster1 = clusterMap1.get(firstItem);
                    Cluster<T> cluster2 = clusterMap1.get(secondItem);
                    if (!cluster1.equals(cluster2)){
                        d++;
                    }
                }
            }
        }
        b = (n * (n - 1)) / 2 - a - c - d;
        return (a + b + 0.0) / (a + b + c + d);
    }
}
