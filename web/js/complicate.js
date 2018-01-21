define(function(require, exports, module) {
	console.debug("complicate start!");

	var viewMap = {
		homeView: {
			html: require("text!view/homeView.html"),
			control: require("view/homeView.js"),
			css: require("view/homeView.css")
		},
		blogEditorView: {
			html: require("text!view/blogEditorView.html"),
			control: require("view/blogEditorView.js"),
			css: require("view/blogEditorView.css")
		},
		resumeView: {
			html: require("text!view/resumeView.html"),
			control: require("view/resumeView.js"),
			css: require("view/resumeView.css")
		},
        alertMessageView: {
            html: require("text!view/alertMessageView.html"),
            control: require("view/alertMessageView.js"),
            css: require("view/alertMessageView.css")
		}
	};

	exports.getViewInfo = function(viewId) {
		if(viewMap[viewId]) {
			console.debug("complicate getViewInfo viewId=%s", viewId);
			return viewMap[viewId];
		} else {
			console.debug("complicate getViewInfo error!");
		}
	};
});