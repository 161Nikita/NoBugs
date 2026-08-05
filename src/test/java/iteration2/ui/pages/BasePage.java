package iteration2.ui.pages;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Alert;

import java.lang.reflect.Field;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.assertj.core.api.Assertions.assertThat;

public abstract class BasePage<T extends BasePage> {
    protected SelenideElement usernameInput = $(Selectors.byAttribute("placeholder", "Username"));
    protected SelenideElement passwordInput = $(Selectors.byAttribute("placeholder", "Password"));

    public abstract String url();

    public T open() {
        return Selenide.open(url(), (Class<T>) this.getClass());
    }

    public <T extends BasePage> T getPage(Class<T> pageClass) {
        return Selenide.page(pageClass);
    }

    public T checkAlertMessageAndAccept(BankAlert bankAlert) {
        Alert alert = switchTo().alert();
        assertThat(alert.getText()).contains(bankAlert.getMessage());
        alert.accept();
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T click(String elementName) {
        try {
            Field field = this.getClass().getDeclaredField(elementName);
            field.setAccessible(true);

            SelenideElement element = (SelenideElement) field.get(this);
            element.click();

        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Ошибка: Элемент '" + elementName + "' не найден на странице " + this.getClass().getSimpleName(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Нет доступа к элементу '" + elementName + "'", e);
        }
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setInput(String elementName, String inputValue) {
        try {
            Field field = this.getClass().getDeclaredField(elementName);
            field.setAccessible(true);

            SelenideElement element = (SelenideElement) field.get(this);
            element.setValue(inputValue);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Ошибка: Поле ввода '" + elementName + "' не найдено на странице " + this.getClass().getSimpleName(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Нет доступа к полю ввода '" + elementName + "'", e);
        }
        return (T) this;
    }
    @SuppressWarnings("unchecked")
    public T selectOption(String elementName, int index) {
        try {
            Field field = this.getClass().getDeclaredField(elementName);
            field.setAccessible(true);
            SelenideElement element = (SelenideElement) field.get(this);
            element.selectOption(index);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Ошибка работы с селектором '" + elementName + "'", e);
        }
        return (T) this;
    }

}
