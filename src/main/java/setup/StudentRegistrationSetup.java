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
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class StudentRegistrationSetup {
    WebDriver driver;
    StudentRegistrationForm studentRegistrationForm;

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
        studentRegistrationForm = new StudentRegistrationForm(driver);
        studentRegistrationForm.verifyPage();
        studentRegistrationForm.enterFirstName("Geethu");
        studentRegistrationForm.enterLastName("Mohan");
        studentRegistrationForm.enterEmailId("test123@gmail.com");
        studentRegistrationForm.selectGender();
        studentRegistrationForm.enterMobNumber("9999555005");
        studentRegistrationForm.enterSubject("Testing");
        studentRegistrationForm.selectAllHobbies();
        File file = new File("./src/main/resources/upload/pic.jpg");
        studentRegistrationForm.fileUpload(file.getAbsolutePath());
        studentRegistrationForm.enterAddress("Puthuparambil House, Pathanamthitta, Kerala");
        studentRegistrationForm.selectState("Haryana");
        studentRegistrationForm.selectCity("Karnal");
        studentRegistrationForm.submitButton();
        studentRegistrationForm.takeScreenshot("./src/main/resources/screenshot/screenshot.png");
    }

    @AfterTest
    public void closeBroser() {
        driver.quit();
    }

}
