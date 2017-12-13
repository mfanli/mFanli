define(function(require, exports, module) {
	console.debug("base start!");
	function extend(fuc, option) {
		var subClass = function(option) {
			console.debug("base subClass!");

			this.initialize(option);
			this.viewDidShow();
		};
		var baseFuc = {
			initialize: function(option) {
				console.debug("base baseFuc initialize option:%s", option);
			},
			viewDidShow: function() {
				console.debug("base baseFuc viewDidShow");
			},
		}

		_.extend(subClass.prototype, baseFuc);
		_.extend(subClass.prototype, this.prototype);
		_.extend(subClass.prototype, fuc);

		return subClass;
	}

	var Base = function() {};
	Base.extend = extend;

	module.exports = Base;
});