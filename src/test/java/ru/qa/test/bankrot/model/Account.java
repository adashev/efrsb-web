package ru.qa.test.bankrot.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

  private String conturname;
  private String baseurl;
  private String certificate;
  private ArrayList<User> users;

  public Account() {
  }

  public String getConturname() {
    return conturname;
  }

  public String getBaseurl() {
    return baseurl;
  }

  public String getCertificate() {
    return certificate;
  }

  public ArrayList<User> getUsers() {
    return users;
  }
}
