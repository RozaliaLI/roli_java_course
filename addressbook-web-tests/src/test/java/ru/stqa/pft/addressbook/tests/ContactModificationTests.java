package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactBirthdayData;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactGroup;
import ru.stqa.pft.addressbook.model.ContactTelephoneEmailData;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Lilia").withLastName("Popova"), new ContactGroup().withGroup("NewGroup"),
              new ContactTelephoneEmailData().withMobile("89878786787").withEmail("roiulatypova@gmail.com"),
              new ContactBirthdayData().withBday("10").withBmonth("November").withByear("1994"), true);
      app.contact().homePage();
    }
  }

  @Test
  public void testContactModification() throws Exception {
    List<ContactData> before = app.contact().list();
    int index = before.size()-1;
    app.contact().initContactModification(index);
    ContactData contactData = new ContactData()
            .withId(before.get(index).getId()).withFirstName("Lilia").withLastName("Latypova");
    ContactGroup contactGroup = new ContactGroup().withGroup("NewGroup");
    ContactTelephoneEmailData phoneEmail = new ContactTelephoneEmailData().withMobile("89878786787").withEmail("roiulatypova@gmail.com");
    ContactBirthdayData birthDay = new ContactBirthdayData().withBday("10").withBmonth("November").withByear("1994");
    app.contact().modify(contactData, contactGroup, phoneEmail, birthDay);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contactData);
    Comparator<? super ContactData> byId = (k1, k2) -> Integer.compare(k1.getId(), k2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
