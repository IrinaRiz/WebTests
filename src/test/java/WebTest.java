import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebTest {
    public WebDriver driver;

    @BeforeMethod
    public void startUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://www.99-bottles-of-beer.net/");
        Thread.sleep(3000);
    }

    @AfterMethod
    public void setDown(){
        driver.close();
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
}


