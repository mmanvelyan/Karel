package org.karel.web;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.karel.karel.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeleniumTest extends RepositoryTest {

    @LocalServerPort
    private int port;
    private final WebDriver driver = new ChromeDriver();

    @Test
    @Order(1)
    public void register() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://localhost:" + port + "/register");
        driver.findElement(By.id("username")).sendKeys("user");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("email")).sendKeys("user@email.com");
        driver.findElement(By.id("firstName")).sendKeys("John");
        driver.findElement(By.id("lastName")).sendKeys("Doe");
        driver.findElement(By.id("phone")).sendKeys("0123456789");
        driver.findElement(By.id("register")).click();
        assertEquals("http://localhost:" + port + "/problems", driver.getCurrentUrl());
        driver.close();
    }

    @Test
    @Order(2)
    public void login(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://localhost:" + port + "/login");
        driver.findElement(By.id("username")).sendKeys("user1");
        driver.findElement(By.id("password")).sendKeys("userPass");
        driver.findElement(By.id("login")).click();
        assertEquals("http://localhost:" + port + "/problems", driver.getCurrentUrl());
        driver.close();
    }

    @Test
    public void submission(){
        System.out.println(new BCryptPasswordEncoder().encode("userPass"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://localhost:" + port + "/login");
        driver.findElement(By.id("username")).sendKeys("user1");
        driver.findElement(By.id("password")).sendKeys("userPass");
        driver.findElement(By.id("login")).click();
        driver.get("http://localhost:"+port+"/submit");
        WebElement problemId = driver.findElement(By.id("problem_id"));
        WebElement code = driver.findElement(By.id("code"));
        WebElement submit = driver.findElement(By.id("submit"));
        problemId.clear();
        problemId.sendKeys("1");
        code.clear();
        code.sendKeys("#");
        submit.submit();
        assertEquals("COMPILATION ERROR", driver.findElement(By.id("status")).getText());
        assertEquals("1", driver.findElement(By.id("problem_id")).getText());
        assertEquals("#", driver.findElement(By.id("code")).getText());
        driver.close();
    }

}













