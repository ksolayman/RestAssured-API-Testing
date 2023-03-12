package HTTPResquest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.hamcrest.Matchers.*;

public class HttpRequest {

    int id;

    @Test(priority = 1)
    public void listUsers(){

        given()
                        .pathParam("myPath","users")
                        .queryParam("page",2)

                .when().
                    get("https://reqres.in/api/{myPath}")

                .then().
                    statusCode(200)
                    .body("page",equalTo(2))
                    .statusLine("HTTP/1.1 200 OK")
                    .log().all();
    }

    @Test(priority = 2)
    public void singleUser(){

        given()
                .pathParam("myPath","users")
                .queryParam("id",2)

                .when().
                    get("https://reqres.in/api/{myPath}")

                .then()
                    .statusCode(200)
                    .statusLine("HTTP/1.1 200 OK")
                    .log().all();
    }

    @Test(priority = 3)
    public void singleUserNotFound(){

        given()
                .pathParam("myPath","users")
                .queryParam("id",23)

                .when().
                get("https://reqres.in/api/{myPath}")

                .then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .log().all();
    }

    @Test(priority = 4)
    public void listResource(){

        given()
                .pathParam("myPath","users")
                .queryParam("unknown")

                .when().
                get("https://reqres.in/api/{myPath}")

                .then()
                .statusCode(200)
                .body("page", equalTo(1))
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }

    @Test(priority = 5)
    public void singleResource(){

        given()
                .pathParam("myPath","users")
                .queryParam("unknown")
                .queryParam("id",2)

                .when().
                get("https://reqres.in/api/{myPath}")

                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }

    @Test(priority = 6)
    public void singleResourceNotFound(){

        given()
                .pathParam("myPath","users")
                .queryParam("unknown")
                .queryParam("id",23)

                .when().
                get("https://reqres.in/api/{myPath}")

                .then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .log().all();
    }

    @Test(priority = 7)
    public void createUser(){
        JSONObject object = new JSONObject();
        object.put("name","Sabuz");
        object.put("job","Tester");

        RestAssured.baseURI = "https://reqres.in/api/users";

        given()
                .header("Content-type","application/json")
                .contentType(ContentType.JSON)
                .body(object.toJSONString())

                .when().post()

                .then()
                .statusCode(201)
                .statusLine("HTTP/1.1 201 Created")
                .log().all();
    }


    @Test(priority = 8)
    public void createNewUser(){
        JSONObject object = new JSONObject();
        object.put("name","MrKhan");
        object.put("job","QA Engineer");

        RestAssured.baseURI = "https://reqres.in/api/users";

        id = RestAssured.given()
                .header("Content-type","application/json")
                .contentType(ContentType.JSON)
                .body(object.toJSONString())

                .when()
                .post()
                .jsonPath().getInt("id");
    }

    @Test(dependsOnMethods = {"createNewUser"}, priority = 9)
    public void updateUser(){

        JSONObject object = new JSONObject();
        object.put("name", "Solayman");
        object.put("job", "SQA Engineer");

        RestAssured.baseURI = "https://reqres.in/api/users/"+id;

        RestAssured.given()
                .header("Content-type","application/json")
                .contentType(ContentType.JSON)
                .body(object.toJSONString())

                .when()
                .put()

                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }

    @Test(dependsOnMethods = {"createNewUser"}, priority = 10)
    public void partialUpdateUser(){

        JSONObject object = new JSONObject();
        object.put("name", "Solayman");
        object.put("job", "QA Lead");

        RestAssured.baseURI = "https://reqres.in/api/users/"+id;

        RestAssured.given()
                .header("Content-type","application/json")
                .contentType(ContentType.JSON)
                .body(object.toJSONString())

                .when()
                .patch()

                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }

    @Test(dependsOnMethods = {"createNewUser"}, priority = 11)
    public void deleteUser(){

        JSONObject object = new JSONObject();
        object.put("name", "Solayman");
        object.put("job", "QA Lead");

        RestAssured.baseURI = "https://reqres.in/api/users/"+id;

        RestAssured.given()

                .when()
                .delete()

                .then()
                .statusCode(204)
                .statusLine("HTTP/1.1 204 No Content")
                .log().all();
    }

    @Test(priority = 12)
    public void registerSuccessful(){

        JSONObject object = new JSONObject();
        object.put("email","eve.holt@reqres.in");
        object.put("password","pistol");


        RestAssured.baseURI = "https://reqres.in/api/register";

        RestAssured.given()
                .header("Content-type","application/json")
                .contentType(ContentType.JSON)
                .body(object.toJSONString())

                .when()
                .post()

                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }


    @Test(priority = 13)
    public void registerUnsuccessful(){

        JSONObject object = new JSONObject();
        object.put("email","labi@gmail.com");

        RestAssured.baseURI = "https://reqres.in/api/register";

        RestAssured.given()
                .header("Content-type","application/json")
                .contentType(ContentType.JSON)
                .body(object.toJSONString())

                .when()
                .post()

                .then()
                .statusCode(400)
                .statusLine("HTTP/1.1 400 Bad Request")
                .log().all();
    }

    @Test(priority = 14)
    public void loginSuccessful(){

        JSONObject object = new JSONObject();
        object.put("email","eve.holt@reqres.in");
        object.put("password","cityslicka");


        RestAssured.baseURI = "https://reqres.in/api/login";

        RestAssured.given()
                .header("Content-type","application/json")
                .contentType(ContentType.JSON)
                .body(object.toJSONString())

                .when()
                .post()

                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }

    @Test(priority = 15)
    public void loginSUnsuccessful(){

        JSONObject object = new JSONObject();
        object.put("email","sabuz@gmail.com");

        RestAssured.baseURI = "https://reqres.in/api/login";

        RestAssured.given()
                .header("Content-type","application/json")
                .contentType(ContentType.JSON)
                .body(object.toJSONString())

                .when()
                .post()

                .then()
                .statusCode(400)
                .statusLine("HTTP/1.1 400 Bad Request")
                .log().all();
    }

    @Test(priority = 16)
    public void delayResponse(){

        RestAssured.given()
                .pathParam("myPath","users")
                .queryParam("delay",3)

                .when().
                get("https://reqres.in/api/{myPath}")

                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }


}
