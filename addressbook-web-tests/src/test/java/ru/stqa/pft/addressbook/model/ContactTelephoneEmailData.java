package ru.stqa.pft.addressbook.model;

public class ContactTelephoneEmailData {
  private String mobile;
  private String email;

  public ContactTelephoneEmailData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactTelephoneEmailData withEmail(String email) {
    this.email = email;
    return this;
  }

  public String getMobile() {
    return mobile;
  }

  public String getEmail() {
    return email;
  }

}
