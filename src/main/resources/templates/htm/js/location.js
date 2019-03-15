// 百度地图API功能

function getLocation(){   
	if (navigator.geolocation){   
	 navigator.geolocation.getCurrentPosition(showPosition,showError);  
	}else{   
	alert("浏览器不支持地理定位。");   
	}   
}  	

function showError(error){   
	switch(error.code) {   
	case error.PERMISSION_DENIED:   
	alert("定位失败,用户拒绝请求地理定位");   
	break;   
	case error.POSITION_UNAVAILABLE:   
	alert("定位失败,位置信息是不可用");   
	break;   
	case error.TIMEOUT:   
	alert("定位失败,请求获取用户位置超时");   
	break;   
	case error.UNKNOWN_ERROR:   
	alert("定位失败,定位系统失效");   
	break;   
	}   
}  


 //百度地图定位
function showPosition(position){  
		var latlon = position.coords.latitude+','+position.coords.longitude; 
		var latlon1='28.703887,115.975741';
		//在百度地图上，拾取的 要反过来写
		//经度,维度---------维度,经度
		//alert(latlon+"|"+latlon1);
		//baidu   
		var url = "http://api.map.baidu.com/geocoder/v2/?ak=egEgREa8RmwN92DyXG5YuDD5nTdlzvTu&callback=renderReverse&location="+latlon1+"&output=json&pois=0";   
		$.ajax({   
				type: "GET",   
				dataType: "jsonp",   
				url: url,   
		beforeSend: function(){   
				$("#baidu_geo").html('正在定位...');   
				},   
		success: function (json,req) {  
			if(json.status==0){   
				  var city=(json.result.addressComponent.city).slice(0,-1);
				// return city;
				$("#baidu_geo").html(city);  
			// alert((json.result.addressComponent.city).slice(0,-1));
			}   
		},   
		error: function (XMLHttpRequest, textStatus, errorThrown) {   
			$("#baidu_geo").html(latlon+"地址位置获取失败");   
			}   
		});   
	}; 	