/*获取音乐列表*/
var path = require("path");//处理目录路径
var media = path.join(_dirname,"../songFile");//获得media目录(当前目录的目录位置,)
funtion(req,res){
	var fs = require("fs");//模块，文件系统,下面readdir读取文件,异步读取
	fs.readdir(media,function(err,names){
		if(err){
			console.log(err);
		}else{
			res.render('music',{music:names});
		}
	});
}