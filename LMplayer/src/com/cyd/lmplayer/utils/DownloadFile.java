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
	// ������·��
	private String urlString = "http://169.254.30.52:8080/Music";
	/**
	 * �ϴ��ļ���·��
	 */
	String filePath;
	/**
	 * �ϴ����ļ���
	 */
	String fileName;
	ProgressDialog dialog;
	/**
	 * �ڶ�ȡ�ļ�����ʱ��ͬһ���Ȼ��λص���ͨ�������ǣ�ֻ���ڽ��ȸ��µ�����²Ż����ui ��ʡ��Դ
	 */
	int oldProcess;

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Log.i("process", "process" + msg.arg1);
			dialog.setProgress(msg.arg1);
			// ��һ��û����ʾdialog��ʱ����ʾdialog
			if (!dialog.isShowing()) {
				Log.i("process", "show");
				dialog.show();
			} else {
				if (msg.arg1 == 100) {// ��ʾ�û��ϴ����
					dialog.dismiss();
					//toast("�ϴ���ɣ�");
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
			// toast("�ϴ�");
			String sdcardPath = Environment.getExternalStorageDirectory()
					.getAbsolutePath();
			filePath = sdcardPath + "/songFile/";
			File file = new File(filePath);
			fileName = file.list()[4];
			filePath += fileName;
			Log.i("file.size", "size=" + file.list().length + "filePath"
					+ filePath);
		} else {
			toast("û���ڴ濨");
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
		// �����ظ�����Ϣ,���԰�if��ȥ�������ᷢ��ʲô
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
		dialog.setTitle("����Ŭ���ϴ�...");
	}

	public void toast(String text) {
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT)
				.show();
	}

}