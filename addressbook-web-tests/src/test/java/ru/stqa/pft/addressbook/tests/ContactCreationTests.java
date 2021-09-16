package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactBirthdayData;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactGroup;
import ru.stqa.pft.addressbook.model.ContactTelephoneEmailData;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreate() throws Exception {
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData("Lilia", "Latypova");
    ContactGroup contactGroup = new ContactGroup("NewGroup");
    ContactTelephoneEmailData phoneEmail = new ContactTelephoneEmailData("89878786787", "roiulatypova@gmail.com");
    ContactBirthdayData birthDay = new ContactBirthdayData("10", "November", "1994");
    app.contact().create(contact, contactGroup, phoneEmail, birthDay, true);
    app.contact().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (k1, k2) -> Integer.compare(k1.getId(), k2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }

}
