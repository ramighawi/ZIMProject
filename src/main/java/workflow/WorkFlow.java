package workflow;

import extensions.UiActions;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import utilities.CommonOps;

public class WorkFlow extends CommonOps {

    @Step("verify if cell text equals expected text")
    public static boolean verifyTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText, String expectedText) {

        String cellText = null;
        boolean result=false;

        try {
            cellText = UiActions.getTableCellText(table, searchColumn, searchText, returnColumnText);

            //The Second way to get the table cell text- by Xpath
            //cellText = UiActions.getTableCellTextByXpath(table,searchColumn,searchText,returnColumnText);

            if (cellText.equals(expectedText)) {
                result = true;
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;

    }

    @Step("verify if cell text equals expected text, parsing int index to string")
    public static boolean verifyTableCellTextByStringIndex(String searchColumn, String searchText, String returnColumn, String expectedValue) {

        if (verifyTableCellText(tablePage.getTable(), Integer.parseInt(searchColumn), searchText, Integer.parseInt(returnColumn), expectedValue))
            return true;
        else return false;
    }




}
