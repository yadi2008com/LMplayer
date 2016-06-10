package com.cyd.lmplayer.utils;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	public static String sendFile(String urlPath, String filePath,
			String newName,OnUploadListener listener) throws Exception {
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "******";

		URL url = new URL(urlPath);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		//������Ҫ��setDoInput�����Ĳ���ֵ��Ϊtrue
		con.setDoInput(true);
		//�ϴ���Ҫ��setDoOutput�����Ĳ���ֵ��Ϊtrue
		con.setDoOutput(true);
		//��ֹHttpURLConnectionʹ�û���
		con.setUseCaches(false);
		//ʹ��POST���󣬱����д
		con.setRequestMethod("POST");
		//������������http����ͷ��Ϣ
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");
		//��ģ��webҳ������������ϴ��ļ�ʱ��ÿ���ļ��Ŀ�ͷ��Ҫ��һ���ֽ����
		//�ֽ����Ҫ��http����ͷ��ָ����boundary������һ���ַ�����һ��Ϊ******
		con.setRequestProperty("Content-Type", "multipart/form-data;boundary="
				+ boundary);
		
		DataOutputStream ds = new DataOutputStream(con.getOutputStream());
		
		ds.writeBytes(twoHyphens + boundary + end);
		//�ϴ��ļ������Ϣ����Щ��Ϣ����������������ϴ��ļ������ļ����ͣ����������ڴ�
		ds.writeBytes("Content-Disposition: form-data; "
				+ "name=\"file1\";filename=\"" + newName + "\"" + end);
		ds.writeBytes(end);

		//����ļ�����������ͨ���������ļ�������������д��FileInputStream��Ϊ�˼����ϴ�����
		CustomFileInputStream fStream = new CustomFileInputStream(filePath);
		fStream.setOnUploadListener(listener);
		/* ����ÿ��д��1024bytes */
		int bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];

		int length = -1;
		// ���ļ���ȡ������������ 
		while ((length = fStream.read(buffer)) != -1) {
			//������д��DataOutputStream�� 
			ds.write(buffer, 0, length);
		}
		ds.writeBytes(end);
		ds.writeBytes(twoHyphens + boundary + twoHyphens + end);

		fStream.close();
		ds.flush();

		//�ϴ�����Ժ��ȡ�������ķ���
		InputStream is = con.getInputStream();
		int ch;
		StringBuffer b = new StringBuffer();
		while ((ch = is.read()) != -1) {
			b.append((char) ch);
		}
		
		ds.close();
		return b.toString();
	}
}
