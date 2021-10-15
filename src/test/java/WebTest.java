import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebTest {
    private WebDriver driver;

    @BeforeMethod
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://www.99-bottles-of-beer.net/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void setDown(){
        driver.close();
        driver.quit();
    }

    @Test
    private void testFirst() {

        String expectedResult = "http://www.99-bottles-of-beer.net/";

        String actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    private void testSecond(){

        String expectedResult = "Welcome to 99 Bottles of Beer";

        String actualResult = driver.findElement(By.cssSelector("#main h2")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    private void headerSongTest(){
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
    private void getHistoricHeader(){
        String expectedResult1 = "http://www.99-bottles-of-beer.net/info.html";
        String expectedResult2 = "History";

        WebElement element = driver.findElement(By.linkText("historic information"));
        element.click();

        String actualResult1 = driver.getCurrentUrl();
        String actualResult2 = driver.findElement(By.cssSelector("#main h2")).getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }

    @Test
    private void validateHeader(){
        String expectedResult1 = "one program in 1500 variations";
        String expectedResult2 = "1500";
        String expectedFont = "700";

        String actualResult1 = driver.findElement(By.cssSelector("#header > h2")).getText();
        String actualResult2 = actualResult1.substring(15, 19);
        WebElement element = driver.findElement(By.xpath("(//*[text()='1500'])[2]"));
        String actualResult3 = element.getCssValue("font-weight");

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedFont);
    }

    @Test
    private  void checkBold() {
        String font = "700";

        List<WebElement> elements = driver.findElements(By.xpath("//*[text()='1500']"));
        for(int i = 0; i < elements.size(); i++){
            Assert.assertTrue(elements.get(i).getCssValue("font-weight").contains(font));
        }
    }
}


