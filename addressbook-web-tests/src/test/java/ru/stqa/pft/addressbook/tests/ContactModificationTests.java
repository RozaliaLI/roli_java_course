package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactBirthdayData;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactGroup;
import ru.stqa.pft.addressbook.model.ContactTelephoneEmailData;
import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Lilia").withLastName("Popova"), new ContactGroup().withGroup("NewGroup"),
              new ContactTelephoneEmailData().withMobile("89878786787").withEmail("roiulatypova@gmail.com"),
              new ContactBirthdayData().withBday("10").withBmonth("November").withByear("1994"), true);
      app.contact().homePage();
    }
  }

  @Test
  public void testContactModification() throws Exception {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    app.contact().initContactModification(modifiedContact);
    ContactData contactData = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("Lilia").withLastName("Latypova");
    ContactGroup contactGroup = new ContactGroup().withGroup("NewGroup");
    ContactTelephoneEmailData phoneEmail = new ContactTelephoneEmailData().withMobile("89878786787").withEmail("roiulatypova@gmail.com");
    ContactBirthdayData birthDay = new ContactBirthdayData().withBday("10").withBmonth("November").withByear("1994");
    app.contact().modify(contactData, contactGroup, phoneEmail, birthDay);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contactData);
    Assert.assertEquals(before, after);
  }

}
