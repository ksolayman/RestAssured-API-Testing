package DummyAPI;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DummyApis {

    @Test(description = "get all employee data", priority = 1)
    public void employeeList(){
        given()
                .pathParam("myPath", "employees")

                .when().get("https://dummy.restapiexample.com/{myPath}")

                .then().statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }


    @Test(description = "get single employee data", priority = 2)
    public void singleEmployee(){
        given()
                .pathParam("myPath", "employees")
                .queryParam("id", 1)

                .when().get("https://dummy.restapiexample.com/{myPath}")

                .then().statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }


    @Test(description = "create employee data", priority = 3)
    public void createEmployee(){
        baseURI = "https://dummy.restapiexample.com/api/v1/create";

        JSONObject object = new JSONObject();
        object.put("name","Sabuz");
        object.put("salary",30000);
        object.put("age",28);

        given().header("Content-type","application/json")
                .contentType(ContentType.JSON)
                .body(object.toJSONString())

                .when().post()

                .then().statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }

    @Test(description = "update employee data", priority = 4)
    public void updateEmployee(){
        baseURI = "https://dummy.restapiexample.com/api/v1/update/21";

        JSONObject object = new JSONObject();
        object.put("name","Solayman");
        object.put("salary",35000);
        object.put("age",30);

        given().header("Content-type","application/json")
                .contentType(ContentType.JSON)
                .body(object.toJSONString())

                .when().put()

                .then().statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }


    @Test(description = "delete employee data", priority = 5)
    public void deleteEmployee(){
        baseURI = "https://dummy.restapiexample.com/api/v1/delete/2";

        given()

                .when().delete()

                .then().statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }

}
