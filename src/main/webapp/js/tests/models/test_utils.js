
"use strict";

define(['QUnit', 'models/utils'], function (QUnit, utils) {

    QUnit.module("models/utils");

    QUnit.test("isNullOrUndefined()", function () {
        QUnit.strictEqual(utils.isNullOrUndefined(1000), false);
        QUnit.strictEqual(utils.isNullOrUndefined(0), false);
        QUnit.strictEqual(utils.isNullOrUndefined(false), false);
        QUnit.strictEqual(utils.isNullOrUndefined(true), false);
        QUnit.strictEqual(utils.isNullOrUndefined(""), false);
        QUnit.strictEqual(utils.isNullOrUndefined("0"), false);
        QUnit.strictEqual(utils.isNullOrUndefined("100"), false);
        QUnit.strictEqual(utils.isNullOrUndefined(utils), false);
        //
        QUnit.strictEqual(utils.isNullOrUndefined(null), true);
        QUnit.strictEqual(utils.isNullOrUndefined(undefined), true);
        QUnit.strictEqual(utils.isNullOrUndefined(), true);
    });

});