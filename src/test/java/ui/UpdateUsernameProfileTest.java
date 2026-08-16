package ui;

import common.annotations.UserSession;
import generators.RandomData;
import ui.pages.BankAlert;
import ui.pages.UserDashboard;
import org.junit.jupiter.api.Test;

@UserSession
public class UpdateUsernameProfileTest extends BaseUiTest {

    @Test
    public void SuccessfulNameChangeToAValidFormatUiTest() {
        String randomName = RandomData.getUsername() + " " + RandomData.getUsername();

        new UserDashboard()
                .open()
                .navigateToEditProfile()
                .enterNewName(randomName)
                .clickSave()
                .checkAlertMessageAndAccept(BankAlert.NAME_UPDATED)
                .verifyUpdatedName(randomName);
    }

    @Test
    public void PasswordChangeAttemptUiTest() {
        new UserDashboard()
                .open()
                .navigateToEditProfile()
                .verifyPasswordFieldsDoNotExist();
    }

    @Test
    public void AttemptToSetANameConsistingOfOnlyOneWordUiTest() {
        new UserDashboard()
                .open()
                .navigateToEditProfile()
                .enterNewName(RandomData.getUsername())
                .clickSave()
                .checkAlertMessageAndAccept(BankAlert.INVALID_NAME_FORMAT);
    }
}