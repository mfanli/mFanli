define(function (require, exports, module) {
	var Tclass = require("js/viewController.js");

	var Class = Tclass.extend({
		initialize: function(option) {
			this.editor = this.createEditor();
			this.createSlideUnLock();
		},

		createEditor: function() {
			var E = window.wangEditor
			var editor = new E('#wangEditor')
			editor.customConfig.colors = [
		        '#000000',
		        '#eeece0',
		        '#1c487f',
		        '#4d80bf',
		        '#c24f4a',
		        '#8baa4a',
		        '#7b5ba1',
		        '#46acc8',
		        '#f9963b',
		        '#000000'
		    ];
		    // 表情面板可以有多个 tab ，因此要配置成一个数组。数组每个元素代表一个 tab 的配置
		    editor.customConfig.emotions = [
		        {
		            // tab 的标题
		            title: '默认',
		            // type -> 'emoji' / 'image'
		            type: 'image',
		            // content -> 数组
		            content: [
		                {
		                    alt: '[坏笑]',
		                    src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/50/pcmoren_huaixiao_org.png'
		                },
		                {
		                    alt: '[舔屏]',
		                    src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/40/pcmoren_tian_org.png'
		                }
		            ]
		        },
		        {
		            // tab 的标题
		            title: 'emoji',
		            // type -> 'emoji' / 'image'
		            type: 'emoji',
		            // content -> 数组
		            content: ['😀', '😃', '😄', '😁', '😆']
		        }
		    ];
		    editor.customConfig.uploadImgShowBase64 = true;
			editor.create();
			E.fullscreen.init('#wangEditor');

			$(".w-e-toolbar").css('background-color', 'transparent');
			$(".w-e-text-container").css('height', '700px');
			// $("p").css('color', '#000000');

			return editor;
		},

		createSlideUnLock: function() {
			var self = this;
			$(".bar1").slideToUnlock({
				height: 35,
				bgColor: "#d0e5f2",
				handleColor: "#92bde8",
				succColor: '#9bdc92',
				progressColor: '#CCCCFF',
				text: '滑动提交博文 》》》',
    			succText: '博文提交成功!',
    			successFunc: function() {
    				var request = {
    					subscriberId: "1",
    					category: "技术日志",
    					blogTitle: $("#wangEditor > * > * > h1").html(),
    					blog: self.editor.txt.html()
    				};
    				var addBlogResp = mFanli.AddBlog(request);
    				if (addBlogResp && addBlogResp.retCode === "000000000") {
    					alert("日志已入库成功！");
					}
				}
			});
		}
	});

	return Class;
});