package api;

import com.truth.model.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiTest {

   @Test
    public void getCategories(){

       String endpoint ="http://localhost:80/api_testing/category/read.php";
       var response =
               given().when().get(endpoint).then();
        response.log().body();

    }

    @Test
    public void getProducts(){
       String endpoint = "http://localhost:80/api_testing/product/read.php";
       var response = given()
               .when()
               .get(endpoint)
               .then().log().headers()
               .assertThat()
                    .statusCode(200).
                        header("Content-Type", equalTo("application/json; charset=UTF-8")).
                        body("records.size()", greaterThan(0))
                        .body("records.id", everyItem(notNullValue())).
                        body("records.name", everyItem(notNullValue())).
                        body("records.description", everyItem(notNullValue())).
                        body("records.price", everyItem(notNullValue())).
                        body("records.category_id", everyItem(notNullValue())).
                        body("records.category_name", everyItem(notNullValue())).
                        body("records.id[0]",equalTo("1003") );


    }


    @Test
    public void getProduct(){
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        var response =
                given().
                        queryParam("id", 2).
                        when().
                            get(endpoint).
                        then()
                        .assertThat().
                            statusCode(200).body("id", equalTo("2"))
                                              .body("name", equalTo("Cross-Back Training Tank")).
                                                body("description", equalTo("The most awesome phone of 2013!"))
                                              .body("price",equalTo( "299.00")).
                                                body("category_id", equalTo("2")).
                                                body("category_name", equalTo("Active Wear - Women"));
       // response.log().body();
    }

    @Test
    public void createProduct(){
       String endpoint ="http://localhost:80/api_testing/product/create.php";
       String body = """
               {
               "name":"Chinese Lamp",
               "description": "This is a light lamp.",
               "price":"10",
               "category_id":"3"
               }
               """;
        var response = given().body(body).when().post(endpoint).then();
        response.log().body();
   }

   @Test
    public void updateProduct(){
       String endpoint = "http://localhost:80/api_testing/product/update.php";
       String body = """
               {
                    "id":1001,
                    "name": "Water Bottle",
                    "description" : "This is a water bottle",
                    "price": 15,
                    "category_id":3
               }
               """;
       var response = given().body(body).when().put(endpoint).then();
        response.log().body();

   }

   @Test
    public void deleteProduct(){
       String endpoint = "http://localhost:80/api_testing/product/delete.php";
        String body = """
                {
                    "id": 1001
                }
                """;
       var response = given().body(body).when().delete(endpoint).then();
       response.log().body();
   }


   @Test
    public void createSerializedProducts(){
       String endpoint = "http://localhost:80/api_testing/product/create.php";
       Product product = new Product(
               "Water Bottle",
               "Blue Water Bottle.Holds 64 ounces",
               14,
               3
       );

       var response = given().body(product)
               .when().post(endpoint).then();

       response.log().body();
   }


    @Test
    public void getDeserializedProduct(){
        String endpoint ="http://localhost:80/api_testing/product/read_one.php";

        Product expectedProduct = new Product(
                2,"Cross-Back Training Tank",
                "The most awesome phone of 2013!",
                299.00,
                2,
                "Active Wear - Women"
        );
        Product actualProduct =
                given().
                        queryParam("id", "2").
                        when().
                        get(endpoint).as(Product.class);

        assertThat(actualProduct, samePropertyValuesAs(expectedProduct));
    }



}
