package trainingxyz;

import models.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {

    @Test
    public void getCategories(){
        String endpoint = "http://localhost:8888/api_testing/category/read.php";
        var response = given().when().get(endpoint).then();
        response.log().body();
    }

    @Test
    public void getProduct(){
        String endpoint = "http://localhost:8888/api_testing/product/read_one.php";
        given().
            queryParam("id", 2).
        when().
            get(endpoint).
        then().
            assertThat().
                statusCode(200);
    }

    @Test
    public void createProduct(){
        String endpoint = "http://localhost:8888/api_testing/product/create.php";
        String body = """
                {
                "name": "Water Bottle",
                "description": "Blue water bottle. Holds 64 ounces",
                "price": 12,
                "category_id": 3
                }
                """;
        var response = given().body(body).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void updateProduct(){
        String endpoint = "http://localhost:8888/api_testing/product/update.php";
        String body = """
                {
                "id": 19,
                "name": "Water Bottle",
                "description": "Blue water bottle. Holds 64 ounces",
                "price": 15,
                "category_id": 3
                }
                """;
        var response = given().body(body).when().put(endpoint).then();
        response.log().body();
    }

    @Test
    public void deleteProduct(){
        String endpoint = "http://localhost:8888/api_testing/product/delete.php";
        String body = """
                {
                "id": 19
                }
                """;
        var response = given().body(body).when().delete(endpoint).then();
        response.log().body();
    }

    @Test
    public void createSerializedProduct(){
        String endpoint = "http://localhost:8888/api_testing/product/create.php";
        Product product = new Product(
               "Water Bottle",
               "Blue water bottle. Holds 64 ounces",
               12,
               3
        );
        var response = given().body(product).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void createSweatband()
    {
        String endpoint = "http://localhost:8888/api_testing/product/create.php";
        String body = """
                    {             
                        "name" : "Sweatband",           
                        "description" : "White sweatband. One size fits all.",                
                        "price" : 5,               
                        "category_id" : 3          
                    }
                      """;
        var response = given().body(body).when().post(endpoint).then();
    }

    @Test
    public void updateSweatBand()
    {
        String endpoint = "http://localhost:8888/api_testing/product/update.php";
        String body = """
                    {   
                        "id": 26,          
                        "price" : 6                      
                    }
                   """;
        var response = given().body(body).put(endpoint).then();
        response.log().body();
    }

    @Test
    public void getSweatband() {
        String endpoint = "http://localhost:8888/api_testing/product/read_one.php";
        var response =
                given().
                    queryParam("id", 26).
                when().
                    get(endpoint).
                then();
        response.log().body();
    }

    @Test
    public void deleteSweatband(){
        String endpoint = "http://localhost:8888/api_testing/product/delete.php";
        String body = """
                {         
                    "id" : 26       
                }
                """;
        var response = given().body(body).when().delete(endpoint).then();
        response.log().body();
    }
}
