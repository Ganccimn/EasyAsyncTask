package com.xlh.raccoon.lib.easyasynctask;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import java.lang.ref.WeakReference;

public class SafeHandler extends Handler {

  private WeakReference<SafeHandlerCallback> weakReference;

  private static HandlerThread getHandlerThread() {
    HandlerThread handlerThread = new HandlerThread("SafeHandler");
    handlerThread.start();
    return handlerThread;
  }

  public SafeHandler(SafeHandlerCallback safeHandlerCallback) {
    super(getHandlerThread().getLooper());
    weakReference = new WeakReference<>(safeHandlerCallback);
  }

  @Override
  public void handleMessage(Message msg) {
    super.handleMessage(msg);
    SafeHandlerCallback safeHandlerCallback = weakReference.get();
    if (safeHandlerCallback != null) {
      safeHandlerCallback.handleMessageCallback(msg);
    }
  }

  public interface SafeHandlerCallback {
    void handleMessageCallback(Message msg);
  }
}
