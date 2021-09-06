package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() throws Exception {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Lilia", "Popova"), new ContactGroup("NewGroup"), new ContactTelephoneEmailData("89878786787", "roiulatypova@gmail.com"), new ContactBirthdayData("10", "November", "1994"), true);
      app.getNavigationHelper().goToHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size()-1);
    ContactData contactData = new ContactData(before.get(before.size()-1).getId(), "Lilia", "Latypova");
    ContactGroup contactGroup = new ContactGroup("NewGroup");
    ContactTelephoneEmailData phoneEmail = new ContactTelephoneEmailData("89878786787", "roiulatypova@gmail.com");
    ContactBirthdayData birthDay = new ContactBirthdayData("10", "November", "1994");
    app.getContactHelper().fillContactForm(contactData, contactGroup, phoneEmail, birthDay, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()-1);
    before.add(contactData);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
