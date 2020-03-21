package com.xlh.raccoon.lib.easyasynctask;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class EasyTaskProgressBar extends AlertDialog {

  private View view;
  private TextView msgTv;

  public EasyTaskProgressBar(Context context) {
    super(context);
    view = View.inflate(context, R.layout.easy_task_progress_bar, null);
    msgTv = view.findViewById(R.id.msg_tv);
    setView(view);
    getWindow().setDimAmount(0f);
  }

  public void setMsg(CharSequence charSequence) {
    msgTv.setText(charSequence);
  }
}
