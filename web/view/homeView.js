define(function(require, exports, module) {
	console.debug("homeView start!");
	var Tclass = require("js/viewController.js");
    var Tools = require("js/utils/tools.js");

	var Class = Tclass.extend({
        initialize: function (option) {
            console.debug("homeView initialize!");
            var iSID = true;

            // this.$$("homeView_header_content").append(gTemplate.homeViewNavigation.render(mFanli.getMessageById("homeViewNavigation")));
            this.$$("homeView_header_content_home").setText("homeViewHome", iSID);
            this.$$("homeView_header_content_wheel").setText("homeViewWheel", iSID);
            this.$$("homeView_header_content_blog").setText("homeViewBlog", iSID);
            this.$$("homeView_header_content_story").setText("homeViewStory", iSID);
            this.$$("homeView_header_content_idea").setText("homeViewIdea", iSID);

            this.$$("homeView_welcome").setText("homeViewWelcome", iSID);
            this.$$("homeView_resume_text").setText("homeViewResume", iSID);

            var date = this.getDate();
            this.$$("homeView_date_week").setText(date.week);
            this.$$("homeView_date_day").setText(date.day);
            this.$$("homeView_date_year").setText(date.year);

            var radomData = this.getRadomData();
            var nearData = this.getNearData();
            this.$$("homeView_radom").append(gTemplate.homeViewArticle.render(radomData));
            this.$$("homeView_near").append(gTemplate.homeViewArticle.render(nearData));

            this.setWeather();

            this.bind();
        },

        getNearData: function () {
            var getBlogByTimeResponse = mFanli.GetBlogByTime({
                subscriberId: "1",
                endTime: new Date().getTime() + "",
                count: "20"
            });
            if (!Tools.isResultSuccess(getBlogByTimeResponse))
            {
                gWindowManagement.pushGlobView("alertMessageView", "查询日志失败！");
            }

            var blogs = getBlogByTimeResponse.blogs;
            var article = [];
            for (var i = 0; i < blogs.length; i++)
            {
            	article[i] = {
                    category: blogs[i].category,
					content: blogs[i].blogTitle,
					title: blogs[i].blogTitle
				};
            }
            var homeViewArticleRadom = mFanli.getMessageById("homeViewArticleNear");
            homeViewArticleRadom.article = article;

            return homeViewArticleRadom;
        },

		getRadomData: function () {
            var getBlogByTimeResponse = mFanli.GetBlogByTime({
                subscriberId: "1",
                endTime: new Date().getTime() + "",
                count: "20"
            });
			if (!Tools.isResultSuccess(getBlogByTimeResponse))
			{
                gWindowManagement.pushGlobView("alertMessageView", "查询日志失败！");
			}

			var blogs = getBlogByTimeResponse.blogs;
			var article = [];
			for (var i = 0; i < blogs.length; i++)
			{
                article[i] = {
                    category: blogs[i].category,
                    content: blogs[i].blogTitle,
                    title: blogs[i].blogTitle
                };
			}
            var homeViewArticleRadom = mFanli.getMessageById("homeViewArticleRadom");
            homeViewArticleRadom.article = article;

            return homeViewArticleRadom;
        },

        bind: function () {
            var self = this;

            this.$$("homeView_header_content_home").click(function () {
                self.homeHandle();
            });
            this.$$("homeView_header_content_wheel").click(function () {
                self.wheelHandle();
            });
            this.$$("homeView_header_content_blog").click(function () {
                self.blogHandle();
            });
            this.$$("homeView_header_content_story").click(function () {
                self.storyHandle();
            });
            this.$$("homeView_header_content_idea").click(function () {
                self.ideaHandle();
            });

            this.$$("homeView_resume_img").mouseover(function () {
                $("#homeView_resume_img").animate({
                    width: "125px",
                    height: "222px",
                    margin: "3px auto"
                }, "fast");
            });

            $(".photo_list").mouseover(function () {
                $(".photo_list").css('animation-play-state', 'paused');
            }).mouseout(function () {
                $(".photo_list").css('animation-play-state', 'running');
            });


            this.$$("homeView_creat").click(function (event) {
                gWindowManagement.pushHomeView("blogEditorView");
            });

            this.$$("homeView_resume_img").click(function (event) {
                gWindowManagement.pushHomeView("resumeView");
            });

            this.$$("homeView_date_day").click(function (event) {
                gWindowManagement.pushGlobView("alertMessageView", "日志已成功入库！")
            });
            this.$$("homeView_date_year").click(function (event) {
                var getBlogByTimeRequest = {
                    subscriberId: "1",
                    endTime: new Date().getTime() + "",
                    count: 3
                };
                var blog = mFanli.GetBlogByTime(getBlogByTimeRequest);
                console.debug(blog.toString());
            });
        },

        setWeather: function () {
            var self = this;
            $.when(mFanli.getWeather()).done(function (weather) {
                console.debug(weather);

                var imgUrl = "./img/weather/" + weather.HeWeather6[0].now.cond_code + ".png";

                self.$$("weather_icon").css("background-image", "url(" + imgUrl + ")");
                self.$$("weather_title").setText(weather.HeWeather6[0].now.cond_txt);
                self.$$("weather_temperature").setText(weather.HeWeather6[0].now.fl + "℃/" + weather.HeWeather6[0].now.tmp + "℃");
            });
        },

        getDate: function () {
            var week = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
            var number = ["〇", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"];
            var now = new Date();
            var month = number[now.getMonth() + 1];
            var year = "";
            _.each((now.getFullYear() + "").split(""), function (y) {
                year += number[y];
            });

            return {
                week: week[now.getDay()],
                day: now.getDate(),
                year: year + "年" + month + "月"
            };
        },

        homeHandle: function () {
            gWindowManagement.destroyHomeView("homeView");
            gWindowManagement.pushMainView("homeView")
        },
        wheelHandle: function () {

        },
        blogHandle: function () {

        },
        storyHandle: function () {

        },
        ideaHandle: function () {

        }
    });

	return Class;
});