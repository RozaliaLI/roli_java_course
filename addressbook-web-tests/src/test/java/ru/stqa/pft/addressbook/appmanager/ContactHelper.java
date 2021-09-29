package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactBirthdayData;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactGroup;
import ru.stqa.pft.addressbook.model.ContactTelephoneEmailData;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void addNewContact() {
    click(By.linkText("add new"));
  }

  public void submitContactCreation() {
    click(By.xpath("//input[21]"));
  }

  public void fillContactForm(ContactData contactData, ContactGroup contactGroup, ContactTelephoneEmailData contactTelephoneEmailData, ContactBirthdayData contactBirthdayData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("mobile"), contactTelephoneEmailData.getMobile());
    type(By.name("email"), contactTelephoneEmailData.getEmail());
    click(By.name("bday"));
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactBirthdayData.getBday());
    click(By.name("bmonth"));
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactBirthdayData.getBmonth());
    type(By.name("byear"), contactBirthdayData.getByear());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactGroup.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactModification(ContactData contact) {
    selectContactById(contact.getId());
    wd.findElement(By.xpath("//img[@alt='Edit']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id +"']")).click();
  }

  public void create(ContactData name, ContactGroup group, ContactTelephoneEmailData contacts, ContactBirthdayData birthday, boolean b) {
    addNewContact();
    fillContactForm( name, group,  contacts, birthday, b);
    submitContactCreation();
  }

  public void modify(ContactData contactData, ContactGroup contactGroup, ContactTelephoneEmailData phoneEmail, ContactBirthdayData birthDay) {
    fillContactForm(contactData, contactGroup, phoneEmail, birthDay, false);
    submitContactModification();
    homePage();
  }

  public void homePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    } click(By.linkText("home"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));;
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements((By.tagName("td")));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstName(firstname).withLastName(lastname);
      contacts.add(contact);
    }
    return contacts;
  }

}
