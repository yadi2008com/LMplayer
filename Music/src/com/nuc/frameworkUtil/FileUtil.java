package com.nuc.frameworkUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * 日期工具类
 * @author yadi
 *
 */

public class FileUtil {

	private static final String TMP_DIR_FLAG_FILE = "__removable__.tmp";

	public static final int BUFFER_SIZE = 4096;

	/**
	 * 拷贝文件
	 * @param in 输入流
	 * @param out 输出流
	 * @return 拷贝
	 * @throws IOException 
	 */
	public static int copy(InputStream in, OutputStream out) throws IOException {
		try {
			int byteCount = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
				byteCount += bytesRead;
			}
			out.flush();
			return byteCount;
		} finally {
			try {
				in.close();
			} catch (IOException ex) {
				System.out.print("Could not close InputStream"+ex.getMessage());
			}
			try {
				out.close();
			} catch (IOException ex) {
				System.out.print("Could not close OutputStream"+ex.getMessage());
			}
		}
	}
	/**
	 * 保存至
	 * @param data 拷贝数据
	 * @param filename 文件名
	 * @throws IOException 
	 */
	public static void saveTo(byte[] data, String filename) throws IOException {
		if (data == null || data.length == 0) {
			return;
		}
		mkdirs(filename.substring(0, filename.lastIndexOf(File.separator)),
				false);
		OutputStream out = new FileOutputStream(filename);
		try {
			out.write(data);
			out.flush();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				System.out.print("Could not close OutputStream" +e.getMessage());
			}
		}
	}
	/**
	 * 保存至
	 * @param in 输入流
	 * @param path 保存路径
	 * @param name 文件名
	 * @throws IOException 
	 */
	public static void saveTo(InputStream in, String path, String name)
			throws IOException {
		copy(in, new FileOutputStream(joinPath(path, name)));
	}

	/**
	 * 保存至
	 * @param data 保存数据
	 * @param filename 文件名
	 * @throws Exception 
	 */
	public static void saveTo(String data, String filename) throws Exception {
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(filename), "UTF-8"));
		try {
			out.write(data);
			out.flush();
		} finally {
			try {
				out.close();
				throw new Exception("写入文件失败！！！！");
			} catch (IOException e) {
				System.out.print("Could not close BufferedWriter"+e.getMessage());
			}
		}
	}

	/**
	 * 保存唯一
	 * @param in 输入流
	 * @param path 路径
	 * @param ext 扩展名
	 * @return 保存文件名
	 * @throws IOException 
	 */
	public static String saveToUnique(InputStream in, String path, String ext)
			throws IOException {
		String name = null;
		File file = null;

		do {
			name = System.currentTimeMillis() + "." + ext;
			file = new File(joinPath(path, name));
		} while (file.exists());

		if (name != null) {
			saveTo(in, path, name);
		}

		return name;
	}

	/**
	 * 合并路径
	 * @param path1 路径1
	 * @param path2 路径2
	 * @return 合并路径
	 */
	public static String joinPath(String path1, String path2) {
		return joinPath(path1, path2, File.separator);
	}

	/**
	 * 合并URL
	 * @param path1 url1
	 * @param path2 url2
	 * @return 合并Url
	 */
	public static String joinUrl(String path1, String path2) {
		return joinPath(path1, path2, "/");
	}

	/**
	 * 合并路径
	 * @param path1 路径1
	 * @param path2 路径2
	 * @param separator 分隔符
	 * @return 合并路径
	 */
	public static String joinPath(String path1, String path2, String separator) {
		if (!path1.endsWith(separator)) {
			path1 += separator;
		}
		if (path2.startsWith(separator)) {
			path2 = path2.substring(1);
		}
		return path1 + path2;

	}
	
	/**
	 * 创建临时文件夹
	 * @param path 路径
	 * @param tmp 是否删除
	 */
	public static void mkdirs(String path, boolean tmp) {
		mkdirs(path);
		File tmpdirFlag = new File(joinPath(path, TMP_DIR_FLAG_FILE));
		if (tmp) {
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(tmpdirFlag);
				fos.write("removable".getBytes());
			} catch (Exception e) {
				
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						System.out.print("Could not close OutputStream"+e.getMessage());
					}
				}
			}
		} else {
			if (tmpdirFlag.exists()) {
				tmpdirFlag.delete();
			}
		}
	}

	/**
	 * 创建文件夹
	 * @param path 路径
	 */
	public static void mkdirs(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	/**
	 * 是否存在
	 * @param filename 文件名
	 * @return 是否存在
	 */
	public static boolean exist(String filename) {
		return filename != null && new File(filename).exists();
	}
}
