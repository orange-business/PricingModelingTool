"use strict";

define(['models/BusinessVpnModel', 'models/AccessLinesModel', 'backbone', 'underscore'],
    function (BusinessVpnModel, AccessLinesModel, Backbone, _) {
      var ScenarioModel = Backbone.Model.extend({
        url: "/rest/scenarios/1", // "/js/data/02.json",
//        initialize: function () {
//            var items = this.get("items");
//        },
//        //
        getItemsCollection: function (Model) {
          var items = this.get("items");
          var CollectionModel = Backbone.Collection.extend({ model: Model });
          var result = new CollectionModel(_.filter(items, function (x) {
            return (x.product.code === Model.PRODUCT_CODE);
          }));
          return result;
        }
      });
      return ScenarioModel;
    });