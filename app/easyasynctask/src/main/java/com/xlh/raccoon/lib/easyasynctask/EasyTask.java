package com.xlh.raccoon.lib.easyasynctask;

import android.content.Context;
import android.os.AsyncTask;

public class EasyTask<T, R> extends AsyncTask<T, String, R> {

  private EasyCallback<T, R> easyCallback;
  private EasyTaskProgressBar easyTaskProgressBar;
  private ForceThrowException forceThrowException;

  public EasyTask<T, R> setProgressDialog(Context context) {
    this.easyTaskProgressBar = new EasyTaskProgressBar(context);
    this.easyTaskProgressBar.setCancelable(false);
    return this;
  }

  public void updateProgress(String... strings) {
    super.publishProgress(strings);
  }

  public void updateProgress(String format, Object... objects) {
    super.publishProgress(String.format(format, objects));
  }

  public void addListener(EasyCallback<T, R> easyCallback) {
    this.easyCallback = easyCallback;
    this.easyCallback.onInt(this);
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    easyCallback.onStart(easyTaskProgressBar);
  }

  @Override
  protected R doInBackground(T... ts) {
    R r = null;
    try {
      r = easyCallback.onWork(this, ts);
    } catch (Exception e) {
      this.forceThrowException = new ForceThrowException(e.getMessage());
    }
    return r;
  }

  @Override
  protected void onPostExecute(R r) {
    super.onPostExecute(r);
    if (easyTaskProgressBar != null) {
      easyTaskProgressBar.dismiss();
    }
    if (this.forceThrowException == null) {
      easyCallback.onFinish(r);
    } else {
      easyCallback.onFail(
          this.forceThrowException.getCode(),
          this.forceThrowException.getMsg(),
          this.forceThrowException.getMessage());
    }
  }

  @Override
  protected void onProgressUpdate(String... values) {
    super.onProgressUpdate(values);
    this.easyCallback.onProgressUpdate(easyTaskProgressBar, values);
  }
}
