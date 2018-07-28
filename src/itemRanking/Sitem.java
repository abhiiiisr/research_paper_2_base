package itemRanking;

import listClustering.Cluster;

import java.util.HashSet;
import java.util.Set;

public class Sitem {

    public static double calculateS(String item, Cluster cluster)
    {
        double answer=0;
        for(String site:cluster.getSiteLists().keySet())
        {
            double avg=avg(cluster,item,site);
            if(avg!=0.0)
                answer+= 1 / Math.sqrt(avg);
        }
        return answer;
    }

    public static double avg(Cluster cluster,String item,String site)
    {
        double answer=0;
        Set<Integer> lists=getLists(cluster,item,site);
        if(!lists.isEmpty())
            answer=1/lists.size();
        for(Integer key:lists)
        {
            answer += cluster.getWeights().get(key);
        }
        return answer;
    }

    public static Set<Integer> getLists(Cluster cluster,String item,String site)
    {
        Set<Integer> answer=new HashSet<>();

        for(Integer list:cluster.getSiteLists().get(site))
        {
            if(cluster.getLists().get(list).contains(item))
            {
                answer.add(list);
            }
        }

        return answer;
    }
}
