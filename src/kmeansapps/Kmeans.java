/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kmeansapps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.ClusteringKMeans;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

/**
 *
 * @author Ima
 */
public class Kmeans {
    
    public static BufferedReader readDataFile(String filename) {
        BufferedReader inputReader = null;

        try {
                inputReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException ex) {
                System.err.println("File not found: " + filename);
        }

        return inputReader;
    }
    
    public void startCluster(String path){
        try {
            // TODO code application logic here
            SimpleKMeans kmeans = new SimpleKMeans();
            
            kmeans.setSeed(10);
            
            //important parameter to set: preserver order, number of cluster.
            kmeans.setPreserveInstancesOrder(true);
            kmeans.setNumClusters(6);
            
            BufferedReader datafile = readDataFile(path);
            Instances data = new Instances(datafile);
            
            
            kmeans.buildClusterer(data);
            
            // This array returns the cluster number (starting with 0) for each instance
            // The array has as many elements as the number of instances
            int[] assignments = kmeans.getAssignments();
            
            int i=0;
            for(int clusterNum : assignments) {
                System.out.printf("Instance %d -> Cluster %d \n", i, clusterNum);
                i++;
            }
        } catch (Exception ex) {
            Logger.getLogger(Kmeans.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
