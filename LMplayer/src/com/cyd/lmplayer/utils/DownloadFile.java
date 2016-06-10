package com.cyd.lmplayer.utils;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class DownloadFile extends Activity implements OnUploadListener {
	// 服务器路径
	private String urlString = "http://169.254.30.52:8080/Music";
	/**
	 * 上传文件的路径
	 */
	String filePath;
	/**
	 * 上传的文件名
	 */
	String fileName;
	ProgressDialog dialog;
	/**
	 * 在读取文件流的时候，同一进度会多次回调，通过这个标记，只有在进度更新的情况下才会更新ui 节省资源
	 */
	int oldProcess;

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Log.i("process", "process" + msg.arg1);
			dialog.setProgress(msg.arg1);
			// 第一次没有显示dialog的时候显示dialog
			if (!dialog.isShowing()) {
				Log.i("process", "show");
				dialog.show();
			} else {
				if (msg.arg1 == 100) {// 提示用户上传完成
					dialog.dismiss();
					//toast("上传完成！");
				}
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		initProgressDialog();
	}

	public void upLoad(View v) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			// toast("上传");
			String sdcardPath = Environment.getExternalStorageDirectory()
					.getAbsolutePath();
			filePath = sdcardPath + "/songFile/";
			File file = new File(filePath);
			fileName = file.list()[4];
			filePath += fileName;
			Log.i("file.size", "size=" + file.list().length + "filePath"
					+ filePath);
		} else {
			toast("没有内存卡");
			return;
		}
		new Thread() {
			public void run() {
				try {
					String response = HttpUtil.sendFile(urlString, filePath,
							fileName, DownloadFile.this);
					Log.i("response", "response" + response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
	}

	@Override
	public void onUpload(double process) {
		process = process * 100;
		int currentProcess = (int) process;
		dialog.setProgress(currentProcess);
		// 避免重复发消息,可以把if给去掉看看会发生什么
		if (currentProcess > oldProcess) {
			Message msg = handler.obtainMessage();
			msg.arg1 = (int) process;
			handler.sendMessage(msg);
		}
		oldProcess = currentProcess;
	}

	public void initProgressDialog() {
		dialog = new ProgressDialog(this);
		dialog.setMax(100);
		dialog.setProgress(0);
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		dialog.setCancelable(false);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setTitle("正在努力上传...");
	}

	public void toast(String text) {
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT)
				.show();
	}

}