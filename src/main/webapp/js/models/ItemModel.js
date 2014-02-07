
"use strict";

define(['models/utils', 'backbone', 'underscore'], function (utils, Backbone, _) {  //, 'backbone_mutators' , Mutators

    var customAttributes = {
        itemId: { get: function(){ return this.get('id'); } },
        productCode: { get: function(){ return this.getNested('product.code'); } },
    };


    var ItemModel = Backbone.Model.extend({
        get: function(attribute){
            var custom = customAttributes[attribute];
            if (custom){
                if (custom.get) return custom.get.apply(this, arguments);
            }else{
                return Backbone.Model.prototype.get.apply(this, arguments);
            }
        },
        //set: function(attributes, options) {
        //    Backbone.Model.prototype.set.apply(this, arguments);
        //},
        //mutators: {
            //itemId: function () { return this.get('id'); },
            //productCode: function () { return this.get('product').code; },
        //},
        //
        getNested: function(key){
            var NOT_FOUND = ""; // для релиза
            //var NOT_FOUND = key; // для отладки
            //
            var parts = key.split('.');
            if (parts.length <= 0) return NOT_FOUND;
            //
            var value = this.get(parts[0]);
            for (var i=1; i<parts.length; i++){
                if (utils.isNullOrUndefined(value)) return NOT_FOUND;
                value = value[parts[i]];
            }
            //
            if (utils.isNullOrUndefined(value)) return NOT_FOUND;
            return value;
        },
        //
        setNested: function(key, value){
            var NOT_FOUND = ""; // для релиза
            //var NOT_FOUND = key; // для отладки
            //
            var parts = key.split('.');
            if (parts.length <= 0) return NOT_FOUND;
            //
            if (parts.length == 1){
                this.set(key, value);
                return;
            }
            //
            var prop = this.get(parts[0]);
            for (var i=1; i<parts.length-1; i++){
                if (utils.isNullOrUndefined(prop)) return NOT_FOUND;
                prop = prop[parts[i]];
            }
            //
            if (utils.isNullOrUndefined(prop)) return NOT_FOUND;
            prop[parts[parts.length-1]] = value;
        },
        //
        getParameterValue: function(parametersCollection, key){
            var NOT_FOUND = ""; // для релиза
            //var NOT_FOUND = key; // для отладки
            //
            if (utils.isNullOrUndefined(parametersCollection)) return NOT_FOUND;
            var parameter = _.find(parametersCollection, function(x){ return (x.code == key);});
            if (utils.isNullOrUndefined(parameter)) return NOT_FOUND;
            //
            return parameter.value;
        },
        //
        setParameterValue: function(parametersCollection, key, value){
            var NOT_FOUND = ""; // для релиза
            //var NOT_FOUND = key; // для отладки
            //
            if (utils.isNullOrUndefined(parametersCollection)) return NOT_FOUND;
            var parameter = _.find(parametersCollection, function(x){ return (x.code == key);});
            if (utils.isNullOrUndefined(parameter)) return NOT_FOUND;
            //
            parameter.value = value;
        },
        //
        getString: function(key){
            return this.getParameterValue(this.get("strings"), key);
        },
        //
        setString: function(key, value){
            this.setParameterValue(this.get("strings"), key, value);
        },
        //
        getNumeric: function(key){
            return this.getParameterValue(this.get("numerics"), key);
        },
        //
        setNumeric: function(key, value){
            this.setParameterValue(this.get("numerics"), key, value);
        },
        //
        getTreasure: function(key){
            return this.getParameterValue(this.get("treasures"), key);
        },
        //
        setTreasure: function(key, value){
            this.setParameterValue(this.get("treasures"), key, value);
        },
        //
    });

    return ItemModel;

});