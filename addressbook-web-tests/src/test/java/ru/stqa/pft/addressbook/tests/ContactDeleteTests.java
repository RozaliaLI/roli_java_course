package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeleteTests extends TestBase {

  @Test
  public void testContactDelete() throws Exception {
    app.getNavigationHelper().selectItem();
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().closeAlert();
  }
}
