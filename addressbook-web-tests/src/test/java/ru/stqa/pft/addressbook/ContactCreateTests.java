package  ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreateTests extends TestBase{

  @Test
  public void testContactCreate() throws Exception {
    addNewContact();
    fillContactForm(new ContactNameData("Lilia", "Latypova"), new ContactTelephoneEmailData("89878786787", "roiulatypova@gmail.com"), new ContactBirthdayData("10", "November", "1994"));
    submitContactCreation();
    returnToHomePage();
  }

}
