define(function(require, exports, module) {
console.debug("mFanli start!");

var contants = require("js/contants");

window.mFanli = {};

(function(core) {
	core.getMessageById = function(id){
		console.debug("mFanli getMessageById");

		if (core.string_zh_catch && core.string_zh_catch[id]) {
			return core.string_zh_catch[id];
		} else {
			var mes = {};
			$.ajax({
				type: "GET",
				dateType: "json",
				async: false,
				url: "res/message/string_zh.json",
				success: function(date) {
					console.debug("mFanli getMessageById ajax success");
					mes = date;
					core.string_zh_catch = date;
				},
				error: function(res) {
					console.debug("mFanli getMessageById ajax error res:%s", res);
				}
			});

			if(mes[id]) {
				return mes[id];
			} else {
				return "NO DATE";
			}
		}
	}
})(mFanli || {});

(function(core) {
	core.ajax = function(url, isAsync) {
		var data = "";
		$.ajax({
			type: "GET",
			dateType: "json",
			async: isAsync,
			url: url,
			success: function(data) {
				console.debug("mFanli ajax success");
				data = date;
			},
			error: function(res) {
				console.debug("mFanli ajax error res:%s", res);
			}
		});
		return data;
	}
})(mFanli);

(function(core) {
	core.getWeather = function(city){
		console.debug("mFanli getWeather");
		var def = $.Deferred();
		// var data = "";

		$.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js',function(){  
		  // alert(remote_ip_info.country);//国家  
		  // alert(remote_ip_info.province);//省份  
		  // alert(remote_ip_info.city);//城市 

		  $.ajax({
				type: "GET",
				dataType: 'json',
				async: false,
				url: gConfig.weather_api_url + (remote_ip_info.city || gConfig.default_city) + gConfig.weather_api_key,
				success: function(data) {
					console.debug("mFanli getWeather success");
					// data = data;
					def.resolve(data);
					
				},
				error: function(res) {
					console.debug("mFanli getWeather error res:%s", res);
					def.reject(res);
				}
			});
		});

		 return def;
	}
})(mFanli);

(function(core) {
	core.AddBlog = function(data){
		console.debug("mFanli AddBlog");
		var def = $.Deferred();

		  	$.ajax({
				type: "POST",
				dataType: "JSON",
				data: JSON.stringify(data),
				async: false,
				url: "http://192.168.1.104:8080/JavaWeb/AddBlog",
				success: function(data) {
					console.debug("mFanli AddBlog success");
					// data = data;
					def.resolve(data);
				},
				error: function(res) {
					console.debug("mFanli AddBlog error res:%s", res);
					def.reject(res);
				}
			});
		return def;
	};

		
})(mFanli);

});
