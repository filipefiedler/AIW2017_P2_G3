/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package readingrss;

 
import java.net.URL;
import java.util.Iterator;
 
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.util.ArrayList;
import java.util.Hashtable;



/**
 *
 * @author UPF
 */
public class ReadingRSS {

   
      
 

 
  public static void main(String[] args) throws Exception {
    
    

   String rssSite="http://estaticos.elmundo.es/elmundo/rss/portada.xml";
   
   rssSite="http://feeds.bbci.co.uk/news/rss.xml?edition=int";
   //rssSite="https://www.theguardian.com/world/rss";
   
   
   //rssSite="http://www.lavanguardia.com/mvc/feed/rss/home";
   
   //rssSite="http://ep00.epimg.net/rss/tags/ultimas_noticias.xml";
   
   //rssSite="https://www.ara.cat/rss/";
   
 
  //rssSite="http://www.elpuntavui.cat/nacional.feed?type=rss";
   URL url  = new URL(rssSite);
    XmlReader reader = null;
    while(true) { 
    try {
      
      reader = new XmlReader(url);
      SyndFeed feed = new SyndFeedInput().build(reader);
      System.out.println("Feed Title: "+ feed.getTitle());
 
     for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
        SyndEntry entry = (SyndEntry) i.next();
        System.out.println(entry.getTitle());
        System.out.println(entry.getLink());
        
        
      
        
            }
        } finally {
            if (reader != null)
                reader.close();
        }
       System.out.println("***********************");
        Thread.sleep(60000);
    }
    }
}
        
    
