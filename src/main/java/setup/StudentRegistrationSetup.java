package setup;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.StudentRegistrationForm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class StudentRegistrationSetup {
    WebDriver driver;
    StudentRegistrationForm studentRegistrationForm;
    Properties prop;

    public void StudentRegistrationSetup() throws IOException {

    }


    @Parameters("browser")
    @BeforeTest
    public void setup(String browser) {
        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "./src/main/resources/driver/geckodriver.exe");
            driver = new FirefoxDriver();

        }else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "./src/main/resources/driver/chromedriver.exe");
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/automation-practice-form");
    }

    @Test
    public void testForm() throws IOException {
        prop = new Properties();
        FileInputStream filePath = new FileInputStream("./src/main/resources/data/testdata.properties");
        prop.load(filePath);
        studentRegistrationForm = new StudentRegistrationForm(driver);
        studentRegistrationForm.verifyPage(prop.getProperty("Title"));
        studentRegistrationForm.enterFirstName(prop.getProperty("FirstName"));
        studentRegistrationForm.enterLastName(prop.getProperty("LastName"));
        studentRegistrationForm.enterEmailId(prop.getProperty("EmailId"));
        studentRegistrationForm.selectGender();
        studentRegistrationForm.enterMobNumber(prop.getProperty("MobNumber"));
        studentRegistrationForm.enterSubject(prop.getProperty("Subject"));
        studentRegistrationForm.selectAllHobbies();
        File file = new File("./src/main/resources/upload/pic.jpg");
        studentRegistrationForm.fileUpload(file.getAbsolutePath());
        studentRegistrationForm.enterAddress(prop.getProperty("Address"));
        studentRegistrationForm.selectState(prop.getProperty("State"));
        studentRegistrationForm.selectCity(prop.getProperty("City"));
        studentRegistrationForm.submitButton();
        studentRegistrationForm.verifySuccessMessage(prop.getProperty("SuccessMessage"));
        studentRegistrationForm.takeScreenshot("./src/main/resources/screenshot/screenshot.png");
    }

    @Test
    public void validateRegistrationForm() throws IOException {
        String name = prop.getProperty("FirstName") + " " + prop.getProperty("LastName");
        String state_city = prop.getProperty("State") + " " + prop.getProperty("City");
        studentRegistrationForm.verifyRegistrationSuccessForm(name,prop.getProperty("EmailId"),prop.getProperty("Gender"),prop.getProperty("MobNumber")
                ,prop.getProperty("Subject"),prop.getProperty("Hobbies"),prop.getProperty("Picture"),prop.getProperty("Address"),state_city);
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }

}
