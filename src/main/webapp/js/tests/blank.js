
"use strict";

define(['QUnit'], function (QUnit) {

    QUnit.module("module1");

    QUnit.test("test1", function () {
        QUnit.strictEqual("abc", "abc");
    });

});