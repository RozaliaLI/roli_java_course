package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactBirthdayData;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactGroup;
import ru.stqa.pft.addressbook.model.ContactTelephoneEmailData;
import java.util.Set;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreate() throws Exception {
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstName("Lilia").withLastName("Latypova");
    ContactGroup contactGroup = new ContactGroup().withGroup("NewGroup");
    ContactTelephoneEmailData phoneEmail = new ContactTelephoneEmailData().withMobile("89878786787").withEmail("roiulatypova@gmail.com");
    ContactBirthdayData birthDay = new ContactBirthdayData().withBday("10").withBmonth("November").withByear("1994");
    app.contact().create(contact, contactGroup, phoneEmail, birthDay, true);
    app.contact().homePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(after, before);
  }

}
