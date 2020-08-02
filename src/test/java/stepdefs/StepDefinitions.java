package stepdefs;

import com.test.pages.*;
import com.test.utilities.ConfigDataProvider;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

public class StepDefinitions {

    private static final Logger LOG = Logger.getLogger(StepDefinitions.class);
    private static final ConfigDataProvider props = new ConfigDataProvider();

    FluentWait<WebDriver> wait;

    private BasePage theBasePage;
    private HomePage theHomePage;
    private LandingPage theLandingPage;
    private CheckOutPage theCheckOutPage;
    private StorePage theStorePage;
    private boolean doClose=true;

    //Second dropdpwn feature testing
    @Given("User visits the website")
    public void landOnLandingPage()  { theLandingPage=new LandingPage();}
    @And("User clicks login on the website")
    public void clickMainLogin(){
        theLandingPage.clickMainLogin();
    }
    @And("User enters email")
    public void enterEmail(){
        theLandingPage.enterEmail(props.getEmail());
    }
    @And("User enters password")
    public void enterPwd(){
        theLandingPage.enterPassword(props.getPassword());
    }
    @And("User clicks continue")
    public void clickContinue(){
        theLandingPage.clickLoginContinue();
    }
    @And("clicks login")
    public void clickLoginAfterCredentials(){
        theLandingPage.clickLoginAfterCredentials();
    }
    @Then("User is shown My Account Button")
    public void validateMyAccount(){
        Assert.assertTrue(theLandingPage.isMyAccountPresent());
    }
    @And ("User opens chat box")
    public void openChat(){
        theLandingPage.openChatBox();
    }
    @And ("User closes chat box")
    public void closeChat(){
        theLandingPage.closeChatBox();
    }
    @And("User enters credentials in chat box")
    public void enterCredsInChat(){
        theLandingPage.enterCredsInChatBox();
    }
    @Given("User is taken to the home page")
    public void landOnHomePage()  throws InterruptedException{
        theHomePage=new HomePage(theLandingPage);
        if(doClose) {
            theHomePage.closePage();
        }
        doClose=true;
    }

    //To implement the feature file for zipcode

    @When("User enters a valid ZipCode")
    public void enterZipCode() { theLandingPage.enterZipCode(props.getZipCode()); }

    @And ("User clicks on find")
    public void clickFind(){ theLandingPage.clickFind();}

    @And ("User scrolls down to the bottom of the page")
    public void scrollDownThePage(){
       theLandingPage.scrollDown();
    }
    @When ("User clicks on twitter icon")
    public void clickTwitterIcon() throws Exception {
        theLandingPage.clickTwitterLogo();
    }
    @Then ("User is routed to the twitter website")
    public void validateTwitterWindow() throws Exception {
        theLandingPage.validateNewUrl();
    }

    //Implement shopping cart
    @Given("User is on the home page")
    public void goToHomePage()  throws InterruptedException{
        doClose=false;
        landOnLandingPage();
        enterZipCode();
        clickFind();
        enterValuesIntoPromptLogin();
        landOnHomePage();
    }
    @When("adds an item to the cart")
    public void addItemToCart(){
        theHomePage.addFirstItemFromFirstStoreToCart();
    }
    @And("proceeds to checkout")
    public void proceedToChecout(){
        theHomePage.clickOnCart();
        theCheckOutPage = new CheckOutPage(theHomePage);
        theCheckOutPage.clickProceedToCart();
    }
    @And("confirms the address")
    public void confirmsAddress(){
        theCheckOutPage.clickNextToConfirmAddress();
    }
    @And("confirms the delivery option")
    public void confirmsDelivery(){
        theCheckOutPage.clickNextToConfirmDelivery();
    }
    @And("clears the cart")
    public void clearCart(){
        theCheckOutPage.clickBrandLogo();
        theHomePage = new HomePage(theCheckOutPage);
        theHomePage.clickOnCart();
        theHomePage.removeItemFromCart();
    }

    @When ("User visits a store")
    public void vistsStore() throws InterruptedException {
        theHomePage.visitStore(props.getStoreName());
        theStorePage = new StorePage(theHomePage);
    }

    @When ("User clicks on proceed to checkout")
    public void proceedToCheckOut() {
        theHomePage.proceedToCheckOut();
    }

    @And("User enters credentials into prompted login")
    public void enterValuesIntoPromptLogin(){
        enterEmail();
        clickContinue();
        enterPwd();
        clickLoginAfterCredentials();
    }
    @And("searches for the item")
    public void searchItemInStore(){

        theStorePage.searchItem(props.getSearchItem());
    }

}
