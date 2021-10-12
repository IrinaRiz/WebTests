import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebTest {

    public static final String CHROMEDRIVER = "webdriver.chrome.driver";
    public static final String DRIVERPATH = "/Chromedriver/chromedriver.exe";
    public static final String URL = "http://www.99-bottles-of-beer.net/";

    @Test
    public void testFirst() throws InterruptedException {

        String expectedResult = "http://www.99-bottles-of-beer.net/";

        System.setProperty(CHROMEDRIVER, DRIVERPATH);
        WebDriver driver = new ChromeDriver();

        driver.get(URL);
        Thread.sleep(3000);

        String actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);

        driver.close();
        driver.quit();
    }

    @Test
    public void testSecond() throws InterruptedException {

        String expectedResult = "Welcome to 99 Bottles of Beer";

        System.setProperty(CHROMEDRIVER, DRIVERPATH);
        WebDriver driver = new ChromeDriver();

        driver.get(URL);
        Thread.sleep(3000);

        String actualResult = driver.findElement(By.cssSelector("#main h2")).getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.close();
        driver.quit();
    }

    @Test
    public void headerSongTest() throws InterruptedException {

        String expectedResult1 = "http://www.99-bottles-of-beer.net/lyrics.html";
        String expectedResult2 = "Lyrics of the song 99 Bottles of Beer";

        System.setProperty(CHROMEDRIVER,DRIVERPATH);
        WebDriver driver = new ChromeDriver();

        driver.get(URL);
        Thread.sleep(3000);

        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'here')]"));
        element.click();

        String actualResult1 = driver.getCurrentUrl();
        String actualResult2 = driver.findElement(By.cssSelector("#main h2")).getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);

        driver.close();
        driver.quit();
    }
}


