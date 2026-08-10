package requests.skelethon.interfaces;

import models.BaseModel;

public interface CrudEndpointInterface {
    Object post(BaseModel model);
    Object get(long id);
    Object delete(long id);


    Object update(long id, BaseModel model);

    default Object update(BaseModel model) {
        return update(-1, model);
    }
}
