package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @Test()
  public void testContactCreate() throws Exception {
    Contacts before = app.contact().all();
    File photo = new File("src/test/recources/Check.jpg");
    ContactData contact = new ContactData().withFirstName("Lilia").withLastName("Latypova").withPhoto(photo);
    ContactGroup contactGroup = new ContactGroup().withGroup("NewGroup");
    ContactTelephoneEmailData phoneEmail = new ContactTelephoneEmailData().withMobile("89878786787").withEmail("roiulatypova@gmail.com");
    ContactBirthdayData birthDay = new ContactBirthdayData().withBday("10").withBmonth("November").withByear("1994");
    app.contact().create(contact, contactGroup, phoneEmail, birthDay, true);
    app.contact().homePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testCurrentDir() {
    File currentDir = new File(".");//this point is a current directory
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/recources/Check.jpg");//this point is a current directory
    System.out.println(photo.exists());
  }

}
