
"use strict";

define(['models/BusinessVpnModel', 'models/AccessLinesModel', 'backbone', 'underscore'], function (BusinessVpnModel, AccessLinesModel, Backbone, _) {


    var ScenarioModel = Backbone.Model.extend({
        //
        url: "/rest/scenarios/1",
        //url: "/js/data/02.json",
        //
        //businessVpnCollection: null,
        //accessLinesCollection: null, 

        collectionToArray: function(collection){
            //var columns = Model.customAttributes;
            //console.log(columns);
            var result = [];
            //
            collection.each(function(item){
                var columns = item.customAttributes;
                var record = [];
                _.each(columns, function(methods, key, index){
                    //var key = column
                    record[key] = methods.get.call(item);
                    //console.log(key);
                });
                result.push(record);
            });
            //
            return result;
        },

        initialize: function () {
            //var items = this.get("items");
            //            this.on('all', function (e, a1, a2, a3, a4) {
            //                console.log("event: " + e + "  " + a1 + "  " + a2 + "  " + a3 + "  " + a4);
            //            });
            //
            var BusinessVpnCollection = Backbone.Collection.extend({ model: BusinessVpnModel });
            var AccessLinesCollection = Backbone.Collection.extend({ model: AccessLinesModel });
            //
            this.businessVpnCollection = new BusinessVpnCollection();
            this.accessLinesCollection = new AccessLinesCollection();
            //
            this.businessVpnArray = [];
            this.accessLinesArray = [];
            //
            this.on('sync', function () {
                //console.log("sync 1");
                var items = this.get("items");
                //
                //this.businessVpnCollection.reset(_.filter(items, function (x) { return (x.product.code === BusinessVpnModel.PRODUCT_CODE); }));
                //this.accessLinesCollection.reset(_.filter(items, function (x) { return (x.product.code === AccessLinesModel.PRODUCT_CODE); }));
                //
                var bvpn = _.filter(items, function (x) { return (x.product.code === BusinessVpnModel.PRODUCT_CODE); });
                var lines = _.filter(items, function (x) { return (x.product.code === AccessLinesModel.PRODUCT_CODE); });
                //console.log("sync 2: " + bvpn.length + " " + lines.length);
                this.businessVpnCollection.reset(bvpn);
                this.accessLinesCollection.reset(lines);
                //console.log("sync 3");
                //
                this.businessVpnArray = this.collectionToArray(this.businessVpnCollection);
                this.accessLinesArray = this.collectionToArray(this.accessLinesCollection);
                //
                this.trigger("reset");
            });
            //
            //console.log("initialize end");

        },
        //
        getItemsCollection: function (Model) {
            var items = this.get("items");
            var CollectionModel = Backbone.Collection.extend({ model: Model });
            var result = new CollectionModel(_.filter(items, function (x) { return (x.product.code === Model.PRODUCT_CODE); }));
            return result;
        },
        //

    });

    return ScenarioModel;

});