package ru.stqa.pft.addressbook.model;

public class ContactNameData {
  private final String firstName;
  private final String lastName;

  public ContactNameData(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }
}
