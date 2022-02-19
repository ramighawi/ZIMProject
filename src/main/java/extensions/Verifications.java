package extensions;

import io.qameta.allure.Step;

import static org.testng.AssertJUnit.assertTrue;

public class Verifications {

    @Step("verify true" )
    public static void verifyTrue(boolean condition){
        try {
            assertTrue(condition);
        }
        catch (Error e){throw new RuntimeException("condition is false");

    }
}}
