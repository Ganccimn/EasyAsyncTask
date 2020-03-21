package com.xlh.raccoon.lib.easyasynctask;

import android.content.Context;

public class EasyAsyncTask {

  public static <T, R> EasyTask<T, R> builder(Context context, Class<T> param, Class<R> result) {
    return new EasyTask<T, R>().setProgressDialog(context);
  }

  public static <T, R> EasyTask<T, R> builder(Class<T> param, Class<R> result) {
    return new EasyTask<T, R>();
  }
}
