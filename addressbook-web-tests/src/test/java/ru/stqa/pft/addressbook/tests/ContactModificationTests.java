package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactBirthdayData;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactTelephoneEmailData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() throws Exception {
    app.getNavigationHelper().selectItem();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Lilia", "Latypova", null), new ContactTelephoneEmailData("89878786787", "roiulatypova@gmail.com"), new ContactBirthdayData("10", "November", "1994"), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
  }
}
