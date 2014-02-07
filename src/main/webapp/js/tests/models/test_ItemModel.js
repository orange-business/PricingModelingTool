
"use strict";

define(['QUnit', 'models/ItemModel'], function (QUnit, ItemModel) {

    QUnit.module("ItemModel");

    QUnit.test("Collection", function () {
        var Collection = Backbone.Collection.extend({
            model: ItemModel
        });

        var collection = new Collection();

        collection.add({ id: 5, product: { code: "code1", site: { address: "address1"}} });

        var itemModel = collection.at(0);
        //
        QUnit.strictEqual(itemModel.get("itemId"), 5);
        QUnit.strictEqual(itemModel.get("productCode"), "code1");
        //
        QUnit.strictEqual(itemModel.get("productSiteAddress"), undefined);
    });

    QUnit.test("get plain properties", function () {
        var itemModel = new ItemModel({ id: 5, product: { code: "code1", site: { address: "address1"}} });
        //
        QUnit.strictEqual(itemModel.get("itemId"), 5);
        QUnit.strictEqual(itemModel.get("productCode"), "code1");
        //
        QUnit.strictEqual(itemModel.get("productSiteAddress"), undefined);
    });

    QUnit.test("getNested(), setNested()", function () {
        var itemModel = new ItemModel({ name: "name1", product: { site: { customer: { id: 9}}} });
        //
        QUnit.strictEqual(itemModel.getNested("name"), "name1");
        QUnit.strictEqual(itemModel.getNested("product.site.customer.id"), 9);
        //
        itemModel.setNested("name", "name2");
        itemModel.setNested("product.site.customer.id", 10);
        QUnit.strictEqual(itemModel.getNested("name"), "name2");
        QUnit.strictEqual(itemModel.getNested("product.site.customer.id"), 10);
    });

    QUnit.test("getParameterValue()", function () {
        var things = [{ code: "code1", value: "value1" }, { code: "code2", value: "value2"}];
        var itemModel = new ItemModel({ things: things });
        var value = itemModel.getParameterValue(things, "code2");
        QUnit.strictEqual(value, "value2");
    });

    QUnit.test("setParameterValue()", function () {
        var things = [{ code: "code1", value: "value1" }, { code: "code2", value: "value2"}];
        var itemModel = new ItemModel({ things: things });
        itemModel.setParameterValue(things, "code2", "value2.2");
        QUnit.strictEqual(things[1].value, "value2.2");
    });

    QUnit.test("getString()", function () {
        var item = { strings: [{ code: "code1", value: "value1"}] };
        var itemModel = new ItemModel(item);
        var value = itemModel.getString("code1");
        QUnit.strictEqual(value, "value1");
    });

    QUnit.test("setString()", function () {
        var item = { strings: [{ code: "code1", value: "value1"}] };
        var itemModel = new ItemModel(item);
        itemModel.setString("code1", "value1.2");
        QUnit.strictEqual(item.strings[0].value, "value1.2");
    });

    QUnit.test("getNumeric()", function () {
        var item = { numerics: [{ code: "code1", value: "value1"}] };
        var itemModel = new ItemModel(item);
        var value = itemModel.getNumeric("code1");
        QUnit.strictEqual(value, "value1");
    });

    QUnit.test("setNumeric()", function () {
        var item = { numerics: [{ code: "code1", value: "value1"}] };
        var itemModel = new ItemModel(item);
        itemModel.setNumeric("code1", "value1.2");
        QUnit.strictEqual(item.numerics[0].value, "value1.2");
    });

    QUnit.test("getTreasure()", function () {
        var item = { treasures: [{ code: "code1", value: "value1"}] };
        var itemModel = new ItemModel(item);
        var value = itemModel.getTreasure("code1");
        QUnit.strictEqual(value, "value1");
    });

    QUnit.test("setTreasure()", function () {
        var item = { treasures: [{ code: "code1", value: "value1"}] };
        var itemModel = new ItemModel(item);
        itemModel.setTreasure("code1", "value1.2");
        QUnit.strictEqual(item.treasures[0].value, "value1.2");
    });


});