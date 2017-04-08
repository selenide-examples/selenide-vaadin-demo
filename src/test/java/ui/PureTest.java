package ui;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;

public class PureTest {

  private PhantomJSDriver driver;

  @Before
  public void setUp() {
    driver = new PhantomJSDriver();
    driver.navigate().to("http://demo.vaadin.com/AddressBook/");
  }
  
  @Test
  public void canAddContact() {
    assertEquals("Address Book Demo application", driver.getTitle());
    new FluentWait<WebDriver>(driver)
        .withTimeout(30, SECONDS)
        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Add contact']")));
    driver.findElement(By.xpath("//*[text()='Add contact']")).click();
  }
}
