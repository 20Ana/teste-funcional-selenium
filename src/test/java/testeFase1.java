import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class testeFase1 extends uteis{
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public static void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 15);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("http://vanilton.net/selenium/game/");
        driver.findElement(By.xpath("//div[@id='modalInitial']//button[@class='close']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("link_login")).click();
        driver.findElement(By.id("nomePlayerCad")).sendKeys("ana123");
        driver.findElement(By.id("senhaPlayerCad")).sendKeys("92551206Ana");
        driver.findElement(By.id("btnAutenticacaoPlayer")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='menuProfile' and contains(.,'ana123')]")));
    }

    @Test
    public void testeLogin () throws InterruptedException {
        driver.findElement(By.id("fase1")).click();
        driver.findElement(By.id("nome")).sendKeys("caboquinho");
        driver.findElement(By.id("senha")).sendKeys("0123456789");
        driver.findElement(By.xpath("//main[@class='mdl-layout__content']//button[@name='entrar']")).click();
        Thread.sleep(10000);
        String msg = driver.findElement(By.id("msg_login")).getText();
        assertEquals("Login efetuado com sucesso!", msg);
    }

    @Test
    public void testeChecarValores() {
        //Clicar na fase
        driver.findElement(By.id("fase2")).click();

        String msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(.,'Fase 2 - Checando Valores')]"))).getText();
        assertEquals("Fase 2 - Checando Valores", msg);

        ArrayList<String> aux = new ArrayList<String>();

        driver.findElement(By.id("dia_atual")).click();
        aux.add(driver.findElement(By.id("dia")).getAttribute("value").toLowerCase());
        driver.findElement(By.id("mes_atual")).click();
        aux.add(driver.findElement(By.id("mes")).getAttribute("value").toLowerCase());
        driver.findElement(By.id("ano_atual")).click();
        aux.add(driver.findElement(By.id("ano")).getAttribute("value"));
        driver.findElement(By.id("limpar")).click();

        ArrayList<String> dataSystem = data();

        assertEquals(dataSystem.get(0), aux.get(0));
        assertEquals(dataSystem.get(1), aux.get(1));
        assertEquals(dataSystem.get(2), aux.get(2));
    }

    @Test
    public void testeAlugaVeiculo (){
        driver.findElement(By.id("fase3")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(.,'Fase 3 - Aluguel de Veículo')]"))).getText();
        driver.findElement(By.id("nome_contratante")).sendKeys("Ana Flávia Rodrigues Lima");
        driver.findElement(By.id("cpf_contratante")).sendKeys("01997873281");
        driver.findElement(By.id("endereco_contratante")).sendKeys("Rua Lalalalala nº 0123");
        driver.findElement(By.id("dt_nascimento_contratante")).sendKeys("20051996");
        driver.findElement(By.id("dt_habilitacao_contratante")).sendKeys("22012018");
        driver.findElement(By.id("telefone_contratante")).sendKeys("92981179226", Keys.TAB);
        driver.findElement(By.id("avancar_veiculo")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("veiculo_opcoes")));
        new Select(driver.findElement(By.id("veiculo_opcoes"))).selectByVisibleText("Fiat");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='veiculo_modelo']/option[text()='Uno - A']")));
        new Select(driver.findElement(By.id("veiculo_modelo"))).selectByVisibleText("Fiorino - A");
        driver.findElement(By.id("dias_aluguel")).sendKeys("10", Keys.TAB);
        driver.findElement(By.id("avancar_orcamento")).click();
        driver.findElement(By.xpath("//div[@class='button-avancar-fase3']//button[@id='dia_atual']")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@id='modalSaveAluguel']//h4"), "Aluguel realizado com sucesso!"));
        String msg = driver.findElement(By.xpath("//div[@id='modalSaveAluguel']//h4")).getText();
        assertEquals("Aluguel realizado com sucesso!", msg);
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}
