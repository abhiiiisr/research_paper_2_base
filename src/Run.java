import facetRanking.Facet;
import itemRanking.Item;
import listClustering.Clusterer;
import listWeighting.Slist;
import util.Data;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Run {

    public Run() {
    }

    public static void main(String nvn[]) throws IOException, StringIndexOutOfBoundsException {
        System.out.println("Started;");
        long start = System.nanoTime();
        //DataPreprocess.combineAll(new File("/run/media/rajnvn/RAM/OUTPUT/extracts"));
        //System.setOut(System.out);
        //Scanner scanner = new Scanner(System.in);
        //boolean b = Pattern.compile("<Entity.*>").matcher(scanner.nextLine()).find();
        //Trail.parse(new File("/run/media/rajnvn/RAM/FACET/1.cleanedxml"),new File("/run/media/rajnvn/RAM/FACET/2.extracts/"));
        //System.setOut(System.out);
        //Trail.parseList(new File("/run/media/rajnvn/RAM/FACET/2.extracts/"),"/run/media/rajnvn/RAM/FACET/3.combined");
        //for outset
        /*String devider=System.getProperty("file.separator");
        for(File query:new File("inset").listFiles()) {
            System.out.println(query.getAbsolutePath() + devider);
            new File("outset" + devider + query.getName()).mkdir();
            for (File file : query.listFiles()) {
                System.out.println(file.getAbsolutePath());
                String outname = "outset" + devider + query.getName() + devider + file.getName();
                if (outname.equals("outset\\312\\64.txt")) {
                    continue;
                }
                DataPreprocess.postProcess(file, outname);
            }
        }*/
        //System.out.println((double)512/16653);
        //System.exit(0);
        //System.setOut(new PrintStream("out.txt"));
        //DataPreprocess.parseRepeat(new File("G:\\FACET\\2.extracts\\16\\listFile.txt"));
        //System.exit(0);
        //System.setOut(new PrintStream("outp.txt"));
        Data data = Data.parse(new File("outset"));
        System.out.println("parsing done!");
        Slist.calculateSl(data);
        //Trail.parseSites("G:\\FACET\\2.extracts");
        //Trail.correctListsfor2("G:\\FACET\\2.extracts");+-----
        Clusterer.formClusters(data);
        Facet.rankFacet(data);
        //System.setOut(new PrintStream("values.txt"));
        Item.rankItems(data);
        int count=0;
        for(Integer qId:data.getQueryClusters().keySet())
        {
            count+=data.getQueryClusters().get(qId).size();
        }
        System.out.println("no of clusters in total : "+count);
        Evaluator.finalize(data);
        System.out.println("Time taken for combineAll() : " + (double) (System.nanoTime() - start) / 1000000000);
    }
}