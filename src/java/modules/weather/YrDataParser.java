/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.weather;

import help.YrForecast;
import java.net.URL;
import java.util.LinkedList;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Yr weather parser class
 * @author Vitaly
 */
public class YrDataParser {
 
    //Necessery variables
    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;
    private Document document;
    private NodeList nodeList;
    private Node node;
    
    /**
     * Class constructor
     * gets XML document from url
     * @param _url - url to XML file
     */
    public YrDataParser(String _url)
    {
        try 
        {
            String url = _url;
            documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(new URL(url).openStream());
            document.getDocumentElement().normalize();
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    }
    
    /**
     * XML parser, gets weather forecast from XML file
     * @param _date - date in format yyyy-MM-dd
     * @return LinkedList<YrForecast> with weather forecast for chosen date
     */
    public LinkedList<YrForecast> getWeather(String _date)
    {
        StringBuilder answer = new StringBuilder();
        nodeList = document.getElementsByTagName("time");
        //search for first node with necessary information
        int offset = 0;
        for(int i = 1; i < nodeList.getLength();i++)
        {
            if(nodeList.item(i).getChildNodes().getLength() > 10)
            {
                offset = i;//set offset
                break;
            }
        }
        
        LinkedList<YrForecast> weatherList = new LinkedList();
        //XML parsing
        for(int i = offset; i < nodeList.getLength();i++)
        {
            node = nodeList.item(i);
            String weatherForecastDate = node.getAttributes().getNamedItem("from").toString();
            weatherForecastDate = weatherForecastDate.substring(6, 16);
            
            String timeStart = node.getAttributes().getNamedItem("from").toString();
            timeStart = timeStart.substring(17,22);
            
            String timeEnd = node.getAttributes().getNamedItem("to").toString();
            timeEnd = timeEnd.substring(15,20);
            /*
            System.out.println(_date);
            System.out.println(weatherForecastDate);
            System.out.println(timeStart);
            System.out.println(timeEnd);
            */
            
            //compare dates
            if(_date.equals(weatherForecastDate))
            {
                NodeList nl = node.getChildNodes();
                YrForecast yf = new YrForecast();
                yf.setTime(timeStart + " - " + timeEnd);
                yf.setIcon(nl.item(3).getAttributes().getNamedItem("var").getNodeValue());
                yf.setTemp(nl.item(13).getAttributes().getNamedItem("value").getNodeValue());
                
                weatherList.add(yf);
            }    
        }    
        return weatherList;
    }
    
    @Deprecated
    /**
     * Search for temperature value in node
     * @return XML node
     */
    private String getTempValue()
    {
        Node nodeValue = node.getChildNodes().item(3).getAttributes().getNamedItem("name"); 
        return nodeValue.toString();
    }//end of getTagValue
    
}
