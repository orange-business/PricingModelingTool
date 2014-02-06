
"use strict";

define(['models/ItemModel', 'models/utils', 'backbone', 'underscore'], function (ItemModel, utils, Backbone, _) {

    var BusinessVPNModel = ItemModel.extend({ 
        //
        //
        mutators: _.extend(ItemModel.prototype.mutators, {
            productTown: function () { return this.getNested('product.town'); },
            //
            portCoverage: function () { return this.getNested('product.port.coverage'); },
            portType: function () { return this.getNested('product.port.type'); },
            portSpeed: function () { return this.getNested('product.port.speed'); },
            portTarifficationScheme: function () { return this.getNested('product.port.tarifficationScheme'); },
            //
            out_standard_price_payment_onetime_money: {
                get: function () { return this.getTreasure('out_standard_price_payment_onetime_money'); },
                set: function (key, value, options, set) { this.setTreasure('out_standard_price_payment_onetime_money', value); },
            },
            //
            monthly_money: {
                get: function () {
                    var tarifficationScheme = this.get("portTarifficationScheme");
                    if (tarifficationScheme == "fix"){
                        return this.getTreasure('out_standard_price_fix_payment_monthly_money');
                    }else{
                        return this.getTreasure('in_required_price_ubb_payment_monthly_money');
                    }
                },
                set: function (key, value, options, set) {
                    var tarifficationScheme = this.get("portTarifficationScheme");
                    if (tarifficationScheme == "fix"){
                        this.setTreasure('out_standard_price_fix_payment_monthly_money', value);
                    }else{
                        this.setTreasure('in_required_price_ubb_payment_monthly_money', value);
                    }
                },
            },
            //
            ref_tariff_ubb_data3_1MB_money: function () { return getTreasureIfTarifficationFix(this, 'ref_tariff_ubb_data3_1MB_money'); },
            ref_tariff_ubb_data2_1MB_money: function () { return getTreasureIfTarifficationFix(this, 'ref_tariff_ubb_data2_1MB_money'); },
            ref_tariff_ubb_data1_1MB_money: function () { return getTreasureIfTarifficationFix(this, 'ref_tariff_ubb_data1_1MB_money'); },
            //
            ref_tariff_ubb_voice_1MB_money: function () { return getTreasureIfTarifficationFix(this, 'ref_tariff_ubb_voice_1MB_money'); },
            ref_tariff_ubb_video_1MB_money: function () { return getTreasureIfTarifficationFix(this, 'ref_tariff_ubb_video_1MB_money'); },
        }),
        //
    });


    function getTreasureIfTarifficationFix (that, key) {
        var tarifficationScheme = that.get("portTarifficationScheme");
        if (tarifficationScheme == "fix"){
            return that.getTreasure(key);
        }else{
            return "";
        }
    };


    BusinessVPNModel.PRODUCT_CODE = "115.00";

    return BusinessVPNModel;

});