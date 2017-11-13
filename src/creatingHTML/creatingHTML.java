/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creatingHTML;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
public class creatingHTML {
    
    public static void createHtmlFile(List<String> titles, List<String> summaries, List<String> links){
        PrintWriter out;
        BufferedReader basePage;
        try {
            out = new PrintWriter(new FileWriter(new File("./site/index.html")));
            basePage = new BufferedReader(new FileReader("./site/base.html"));
            
            String line = basePage.readLine();
            
            while (line != null) {
                out.println(line);
                if (line.equals("        <!-- Insert summaries -->")){
                    //System.out.println(line);
                    
                    for(int i=0;i<titles.size();i++){
                        out.println("        <div class=\"news-summary\">\n");
                        out.println("          <div class=\"news-head\">"+ titles.get(i) +"</div>\n");
                        out.println("          <div class=\"news-body\">\n");
                        out.println("            <p class=\"summary-content\">\n" + summaries.get(i) +"\n");
                        out.println("            </p>\n");
                        out.println("            <p class=\"news-link\">\n");
                        out.println("              Click <a href=\""+ links.get(i) + "\">here</a> to access the complete text\n");
                        out.println("            </p>\n");
                        out.println("          </div>\n");
                        out.println("        </div>");

                        out.flush();
                    }
                }
                out.flush();
                line = basePage.readLine();
            }
            
            out.close();
            basePage.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String [] args){
        List<String> newsTitles = new ArrayList<String>();
        List<String> newsLinks = new ArrayList<String>();
        List<String> newsSummaries = new ArrayList<String>();

        newsTitles.add("Parliament to get binding vote on final Brexit deal");
        newsLinks.add("http://www.bbc.co.uk/news/uk-politics-41975277");
        newsSummaries.add("Test 1");

        newsTitles.add("Iran-Iraq border earthquake is deadliest of 2017");
        newsLinks.add("http://www.bbc.co.uk/news/world-middle-east-41972338");
        newsSummaries.add("Test 2");
        
        createHtmlFile(newsTitles, newsSummaries, newsLinks);
    }
    
    

    
}
