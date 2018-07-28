package listClustering;

import java.util.Set;

public class Distance {
    public static double getDistance(Set<String> l1, Set<String> l2)
    {
        //System.out.println("get dl for("+l1+","+l2+')');
        double answer=1;
        double itemsCount=0;
        for(String item:l1)
            if(l2.contains(item))
                itemsCount++;
        answer-=itemsCount/Integer.min(l1.size(),l2.size());
        return answer;
    }

    public static double getDistance(Cluster cluster1, Cluster cluster2)
    {
        double answer=0;
        /*double ans=0;
        for(Set<String> list1:cluster1)
        {
            for (Set<String> list2:cluster2)
            {
                ans= getDistance(list1,list2);
                if(ans>answer)
                    answer=ans;
            }
        }*/
        return answer;
    }
}
