import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class HomeWorkTest {
    private static final Logger logger = LogManager.getLogger(HomeWorkTest.class);
    private WebDriver driver;

    @BeforeAll
    public static void setUpDriver() {
        logger.trace("Драйвер устанавливается");
        WebDriverManager.chromedriver().setup();
        logger.trace("Драйвер установлен");
    }

    @BeforeEach
    public void openBrowser() {
        ChromeOptions options = new ChromeOptions();
        //      options.addArguments("headless");
        logger.trace("Открываем браузер");
        driver = new ChromeDriver(options);
        driver.get("https://otus.home.kartushin.su/training.html");
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            logger.trace("Закрываем браузер");
        }
    }

    @Test
    public void headless() {
        WebElement searchOtus = driver.findElement(By.id("textInput"));
        searchOtus.click();
        searchOtus.sendKeys("Отус");
        String textSearch = searchOtus.getAttribute("value");
        System.out.println(textSearch);
        Assertions.assertEquals(textSearch, "Отус");
    }

    @Test
    public void openModal(){
        driver.manage().window().fullscreen();
        driver.findElement(By.xpath("//button[@id='openModalBtn']")).click();
        driver.findElement(By.xpath("//*[@id='myModal']/div/h2"));
    }

    @Test
    public void emailTest(){
        driver.manage().window().maximize();
        WebElement nameButton = driver.findElement(By.xpath("//input[@id='name']"));
        nameButton.click();
        nameButton.sendKeys("Irina");
        WebElement emailButton = driver.findElement(By.xpath("//input[@id='email']"));
        emailButton.click();
        emailButton.sendKeys("Irirna@mail.ru");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String messageBox = driver.findElement(By.id("messageBox")).getText();
        String expectedResult ="Форма отправлена с именем: Irina и email: Irirna@mail.ru";
        Assertions.assertEquals(expectedResult,messageBox);
        System.out.println(messageBox);
    }
    @Test
    public void enter(){
        System.out.println("Hello World");
    }
}
