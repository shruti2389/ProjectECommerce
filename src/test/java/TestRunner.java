

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

@CucumberOptions(features = "src/test/resources/features"
        , glue = "stepdefs", tags = { "@login or @chatBox or @zip or @twitter or @shoppingCart" })
public class TestRunner extends AbstractTestNGCucumberTests{

}

