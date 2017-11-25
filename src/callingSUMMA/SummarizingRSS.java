/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package callingSUMMA;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import gate.*;
import gate.creole.ExecutionException;
import gate.creole.ResourceInstantiationException;
import gate.persist.PersistenceException;
import gate.util.GateException;
import gate.util.OffsetComparator;
import gate.util.persistence.PersistenceManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UPF
 */
public class SummarizingRSS {
    
    public static String gappToTest="MySUMMA.gapp";
    // this is the controller to load the GAPP
    public static CorpusController application;
   
    
    
    public static void main(String[] args) {
        try {
         

           
            
            URL url;
            String rssSite;
            XmlReader reader = null;
            Document doc;
            Corpus corpus;
            
            OutputStreamWriter osw;
         //   File fout=new File("these_are_the_news_australia.html");
             File fout=new File("."+File.separator+"output"+File.separator+"ultimas_noticias_del_mundo.html");
            String header="<!DOCTYPE html>\n" +
                    "<head>"+
                    "<meta charset=\"ISO-8859-1\">"+
                    "</head>"+
                    "<html>\n" +
                    "<body>\n" +
                    "\n" +
                    "<h1>Estas son las últimas noticias</h1>\n" ;
            
            String footer="</body>\n" +
                    "</html>";
            FileOutputStream writer=new FileOutputStream(fout);
            osw=new OutputStreamWriter(writer,"ISO-8859-1");
      
            osw.append(header+"\n");
            osw.flush();
            
            String title;
            String link;
            
            try {
                Gate.init();
                
                // load the GAPP
                application =
                        (CorpusController)
                        PersistenceManager.loadObjectFromFile(new
                             File("."+File.separator+"gapps"+File.separator+gappToTest));
                
                rssSite="http://estaticos.elmundo.es/elmundo/rss/portada.xml";
                
            
            
           
                
                url  = new URL(rssSite);
                reader = new XmlReader(url);
                SyndFeed feed = new SyndFeedInput().build(reader);
                System.out.println("Feed Title: "+ feed.getTitle());
                //  The corpus to store the document
                corpus=Factory.newCorpus("");
                int count=0;
                Iterator i=feed.getEntries().iterator();
                Document cleanDocument;
                while(i.hasNext() && count<10) {        
                    count++;
                    SyndEntry entry = (SyndEntry) i.next();
                    title=entry.getTitle();
                    SyndContent c;
                    
                    link=entry.getLink();
                    
                    System.out.println(title);
                    System.out.println(link);
                    doc=Factory.newDocument(new URL(link),"ISO-8859-1");
                     // !!!!!!! //
                    // YOU HAVE TO IMPLEMENT A METHOD getTextNews(doc) 
                    // THE METHOD SHOULD EXTRACT THE TEXT FROM THE DOCUMENT WITHOUT ANY
                    // EXTRA INFORMATION WHICH IS NOT THE ACTUAL NEWS
                    
                    // cleanDocument=Factory.newDocument(getTextNews(doc)); //
                   
                    // THIS STATEMENT WILL BE REPLACED IN YOUR PROJECT BY THE CALL TO getTextNews() //
                    // SEE BELOW //
                    // !!!!!!!!  //
                    cleanDocument=Factory.newDocument(getParagraphs(doc));

                    corpus.add(cleanDocument);
                    application.setCorpus(corpus);
                    application.execute();
                    System.out.println("*** SUMMARY ***");
                    System.out.println(getSummary(cleanDocument));
                    System.out.println("***************");
                    
                    osw.append("<h2>"+title+"</h2>"+"\n");
                    
                    osw.append(getSummary(cleanDocument)+"\"");
                    osw.append("<a href="+link+">link</a>"+"\"");
                    osw.flush();
                    
                    
                    Factory.deleteResource(doc);
                    
                    Factory.deleteResource(cleanDocument);
                }
                
                osw.close();
                      
                
                
                
                
                
                
                
                
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(SummarizingRSS.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FeedException ex) {
                Logger.getLogger(SummarizingRSS.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(SummarizingRSS.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PersistenceException ex) {
                
            } catch (IOException ex) {
                
            } catch (ResourceInstantiationException ex) {
                
            } catch(GateException ge) {
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(SummarizingRSS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // get paragraphs from document 
    public static String getParagraphs(Document doc) {
        String text="";
        String line;
        AnnotationSet paras;
        paras = doc.getAnnotations("Original markups").get("p");
        String dc=doc.getContent().toString();
        Long start, end;
        ArrayList<Annotation> list=new ArrayList(paras);
        Collections.sort(list, new OffsetComparator());
        Annotation para;
        for(int i=1;i<list.size();i++) {
            para=list.get(i);
            start=para.getStartNode().getOffset();
            end=para.getEndNode().getOffset();
            line=dc.substring(start.intValue(), end.intValue());
            text=text+"\n"+line;
            
        }
        
        return text;
        
    }
    
  
    public static String getSummary(Document doc) {
        String summary="";
        String dc=doc.getContent().toString();
        AnnotationSet sentences=doc.getAnnotations("EXTRACT").get("Sentence");
        // sort the annotations
        Annotation sentence;
        Long start, end;
        ArrayList<Annotation> sentList=new ArrayList(sentences);
        Collections.sort(sentList,new OffsetComparator());
        for(int s=0;s<sentList.size();s++) {
            sentence=sentList.get(s);
            start=sentence.getStartNode().getOffset();
            end  =sentence.getEndNode().getOffset();
            summary=summary+dc.substring(start.intValue(), end.intValue())+"\n";
        }
        
        return summary;
    } 
            
    
}
