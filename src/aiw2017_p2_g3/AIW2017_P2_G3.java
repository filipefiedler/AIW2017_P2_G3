/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiw2017_p2_g3;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import readingrss.ReadingRSS;
import utils.SimpleHTMLExtractor;
import callingSUMMA.CallSUMMAGapp;
import creatingHTML.creatingHTML;

/**
 *
 * @author u146236
 */
public class AIW2017_P2_G3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        List<List<String>> titlesAndLinksRSS;
        titlesAndLinksRSS = ReadingRSS.readBbcRss();
        //System.out.println(titlesAndLinksRSS);
        List<String> newsTitles = titlesAndLinksRSS.get(0);
        List<String> newsLinks = titlesAndLinksRSS.get(1);
        
        List<String> newsTextAndImageLink;
        String newsText;
        String imageLink;
        List<String> allNewsTexts;
        List<String> allImageLinks;
        List<String> allNewsLinks;
        List<String> allNewsTitles;
        allNewsTexts = new ArrayList<String>();
        allNewsLinks = new ArrayList<String>();
        allNewsTitles = new ArrayList<String>();
        allImageLinks = new ArrayList<String>();
        int i = 0;

        
        for(ListIterator<String> iter = newsLinks.listIterator(); iter.hasNext(); ){
            String link = iter.next();
            //System.out.println(link);
            System.out.println(link);
            newsTextAndImageLink = SimpleHTMLExtractor.extractFromBbc(link);
            newsText = (String) newsTextAndImageLink.get(0);
            imageLink = newsTextAndImageLink.get(1);
            if (!("".equals(newsText)) || (!("".equals(imageLink)))) {
                //System.out.println("Got!");
                allNewsTitles.add(newsTitles.get(i));
                allNewsLinks.add(newsLinks.get(i));
                allNewsTexts.add(newsText);
                allImageLinks.add(imageLink);
            }
            i++;
        }
        
//        for(int x = 0; x<allNewsTexts.size(); x++){
//            System.out.println(allNewsTexts.get(x));
//        }
        
//        System.out.println("Porra!");
        
        List<String> allNewsSummaries = CallSUMMAGapp.summarize(allNewsTexts);
        creatingHTML.createHtmlFile(allNewsTitles, allNewsSummaries, allNewsLinks);
        
        
        
    }
    
}
