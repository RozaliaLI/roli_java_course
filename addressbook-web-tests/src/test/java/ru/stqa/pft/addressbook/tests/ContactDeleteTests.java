package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactBirthdayData;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactTelephoneEmailData;

public class ContactDeleteTests extends TestBase {

  @Test
  public void testContactDelete() throws Exception {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Lilia", "Popova", null), new ContactTelephoneEmailData("89878786787", "roiulatypova@gmail.com"), new ContactBirthdayData("10", "November", "1994"), true);
      app.getNavigationHelper().goToHomePage();
    }
    app.getNavigationHelper().selectItem();
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().closeAlert();
  }
}
