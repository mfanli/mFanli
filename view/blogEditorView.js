define(function (require, exports, module) {
	var Tclass = require("js/viewController.js");

	var Class = Tclass.extend({
		initialize: function(option) {
			

			var E = window.wangEditor
			var editor = new E('#wangEditor')
			
			editor.create();
			E.fullscreen.init('#wangEditor');

			$(".w-e-toolbar").css('background-color', 'transparent');
			$(".w-e-text-container").css('height', '700px');
			$("p").css('color', '#000000');

			var self = this;
			$(".bar1").slideToUnlock({
				height: 35,
				bgColor: "#336699",
				text: '滑动提交博文', 
    			succText: '博文提交成功',
    			successFunc: self.aa
			});

		},

		aa: function() {
			alert('successfully unlock!mfanli');
		}
	});

	return Class;
});