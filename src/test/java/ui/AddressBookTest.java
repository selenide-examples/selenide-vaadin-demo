package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AddressBookTest {
  @Before
  public void setUp() {
    Configuration.reportsFolder = "target/surefire-reports";
    open("http://demo.vaadin.com/AddressBook/");
  }

  @Test
  public void showsContacts() {
    SelenideElement table = $(byText("First name")).closest(".v-table");
    table.findAll(".v-table-header .v-table-header-cell").shouldHave(
        texts("First name", "Last name", "Email", "Phone number", "Street Address", "Postal Code", "City")
    );

    table.findAll(".v-table-table .v-table-row").shouldHave(sizeGreaterThan(10));
    
    table.find(".v-table-table .v-table-row", 0).findAll(".v-table-cell-content").shouldHave(
        texts("Lisa", "Schneider", "lisa.schneider@vaadin.com", "+358 02 555 7531", "561-9262 Iaculis Avenue", "69761", "Stockholm")
    );
  }

  @Test
  public void canAddContact() {
    $(byText("Add contact")).click();
    
    SelenideElement form = $(byText("Save")).closest(".v-form");
    form.find(".v-formlayout-row", 0).find("input").val("Sarah");
    form.find(".v-formlayout-row", 1).find("input").val("Connor");
    form.find(".v-formlayout-row", 2).find("input").val("sarah.connor@gmail.com");
    form.find(".v-formlayout-row", 3).find("input").val("0000000");
    form.find(".v-formlayout-row", 4).find("input").val("-");
    form.find(".v-formlayout-row", 5).find("input").val("11111");
    
    form.find(".v-formlayout-row", 6).find("input").sendKeys("Hon");
    $(".popupContent .v-filterselect-suggestmenu").find(byText("Hong Kong")).shouldBe(visible).click();
    
    form.find(byText("Save")).click();
  }
}
