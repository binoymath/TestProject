package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class StudentRegistrationForm {

    WebDriver driver;

    @FindBy(xpath = "//h5[text()='Student Registration Form']")
    WebElement pageTitle;

    @FindBy(id = "firstName")
    WebElement firstName;

    @FindBy(id = "lastName")
    WebElement lastName;

    @FindBy(id = "userEmail")
    WebElement emailId;

    @FindBy(xpath = "//input[@name='gender' and @value='Female']")
    WebElement gender;

    @FindBy(id = "userNumber")
    WebElement mobNumber;

    @FindBy(id = "subjectsContainer")
    WebElement subject;

    @FindBy(xpath = "//input[@type='checkbox']")
    private List<WebElement> selectAllHobbies;

    @FindBy(id = "uploadPicture")
    WebElement uploadFile;

    @FindBy(id = "currentAddress")
    WebElement currentAddress;

    @FindBy(xpath = "//div[@id='state']/..//input")
    WebElement selectState;

    @FindBy(xpath = "//div[@id='city']/..//input")
    WebElement selectCity;

    @FindBy(id = "submit")
    WebElement submitButton;

    public StudentRegistrationForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyPage() {
        String formName = pageTitle.getText();
        assertEquals("Student Registration Form", formName);
    }
    public void enterFirstName(String firstname) {
        firstName.sendKeys(firstname);
    }
    public void enterLastName(String lastname) {
        lastName.sendKeys(lastname);
    }
    public void enterEmailId(String email_id) {
        emailId.sendKeys(email_id);
    }
    public void selectGender()
    {
        Actions act =  new Actions(driver);
        act.moveToElement(gender).click().perform();
    }
    public void enterMobNumber(String number) {
        mobNumber.sendKeys(number);
    }
    public void enterSubject(String sub) {
        mobNumber.sendKeys(sub);
    }
    public void selectAllHobbies() {
        int size = selectAllHobbies.size();
        System.out.println(size);
        for (int i = 0; i<size; i++) {
            Actions act =  new Actions(driver);
            act.moveToElement(selectAllHobbies.get(i)).click().perform();
        }
    }
    public void fileUpload(String url) {
        uploadFile.sendKeys(url);
    }
    public void enterAddress(String address) {
        currentAddress.sendKeys(address);
    }
    public void selectState(String state_arg) {
        selectState.sendKeys(state_arg);
        selectState.sendKeys(Keys.DOWN, Keys.ENTER);
    }
    public void selectCity(String city_arg) {
        selectCity.sendKeys(city_arg);
        selectCity.sendKeys(Keys.DOWN, Keys.ENTER);

    }
    public void submitButton() {
        WebDriverWait wait = new WebDriverWait(driver,500);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
        submitButton.click();
    }
    public void takeScreenshot(String filePath) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(filePath));
    }
}
