package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.*;

import java.util.List;

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
    attach(By.name("photo"), contactData.getPhoto());
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

  public void initContactModificationById(int id) {
    /*WebElement checkbox = wd.findElement(By.cssSelector("input[id='" + id +"']"));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();*/

    //wd.findElement(By.xpath("//input[@id='" + id +"']/../../td[8]/a")).click();
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
    wd.findElement(By.cssSelector("div.msgbox"));
    contactCashe = null;
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id +"']")).click();
  }

  public void create(ContactData name, ContactGroup group, ContactTelephoneEmailData contacts, ContactBirthdayData birthday, boolean b) {
    addNewContact();
    fillContactForm( name, group,  contacts, birthday, b);
    submitContactCreation();
    contactCashe = null;
  }

  public void modify(ContactData contactData, ContactGroup contactGroup, ContactTelephoneEmailData phoneEmail, ContactBirthdayData birthDay) {
    fillContactForm(contactData, contactGroup, phoneEmail, birthDay, false);
    submitContactModification();
    contactCashe = null;
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

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCashe = null;

  public Contacts all() {
    if (contactCashe != null) {
      return new Contacts(contactCashe);
    }

    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));;
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements((By.tagName("td")));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      String address = cells.get(3).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname)
              .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
    }
    return contacts;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String emailFirst = wd.findElement(By.name("email")).getAttribute("value");
    String emailSecond = wd.findElement(By.name("email2")).getAttribute("value");
    String emailThird = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.xpath("//textarea[@name='address']")).getText();
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(contact.getFirstName()).withLastName(contact.getLastName())
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmailFirst(emailFirst).withEmailSecond(emailSecond).withEmailThird(emailThird)
            .withAddress(address);
  }
}
