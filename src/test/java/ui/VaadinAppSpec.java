package ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.selenide.LocalHttpServer;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class VaadinAppSpec {

  private LocalHttpServer server = new LocalHttpServer(8080);

  @Before
  public void setUp() throws Exception {
    server.start();
    open("/");
    $(byText("Click Me")).waitUntil(appears, 12000);
  }

  @After
  public void tearDown() throws Exception {
    server.stop();
  }

  @Test
  public void canClick() {
    $$(byText("Thank you for clicking")).shouldHave(size(0));
    $(byText("Click Me")).click();
    $$(byText("Thank you for clicking")).shouldHave(size(1));
    $(byText("Click Me")).click();
    $$(byText("Thank you for clicking")).shouldHave(size(2));
  }
}
