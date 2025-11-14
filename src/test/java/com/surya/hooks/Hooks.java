package com.surya.hooks;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.surya.utilities.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {
	
	public static WebDriver driver;
	
	@Before
	public void setup(Scenario scenario) {
		System.out.println("===== Starting Scenario: " + scenario.getName() + " =====");
		
		// Load config file
		ConfigReader.loadConfig();
		
		// Read browser and environment details
		String browser = ConfigReader.getProperty("browser");
	    String env = ConfigReader.getProperty("env");
	    String url = ConfigReader.getProperty("url_" + env);
	    
	    // Launch browser based on config
	    if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            throw new RuntimeException("Browser not supported: " + browser);
        }
	    driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);	
	}
	
	
	@After
    public void tearDown(Scenario scenario) {
        System.out.println("===== Ending Scenario: " + scenario.getName() + " =====");

        // Take screenshot if test fails
        if (scenario.isFailed()) {
            System.out.println("Scenario failed. Screenshot captured.");
            // You can later add screenshot logic here
        }

        // Quit browser
        if (driver != null) {
            driver.quit();
        }
    }

}
