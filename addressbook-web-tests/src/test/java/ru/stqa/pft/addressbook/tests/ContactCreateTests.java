package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactBirthdayData;
import ru.stqa.pft.addressbook.model.ContactNameData;
import ru.stqa.pft.addressbook.model.ContactTelephoneEmailData;

public class ContactCreateTests extends TestBase{

  @Test
  public void testContactCreate() throws Exception {
    app.getContactHelper().addNewContact();
    app.getContactHelper().fillContactForm(new ContactNameData("Lilia", "Latypova"), new ContactTelephoneEmailData("89878786787", "roiulatypova@gmail.com"), new ContactBirthdayData("10", "November", "1994"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToHomePage();
  }

}
