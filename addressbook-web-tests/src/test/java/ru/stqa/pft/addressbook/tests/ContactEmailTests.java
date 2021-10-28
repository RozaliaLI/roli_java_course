package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactBirthdayData;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactGroup;
import ru.stqa.pft.addressbook.model.ContactTelephoneEmailData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase{

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
  public void testContactEmail() {
    app.contact().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmailFirst(), contact.getEmailSecond(), contact.getEmailThird())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }
}

