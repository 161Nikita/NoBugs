package requests.skelethon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import models.*;

@Getter
@AllArgsConstructor
public enum Endpoint {
    ADMIN_USER(
            "/admin/users",
            CreateUserRequest.class,
            CreateUserResponse.class
    ),

    LOGIN(
            "/auth/login",
            LoginUserRequest.class,
            LoginUserResponse.class
    ),

    ACCOUNTS(
            "/accounts",
            BaseModel.class,
            CreateAccountResponse.class
    ),
    CUSTOMER_ACCOUNTS(
            "/customer/accounts",
            BaseModel.class,
            CreateAccountResponse.class
    ),

    UPDATE_PROFILE(
            "/customer/profile",
            UpdateProfileRequest.class,
            BaseModel.class
    ),
    CUSTOMER_PROFILE(
            "/customer/profile",
            BaseModel.class,
            CreateUserResponse.class
    ),

    USER_TOP_UP_ACCOUNT(
            "/accounts/deposit",
            UserTopUpAccountRequest.class,
            UserTopUpAccountResponse.class
    ),

    USER_TRANSFER_ACCOUNT(
            "/accounts/transfer",
            UserTransferAccountRequest.class,
            UserTransferAccountResponse.class
    );

    private final String url;
    private final Class<? extends BaseModel> requestModel;
    private final Class<? extends BaseModel> responseModel;

}
