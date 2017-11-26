package utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import gate.Factory;
import gate.Gate;
import gate.util.GateException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author UPF
 */
public class SimpleHTMLExtractor {
    
    
    public static String extractFromElMundo(String url) throws IOException {
        String text="";
        Document doc;
        doc = (Document) Jsoup.connect(url).get();
        String title;
        System.out.println(doc.title());
        Elements els;
       
        els=doc.getElementsByTag("div");
        String txt;
        for(Element ele: els) {
             if(ele.hasAttr("itemprop")) {
                if(ele.attributes().get("itemprop").equals("articleBody")) {
                    txt=ele.text();
                    
                   text=text+ " " + txt;
                }
        }
            
        }
        return text;
    }
       
    public static ArrayList extractFromBbc(String url) throws IOException {
        String text="";
        Document doc;
        doc = (Document) Jsoup.connect(url).get();
        ArrayList<String> list=new ArrayList();
        
        //String title;
        Element storyBody;
        String image;
        Elements paragraphs;
        
        //title = doc.getElementsByClass("story-body__h1").first().text();
        storyBody=doc.getElementsByAttributeValue("property","articleBody").first();
        
        if (storyBody == null){
            list.add("");
            list.add("");
            return list;
        }
        
        Elements imageEl;
        
        imageEl = storyBody.getElementsByTag("img");
        if (imageEl == null){
            image = "";
        } else {
            try{
            image = imageEl.first().attributes().get("src");
            } catch (NullPointerException e) {
                image = "";
            }
        }
        
        paragraphs = storyBody.getElementsByTag("p");
        if (paragraphs == null){
            text = "";
        } else {
            String txt;        
            for(Element par: paragraphs) {
                if(par.hasText()) {
                    txt=par.text();
                    //System.out.println(txt);
                    text=text+ " " + txt;
                }    
            }
        }
        
        //list.add(title); 
        list.add(text); list.add(image);
        
        return list;
    }
    
    public static void main(String[] args) throws IOException  {
        
      //String text=extractFromElMundo("http://www.elmundo.es/internacional/2017/11/15/5a0c423b268e3eeb718b45ca.html");
      
      ArrayList list=extractFromBbc("http://www.bbc.com/news/science-environment-42059551");
      //ArrayList list=extractFromBbc("http://www.bbc.com/news/av/world-middle-east-42098201/inside-saudi-arabia-s-gilded-prison-at-riyadh-ritz-carlton");
      String text = (String) list.get(0);
      if (text.equals("")){
          System.out.println(text);
      }
      try {
          
          Gate.init();
          gate.Document doc;
          doc=Factory.newDocument(text);
          System.out.println(doc.getContent());
      } catch(GateException ge) {
          ge.printStackTrace();
      }    
               
        
      
    }
}

    