define(function(require, exports, module) {
	console.debug("viewController start!");
	var Base = require("js/base");

	var Class = Base.extend({
		initialize: function() {
			console.debug("viewController intialize!");
		},

		$$: function(ele) {
			console.debug("jQuery $$ begin!");
			
			var el = $("#" + ele);
			el.setText = function(id, isId) {
				console.debug("jQuery $$.setText");

				var str = "";
				if (isId){
					str = eBase.getMessageById(id);
				} else {
					str = id;
				}
				this.text(str);
			};

			return el;
		}
	});

	Class.extend = Base.extend;

	exports = module.exports = Class;

});