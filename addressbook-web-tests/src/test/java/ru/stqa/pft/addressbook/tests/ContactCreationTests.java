package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreate() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstName("Lilia").withLastName("Latypova");
    ContactGroup contactGroup = new ContactGroup().withGroup("NewGroup");
    ContactTelephoneEmailData phoneEmail = new ContactTelephoneEmailData().withMobile("89878786787").withEmail("roiulatypova@gmail.com");
    ContactBirthdayData birthDay = new ContactBirthdayData().withBday("10").withBmonth("November").withByear("1994");
    app.contact().create(contact, contactGroup, phoneEmail, birthDay, true);
    app.contact().homePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
