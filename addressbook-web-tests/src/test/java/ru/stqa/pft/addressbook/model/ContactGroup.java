package ru.stqa.pft.addressbook.model;

public class ContactGroup {

  private String group;


  public ContactGroup withGroup(String group) {
    this.group = group;
    return this;
  }

  public String getGroup() {
    return group;
  }

}
