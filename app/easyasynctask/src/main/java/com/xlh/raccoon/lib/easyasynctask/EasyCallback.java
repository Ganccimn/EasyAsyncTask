package com.xlh.raccoon.lib.easyasynctask;

public interface EasyCallback<T, R> {

  void onInt(EasyTask<T, R> easyTask);

  void onStart(EasyTaskProgressBar easyTaskProgressBar);

  R onWork(EasyTask<T, R> easyTask, T... ts);

  void onFinish(R r);

  void onFail(int code, String msg, String exceptionMsg);

  void onProgressUpdate(EasyTaskProgressBar easyTaskProgressBar, String... strings);
}
