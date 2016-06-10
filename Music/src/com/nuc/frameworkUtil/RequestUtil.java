package com.nuc.frameworkUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**]
 * 
 * @author wodezuiaishinageren
 *
 */
public class RequestUtil {
	/**
	 * 读写文件
	 * @param request
	 * @param storageFileName
	 * @throws Exception 
	 */
	public static int writeSongFile(HttpServletRequest request,int songId) throws Exception{
		  String basePath = request.getServletContext().getRealPath("")+"songFile\\";  
		  System.out.println("上传路径"+basePath+"**********************");
		  int sizeThreshold = 1024 * 64;  //写满该大小的缓存后，存入硬盘中。  
          File repositoryFile = new File(basePath);  
          FileItemFactory factory=new DiskFileItemFactory(sizeThreshold, repositoryFile);
          ServletFileUpload fileUpload=new ServletFileUpload(factory);
          fileUpload.setHeaderEncoding("utf-8");  //设置字符编码  
          fileUpload.setSizeMax(50 * 1024 * 1024); // set every upload file'size less than 50M  
          try {
			@SuppressWarnings("rawtypes")
			List items = fileUpload.parseRequest(request);
			System.out.println("文件信息"+items+"**********************");
			for (Object fileObject : items) {
				if(fileObject instanceof DiskFileItem){
					DiskFileItem item = (DiskFileItem) fileObject;
					String fileName = item.getName();
                    if (fileName != null) {
                        String firstFileName = item.getName().substring(
                                item.getName().lastIndexOf("\\") + 1);
                        String formatName = firstFileName
                                .substring(firstFileName.lastIndexOf("."));// 获取文件后缀名                    
                       return FileUtil.copy(item.getInputStream(), new FileOutputStream(FileUtil.joinPath(basePath, songId + formatName)));
                    }
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
			throw new Exception("上传歌曲失败！！！");
		} 
          throw new Exception("上传歌曲失败！！！");//此句可读性不好，暂时放此处（TODO）
	}
	
	public static void main(String[] args) {
		String fileName="我的爱人.mp3";
		  String formatName = fileName
                  .substring(fileName.lastIndexOf("."));
		  System.out.println(formatName);
	}
	
}
