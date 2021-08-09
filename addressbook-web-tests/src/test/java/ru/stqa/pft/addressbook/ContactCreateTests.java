package  ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class ContactCreateTests {
  private WebDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    login("admin", "secret");
  }

  @Test
  public void testContactCreate() throws Exception {
    addNewContact();
    fillContactForm(new ContactNameData("Rozalia", "Latypova"), new ContactTelephoneEmailData("89878786787", "roiulatypova@gmail.com"), new ContactBirthdayData("10", "May", "1994"));
    submitContactCreation();
    returnToHomePage();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();
  }

  private void returnToHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  private void submitContactCreation() {
    wd.findElement(By.xpath("//input[21]")).click();
  }

  private void fillContactForm(ContactNameData contactNameData, ContactTelephoneEmailData contactTelephoneEmailData, ContactBirthdayData contactBirthdayData) {
    wd.findElement(By.name("firstname")).sendKeys(contactNameData.getFirstName());
    wd.findElement(By.name("lastname")).sendKeys(contactNameData.getLastName());
    wd.findElement(By.name("mobile")).sendKeys(contactTelephoneEmailData.getMobile());
    wd.findElement(By.name("email")).sendKeys(contactTelephoneEmailData.getEmail());
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactBirthdayData.getBday());
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactBirthdayData.getBmonth());
    wd.findElement(By.name("byear")).sendKeys(contactBirthdayData.getByear());
  }

  private void addNewContact() {
    wd.findElement(By.linkText("add new")).click();
  }

  private void login(String username, String password) {
    wd.get("http://localhost/addressbook/delete.php?part=1;");
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

}
