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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Compartilhar {
	
	private WebDriver driver;
	private static WebDriverWait wait;
	private static String currentWindow_1;
	private static java.util.Set<String> s1;
	
	
	@Before
	public void inicializa(){
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe"); 
		ProfilesIni listProfiles;
		FirefoxProfile profile;
		
		listProfiles = new ProfilesIni();
		profile = listProfiles.getProfile("Selenium");

		driver = new FirefoxDriver(profile);
		wait = new WebDriverWait(driver, 180);
		
		driver.get("https://developers.facebook.com/videos/f8-2017/crafting-compelling-narratives-in-vr/");  
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
	public void deveCompartilhar() throws InterruptedException{
      
		//Inserir login
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("usuario@hotmail.com");
		//Inserir senha
		driver.findElement(By.id("pass")).sendKeys("12345");
		//clicar em entrar
		driver.findElement(By.id("loginbutton")).click();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText("Começar")));

		currentWindow_1 = driver.getWindowHandle();
		
		//Mudar de frame
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'https://www.facebook.com')]")));

		
		driver.findElement(By.linkText("Compartilhar")).click();
		
		Thread.sleep(20000);
	
		//driver.findElement(By.xpath("//td/div/a/button/div/span[contains(text(),'Compartilhar')]")).click();
		System.out.println("clicou");
		s1 = driver.getWindowHandles();
		System.out.println("Janelas abertas = "+s1);
		java.util.Iterator<String> i1=s1.iterator(); 
		
		Thread.sleep(20000);
		while(i1.hasNext())                    
		{     
		 
		   String ChildWindow=i1.next();     
		   if(!currentWindow_1.equalsIgnoreCase(ChildWindow))                
		   {              
			
		        // Mudar de janela
		        driver.switchTo().window(ChildWindow); 
		   }
		}

		
		System.out.println("clicou");
		Thread.sleep(1200);
		//clicar publicar no facebook
		driver.findElement(By.id("u_0_1v")).click();
	    
	}
	

	

}
