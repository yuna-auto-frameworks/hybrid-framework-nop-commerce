package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.RewardPointPageUI;

public class UserRewardPointPageObject extends BasePage {
	WebDriver driver;
	
	public UserRewardPointPageObject(WebDriver driver) {
		this.driver = driver;
}

}
