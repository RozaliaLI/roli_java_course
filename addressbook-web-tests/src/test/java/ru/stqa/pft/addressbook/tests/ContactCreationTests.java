package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreate() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Lilia", "Latypova");
    ContactGroup contactGroup = new ContactGroup("NewGroup");
    ContactTelephoneEmailData phoneEmail = new ContactTelephoneEmailData("89878786787", "roiulatypova@gmail.com");
    ContactBirthdayData birthDay = new ContactBirthdayData("10", "November", "1994");
    app.getContactHelper().createContact(contact, contactGroup, phoneEmail, birthDay, true);
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId = (k1, k2) -> Integer.compare(k1.getId(), k2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }

}
