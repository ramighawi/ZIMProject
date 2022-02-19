package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TablePage {

    @FindBy(id= "customers")
    private WebElement table;

    public WebElement getTable(){
        return table;
    }

}
