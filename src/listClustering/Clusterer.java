package listClustering;

import util.Data;
import util.Sorter;

import java.util.*;

public class Clusterer {

    public static double diaMax=0.6, wC=2.0;


    public static void formClusters(Data data) {
        System.out.println("FORM CLUSTERS :->");
        for (int qId : data.getQueryLists().keySet()) {
            System.out.println("QId - "+qId);
            boolean created;
            List<Cluster> clusters = new ArrayList<>();
            Map<Integer, Double> remainingLists = Sorter.sortByValues(data.getQuerySl().get(qId));
            while (!remainingLists.isEmpty()) {
                created=false;
                System.out.println("ramaing count : "+remainingLists.size());
                Cluster cluster=null;
                List<Integer> rem=new ArrayList<>();
                for (Iterator<Integer> key = remainingLists.keySet().iterator(); key.hasNext(); ) {
                    int thisKey = key.next();
                    //System.out.println(qId+"|"+thisKey);
                    if (!created) {
                        cluster=new Cluster(thisKey);
                        cluster.addList(qId + "|" + thisKey, data);
                        created = true;
                        System.out.println("cluster initialize ");
                        continue;
                    }
                    Double dl = Distance.getDistance(data.getList(qId).get(cluster.getCenterKey()), data.getList(qId).get(thisKey));
                    //System.out.println("DL : "+dl);
                    if (dl <= diaMax) {
                        cluster.addList(qId + "|" + thisKey, data);
                        System.out.println("addList|"+remainingLists.get(thisKey));
                        rem.add(thisKey);
                    }
                }
                rem.add(cluster.getCenterKey());
                for(int remKey:rem)
                    remainingLists.remove(remKey);
                if (cluster.getSitesCount() >= wC)
                    clusters.add(cluster);
            }
            //write sites related code here...
            data.getQueryClusters().put(qId,clusters);
        }
    }
}
