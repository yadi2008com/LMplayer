<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+
   request.getServerName()+":"+request.getServerPort();
   String projectUrl=basePath+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
  	<meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
    <meta name="Keywords" content="音乐可视化,webAudio,passion,passionate,music,音乐,HTML5,canvas,CSS3,自适应"> 
    <meta name="Description" content="利用HTML5 webAudio API 和canvas API以及CSS3制作成的自适应的音乐可视化应用">
    <meta name="Author" content="Doving">
    <meta name="Robots" content= "all">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=projectUrl%>Css/music.css"/>
    <title>LOVE MUSIC 音乐播放器</title>
<script type="text/javascript">
$.ajax({
	url:'<%=path%>/SongListServlet?',
	type:'post',
	dataType:'json',
	success:function(data){
		if(null!=data && ""!=data){
			var songs=data.songsVoInfoList;
			console.log(songs);
			$.each(songs,function(num,item){
				$("#list").append("<li>"+item.songName+"</li>");
			});
		}
	}
	
}); 
</script>
  </head>

  <body>
  	<header>
  		<h1>LOVE MUSIC</h1>
      <ul class="type">
        <li class="selected">Dot</li>
        <li>Column</li>
      </ul>
      <p>Volume <input id="volume" type="range" min="0" max="100" value="60"></p>
      
  	</header>  	
     <div class="left">
     	<ul id="list">
     	</ul>
     </div>
     <div class="right">
     
     </div>
    
  </body>
</html>
