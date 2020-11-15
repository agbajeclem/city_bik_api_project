package es.api.rest.api.restassuredhelper;

import org.junit.BeforeClass;
import static io.restassured.RestAssured.*;

public class BaseClass {

    // 1. To create the Cons
    // 2. Hooks -> BDD concept
    @BeforeClass
    public static void setUp() {
        baseURI = "http://api.citybik.es";
        basePath = "/v2/networks";
    }

    public BaseClass() {
        baseURI = "http://api.citybik.es";
        basePath = "/v2/networks";
    }


}
