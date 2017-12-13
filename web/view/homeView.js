define(function(require, exports, module) {
	console.debug("homeView start!");
	var Tclass = require("js/viewController.js");

	var Class = Tclass.extend({
		initialize: function(option) {
			console.debug("homeView initialize!");
			var iSID = true;

			this.$$("homeView_header_content").append(gTemplate.homeViewNavigation.render(mFanli.getMessageById("homeViewNavigation")));

			this.$$("homeView_welcome").setText("homeViewWelcome", iSID);
			this.$$("homeView_resume_text").setText("homeViewResume", iSID);

			var date = this.getDate();
			this.$$("homeView_date_week").setText(date.week);
			this.$$("homeView_date_day").setText(date.day);
			this.$$("homeView_date_year").setText(date.year);

			this.$$("homeView_radom").append(gTemplate.homeViewArticle.render(mFanli.getMessageById("homeViewArticleRadom")));
			this.$$("homeView_near").append(gTemplate.homeViewArticle.render(mFanli.getMessageById("homeViewArticleNear")));

			this.setWeather();

			this.bind();

			this.$$("weather_icon").click(function () {
                var request = {
                    subscriberId: "1",
                    category: "技术日志",
                    blogTitle: "lallfdlasldflasd",
                    blog: "askdfhjasklgaslh"
                };
                $.when(mFanli.AddBlog(request)).done(function(result) {
                    result && result.returnCode === "000000000" && alert("日志已入库");
                });
            });
		},

		bind: function() {
			var self = this;
			this.$$("homeView_resume_img").mouseover(function() {
				$("#homeView_resume_img").animate({
					width: "125px",
					height: "222px",
					margin: "3px auto"
				}, "fast");
			});

			$(".photo_list").mouseover(function() {
				$(".photo_list").css('animation-play-state', 'paused');
			}).mouseout(function() {
				$(".photo_list").css('animation-play-state', 'running');
			});


			this.$$("homeView_creat").click(function(event) {
				gWindowManagement.pushHomeView("blogEditorView");
			});

			this.$$("homeView_resume_img").click(function(event) {
				gWindowManagement.pushHomeView("resumeView");
			});
			

			
		},

		setWeather: function() {
			var self = this;
			$.when(mFanli.getWeather()).done(function(weather) {
				console.debug(weather);

				imgUrl = "./img/weather/" + weather.HeWeather6[0].now.cond_code + ".png";

				self.$$("weather_icon").css("background-image", "url(" + imgUrl + ")");
				self.$$("weather_title").setText(weather.HeWeather6[0].now.cond_txt);
				self.$$("weather_temperature").setText(weather.HeWeather6[0].now.fl + "℃/" + weather.HeWeather6[0].now.tmp + "℃");
			});
		},

		getDate: function() {
			var week = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
			var number = ["〇", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"];
			var now = new Date();
			var month = number[now.getMonth() + 1];
			var year = "";	
			_.each((now.getFullYear() + "").split(""), function(y) {
				year += number[y];
			});
			
			return {
				week: week[now.getDay()],
				day: now.getDate(),
				year: year + "年" + month + "月"
			};
		}
	});

	return Class;
});