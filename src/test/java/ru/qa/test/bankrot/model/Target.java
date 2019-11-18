package ru.qa.test.bankrot.model;



public class Target {
  private String serverType;
  private String serverURI;
  private boolean enableVNC;


  public Target() {  }

  public String getServerType() {
    return serverType;
  }

  public String getServerURI() {
    return serverURI;
  }

  public boolean getEnableVNC() {
    return enableVNC;
  }
}
