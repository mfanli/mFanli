define(function(require, exports, module) {
	window.gTemplate = {};
	
	gTemplate.homeViewNavigation = Hogan.compile(require("text!view/template/homeViewNavigation.mustache"));

	gTemplate.homeViewArticle = Hogan.compile(require("text!view/template/homeViewArticleContent.mustache"));

});