define(function(require, exports, module) {
	console.debug("windowManagement start!");

	var viewController = require("js/viewController.js");
	var Complicate = require("js/complicate.js");
	var Base = require("js/base");

	var view = {};

	exports.pushMainView = function(viewId, option) {
		willPushMainView(viewId, option);
	};

	exports.pushHomeView = function(viewId, option) {
		getViewById(viewId);

		var homeViewBody = $("#homeView_body");

		homeViewBody.children().css('display', 'none');
		
		homeViewBody.append(view.html);

		viewControl = view.control;
		new viewControl(option);
	};

	willPushMainView = function(viewId, option) {
		getViewById(viewId);

		$("#main_panle").append(view.html);

		viewControl = view.control;
		new viewControl(option);
	};

	getViewById = function(viewId) {
		view = Complicate.getViewInfo(viewId);
	}


	module.exports = exports;
});