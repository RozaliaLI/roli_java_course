package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactBirthdayData;
import ru.stqa.pft.addressbook.model.ContactNameData;
import ru.stqa.pft.addressbook.model.ContactTelephoneEmailData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  FirefoxDriver wd;

  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;

  public void init() {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    login("admin", "secret");
  }

  public void stop() {
   wd.quit();
  }

  private void login(String username, String password) {
    wd.get("http://localhost/addressbook/");
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
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

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
