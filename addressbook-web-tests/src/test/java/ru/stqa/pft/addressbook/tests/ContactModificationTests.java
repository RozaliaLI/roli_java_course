package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    //app.contact().initContactModification(modifiedContact);
    app.contact().initContactModificationById(modifiedContact.getId());
    ContactData contactData = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("Lilia").withLastName("Latypova");
    ContactGroup contactGroup = new ContactGroup().withGroup("NewGroup");
    ContactTelephoneEmailData phoneEmail = new ContactTelephoneEmailData().withMobile("89878786787").withEmail("roiulatypova@gmail.com");
    ContactBirthdayData birthDay = new ContactBirthdayData().withBday("10").withBmonth("November").withByear("1994");
    app.contact().modify(contactData, contactGroup, phoneEmail, birthDay);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contactData)));
  }

}
