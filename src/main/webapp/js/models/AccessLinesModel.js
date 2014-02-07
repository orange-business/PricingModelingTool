
"use strict";

define(['models/ItemModel', 'models/utils', 'backbone', 'underscore'], function (ItemModel, utils, Backbone, _) {

    var customAttributes = {

        itemId: { get: function(){ return this.get('id'); } },
        customNote: { get: function(){ return this.get('note'); } },

        productSiteAddress: { 
            get: function(){ return this.getNested('product.site.address'); } 
        },
        productType: { 
            get: function(){ return this.getNested('product.type'); } 
        },
        //
        in_lines_type_string: { 
            get: function () { return this.getString('in_lines_type_string'); }, 
            set: function (key, value, options, set) { this.setString('in_lines_type_string', value); }, 
        },
        //
        in_capex_equipment_money: { 
            get: function () { return this.getTreasure('in_capex_equipment_money'); }, 
            set: function (key, value, options, set) { this.setTreasure('in_capex_equipment_money', value); }, 
        },
        // 
        onetime_money: {
            get: function () {
                var productType = this.get("productType");
                //
                if (productType == "build"){
                    return this.getTreasure('in_capex_build_money');
                }else if (productType == "lease") {
                    return this.getTreasure('in_cost_onetime_lease_money');
                }else{
                    return "";
                }
            },
            set: function (key, value, options, set) {
                var productType = this.get("productType");
                //
                if (productType == "build"){
                    this.setTreasure('in_capex_build_money', value);
                }else if (productType == "lease") {
                    this.setTreasure('in_cost_onetime_lease_money', value);
                }else{
                    //
                }
            },
        },
        //
        monthly_money: {
            get: function () {
                var productType = this.get("productType");
                //
                if (productType == "build"){
                    return this.getTreasure('in_cost_monthly_build_money');
                }else if (productType == "lease") {
                    return this.getTreasure('in_cost_monthly_lease_money');
                }else{
                    return "";
                }
            },
            set: function (key, value, options, set) {
                var productType = this.get("productType");
                //
                if (productType == "build"){
                    this.setTreasure('in_cost_monthly_build_money', value);
                }else if (productType == "lease") {
                    this.setTreasure('in_cost_monthly_lease_money', value);
                }else{
                    //
                }
            },
        },
        //
        inout_required_price_payment_onetime_money: {
            get: function () { return this.getTreasure('inout_required_price_payment_onetime_money'); },
            set: function (key, value, options, set) { this.setTreasure('inout_required_price_payment_onetime_money', value); },
        },
        //
        inout_required_price_payment_monthly_money: {
            get: function () { return this.getTreasure('inout_required_price_payment_monthly_money'); },
            set: function (key, value, options, set) { this.setTreasure('inout_required_price_payment_monthly_money', value); },
        },
        //
        out_required_price_margin_percent: {
            get: function () { return this.getNumeric('out_required_price_margin_percent'); },
        },
        out_required_price_payback_months: {
            get: function () { return this.getNumeric('out_required_price_payback_months'); },
        },
        out_standard_price_payment_onetime_money: {
            get: function () { return this.getTreasure('out_standard_price_payment_onetime_money'); },
        },
        out_standard_price_payment_monthly_money: {
            get: function () { return this.getTreasure('out_standard_price_payment_monthly_money'); },
        },
        out_standard_price_margin_percent: {
            get: function () { return this.getNumeric('out_standard_price_margin_percent'); },
        },
        out_standard_price_payback_months: {
            get: function () { return this.getNumeric('out_standard_price_payback_months'); },
        },
    };

    var AccessLinesModel = ItemModel.extend({
        //
        customAttributes: customAttributes,
        //
        get: function(attribute){
            var custom = customAttributes[attribute];
            if (custom){
                if (custom.get) return custom.get.apply(this, arguments);
            }else{
                return ItemModel.prototype.get.apply(this, arguments);
            }
        },
        set: function(attributes, options) {
            var custom = customAttributes[attributes];
            if (custom){
                if (custom.set) custom.set.apply(this, arguments);
            }else{
                ItemModel.prototype.set.apply(this, arguments);
            }
        },
        //
        //mutators:  _.extend(ItemModel.prototype.mutators, {
            //productSiteAddress: function () { return this.getNested('product.site.address'); },
            //productType: function () { return this.getNested('product.type'); },
            //
            // 
//            out_required_price_margin_percent: function () { return this.getNumeric('out_required_price_margin_percent'); },
//            out_required_price_payback_months: function () { return this.getNumeric('out_required_price_payback_months'); },
//            //
//            out_standard_price_payment_onetime_money: function () { return this.getTreasure('out_standard_price_payment_onetime_money'); },
//            out_standard_price_payment_monthly_money: function () { return this.getTreasure('out_standard_price_payment_monthly_money'); },
//            //
//            out_standard_price_margin_percent: function () { return this.getNumeric('out_standard_price_margin_percent'); },
//            out_standard_price_payback_months: function () { return this.getNumeric('out_standard_price_payback_months'); },
//        }),
    });

    AccessLinesModel.PRODUCT_CODE = "802.01";

    return AccessLinesModel;

});