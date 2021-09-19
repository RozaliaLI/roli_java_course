package ru.stqa.pft.addressbook.model;

public class ContactBirthdayData {
  private String bday;
  private String bmonth;
  private String byear;

  public ContactBirthdayData withBday(String bday) {
    this.bday = bday;
    return this;
  }

  public ContactBirthdayData withBmonth(String bmonth) {
    this.bmonth = bmonth;
    return this;
  }

  public ContactBirthdayData withByear(String byear) {
    this.byear = byear;
    return this;
  }

  public String getBday() {
    return bday;
  }

  public String getBmonth() {
    return bmonth;
  }

  public String getByear() {
    return byear;
  }
}
