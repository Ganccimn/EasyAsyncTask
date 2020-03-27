package com.xlh.raccoon.lib.easyasynctask;

public class ForceThrowException extends RuntimeException {

  private int code;
  private String msg = "";

  public ForceThrowException(String message) {
    super(message);
  }

  public ForceThrowException(int code, String msg, String message) {
    super(message);
    this.code = code;
    this.msg = msg;
  }

  public ForceThrowException(int code, String msg) {
    super("ForceThrowException");
    this.code = code;
    this.msg = msg;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
