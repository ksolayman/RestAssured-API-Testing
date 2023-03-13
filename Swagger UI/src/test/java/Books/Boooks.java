package Books;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Boooks {


    @Test(description = "Get all books",priority = 1)
    public void getBooks(){

        baseURI = "https://fakerestapi.azurewebsites.net";
        basePath = "/api/v1/Books";

        given()

                .when()
                .get()

                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();

    }

    @Test(description = "post book",priority = 2)
    public void postBook(){

        JSONObject object = new JSONObject();
        object.put("id",3);
        object.put("title","Who Am I");
        object.put("description","Biography");
        object.put("pageCount",410);
        object.put("excerpt","Available");
        object.put("dueDate","2023-03-12T17:17:55.492Z");


        baseURI = "https://fakerestapi.azurewebsites.net";
        basePath = "/api/v1/Books";

        given()
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .body(object.toJSONString())

                .when()
                .post()

                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }

    @Test(description = "get book by ID",priority = 3)
    public void getBookByID(){

        baseURI = "https://fakerestapi.azurewebsites.net";
        basePath = "/api/v1/Books/3";

        given()

                .when()
                .get()

                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }
    @Test(description = "update book",priority = 4)
    public void updateBook(){

        JSONObject object = new JSONObject();
        object.put("id",7);
        object.put("title","Songs Offering");
        object.put("description","Singing");
        object.put("pageCount",210);
        object.put("excerpt","Not Available");
        object.put("dueDate","2023-03-12T17:17:55.492Z");


        baseURI = "https://fakerestapi.azurewebsites.net";
        basePath = "/api/v1/Books/7";

        given()
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .body(object.toJSONString())

                .when()
                .put()

                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }


    @Test(description = "delete book",priority = 5)
    public void deleteBookByID(){

        baseURI = "https://fakerestapi.azurewebsites.net";
        basePath = "/api/v1/Books/1";

        given()

                .when()
                .delete()

                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }

}
