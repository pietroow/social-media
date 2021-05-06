package br.com.frwk.socialmedia;

import br.com.frwk.socialmedia.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SocialMediaApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public abstract class AbstractTest {

    protected static final Pattern UUID_PATTERN = Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @LocalServerPort
    protected int port;

    protected String accessToken;

    public void setUp() {
        databaseCleaner.clearTables();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        login();
    }

    public void login() {
        RestAssured.basePath = "/oauth/token";
        JsonPath path =
                given()
                        .contentType("application/x-www-form-urlencoded")
                        .auth()
                        .basic("pietroow", "S3CUR1TYD3V3L0PM3NT")
                        .formParam("grant_type", "password")
                        .formParam("username", "ADMIN@SECURITY.COM")
                        .formParam("password", "123mudar")
                        .when()
                        .post()
                        .andReturn()
                        .jsonPath();
        accessToken = path.getObject("access_token", String.class);
    }

    protected String getIdHeaderLocation(Response response) {
        String location = response.getHeader("Location");
        Matcher matcher = UUID_PATTERN.matcher(location);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

}
