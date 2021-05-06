package br.com.frwk.socialmedia;

import br.com.frwk.socialmedia.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class PublicacaoTest extends AbstractTest {

    private String publicacaoJson;

    @Before
    public void setUp() {
        super.setUp();
        RestAssured.basePath = "/clientes";
        publicacaoJson = ResourceUtils.getContentFromResource("/json/criar-publicacao.json");
        prepararDados();
    }

    private void prepararDados() {

    }

    @Test
    public void criarComSucesso() {
        String payload = publicacaoJson
                .replace("{{texto}}", "MINHAS FÉRIAS FORAM ÓTIMASSS !!!!");

        Response response = given()
                .body(payload)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post();
        response.then()
                .statusCode(HttpStatus.CREATED.value());

//        String id = getIdHeaderLocation(response);
//
//        given()
//                .pathParam("clienteId", id)
//                .when()
//                .get("/{clienteId}")
//                .then()
//                .body("size()", is(5))
//                .body("id", is(id))
//                .body("nome", is("GUILHERME ARRUDA"))
//                .body("email", is("GUILHERMINO@HOTMAIL.COM"))
//                .body("dataNascimento", is("2000-05-04"))
//                .body("idade", is(20))
//                .statusCode(HttpStatus.OK.value());
    }

//    @Test
//    public void findAllByUsuarioLogado() {
////        @GetMapping("/{usuarioId}")
//    }
//
//    @Test
//    public void deleteById() {
////        @PathVariable("publicacaoId") UUID publicacaoId,
//
//    }

}
