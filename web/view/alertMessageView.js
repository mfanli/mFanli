define(function (require, exports, module) {
    console.debug("messageView start!");
    var Tclass = require("js/viewController.js");

    var Class = Tclass.extend({
        initialize: function (option) {
            var globePlanle = this.$$("globe_panle");
            globePlanle.css({
                "height": "100%",
                "width": "100%"
            });

            var messageView = this.$$("alertMessageView");
            messageView.fadeIn("slow", function () {
                setTimeout(function () {
                    messageView.fadeOut("slow", function () {
                        globePlanle.css({
                            "height": "0px",
                            "width": "0px"
                        });
                        messageView.css({
                            "display": "none"
                        });
                        messageView.remove();
                    })
                }, 5000);

            });
        }
    });

    return Class;
});