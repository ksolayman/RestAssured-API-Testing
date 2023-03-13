package Activities;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;


@Test
public class Activities {

    @Test(description = "Get all activities",priority = 1)
    public void getActivities(){

        baseURI = "https://fakerestapi.azurewebsites.net";
        basePath = "/api/v1/Activities";

        given()

        .when()
            .get()

        .then()
            .statusCode(200)
            .statusLine("HTTP/1.1 200 OK")
            .log().all();

    }

    @Test(description = "post activities",priority = 2)
    public void postActivities(){

        JSONObject object = new JSONObject();
        object.put("id",201);
        object.put("title","Rest Assured");
        object.put("dueDate","2023-03-12T17:17:55.492Z");
        object.put("completed",true);


        baseURI = "https://fakerestapi.azurewebsites.net";
        basePath = "/api/v1/Activities";

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


    @Test(description = "Get single activities",priority = 3)
    public void getActivitiesByID(){

        baseURI = "https://fakerestapi.azurewebsites.net";
        basePath = "/api/v1/Activities/5";

        given()

                .when()
                .get()

                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }

    @Test(description = "Update activities",priority = 4)
    public void updateActivities(){

        JSONObject object = new JSONObject();
        object.put("id", 2);
        object.put("title","Update Activity - 5");
        object.put("dueDate","2023-03-13T15:00:19.048Z");
        object.put("completed",true);


        baseURI = "https://fakerestapi.azurewebsites.net";
        basePath = "/api/v1/Activities/3";

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

    @Test(description = "Delete activities",priority = 5)
    public void deleteActivitiesByID(){

        baseURI = "https://fakerestapi.azurewebsites.net";
        basePath = "/api/v1/Activities/201";

        given()

                .when()
                .delete()

                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }

}
