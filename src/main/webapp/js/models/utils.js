
"use strict";

define(function () {

    var utils = {
        isNullOrUndefined: function(x){
            if (x === undefined) return true;
            if (x === null) return true;
            return false;
        },
        //
//        formatTreasure: function(value, currencyCode){
//            return value + " " + currencyCode;
//        },
    };

    return utils;

});