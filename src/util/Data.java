package util;

import facetRanking.Facet;
import listClustering.Cluster;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Data {
    private Map<Integer, Map<Integer, Set<String>>> queryR;
    private Map<Integer, Map<Integer, Set<String>>> queryLists;
    private Map<Integer, Map<Integer, Double>> querySl;
    private Map<Integer, List<Cluster>> queryClusters;
    private Map<Integer, Map<Integer, String>> querySites;

    public Map<Integer, List<Cluster>> getQueryClusters() {
        return queryClusters;
    }

    public Map<Integer, Map<Integer, Set<String>>> getQueryR() {
        return queryR;
    }

    public Map<Integer, Map<Integer, Set<String>>> getQueryLists() {
        return queryLists;
    }

    public Map<Integer, Map<Integer, Double>> getQuerySl() {
        return querySl;
    }

    public Data() {
        this.queryR = new HashMap<>();
        this.queryLists = new HashMap<>();
        this.querySl = new HashMap<>();
        this.queryClusters=new HashMap<>();
        this.querySites=new HashMap<>();
    }

    public static Data parse(File mainFolder) throws FileNotFoundException {
        Data data = new Data();
        for (File query : Objects.requireNonNull(mainFolder.listFiles())) {
            if (query.isDirectory()) {
                int qId = Integer.parseInt(query.getName());
                data.queryR.put(qId, new HashMap<>());
                data.parseList(new File(query.getAbsolutePath() + System.getProperty("file.separator") + "listFile.txt"), qId);
                System.out.println("parseDoc : "+qId);
                for (File document : Objects.requireNonNull(query.listFiles()))
                    data.parseDocument(document, qId);
            }
        }
        return data;
    }

    private void parseList(File listFile, int qId) throws FileNotFoundException {
        System.out.println("parseList : "+qId);
        Scanner listScanner = new Scanner(listFile);
        queryLists.put(qId, new HashMap<>());
        querySites.put(qId, new HashMap<>());
        int index=0;
        while (listScanner.hasNextLine()) {
            String line = listScanner.nextLine();
            String[] temp = line.split("\\|");
            String list = temp[0];
            String site="";
            try {
                site = temp[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
            if (site.contains("<"))
                continue;
            queryLists.get(qId).put(index, new HashSet<>());
            queryLists.get(qId).get(index).addAll(Arrays.asList(list.split("[,]")));
            querySites.get(qId).put(index, site);
            index++;
        }
        //System.out.println("parseList done for : " + qId + " and size : " + queryLists.get(qId).size());
    }

    private void parseDocument(File document, int qId) throws FileNotFoundException {
        String dName = document.getName();
        if (dName.equals("listFile.txt"))
            return;
        int docId = Integer.parseInt(dName.substring(0, dName.indexOf(".")));
        Scanner scanner = new Scanner(document);
        queryR.get(qId).put(docId, new HashSet<>());
        while (scanner.hasNextLine())
            queryR.get(qId).get(docId).addAll(Arrays.asList(scanner.nextLine().split("[,]")));
        //System.out.println("parseDoc() done for docId : " + qId + "." + docId + " and size : " + queryR.get(qId).get(docId).size());
    }

    public Map<Integer, Set<String>> getR(int qId) {
        return queryR.get(qId);
    }

    public Map<Integer, Set<String>> getList(int qId) {
        return queryLists.get(qId);
    }

    public Map<Integer, Double> getSl(int qId)
    {
        return querySl.get(qId);
    }

    public Map<Integer, String> getSite(int qId)
    {
        return querySites.get(qId);
    }
}
