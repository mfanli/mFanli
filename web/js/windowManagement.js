define(function(require, exports, module) {
	console.debug("windowManagement start!");

	var viewController = require("js/viewController.js");
	var Complicate = require("js/complicate.js");
	var Base = require("js/base");

	exports.pushMainView = function(viewId, option) {
        var view = getViewById(viewId);

        $("#main_panle").append(view.html);

        viewControl = view.control;
        new viewControl(option);
	};

	exports.pushGlobView = function (viewId, option) {
        var view = getViewById(viewId);
        $("#globe_panle").append(view.html);

        viewControl = view.control;
        new viewControl(option);
    };

	exports.pushHomeView = function(viewId, option) {
        var view = getViewById(viewId);

		var homeViewBody = $("#homeView_body");

		homeViewBody.children().css('display', 'none');

		homeViewBody.append(view.html);

		viewControl = view.control;
		new viewControl(option);
	};

	exports.destroyHomeView = function (viewId) {
        $("#" + viewId).remove();
		$("#homeView_body").children().css('display', 'block')
    };

    // exports.alertView = function (viewId, option) {
    //     var view = getViewById(viewId);
		// var globePanle = $("#globe_panle");
    //
		// if ($("#" + viewId).length == 0) {
    //
		// }
    // };

	getViewById = function(viewId) {
		return Complicate.getViewInfo(viewId);
	};


	module.exports = exports;
});