define(function(require, exports, module) {
    var Tools = {
        isResultSuccess: function (req) {
            return req && req.result && req.result.retCode === "000000000";
        }
    };

    return Tools;
});