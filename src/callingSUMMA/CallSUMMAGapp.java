package callingSUMMA;

import gate.*;
import gate.creole.ResourceInstantiationException;
import gate.creole.SerialAnalyserController;
import gate.persist.PersistenceException;
import gate.util.GateException;
import gate.util.OffsetComparator;
import gate.util.persistence.PersistenceManager;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Loading in JAVA a SUMMA application developed with the GATE GUI
 * and showing how to summarize a document.
 * @author UPF
 */
public class CallSUMMAGapp {
    // this GAPP is distributed with SUMMA and lives uder ".../summa_plugin/gapps"
    public static String gappToTest="MySUMMA.gapp";
    // this is the controller to load the GAPP
    public static CorpusController application;
   
    
    
    public static void main(String[] args) {
        
        try {
            Gate.init();
          
            // load the GAPP 
             application =
            (CorpusController)
                            PersistenceManager.loadObjectFromFile(new 
                     File("./gapps"+File.separator+gappToTest));
          
            
            Document doc;
            doc=Factory.newDocument(new URL ("http://www.elmundo.es/espana/2017/11/15/5a0c1620e2704eda608b45ad.html"),"ISO-8859-1"); 
            
    
                    
           //  The corpus to store the document
            Corpus corpus=Factory.newCorpus("");
            // put document in corpus
            corpus.add(doc);
            // set controller with corpus
            application.setCorpus(corpus);
            // execute the application
          
            application.execute();
            // show the original text
            System.out.println("+++INPUT+++");
            System.out.println(doc.getContent().toString());
            
            // show the summary
            System.out.println("+++SUMMARY+++");
            System.out.println(getSummary(doc));
            
            
            
            
        } catch (PersistenceException ex) {
            Logger.getLogger(CallSUMMAGapp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CallSUMMAGapp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ResourceInstantiationException ex) {
            Logger.getLogger(CallSUMMAGapp.class.getName()).log(Level.SEVERE, null, ex);
        } catch(GateException ge) {
            ge.printStackTrace();
        }
        
    }
    // this is just an example text. You will have to figure out how to
    // get the input text to be summarizer... not difficult at all!
    public static String getInputText() {
        return  "Global emissions of CO2 in 2017 are projected to rise for the first time in four years, dashing hopes that a peak might soon be reached.\n" +
                "The main cause of the expected growth has been greater use of coal in China as its economy expanded.\n" +
                "Researchers are uncertain if the rise in emissions is a one-off or the start of a new period of CO2 build-up.\n" +
                "Scientists say that a global peak in CO2 before 2020 is needed to limit dangerous global warming this century.\n" +
                "The Global Carbon Project has been analysing and reporting on the scale of emissions of CO2 since 2006.\n" +
                "Carbon output has grown by about 3% per year in that period, but growth essentially declined or remained flat between 2014 and 2016.Concern at first CO2 rise in four years\n" +
                "The latest figures indicate that in 2017, emissions of CO2 from all human activities grew by about 2% globally.\n" +
                "There is some uncertainty about the data but the researchers involved have concluded that emissions are on the rise again.\n" +
                "\"Global CO2 emissions appear to be going up strongly once again after a three-year stable period. This is very disappointing,\" said the lead author of the study, Prof Corinne Le Quéré from the University of East Anglia.\n" +
                "\"With global CO2 emissions from human activities estimated at 41 billion tonnes for 2017, time is running out on our ability to keep warming well below 2 degrees C, let alone 1.5C.\"\n" +
                "The most important element in causing this rise has been China, which is responsible for around 28% of the global total. Emissions there went up 3.5% in 2017, mainly because of increased coal use, driven in the main by a growing economy.\n" +
                "Another important factor in China has been lower water levels in rivers which have seen a drop in the amount of electricity made from hydro-power, with utilities turning to coal and gas to make up the shortfall.\n" +
                "US emissions have continued to decline but the fall has been less than expected. Higher prices saw a drop in the use of natural gas for electricity - with renewables and hydro-power picking up the slack.\n" +
                "Coal use has also grown slightly in the US this year, with consumption up about a half of one percent.\n" +
                "India's emissions are projected to grow by about 2%, which is a considerable decrease from around 6% per year over the last decade.\n" +
                "However, experts believe that this may be a temporary drop-off caused by a number of factors that have hampered the consumption of oil and cement.\n" +
                "Action required\n" +
                "Europe also saw a smaller decline than expected, falling by 0.2% compared with 2.2% over the last 10 years.\n" +
                "One common theme around the world is continued use of gas and oil, says Prof Le Quéré.\n" +
                "\"There have been lots of ups and downs in the use of coal but in the background there has been no weakening in the use of oil and gas. And that is quite worrisome.\"\n" +
                "The report has been launched in Bonn where UN negotiators are trying to move forward with the rules for the Paris climate agreement.\n" +
                "Researchers involved with the study say they are not moving fast enough.\n" +
                "\"Lots of diplomats are working out the rules but that is all a little bit meaningless unless they go back home to their countries and ratchet up climate action and that is where the gap is,\" said Dr Glen Peters, from the Centre for International Climate Research in Norway.\n" +
                "\"These countries have to be pushing on with the policies, but everything keeps getting pushed back.\"\n" +
                "The report is sure to increase tensions in Bonn between developed and developing nations.\n" +
                "There is increasing resentment about the fact that all the focus is on future commitments made under the Paris climate agreement but very little on the years before it becomes active.\n" +
                "Poorer countries want the richer ones to increase their carbon-cutting actions over the next three years.\n" +
                "\"The climate will not let us wait until 2020 when the Paris agreement comes into force,\" said Nicaragua's chief negotiator, Paul Oquist.\n" +
                "\"Climate change is happening now and it's vital that immediate actions to cut emissions become a feature of this summit.\"\n" +
                "The new research on carbon emissions has been published simultaneously in the journals Nature Climate Change,Earth System Science Data Discussions and Environmental Research Letters.";
        
//                "Cataluña es una comunidad autónoma española considerada como"
//                + " nacionalidad histórica,4 situada al nordeste de la península ibérica. "
//                + "Ocupa un territorio de unos 32 000 km cuadrados que limita al norte con"
//                + " Francia (Mediodía-Pirineos y Languedoc-Rosellón) y Andorra, al este con "
//                + "el mar Mediterráneo a lo largo de una franja marítima de unos 580 kilómetros,"
//                + " al sur con la Comunidad Valenciana, y al oeste con Aragón. Esta situación estratégica ha "
//                + "favorecido una relación muy intensa con los territorios de la cuenca mediterránea y con la"
//                + " Europa continental. Cataluña está formada por las provincias de Barcelona, Gerona,"
//                + " Lérida y Tarragona. Su capital es la ciudad de Barcelona.\n" +
//"\n" +
//"En el territorio catalán habitan actualmente 7 504 881 personas en un total de 947 municipios de los"
//                + " que 64 superan los 20 000 habitantes (en los que vive el 70% de la población catalana)."
//                + " Dos tercios de la población vive en la Región Metropolitana de Barcelona. "
//                + "Constituye un territorio muy denso y altamente industrializado, liderando el"
//                + " sector en España desde el siglo XIX, y su economía es la más importante de "
//                + "entre las comunidades autónomas, al generar el 18,7% del PIB español. Respecto al PIB per cápita,"
//                + " se sitúa en cuarta posición, tras el País Vasco, la Comunidad de Madrid y Navarra.\n" +
//"\n" +
//"Según datos del Instituto Valenciano de Investigaciones Económicas de 2007, su índice de "
//                + "desarrollo humano (0,958) es el octavo mayor de España, por detrás de la comunidad autónoma "
//                + "de La Rioja, y por delante de Asturias.\n" +
//"\n" +
//"El relieve de Cataluña presenta, a grandes rasgos, tres grandes unidades morfoestructurales generales: "
//                + "los Pirineos, la formación montañosa que conecta la Península Ibérica con el territorio "
//                + "continental europeo y queda situado al norte de Cataluña; otra unidad formada por una"
//                + " alternancia de elevaciones y llanuras en paralelo a la costa mediterránea, llamado"
//                + " Sistema Mediterráneo Catalán o Cordilleras Costero Catalanas y una última unidad estructural situada entre las anteriores llamada depresión central que configura el sector oriental del Valle del Ebro.\n" +
//"Los Encantados, dos picos emblemáticos de los Pirineos y el estanque San Mauricio.\n" +
//"\n" +
//"El Pirineo catalán representa casi la mitad en longitud de todo el Pirineo español, pues se distribuye"
//                + " a lo largo de más de 200 kilómetros. Tradicionalmente se ha diferenciado el Pirineo"
//                + " Axial, el principal, del Prepirineo (meridional en el territorio catalán) y "
//                + "que son unas formaciones montañosas paralelas a las sierras principales aunque"
//                + " de menor altitud, menos escarpadas, y de una formación geológica diferente. "
//                + "Ambas unidades son más anchas en el sector occidental que en el oriental, y es "
//                + "ahí donde presentan sus mayores cumbres. La elevación más alta de Cataluña, que "
//                + "se encuentra al norte de la comarca de Pallars Sobirá, es la Pica d'Estats con 3.143 m de altitud."
//                + " A lo largo de la frontera con Francia le siguen el Puig Pedrós con 2.914 m y el Puigmal con 2.910 m. "
//                + "El macizo de Besiberri alcanza los 3.029 m. Del Prepirineo destacan varias sierras y "
//                + "cimas como la sierra del Cadí (Vulturó, 2.648 m) o la de Pedraforca (Pollegó Superior, 2.497 m).\n" +
//"\n" +
//"El Sistema Mediterráneo Catalán tiene su base en dos cordilleras más o menos paralelas entre sí y entre "
//                + "el mar siguiendo una orientación noreste-suroeste y son la Cordillera Litoral, la más "
//                + "próxima al mar y la Cordillera Prelitoral detrás de la anterior. La Cordillera Litoral "
//                + "es menos extensa y de menor altitud (Turó Gros, Sierra del Montnegre, 773 m) mientras "
//                + "que en la Prelitoral el rango es más amplio y de mayor altitud (Turó de l'Home, 1.706 m)."
//                + " Dentro del sistema se encuentra una serie de tierras llanas, cuyas entidades mayores"
//                + " forman la Depresión Litoral y la depresión prelitoral. La Depresión Litoral se sitúa "
//                + "al borde de la costa y es previa (exceptuando algunos sectores) a las Cordilleras Litorales. "
//                + "La depresión prelitoral se sitúa en el interior, entre las dos cordilleras litorales, "
//                + "y constituye la base de las tierras llanas del Vallés y el Penedés. Otras llanuras mayores "
//                + "son la Depresión de la Selva y el Llano del Ampurdán, mayoritariamente en las comarcas de"
//                + " la Selva y Ampurdán respectivamente. Finalmente, en el Sistema también se incluye la "
//                + "Cordillera Transversal, que son unas formaciones tardías al norte de la Cordillera "
//                + "Prelitoral y en contacto con el Pirineo y Prepirineo, originando así altitudes medias y "
//                + "volcanes en la zona de la Garrocha hoy en día extintos.\n" +
//"\n" +
//"La depresión central catalana es una llanura situada entre los Prepirineos y la Cordillera Prelitoral. "
//                + "Las comarcas del sur de la provincia de Lérida y las centrales de Barcelona ocupan este territorio."
//                + " Sus tierras se sitúan entre los 200 y los 600 metros de altitud en un continuo de oeste a este,"
//                + " aunque cuenta con algunas estribaciones intermedias. Las llanuras y el agua que baja de los"
//                + " Pirineos han transformado esta zona en grandes campos de cultivo en los que se han construido"
//                + " numerosos canales de riego.";
//        
    }
    
    
    // NOTE: we are assuming the summary is in an annotation set called
    // EXTRACT
    // and we are assuming the sentence annotations are "Sentence"
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