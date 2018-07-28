package listClustering;

import util.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cluster{
    Map<Integer,Set<String>> lists;
    Map<Integer,Double> weights;
    Map<Integer,String> sites;

    public Map<String, Double> getFinalItems() {
        return finalItems;
    }

    public void setFinalItems(Map<String, Double> finalItems) {
        this.finalItems = finalItems;
    }

    Map<String,Double> finalItems;

   /* public Map<Integer, String> getItems() {
        return items;
    }*/

    public Map<String, Double> getItemWeight() {
        return itemWeight;
    }

    //Map<Integer,String> items;
    Map<String,Double> itemWeight;
    double s;

    public Map<Integer, Set<String>> getLists() {
        return lists;
    }

    public Map<Integer, Double> getWeights() {
        return weights;
    }

    public Map<Integer, String> getSites() {
        return sites;
    }

    public Map<String, Set<Integer>> getSiteLists() {
        return siteLists;
    }

    Map<String,Set<Integer>> siteLists;
    Integer centerKey;

    public Cluster(Integer centerKey)
    {
        this.centerKey=centerKey;
        lists=new HashMap<>();
        weights=new HashMap<>();
        sites=new HashMap<>();
        siteLists=new HashMap<>();
        //items=new HashMap<>();
        itemWeight=new HashMap<>();
    }


    public void addList(String identities, Data data)
    {
        int qId=Integer.parseInt(identities.split("\\|")[0]);
        int key=Integer.parseInt(identities.split("\\|")[1]);
        //System.out.println(qId+"."+key);
        try {
            weights.put(key, data.getSl(qId).get(key));
            lists.put(key, data.getList(qId).get(key));
            sites.put(key, data.getSite(qId).get(key));
        }catch (NullPointerException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public Set<String> getCenterList()
    {
        return lists.get(centerKey);
    }

    public int getSitesCount()
    {
        Set<String> siteList=new HashSet<>();
        siteList.addAll(sites.values());
        return siteList.size();
    }

    public void parseSiteLists()
    {
        for(Integer key:sites.keySet())
        {
            String site=sites.get(key);
            if(siteLists.containsKey(site))
                siteLists.get(site).add(key);
            else
            {
                siteLists.put(site,new HashSet<>());
                siteLists.get(site).add(key);
            }
        }
    }

    public Integer getCenterKey() {
        return centerKey;
    }

    public double getS() {
        return s;
    }

    public void setS(double s) {
        this.s = s;
    }

    public void parseItems()
    {
        int index=0;
        for (Set<String> set:lists.values())
        {
            for (String item:set)
            {
                itemWeight.put(item,null);
                index++;
            }
        }
    }
}
