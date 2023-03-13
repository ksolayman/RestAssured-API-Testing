package Authors;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Authors {

    @Test(description = "Get Author List", priority = 1)
    public void getAuthors(){

        baseURI = "https://fakerestapi.azurewebsites.net";
        basePath = "/api/v1/Authors";

        given()

        .when().get()

        .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();

    }


    @Test(description = "Create Author", priority = 2)
    public void createAuthors(){

        baseURI = "https://fakerestapi.azurewebsites.net";
        basePath = "/api/v1/Authors";

        JSONObject object = new JSONObject();
        object.put("id",201);
        object.put("idBook",10001);
        object.put("firstName","J.K.");
        object.put("lastName","Rowling");

        given()
                .header("Content-type","application/json")
                .contentType(ContentType.JSON)
                .body(object.toJSONString())

                .when().post()

                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }


    @Test(description = "Get by book ID", priority = 3)
    public void getByBookID(){

        baseURI = "https://fakerestapi.azurewebsites.net";
        basePath = "/api/v1/Authors/authors/books/9";

        given()

                .when().get()

                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }


    @Test(description = "Get Author by ID", priority = 4)
    public void getAuthorByID(){

        baseURI = "https://fakerestapi.azurewebsites.net";
        basePath = "/api/v1/Authors/9";

        given()

                .when().get()

                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }


    @Test(description = "Update Author", priority = 5)
    public void updateAuthors(){

        baseURI = "https://fakerestapi.azurewebsites.net";
        basePath = "/api/v1/Authors/1";

        JSONObject object = new JSONObject();
        object.put("id",1);
        object.put("idBook",9);
        object.put("firstName","Humayun");
        object.put("lastName","Ahmed");

        given()
                .header("Content-type","application/json")
                .contentType(ContentType.JSON)
                .body(object.toJSONString())

                .when().put()

                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }

    @Test(description = "Delete Author", priority = 6)
    public void deleteAuthors(){

        baseURI = "https://fakerestapi.azurewebsites.net";
        basePath = "/api/v1/Authors/2";

        given()

                .when().delete()

                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }

}
