define(function (require, exports, module) {
	var Tclass = require("js/viewController.js");

	var Class = Tclass.extend({
		initialize: function(option) {
			console.debug("resumeView");
		}
	});

	return Class;
});