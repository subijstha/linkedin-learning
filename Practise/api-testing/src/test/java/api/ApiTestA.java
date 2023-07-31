package api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ApiTestA {

    // Assignment -- test

    @Test
    public void createSweatband(){
        String endpoint = "http://localhost:80/api_testing/product/create.php";
        String body = """
               {
                    "name": "Sweatband",
                    "description": "This is Strechable sweatband made of nylon",
                    "price": 5,
                    "category_id": 3
               }
               """;
        var response = given().body(body).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void updateSweatBand(){
        String endpoint = "http://localhost:80/api_testing/product/update.php";
        String body = """
               {
                    "id" : 1004,
                    "name": "Sweatband",
                    "description": "This is Streachable sweatband made of nylon",
                    "price": 6,
                    "category_id":3
               }
               """;

        var response = given().body(body).when().put(endpoint).then();
        response.log().body();
    }


    @Test
    public void getSweatband(){
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        var response =
                given().
                        queryParam("id","1004").
                        when().
                        get(endpoint).
                        then();
        response.log().body();
    }

    @Test
    public void deleteSweatband(){
        String endpoint = "http://localhost:80/api_testing/product/delete.php";
        String body = """
               {
                "id": 1004
               }
               
               """;

        var response = given().body(body).when().delete(endpoint).then();

        response.log().body();
    }
}
