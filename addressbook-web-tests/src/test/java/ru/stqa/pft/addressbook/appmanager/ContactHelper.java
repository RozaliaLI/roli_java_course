package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactBirthdayData;
import ru.stqa.pft.addressbook.model.ContactNameData;
import ru.stqa.pft.addressbook.model.ContactTelephoneEmailData;

public class ContactHelper {
  private FirefoxDriver wd;

  public ContactHelper(FirefoxDriver wd) {
    this.wd = wd;
  }

  public void addNewContact() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void submitContactCreation() {
    wd.findElement(By.xpath("//input[21]")).click();
  }

  public void fillContactForm(ContactNameData contactNameData, ContactTelephoneEmailData contactTelephoneEmailData, ContactBirthdayData contactBirthdayData) {
    wd.findElement(By.name("firstname")).sendKeys(contactNameData.getFirstName());
    wd.findElement(By.name("lastname")).sendKeys(contactNameData.getLastName());
    wd.findElement(By.name("mobile")).sendKeys(contactTelephoneEmailData.getMobile());
    wd.findElement(By.name("email")).sendKeys(contactTelephoneEmailData.getEmail());
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactBirthdayData.getBday());
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactBirthdayData.getBmonth());
    wd.findElement(By.name("byear")).sendKeys(contactBirthdayData.getByear());
  }
}
