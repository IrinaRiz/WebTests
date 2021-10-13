import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebTest {
    private WebDriver driver;

    @BeforeMethod
    public void startUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://www.99-bottles-of-beer.net/");
        Thread.sleep(3000);
    }

    @AfterMethod
    public void setDown(){
        driver.quit();
    }

    @Test
    public void testFirst() {
        String expectedResult = "http://www.99-bottles-of-beer.net/";

        String actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testSecond(){
        String expectedResult = "Welcome to 99 Bottles of Beer";

        String actualResult = driver.findElement(By.cssSelector("#main h2")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void headerSongTest(){
        String expectedResult1 = "http://www.99-bottles-of-beer.net/lyrics.html";
        String expectedResult2 = "Lyrics of the song 99 Bottles of Beer";

        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'here')]"));
        element.click();

        String actualResult1 = driver.getCurrentUrl();
        String actualResult2 = driver.findElement(By.cssSelector("#main h2")).getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }

    @Test
    public void getHistoricHeader(){
        String expectedResult1 = "http://www.99-bottles-of-beer.net/info.html";
        String expectedResult2 = "History";

        WebElement element = driver.findElement(By.linkText("historic information"));
        element.click();

        String actualResult1 = driver.getCurrentUrl();
        String actualResult2 = driver.findElement(By.cssSelector("#main h2")).getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }
}


