package api;

import com.truth.model.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ApiTest {

   @Test
    public void getCategories(){

       String endpoint ="http://localhost:80/api_testing/category/read.php";
       var response =
               given().when().get(endpoint).then();
        response.log().body();

    }


    @Test
    public void getProduct(){
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        var response =
                given().
                        queryParam("id", 2).
                        when().
                        get(endpoint).
                        then();
        response.log().body();
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




}
