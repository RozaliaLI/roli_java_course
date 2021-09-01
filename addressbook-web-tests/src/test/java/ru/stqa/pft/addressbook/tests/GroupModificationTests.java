package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup (new GroupData("Base", "Test", "Description"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getNavigationHelper().selectItem(before.size() - 1);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(before.get(before.size() - 1).getId(), "Test0", "Test 0", "Test 3");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()-1);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
