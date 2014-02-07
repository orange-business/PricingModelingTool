"use strict";
define(['models/utils','backbone','underscore','lib/backbone/backbone.mutators'], function (utils, Backbone, _,Mutators) {
  var ItemModel = Backbone.Model.extend({
    mutators: {
      itemId: function () {
        return this.get('id');
      },
      productCode: function () {
        return this.get('product').code;
      },
      productSiteAddress: function () {
        return this.getNested('product.site.address');
      }
    },
    //
    getString: function (key) {
      var NOT_FOUND = ""; // для релиза
      //var NOT_FOUND = key; // для отладки
      //
      var strings = this.get("strings");
      if (utils.isNullOrUndefined(strings)) return NOT_FOUND;
      var string = _.find(strings, function (x) {
        return (x.code == key);
      });
      if (utils.isNullOrUndefined(string)) return NOT_FOUND;
      //
      return string.value;
    },
    //
    setString: function (key, value) {
      var NOT_FOUND = ""; // для релиза
      //var NOT_FOUND = key; // для отладки
      //
      var strings = this.get("strings");
      if (utils.isNullOrUndefined(strings)) return NOT_FOUND;
      var string = _.find(strings, function (x) {
        return (x.code == key);
      });
      if (utils.isNullOrUndefined(string)) return NOT_FOUND;
      //
      string.value = value;
    },
    //
    getTreasure: function (key) {
      var NOT_FOUND = ""; // для релиза
      //var NOT_FOUND = key; // для отладки
      //
      var treasures = this.get("treasures");
      if (utils.isNullOrUndefined(treasures)) return NOT_FOUND;
      var treasure = _.find(treasures, function (x) {
        return (x.code == key);
      });
      if (utils.isNullOrUndefined(treasure)) return NOT_FOUND;
      //
      return utils.formatTreasure(treasure.value, treasure.currencyCode);
    },
    //
    setTreasure: function (key, value) {
      var NOT_FOUND = ""; // для релиза
      //var NOT_FOUND = key; // для отладки
      //
      var treasures = this.get("treasures");
      if (utils.isNullOrUndefined(treasures)) return NOT_FOUND;
      var treasure = _.find(treasures, function (x) {
        return (x.code == key);
      });
      if (utils.isNullOrUndefined(treasure)) return NOT_FOUND;
      //
      treasure.value = value;
    },
    //
    getNested: function (key) {
      var NOT_FOUND = ""; // для релиза
      //var NOT_FOUND = key; // для отладки
      //
      var parts = key.split('.');
      if (parts.length <= 0) return NOT_FOUND;
      //
      var value = this.get(parts[0]);
      for (var i = 1; i < parts.length; i++) {
        if (utils.isNullOrUndefined(value)) return NOT_FOUND;
        value = value[parts[i]];
      }
      //
      if (utils.isNullOrUndefined(value)) return NOT_FOUND;
      return value;
    }
  });
  return ItemModel;
});