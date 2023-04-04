package test.blogAgi;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class PesquisaLupaTest {

    private WebDriver driver;
    public static final String BASE_URL = "https://blogdoagi.com.br/";
    public static final String DRIVER_LOCATION = "C:/Driver/chromedriver.exe";

    @BeforeTest
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", DRIVER_LOCATION);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(BASE_URL);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void pesquisaSemanaDoConsumidor() {
        driver.findElement(By.id("search-open")).click();
        driver.findElement(By.cssSelector(".desktop-search input[placeholder='Pesquisar …']")).clear(); 
        driver.findElement(By.cssSelector(".desktop-search input[placeholder='Pesquisar …']")).sendKeys("Semana do Consumidor");
        driver.findElement(By.cssSelector(".desktop-search input[value='Pesquisar']")).click();
        String searchResultTitle = driver.findElement(By.cssSelector("#primary header h1")).getText();
        Assert.assertEquals(searchResultTitle, "Resultados da busca por: Semana do Consumidor");
    }

    @Test
    public void pesquisaNenhumResultadoEncontrado() {
        driver.findElement(By.id("search-open")).click();
        driver.findElement(By.cssSelector(".desktop-search input[placeholder='Pesquisar …']")).sendKeys("ab@123");
        driver.findElement(By.cssSelector(".desktop-search input[value='Pesquisar']")).click();
        String searchResultTitle = driver.findElement(By.cssSelector("#primary header h1")).getText();
        Assert.assertEquals(searchResultTitle, "Nenhum resultado");
    }
}
