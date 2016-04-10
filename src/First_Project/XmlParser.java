package First_Project;

import First_Project.exception.ExceptionNegativeBalance;
import First_Project.exception.ExceptionNotValidClass;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dotin school 6 on 4/6/2016.
 */
public class XmlParser {

    public static List<Deposit> readXML() throws NoSuchMethodException, IllegalAccessException, SAXException, InstantiationException, ParserConfigurationException, InvocationTargetException, IOException {

        String xmlAddress = "staf.xml";

        List<Deposit> deposits = new ArrayList<Deposit>();


        File fXmlFile = new File(xmlAddress);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();

        System.out.println("Root elements :" + doc.getDocumentElement().getNodeName());
        NodeList nlist = doc.getElementsByTagName("deposit");
        System.out.println("*************************");

        for (int temp = 0; temp < nlist.getLength(); temp++) {
            Node nNode = nlist.item(temp);


            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                String depType = eElement.getElementsByTagName("depositType").item(0).getTextContent();

                try {
                    Deposit deposit = null;

                        deposit = new Deposit(depType,
                                new BigDecimal(eElement.getElementsByTagName("depositBalance").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("durationInDays").item(0).getTextContent()),
                                eElement.getElementsByTagName("customerNumber").item(0).getTextContent());
                    deposits.add(deposit);
                } catch (ExceptionNotValidClass exceptionNotValidClass) {
                    System.out.println(exceptionNotValidClass.getMessage());
                } catch (ExceptionNegativeBalance exceptionNegativeBalance) {
                    System.out.println(exceptionNegativeBalance.getMessage());
                } catch (ClassNotFoundException e) {
                    System.out.println("The deposit type is invalid!");
               }


            }
        }


        return deposits;
    }//read xml


//    @Override
//    public int compareTo(XmlParser o) {
//        return Integer.compare()
//    }

}
