package petstore;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class Pet {
    String  uri = "https://petstore.swagger.io/v2/pet";

    public String lerJson(String caminhoJson) throws IOException {
        //caminhoJson é a variavel que ira receber/ler os dados do arquivo pet1...
        //throws IOException: caso não consiga ler as informações não fique travado e exiba uma mensagem

        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test //identifica o metodo ou função como um teste para o TestNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        //Rest assurend
        given()// Dado
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when() //Quando
                .post(uri)
        .then()//Então
                .log().all()
                .statusCode(200)
                .body("name", is("Snoopy"))
                .body("status", is("available"))

        ;

    }

}
