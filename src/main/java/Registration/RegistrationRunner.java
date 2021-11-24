package Registration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features="C:\\Users\\malusi.msomi\\IdeaProjects\\Order-Cucumber\\src\\main\\resources\\Features\\Registration.feature", glue={"Registration"},
        monochrome = true,
        plugin = {"pretty","junit:target/JUnitReport.xml","html:target/HtmlReport.html","json:target/JsonReport.json"},
        tags = "@Registration")
public class RegistrationRunner {

}
