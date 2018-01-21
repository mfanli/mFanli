define(function(require, exports, module) {
	console.debug("index start!");
	require("js/template.js");
	require("js/mFanli.js");
	window.gWindowManagement = require("js/windowManagement.js");

	gWindowManagement.pushMainView("homeView", {
		name: "helen",
		age: 18
	});
});