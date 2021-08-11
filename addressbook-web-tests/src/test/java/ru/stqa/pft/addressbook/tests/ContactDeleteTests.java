package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeleteTests extends TestBase {

  @Test
  public void testContactDelete() throws Exception {
    app.getContactHelper().selectContact("19");
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().closeAlert();
  }
}
