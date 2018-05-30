package Clustering;

public interface SimilarityMeasure<T> {
    double computeSimilarity(DataClustering<T> dataClustering1, DataClustering<T> dataClustering2);
}
