/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kmeansapps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

/**
 *
 * @author Ima
 */
public class Kmeans {
    
    public static BufferedReader readDataFile(String filename) {
        BufferedReader inReader = null;

        try {
                inReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException ex) {
                System.err.println("File you are choose is not found: " + filename);
        }
        return inReader;
    }
    
    public void startCluster(String path, int numOfCluster, JTable tableResult,JFrame apps){
        try {
            // TODO code application logic here
            SimpleKMeans kmeans = new SimpleKMeans();
            String[] columnNames = new String[numOfCluster];
            
            
            
            kmeans.setSeed(10);
            kmeans.setPreserveInstancesOrder(true);
            kmeans.setNumClusters(numOfCluster);
            
            BufferedReader datafile = readDataFile(path);
            Instances data = new Instances(datafile);
            
            kmeans.buildClusterer(data);
            double SSE = kmeans.getSquaredError();
            // This array returns the cluster number (starting with 0) for each instance
            // The array has as many elements as the number of instances
            int[] assignments = kmeans.getAssignments();
            
//            //setting columNames
//            for (int i = 0; i < numOfCluster; i++) {
//                columnNames[i] = "Cluster "+i+"";
//            }
            
            // bikin arraylist 2 dimensi untuk menampung instance masuk ke cluster berapa.
            ArrayList<ArrayList<String>> listOfCluster = new ArrayList<ArrayList<String>>();
            ArrayList<String> listMemberOfCluster;
            
            //tambahkan list cluster
            for (int i = 0; i < numOfCluster; i++) {
                listMemberOfCluster = new ArrayList<>();
                listOfCluster.add(listMemberOfCluster);
            }
            //tambahkan anggota list ke cluster
            int j=0;
            for(int clusterNum : assignments) {
                listOfCluster.get(clusterNum).add(j+"");
                j++;
            }
            
            for(int i=0; i<listOfCluster.size();i++){
                System.out.print("Cluster - "+i+" -> ");
                for (String listMemberOfCluster1 : listOfCluster.get(i)) {
                    System.out.print(listMemberOfCluster1 + " ");
                }
                System.out.println("");
            }
            
//            int i=0;
//            for(int clusterNum : assignments) {
//                System.out.printf("Instance %d -> Cluster %d \n", i, clusterNum);
//                i++;
//                System.out.println(SSE);
//            }
            
            
//            //output to table
//            tableResult.setModel(new DefaultTableModel(
//            new Object[][]{
//            },
//            columnNames));
//            apps.setVisible(true);
//            
//            int j=0;
//            DefaultTableModel model = (DefaultTableModel) tableResult.getModel();
//            for(int clusterNum : assignments) {
//                if (clusterNum==0){
//                    model.addRow(new Object[]{j, "", "", "", "", ""});
//                }
//                else if (clusterNum==1){
//                    model.addRow(new Object[]{"", j, "", "", "", ""});
//                }
//                else if (clusterNum==2){
//                    model.addRow(new Object[]{"", "", j, "", "", ""});
//                }
//                else if (clusterNum==3){
//                    model.addRow(new Object[]{"", "", "", j, "", ""});
//                }
//                else if (clusterNum==4){
//                    model.addRow(new Object[]{"", "", "", "", j, ""});
//                }
//                else if (clusterNum==5){
//                    model.addRow(new Object[]{"", "", "", "", "", j});
//                }
//                
//                j++;
//            }
        } catch (Exception ex) {
            Logger.getLogger(Kmeans.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startCluster(String path, int numOfCluster, JTextArea textarea) {
        try {
            // TODO code application logic here
            SimpleKMeans kmeans = new SimpleKMeans();
            String[] columnNames = new String[numOfCluster];
            kmeans.setSeed(10);
            kmeans.setPreserveInstancesOrder(true);
            kmeans.setNumClusters(numOfCluster);
            
            BufferedReader datafile = readDataFile(path);
            Instances data = new Instances(datafile);
            
            kmeans.buildClusterer(data);
            double SSE = kmeans.getSquaredError();
            // This array returns the cluster number (starting with 0) for each instance
            // The array has as many elements as the number of instances
            int[] assignments = kmeans.getAssignments();
            
            // bikin arraylist 2 dimensi untuk menampung instance masuk ke cluster berapa.
            ArrayList<ArrayList<String>> listOfCluster = new ArrayList<ArrayList<String>>();
            ArrayList<String> listMemberOfCluster;
            
            //tambahkan list cluster
            for (int i = 0; i < numOfCluster; i++) {
                listMemberOfCluster = new ArrayList<>();
                listOfCluster.add(listMemberOfCluster);
            }
            //tambahkan anggota list ke cluster
            int j=0;
            for(int clusterNum : assignments) {
                listOfCluster.get(clusterNum).add(j+"");
                j++;
            }
            textarea.setText("");
            String result="";
            for(int i=0; i<listOfCluster.size();i++){
                result=result+("Cluster - "+i+" ==> ");
                for (String listMemberOfCluster1 : listOfCluster.get(i)) {
                    result=result+(listMemberOfCluster1 + " ");
                }
                result=result+("\n");
            }
            result=result+("\nSSE : ")+kmeans.getSquaredError();
            textarea.setText(result);
        } catch (Exception ex) {
            Logger.getLogger(Kmeans.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
