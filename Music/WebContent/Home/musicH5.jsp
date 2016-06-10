<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+
   request.getServerName()+":"+request.getServerPort();
   String projectUrl=basePath+path+"/";
%>
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">  
<script>  
var filesList = new Array();  
var filesIndex = 0;  
var fso = new ActiveXObject("Scripting.FileSystemObject");  
function searchFilesList(filePath){  
    var f = fso.GetFolder(filePath);  
    // 遍历目录  
    var fk = new Enumerator(f.SubFolders);  
    for (; !fk.atEnd(); fk.moveNext()) {  
        filesList[filesIndex++] = fk.item();  
        searchFilesList(fk.item());  
    }  
    // 遍历目录文件  
    var fc = new Enumerator(f.files);  
    for (; !fc.atEnd(); fc.moveNext()) {  
        filesList[filesIndex++] = fc.item();  
    }  
}  
function searchFiles(){  
    searchFilesList(fixfolder.value);  
    // 循环信息信息  
    for (var i=0; i<filesList.length; i++){  
        textarea.innerHTML += filesList[i]+"</br>";  
    }  
}  
</script>  
</head>  
<body bgcolor="#FFFFFF">  
指定文件夹：<input type="text" name="fixfolder" value ="C:\Users\chenyadi\Desktop\Music\Music\WebContent\songFile">  
<input type="button" value ="搜索" onclick="searchFiles()">  
<table >  
<tr>  
<td id = "textarea">  
  
</td>  
</tr>  
</table>  
  
</body>  
</html>  
    