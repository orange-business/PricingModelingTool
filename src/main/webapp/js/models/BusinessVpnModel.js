
"use strict";

define(['models/ItemModel', 'models/utils', 'backbone', 'underscore'], function (ItemModel, utils, Backbone, _) {

    var customAttributes = {

        itemId: { get: function(){ return this.get('id'); } },

        productSiteAddress: { 
            get: function(){ return this.getNested('product.site.address'); } 
        },
        productTown: { 
            get: function(){ return this.getNested('product.town'); } 
        },
        //
        productPortCoverage: { 
            get: function(){ return this.getNested('product.port.coverage'); }, 
            set: function (key, value, options, set) { this.setNested('product.port.coverage', value); } 
        },
        productPortType: {
            get: function () { return this.getNested('product.port.type'); },
            set: function (key, value, options, set) { this.setNested('product.port.type', value); },
        },
        productPortSpeed: {
            get: function () { return this.getNested('product.port.speed'); },
            set: function (key, value, options, set) { this.setNested('product.port.speed', value); },
        },
        productPortTarifficationScheme: {
            get: function () { return this.getNested('product.port.tarifficationScheme'); },
            set: function (key, value, options, set) { this.setNested('product.port.tarifficationScheme', value); },
        },
        //
        out_standard_price_payment_onetime_money: {
            get: function () { return this.getTreasure('out_standard_price_payment_onetime_money'); },
            set: function (key, value, options, set) { this.setTreasure('out_standard_price_payment_onetime_money', value); },
        },
        //
        monthly_money: {
            get: function () {
                var tarifficationScheme = this.get("productPortTarifficationScheme");
                if (tarifficationScheme == "fix"){
                    return this.getTreasure('out_standard_price_fix_payment_monthly_money');
                }else{
                    return this.getTreasure('in_required_price_ubb_payment_monthly_money');
                }
            },
            set: function (key, value, options, set) {
                var tarifficationScheme = this.get("productPortTarifficationScheme");
                if (tarifficationScheme == "fix"){
                    this.setTreasure('out_standard_price_fix_payment_monthly_money', value);
                }else{
                    this.setTreasure('in_required_price_ubb_payment_monthly_money', value);
                }
            },
        },
        //

        ref_tariff_ubb_data3_1MB_money: { 
            get: function(){ return getTreasureIfTarifficationUbb(this, 'ref_tariff_ubb_data3_1MB_money'); } 
        },
        ref_tariff_ubb_data2_1MB_money: { 
            get: function(){ return getTreasureIfTarifficationUbb(this, 'ref_tariff_ubb_data2_1MB_money'); } 
        },
        ref_tariff_ubb_data1_1MB_money: { 
            get: function(){ return getTreasureIfTarifficationUbb(this, 'ref_tariff_ubb_data1_1MB_money'); } 
        },
        ref_tariff_ubb_voice_1MB_money: { 
            get: function(){ return getTreasureIfTarifficationUbb(this, 'ref_tariff_ubb_voice_1MB_money'); } 
        },
        ref_tariff_ubb_video_1MB_money: { 
            get: function(){ return getTreasureIfTarifficationUbb(this, 'ref_tariff_ubb_video_1MB_money'); } 
        },



//            ref_tariff_ubb_data3_1MB_money: function () { return getTreasureIfTarifficationFix(this, 'ref_tariff_ubb_data3_1MB_money'); },
//            ref_tariff_ubb_data2_1MB_money: function () { return getTreasureIfTarifficationFix(this, 'ref_tariff_ubb_data2_1MB_money'); },
//            ref_tariff_ubb_data1_1MB_money: function () { return getTreasureIfTarifficationFix(this, 'ref_tariff_ubb_data1_1MB_money'); },
//            //
//            ref_tariff_ubb_voice_1MB_money: function () { return getTreasureIfTarifficationFix(this, 'ref_tariff_ubb_voice_1MB_money'); },
//            ref_tariff_ubb_video_1MB_money: function () { return getTreasureIfTarifficationFix(this, 'ref_tariff_ubb_video_1MB_money'); },


    };



    var BusinessVPNModel = ItemModel.extend({ 
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
        //mutators: _.extend(ItemModel.prototype.mutators, {
            //productSiteAddress: function () { return this.getNested('product.site.address'); },
            //productTown: function () { return this.getNested('product.town'); },
            //
//            productPortCoverage: {
//                get: function () { return this.getNested('product.port.coverage'); },
//                set: function (key, value, options, set) { this.setNested('product.port.coverage', value); },
//            },

            //
        //}),
        //
    });


    function getTreasureIfTarifficationUbb (that, key) {
        var tarifficationScheme = that.get("productPortTarifficationScheme");
        if (tarifficationScheme == "fix"){
            return "";
        }else{
            return that.getTreasure(key);
        }
    };

    BusinessVPNModel.PRODUCT_CODE = "115.00";

    return BusinessVPNModel;

});