package extensions;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Base;

import java.util.List;

public class UiActions extends Base {


    @Step("get table cell text By Xpath")
    public static String getTableCellTextByXpath(WebElement table, int searchColumn, String searchText, int returnColumnText){

        String cellXpath = "";
        String table_xpath = "//*[@id=\"" + table.getAttribute("id") + "\"]";

        cellXpath = table_xpath + "/tbody//tr/td[" + searchColumn + "][text()='" + searchText + "']/ancestor::tr/td[" + returnColumnText + "]";

        return webDriver.findElement(By.xpath(cellXpath)).getText();

    }


    @Step("get table cell text")
    public static String getTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText) {
        int myIndex = 0;
        String myText = "";
        String table_xpath = "//*[@id=\"" + table.getAttribute("id") + "\"]";
        List<WebElement> columnOneElements = table.findElements(By.xpath(table_xpath + "/tbody/tr/td[1]"));
        List<WebElement> columnTwoElements = table.findElements(By.xpath(table_xpath + "/tbody/tr/td[2]"));
        List<WebElement> columnThreeElements = table.findElements(By.xpath(table_xpath + "/tbody/tr/td[3]"));


        switch (searchColumn) {
            case 1:
                myIndex=getSearchTextIndex(columnOneElements,searchText);
                break;
            case 2:
                myIndex=getSearchTextIndex(columnTwoElements,searchText);
                break;

            case 3:
                myIndex=getSearchTextIndex(columnThreeElements,searchText);
                break;

        }

        switch (returnColumnText) {
            case 1:
                myText = columnOneElements.get(myIndex).getText();
                break;
            case 2:
                myText = columnTwoElements.get(myIndex).getText();
                break;
            case 3:
                myText = columnThreeElements.get(myIndex).getText();
                break;
        }


        return myText;


    }

    @Step("get search text index")
    public static int getSearchTextIndex(List<WebElement> elements, String searchText) {
        int myIndex=0;
        for (int i = 0; i < elements.size(); i++) {

            if (elements.get(i).getText().equals(searchText)) {
                myIndex= i;
                break;
            }

        }

            return myIndex;
    }

    }
