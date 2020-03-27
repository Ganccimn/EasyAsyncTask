package com.xlh.raccoon.easyasynctask;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xlh.raccoon.lib.easyasynctask.EasyAsyncTask;
import com.xlh.raccoon.lib.easyasynctask.EasyCallback;
import com.xlh.raccoon.lib.easyasynctask.EasyTask;
import com.xlh.raccoon.lib.easyasynctask.EasyTaskProgressBar;
import com.xlh.raccoon.lib.easyasynctask.ForceThrowException;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    EasyAsyncTask.builder(this, String.class, Integer.class)
        .addListener(new EasyCallback<String, Integer>() {
          @Override
          public void onInt(EasyTask<String, Integer> easyTask) {
            easyTask.execute("abc");
          }

          @Override
          public void onStart(EasyTaskProgressBar easyTaskProgressBar) {
            easyTaskProgressBar.show();
          }

          @Override
          public Integer onWork(EasyTask<String, Integer> easyTask, String... strings) {
            for (int i = 0; i <= 10; i++) {
              easyTask.updateProgress("已下载%s%%", i * 10);
              SystemClock.sleep(1000);
              throw new ForceThrowException(1, "手动异常");
            }
            return null;
          }

          @Override
          public void onFinish(Integer integer) {
            Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onFail(int code, String msg, String exceptionMsg) {
            Toast.makeText(MainActivity.this, "下载失败  错误信息：" + exceptionMsg, Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onProgressUpdate(EasyTaskProgressBar easyTaskProgressBar, String... strings) {
            easyTaskProgressBar.setMsg(strings[0]);
          }
        });
  }
}
