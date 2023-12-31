package api;

import com.truth.model.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.samePropertyValuesAs;

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


    @Test
    public void getMultiVitamin(){
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        var response = given().
                                queryParam("id", 18).
                        when().get(endpoint).then()
                .assertThat().statusCode(200).header("Content-Type",equalTo("application/json"))
                .body("id", equalTo("18"))
                .body("name", equalTo("Multi-Vitamin (90 capsules)"))
                .body("description",equalTo("A daily dose of our Multi-Vitamins fulfills a day’s nutritional needs for over 12 vitamins and minerals."))
                .body("price",equalTo("10.00"))
                .body("category_id",equalTo("4"))
                .body("category_name",equalTo("Supplements"));

    }

}
