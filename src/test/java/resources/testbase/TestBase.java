package resources.testbase;


import com.gorest.restassured.constants.Path;
import com.gorest.restassured.utils.PropertyReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;


public class TestBase {
    public static PropertyReader propertyReader;

    @BeforeClass
    public static void init() {
      propertyReader = PropertyReader.getInstance();
      RestAssured.baseURI = propertyReader.getProperty("baseUrl");
      RestAssured.basePath = Path.RESOURCE;

    }

}
