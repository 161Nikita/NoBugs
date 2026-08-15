package extensions;

import com.codeborne.selenide.Configuration;
import common.annotations.UserSession;
import common.storage.SessionStorage;
import configs.Config;
import models.CreateUserRequest;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import requests.skelethon.steps.AdminSteps;

import java.util.LinkedList;
import java.util.List;

import static ui.BaseUiTest.authAsUser;

public class UserSessionExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        UserSession annotation = extensionContext.getRequiredTestMethod().getAnnotation(UserSession.class);
        if (annotation == null) {
            annotation = extensionContext.getRequiredTestClass().getAnnotation(UserSession.class);
        }
        if (annotation != null) {
            int userCount = annotation.value();
            SessionStorage.clear();
            List<CreateUserRequest> users = new LinkedList<>();

            for (int i = 0; i < userCount; i++) {
                CreateUserRequest user = AdminSteps.createUser();
                users.add(user);
            }
            SessionStorage.addUsers(users);

            int authAsUser = annotation.auth();
            authAsUser(SessionStorage.getUser(authAsUser));
        }
        Platform platformAnn = extensionContext.getRequiredTestMethod()
                .getAnnotation(Platform.class);

        if (platformAnn == null) {
            platformAnn = extensionContext.getRequiredTestClass()
                    .getAnnotation(Platform.class);
        }
        if (platformAnn != null) {
            if (platformAnn.value() == Platform.Type.MOBILE) {
                // Разрешение для мобилок (например, iPhone 12/13)
               Configuration.browserSize = "390x844";
            } else {
                // Дефолтное разрешение для десктопа
                Configuration.browserSize = Config.getProperty("browserSize");
            }
        }
    }
}