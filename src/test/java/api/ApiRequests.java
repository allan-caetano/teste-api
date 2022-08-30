package api;

import static io.restassured.RestAssured.given;

public class ApiRequests  extends ApiUtils implements ApiVerbos{ //Herda os dados da APIUtils e implementa a ApiVerbos
    @Override
    public void GET() {
        response = given()
                .relaxedHTTPSValidation()
                .params(params)
                .headers(headers)
                .get(url);
        super.log("GET");
    }

    @Override
    public void POST() {
        response = given()
                .relaxedHTTPSValidation()
                .params(params)
                .headers(headers)
                .body(body.toString())
                .post(url);
        super.log("POST");
    }

    @Override
    public void PUT() {
               response = given()
                .relaxedHTTPSValidation()
                .params(params)
                .headers(headers)
                .body(body.toString())
                .put(url);
        super.log("PUT");

    }

    @Override
    public void PATCH() {
        response = given()
                .relaxedHTTPSValidation()
                .params(params)
                .headers(headers)
                .body(body.toString())
                .patch(url);
        super.log("PATCH");
    }

    @Override
    public void DELETE() {
        response = given()
                .relaxedHTTPSValidation()
                .params(params)
                .headers(headers)
                .delete(url);
        super.log("DELETE");

    }
}
