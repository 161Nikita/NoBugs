package requests.skelethon.requesters;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.BaseModel;
import requests.skelethon.Endpoint;
import requests.skelethon.HttpRequest;
import requests.skelethon.interfaces.CrudEndpointInterface;

public class ValidatedCrudRequester<T extends BaseModel> extends HttpRequest implements CrudEndpointInterface {
    private CrudRequester crudRequester;

    public ValidatedCrudRequester(RequestSpecification requestSpecification, Endpoint endpoint, ResponseSpecification responseSpecification) {
        super(requestSpecification, endpoint, responseSpecification);
        this.crudRequester = new CrudRequester(requestSpecification, endpoint, responseSpecification);
    }

    @Override
    public T post(BaseModel model) {
        return (T) crudRequester.post(model).extract().as(endpoint.getResponseModel());
    }

    // Перегруженный метод
    public T post() {
        return (T) post(null);
    }

    @Override
    public Object delete(long id) {
        return null;
    }

    // (БЕЗ ID): вызывает основной апдейт, передавая флаг -1
    public T update(BaseModel model) {
        return (T) update(-1, model);
    }

    @Override
    public T update(long id, BaseModel model) {
        return (T) crudRequester.update(id, model).extract().as(endpoint.getResponseModel());
    }

    public <T> java.util.List<T> getList() {
        return crudRequester.get(-1)
                .extract()
                .body()
                .jsonPath()
                .getList(".", (Class<T>) endpoint.getResponseModel());
    }

    @Override
    public Object get(long id) {
        return crudRequester.get(id).extract().as(endpoint.getResponseModel());
    }
    public T getSingle() {
        return (T) get(-1);
    }
}
