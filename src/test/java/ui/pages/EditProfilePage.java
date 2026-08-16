package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import common.helpers.StepLogger;

import static com.codeborne.selenide.Selenide.$;

public class EditProfilePage extends BasePage<EditProfilePage> {
    private final SelenideElement nameInput = $(Selectors.byAttribute("placeholder", "Enter new name"));
    private final SelenideElement saveButton = $(Selectors.withText("Save Changes"));
    private final SelenideElement profileHeader = $(".profile-header");

    @Override
    public String url() {
        return "/edit-profile";
    }

    public EditProfilePage enterNewName(String newName) {
        return StepLogger.log("Ввод нового имени в профиле: " + newName, () -> {
            nameInput.shouldBe(Condition.visible);
            nameInput.clear();
            nameInput.setValue(newName);
            return this;
        });
    }

    public EditProfilePage clickSave() {
        return StepLogger.log("Нажатие кнопки 'Save Changes' для сохранения профиля", () -> {
            saveButton.shouldBe(Condition.visible, Condition.enabled).click();
            return this;
        });
    }

    public void verifyUpdatedName(String expectedName) {
        StepLogger.log("Проверка, что имя в шапке профиля успешно изменилось на: " + expectedName, () -> {
            profileHeader
                    .shouldBe(Condition.visible)
                    .shouldHave(Condition.text(expectedName));
            return null;
        });
    }
    public EditProfilePage verifyPasswordFieldsDoNotExist() {
        return StepLogger.log("Проверка отсутствия полей ввода нового пароля на странице", () -> {
            $(Selectors.byAttribute("placeholder", "Enter new password")).shouldNotBe(Condition.exist);
            $(Selectors.byAttribute("type", "password")).shouldNotBe(Condition.exist);
            return this;
        });
    }

    public UserDashboard clickHome() {
        return StepLogger.log("Клик по кнопке Home для обновления стейта профиля", () -> {
            $("button.btn-outline-primary").click();
            return new UserDashboard();
        });
    }
}