package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactBirthdayData;
import ru.stqa.pft.addressbook.model.ContactNameData;
import ru.stqa.pft.addressbook.model.ContactTelephoneEmailData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() throws Exception {
    app.getNavigationHelper().selectItem();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactNameData("Lilia", "Latypova"), new ContactTelephoneEmailData("89878786787", "roiulatypova@gmail.com"), new ContactBirthdayData("10", "November", "1994"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
  }
}
