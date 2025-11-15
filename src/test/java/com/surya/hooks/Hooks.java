package com.surya.hooks;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    // Logger for this class
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    
    @Before
    public void setup(Scenario scenario) {
        logger.info("===== Starting Scenario: " + scenario.getName() + " =====");
        
        // Load config file
        ConfigReader.loadConfig();
        logger.info("Config file loaded successfully");
        
        // Read browser and environment details
        String browser = ConfigReader.getProperty("browser");
        String env = ConfigReader.getProperty("env");
        String url = ConfigReader.getProperty("url_" + env);
        logger.info("Launching browser: " + browser + " on environment: " + env);
        
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
            logger.error("Browser not supported: " + browser);
            throw new RuntimeException("Browser not supported: " + browser);
        }
        
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);
        logger.info("Navigated to URL: " + url);
    }
    
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            logger.error("Scenario failed: " + scenario.getName());
            // Screenshot logic can be added here
        } else {
            logger.info("Scenario passed: " + scenario.getName());
        }

        // Quit browser
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed");
        }

        logger.info("===== Ending Scenario: " + scenario.getName() + " =====");
    }
}