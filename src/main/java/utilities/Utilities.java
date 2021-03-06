package utilities;

import com.opencsv.CSVReader;
import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

public class Utilities {

    private static Object[][] data = null;

    @Step("get data from csv with data-provider ")
    @DataProvider(name = "data-provider")
    public static Object[][] createData() {
        getFromCsvData("dataSources/DataForTestCase.csv");
        return data;
    }

    @Step("getFromCsvData")
    private static void getFromCsvData(String fileName) {

        try {
            data = new Object[4][4];
            CSVReader reader = new CSVReader(new FileReader(fileName));

            // this will load content into list
            List<String[]> li = reader.readAll();
            System.out.println("Total rows which we have is " + li.size());

            // create Iterator reference
            Iterator<String[]> i1 = li.iterator();

            // Iterate all values
            for (int j = 0 ; i1.hasNext() ; j++) {
                String[] str = i1.next();
                for (int i = 0; i < str.length; i++)
                    data[j][i] =  str[i];

            }

        } catch (Exception fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }


    @Step("getDataXML")
    public static String getDataXML(String nodeName) {
        DocumentBuilder dBuilder;
        Document doc = null;
        File fxmlFile = new File("dataSources/Data.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fxmlFile);
        } catch (Exception e) {
            System.out.println("exeption in reading xml file- " + e);
        }
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }
}
