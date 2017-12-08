define(function(require, exports, module) {
console.debug("eBase start!");

var contants = require("js/contants");

window.eBase = {};

(function(core) {
	core.getMessageById = function(id){
		console.debug("eBase getMessageById");

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
					console.debug("eBase getMessageById ajax success");
					mes = date;
					core.string_zh_catch = date;
				},
				error: function(res) {
					console.debug("eBase getMessageById ajax error res:%s", res);
				}
			});

			if(mes[id]) {
				return mes[id];
			} else {
				return "NO DATE";
			}
		}
	}
})(eBase || {});

(function(core) {
	core.ajax = function(url, isAsync) {
		var data = "";
		$.ajax({
			type: "GET",
			dateType: "json",
			async: isAsync,
			url: url,
			success: function(data) {
				console.debug("eBase ajax success");
				data = date;
			},
			error: function(res) {
				console.debug("eBase ajax error res:%s", res);
			}
		});
		return data;
	}
})(eBase);

(function(core) {
	core.getWeather = function(city){
		console.debug("eBase getWeather");
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
					console.debug("eBase getWeather success");
					// data = data;
					def.resolve(data);
					
				},
				error: function(res) {
					console.debug("eBase getWeather error res:%s", res);
					def.reject(res);
				}
			});
		});

		 return def;
	}
})(eBase);
});
