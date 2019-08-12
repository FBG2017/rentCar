function createAjax(){
	var ajax =null;
	try{//Ie6 ---Ie10
		ajax=new ActiveXObject("microsoft.xmlhttp");
	}catch(e1){
		try{//非Ie
			ajax =new XMLHttpRequest();
		}catch(e2){
			alert("你的浏览器不支持ajax,请更换浏览器");
		}
	};
	return ajax;
}