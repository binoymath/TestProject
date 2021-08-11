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

    @FindBy(id = "subjectsInput")
    WebElement subject;

    @FindBy(id = "hobbiesWrapper")
    WebElement hobbyWrapper;

    @FindBy(xpath = "//input[@type='checkbox']")
    List<WebElement> selectAllHobbies;

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

    @FindBy(id = "example-modal-sizes-title-lg")
    WebElement verifySuccessMsg;

    @FindBy(xpath = "//td[text()='Student Name']/..//td[2]")
    WebElement verifyStudentName;

    @FindBy(xpath = "//td[text()='Student Email']/..//td[2]")
    WebElement verifyStudentEmail;

    @FindBy(xpath = "//td[text()='Gender']/..//td[2]")
    WebElement verifyGender;

    @FindBy(xpath = "//td[text()='Mobile']/..//td[2]")
    WebElement verifyMobile;

    @FindBy(xpath = "//td[text()='Subjects']/..//td[2]")
    WebElement verifySubjects;

    @FindBy(xpath = "//td[text()='Hobbies']/..//td[2]")
    WebElement verifyHobbies;

    @FindBy(xpath = "//td[text()='Picture']/..//td[2]")
    WebElement verifyPicture;

    @FindBy(xpath = "//td[text()='Address']/..//td[2]")
    WebElement verifyAddress;

    @FindBy(xpath = "//td[text()='State and City']/..//td[2]")
    WebElement verifyStateCity;

    public StudentRegistrationForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyPage(String actualTitle) {
        String formName = pageTitle.getText();
        assertEquals(actualTitle, formName);
    }
    public void enterFirstName(String firstname) {
        assertEquals(true, firstName.isDisplayed());
        firstName.sendKeys(firstname);
    }
    public void enterLastName(String lastname) {
        assertEquals(true, lastName.isDisplayed());
        lastName.sendKeys(lastname);
    }
    public void enterEmailId(String email_id) {
        assertEquals(true, emailId.isDisplayed());
        emailId.sendKeys(email_id);
    }
    public void selectGender()
    {
        assertEquals(false, gender.isSelected());
        Actions act =  new Actions(driver);
        act.moveToElement(gender).click().perform();
    }
    public void enterMobNumber(String number) {
        assertEquals(true, mobNumber.isDisplayed());
        mobNumber.sendKeys(number);
    }
    public void enterSubject(String sub) {
        assertEquals(true, subject.isDisplayed());
        Actions act =  new Actions(driver);
        act.moveToElement(subject).click().perform();
        act.moveToElement(subject).sendKeys(sub).sendKeys(Keys.DOWN, Keys.ENTER).perform();
    }
    public void selectAllHobbies() {
        assertEquals(true, hobbyWrapper.isDisplayed());
        int size = selectAllHobbies.size();
        System.out.println(size);
        for (int i = 0; i<size; i++) {
            Actions act =  new Actions(driver);
            act.moveToElement(selectAllHobbies.get(i)).click().perform();
        }
    }
    public void fileUpload(String url) {
        assertEquals(true, uploadFile.isDisplayed());
        uploadFile.sendKeys(url);
    }
    public void enterAddress(String address) {
        assertEquals(true, currentAddress.isDisplayed());
        currentAddress.sendKeys(address);
    }
    public void selectState(String state_arg) {
        assertEquals(true, selectState.isDisplayed());
        selectState.sendKeys(state_arg);
        selectState.sendKeys(Keys.DOWN, Keys.ENTER);
    }
    public void selectCity(String city_arg) {
        assertEquals(true, selectCity.isDisplayed());
        selectCity.sendKeys(city_arg);
        selectCity.sendKeys(Keys.DOWN, Keys.ENTER);
    }
    public void submitButton() {
        WebDriverWait wait = new WebDriverWait(driver,500);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
        assertEquals(true, submitButton.isDisplayed());
        submitButton.click();
    }
    public void takeScreenshot(String filePath) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(filePath));
    }
    public void verifySuccessMessage(String actualMessage) {
        String message = verifySuccessMsg.getText();
        assertEquals(actualMessage, message);
    }
    public void verifyRegistrationSuccessForm(String studentName, String studentEmail, String gender, String mobile, String subject, String hobbies,
                                              String picture, String address, String stateCity) {
        String name = verifyStudentName.getText();
        assertEquals(studentName, name);
        String email = verifyStudentEmail.getText();
        assertEquals(studentEmail, email);
        String getGender = verifyGender.getText();
        assertEquals(gender, getGender);
        String getMobile = verifyMobile.getText();
        assertEquals(mobile, getMobile);
        String getSubject = verifySubjects.getText();
        assertEquals(subject, getSubject);
        String getHobbies = verifyHobbies.getText();
        assertEquals(hobbies, getHobbies);
        String picUrl = verifyPicture.getText();
        assertEquals(picture, picUrl);
        String getaddress = verifyAddress.getText();
        assertEquals(address, getaddress);
        String getStateCity = verifyStateCity.getText();
        assertEquals(stateCity, getStateCity);
    }
}
