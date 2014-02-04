"use strict";

define(['data/test_02_raw', 'backbone', 'lib/backbone/backbone.chosen', 'underscore', 'lib/backbone/backbone.mutators', 'backbone_nested'], function (rawData, Backbone, Chosen, _, Mutators) {

    var ItemModel = Backbone.Model.extend({
        mutators: {
            itemId: function () { return this.get('id'); },
            productCode: function () { return this.get('product').code; },
        },

        getTreasure: function(key){
            var treasures = this.get("treasures");
            if (!treasures) return "";
            var treasure = _.find(treasures, function(x){ return (x.code == key);});
            if (!treasure) return "";
            return treasure.value + " " + treasure.currencyCode;
        },

        getNested: function(key){
            var parts = key.split('.');
            var value = this.get(parts[0]);
            for (var i=1; i<parts.length; i++) value = value[parts[i]];
            return value;
        }

    });


    var BusinessVPNModel = ItemModel.extend({   // NestedModel
        mutators: {
            itemId: function () { return this.get('id'); },
            //
            productSiteAddress: function () {
                return this.getNested('product.site.address');
                //return this.get('product.site.address');
                //var product = this.get('product');
                //return product.site.address;
            },
            productTown: function () { return this.getNested('product.town'); },
            //
            portCoverage: function () { return this.getNested('product.port.coverage'); },
            portType: function () { return this.getNested('product.port.type'); },
            portSpeed: function () { return this.getNested('product.port.speed'); },
            portTarifficationScheme: function () { return this.getNested('product.port.tarifficationScheme'); },
            //
            out_standard_price_payment_onetime_money: function () { return this.getTreasure('out_standard_price_payment_onetime_money'); },
            out_standard_cost_monthly_money: function () { return this.getTreasure('out_standard_cost_monthly_money'); },
            //
            ref_tariff_ubb_data3_1MB_money: function () { return this.getTreasure('ref_tariff_ubb_data3_1MB_money'); },
            ref_tariff_ubb_data2_1MB_money: function () { return this.getTreasure('ref_tariff_ubb_data2_1MB_money'); },
            ref_tariff_ubb_data1_1MB_money: function () { return this.getTreasure('ref_tariff_ubb_data1_1MB_money'); },
            //
            ref_tariff_ubb_voice_1MB_money: function () { return this.getTreasure('ref_tariff_ubb_voice_1MB_money'); },
            ref_tariff_ubb_video_1MB_money: function () { return this.getTreasure('ref_tariff_ubb_video_1MB_money'); },

        },

    });

    var AccessLinesModel = ItemModel.extend({
        mutators: {
            itemId: function () { return this.get('id'); },
            //
            productSiteAddress: function () { return this.getNested('product.site.address'); },
            productType: function () { return this.getNested('product.type'); },
            //
            in_capex_equipment_money: function () { return this.getTreasure('in_capex_equipment_money'); },
            out_cost_capex_onetime_money: function () { return this.getTreasure('out_cost_capex_onetime_money'); },
            out_cost_monthly_money: function () { return this.getTreasure('out_cost_monthly_money'); },
            inout_required_price_payment_onetime_money: function () { return this.getTreasure('inout_required_price_payment_onetime_money'); },
            inout_required_price_payment_monthly_money: function () { return this.getTreasure('inout_required_price_payment_monthly_money'); },
            out_standard_price_payback_months: function () { return this.getTreasure('out_standard_price_payback_months'); },
            out_standard_price_payment_onetime_money: function () { return this.getTreasure('out_standard_price_payment_onetime_money'); },
            out_standard_price_payment_monthly_money: function () { return this.getTreasure('out_standard_price_payment_monthly_money'); },
            ref_floor_price_margin_percent: function () { return this.getTreasure('ref_floor_price_margin_percent'); },
            ref_standard_required_price_payback_months: function () { return this.getTreasure('ref_standard_required_price_payback_months'); },
        }
    });

    var BusinessVPNCollection = Backbone.Collection.extend({
        model: BusinessVPNModel
    });

    var AccessLinesCollection = Backbone.Collection.extend({
        model: AccessLinesModel
    });

    var ItemCollection = Backbone.Collection.extend({
        model: {
            chosen: {
                attr: 'product.code',
                defaults: ItemModel,
                map: {
                    "115.00": BusinessVPNModel,
                    "802.01": AccessLinesModel
                }
            }
        }
    });

    //var itemCollection = new ItemCollection(rawData.items);

    //var businessVpnCollection = new BusinessVPNCollection(itemCollection.where({ productCode: "115.00"}));

    var businessVpnCollection = new BusinessVPNCollection(_.filter(rawData.items, function (x) { return x.product.code == "115.00"; }));

    var accessLinesCollection = new AccessLinesCollection(_.filter(rawData.items, function (x) { return x.product.code == "802.01"; }));

    //var m = businessVpnCollection.at(0);
    //alert(m.get("product.site.address"));

    return { 
        businessVpn: businessVpnCollection,
        accessLines: accessLinesCollection
    };

});