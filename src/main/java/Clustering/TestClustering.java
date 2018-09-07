package Clustering;

import java.io.*;

public class TestClustering {

    public static DataClustering<String> readClusters(String fileName){
        DataClustering<String> dataClustering = new DataClustering<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF8"));
            String line = br.readLine();
            while (line != null){
                String[] words = line.split(",");
                Cluster<String> cluster = new Cluster<>();
                for (String word : words){
                    cluster.addItem(word.trim());
                }
                dataClustering.addCluster(cluster);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataClustering;
    }

    public static void main(String[] args){
        String[] fileNames = {"manual_single.txt", "balkanet_single.txt", "auto_single_comma.txt", "auto_single_right.txt", "auto_multi_comma.txt", "auto_multi_right.txt"};
        VariationOfInformation<String> variationOfInformation = new VariationOfInformation<>();
        DataClustering<String> dataClustering1 = null, dataClustering2 = null;
        for (int i = 0; i < 5; i++){
            dataClustering1 = readClusters(fileNames[i]);
            for (int j = i + 1; j < 6; j++){
                dataClustering2 = readClusters(fileNames[j]);
                System.out.println(fileNames[i] + " " + fileNames[j] + "->" + variationOfInformation.computeSimilarity(dataClustering1, dataClustering2));
            }
        }
    }
}
