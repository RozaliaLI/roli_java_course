package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactBirthdayData;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactGroup;
import ru.stqa.pft.addressbook.model.ContactTelephoneEmailData;
import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Lilia").withLastName("Popova"),
              new ContactGroup().withGroup("Test2"),
              new ContactTelephoneEmailData().withMobile("89878786787").withEmail("roiulatypova@gmail.com"),
              new ContactBirthdayData().withBday("10").withBmonth("November").withByear("1994"), true);
      app.contact().homePage();
    }
  }

  @Test
  public void testContactDelete() {
    List<ContactData> before = app.contact().list();
    int index = before.size()-1;
    app.contact().select(index);
    app.contact().delete();
    app.waitTime();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size()-1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }
}
