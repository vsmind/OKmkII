/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.weather;

import java.net.URL;
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
    
    public String getWeather()
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
        
        for(int i = offset; i < nodeList.getLength();i++)
        {
            node = nodeList.item(i);
            answer.append(node.getAttributes().getNamedItem("from").toString()).append(" ");
            
            NodeList nl = node.getChildNodes();
            answer.append(nl.item(13).getAttributes().getNamedItem("value")).append(" ");
            //answer.append(node.getAttributes().getNamedItem("from").toString()).append(" ");
            //answer.append(node.getChildNodes().toString());
            //answer.append(node.getChildNodes().item(4).getAttributes().toString());
            /*
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) node;
                String timeOut = node.getAttributes().getNamedItem("from").toString();
                answer.append(getTempValue()).append(" ");
            }
            * */
        }    
        return answer.toString();
    }
    
    private String getTempValue()
    {
        Node nodeValue = node.getChildNodes().item(3).getAttributes().getNamedItem("name"); 
        return nodeValue.toString();
    }//end of getTagValue
    
}
