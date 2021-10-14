
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

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
    private void testSecond(){

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

    @Test
    public void validateHeader(){
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
    public  void checkBold() {
        //boolean expectedResult =  "700" == "Bold";

        List<WebElement> elements = driver.findElements(By.cssSelector("1500"));
        System.out.println(elements);
    }
}


