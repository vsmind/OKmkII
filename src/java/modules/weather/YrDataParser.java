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
 *
 * @author Vitaly
 */
public class YrDataParser {
 
    //Necessery variables
    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;
    private Document document;
    private NodeList nodeList;
    private Node node;
    
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
    
    public LinkedList<YrForecast> getWeather(String _date)
    {
        StringBuilder answer = new StringBuilder();
        nodeList = document.getElementsByTagName("time");
        int offset = 0;
        for(int i = 1; i < nodeList.getLength();i++)
        {
            if(nodeList.item(i).getChildNodes().getLength() > 10)
            {
                offset = i;
                break;
            }
        }
        
        LinkedList<YrForecast> weatherList = new LinkedList();
        
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
            if(_date.equals(weatherForecastDate))
            {
                NodeList nl = node.getChildNodes();
                YrForecast yf = new YrForecast();
                yf.setTime(timeStart + " - " + timeEnd);
                yf.setIcon(nl.item(3).getAttributes().getNamedItem("var").getNodeValue());
                yf.setTemp(nl.item(13).getAttributes().getNamedItem("value").getNodeValue());
                
                weatherList.add(yf);
                
                /*
                 * answer.append(node.getAttributes().getNamedItem("from").toString()).append(" ");
                 * answer.append(nl.item(13).getAttributes().getNamedItem("value").getNodeValue()).append("-");
                 * answer.append(nl.item(3).getAttributes().getNamedItem("number").getNodeValue()).append(" ");
                 */
            }    
        }    
        return weatherList;
    }
    
    private String getTempValue()
    {
        Node nodeValue = node.getChildNodes().item(3).getAttributes().getNamedItem("name"); 
        return nodeValue.toString();
    }//end of getTagValue
    
}
