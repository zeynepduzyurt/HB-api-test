import com.google.gson.Gson;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {
    String baseURI = "https://petstore.swagger.io/v2/user";
    User user = new User();

//user ekleme
    @Test()
    public void addUserTest() { //Post
        given()
                .contentType(ContentType.JSON)
                .body(new Gson().toJson(user))
                .when()
                .post(baseURI)
                .then()
                .body("message", equalTo(Integer.toString(user.id)))
                .statusCode(200);
    }

//user guncellemesi
    @Test()
    public void UpdateUserApiTest() { //Put

        user.password = "678798";
        System.out.println(new Gson().toJson(user));


        given()
                .contentType(ContentType.JSON)
                .body(new Gson().toJson(user))
                .when()
                .put(baseURI+"/"+user.username)
                .then()
                .body("message", equalTo(Integer.toString(user.id)))
                .statusCode(200);

    }


    // User kontrolu
    @Test()
    public void getUser() {  //GET
        User user = new User();
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(baseURI+"/"+user.username)
                .then()
                .body("username", equalTo(user.username))
                .body("firstName", equalTo(user.firstName))
                .statusCode(200).log().all();


    }

// user silinmesi
    @Test()
    public void deleteUser() { //DELETE
        User user = new User();
        given()
                .contentType(ContentType.JSON)
                .body(new Gson().toJson(user))
                .when()
                .delete(baseURI+"/"+user.username)
                .then()
                .body("message", equalTo(user.username))
                .statusCode(200);
    }

}

