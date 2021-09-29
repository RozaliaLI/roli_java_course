package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactBirthdayData;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactGroup;
import ru.stqa.pft.addressbook.model.ContactTelephoneEmailData;
import java.util.Set;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Lilia").withLastName("Popova"),
              new ContactGroup().withGroup("Test2"),
              new ContactTelephoneEmailData().withMobile("89878786787").withEmail("roiulatypova@gmail.com"),
              new ContactBirthdayData().withBday("10").withBmonth("November").withByear("1994"), true);
      app.contact().homePage();
    }
  }

  @Test
  public void testContactDelete() {
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.waitTime();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size()-1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }
}
