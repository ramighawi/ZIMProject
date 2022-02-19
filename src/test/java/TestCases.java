import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import utilities.CommonOps;
import utilities.Utilities;
import workflow.WorkFlow;

public class TestCases extends CommonOps {

    @Test(dataProvider = "data-provider",dataProviderClass = Utilities.class,description = "Table Search Test")
    @Description("verify we get the correct data from chosen column by giving a cell text in given column")
    public void tableSearchTest(String searchColumn, String searchText, String returnColumn, String expectedValue){

        Verifications.verifyTrue(WorkFlow.verifyTableCellTextByStringIndex(searchColumn,searchText,returnColumn,expectedValue));

    }


}
