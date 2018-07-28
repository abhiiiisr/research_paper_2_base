package facetRanking;

import listClustering.Cluster;
import util.Data;

public class Facet {

    public static void rankFacet(Data data) {
        for (int qId : data.getQueryClusters().keySet()) {
            for (Cluster cluster:data.getQueryClusters().get(qId)) {
                cluster.parseSiteLists();
                cluster.setS(UModel.updateScore(cluster));
            }
            //System.out.println("Highest value : " + Collections.max(list));
        }
    }
}
