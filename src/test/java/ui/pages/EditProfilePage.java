package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

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
        nameInput.shouldBe(Condition.visible);
        nameInput.clear();
        nameInput.setValue(newName);
        return this;
    }

    public EditProfilePage clickSave() {
        saveButton.shouldBe(Condition.visible, Condition.enabled).click();
        return this;
    }

    public void verifyUpdatedName(String expectedName) {
        profileHeader
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text(expectedName));
    }
    public EditProfilePage verifyPasswordFieldsDoNotExist() {
        $(Selectors.byAttribute("placeholder", "Enter new password")).shouldNotBe(Condition.exist);
        $(Selectors.byAttribute("type", "password")).shouldNotBe(Condition.exist);
        return this;
    }
}