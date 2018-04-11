package br.com.test;

import static org.junit.Assert.*;

import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Autenticar {
	
	private WebDriver driver;
	private static WebDriverWait wait;
	
	
	@Before
	public void inicializa(){
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe"); //
		ProfilesIni listProfiles;
		FirefoxProfile profile;
		
		listProfiles = new ProfilesIni();
		profile = listProfiles.getProfile("Selenium");

		driver = new FirefoxDriver(profile);
		wait = new WebDriverWait(driver, 180);
		
		driver.get("https://developers.facebook.com/");  
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText("Entrar")));
		//Seleciona o botão Entrar
		driver.findElement(By.linkText("Entrar")).click();
					
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("email")));
		
	   
	}
	
	@After
	public void finaliza(){
		driver.close();
	}

	@Test
	public void deveRealizarAutenticacaoComSucesso() throws InterruptedException{
      
		//Inserir login
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("usuario@hotmail.com");
		//Inserir senha
		driver.findElement(By.id("pass")).sendKeys("12345");
		//clicar em entrar
		driver.findElement(By.id("loginbutton")).click();

	
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText("Começar")));
		Assert.assertTrue(driver.findElement(By.cssSelector("._p47.lfloat")).getText().startsWith("Começar"));
	    
	}
	
	@Test
	public void deveValidarEmailObrigatorio() throws InterruptedException{


		Thread.sleep(5000);
		//Inserir login
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("loginbutton")).click();
   
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[@id='globalContainer']/div[3]/div/div/div")));

		String texto = driver.findElement(By.xpath(".//*[@id='globalContainer']/div[3]/div/div/div")).getText();
		System.out.println(texto);
      
		if ((texto.equals("O email ou o número de telefone inserido não corresponde a nenhuma conta. Cadastre-se para abrir uma conta."))){
			System.out.println("Campo email obrigatório!");
		}
	   
	}
	
	@Test
	public void deveValidarSenhaObrigatorio() throws InterruptedException{
		
		//Inserir login
		driver.findElement(By.id("email")).sendKeys("usuario@hotmail.com");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("loginbutton")).click();
		
		
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[@id='globalContainer']/div[3]/div/div/div")));

		String textoSenha = driver.findElement(By.xpath(".//*[@id='globalContainer']/div[3]/div/div/div")).getText();
		System.out.println(textoSenha);
      
		if ((textoSenha.equals("A senha inserida está incorreta. Esqueceu a senha?"))){
			System.out.println("Campo email obrigatório!");
		}
		
	}
	

}
