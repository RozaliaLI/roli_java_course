package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactBirthdayData;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactGroup;
import ru.stqa.pft.addressbook.model.ContactTelephoneEmailData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() throws Exception {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Lilia", "Popova"), new ContactGroup("Test2"), new ContactTelephoneEmailData("89878786787", "roiulatypova@gmail.com"), new ContactBirthdayData("10", "November", "1994"), true);
      app.getNavigationHelper().goToHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().selectItem(before.size() - 1);
    app.getContactHelper().initContactModification();
    ContactData nameGroup = new ContactData("Lilia", "Latypova");
    ContactGroup contactGroup = new ContactGroup("Test3");
    ContactTelephoneEmailData phoneEmail = new ContactTelephoneEmailData("89878786787", "roiulatypova@gmail.com");
    ContactBirthdayData birthDay = new ContactBirthdayData("10", "November", "1994");
    app.getContactHelper().fillContactForm(nameGroup, contactGroup, phoneEmail, birthDay, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()-1);
    before.add(nameGroup);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
