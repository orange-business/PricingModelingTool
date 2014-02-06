
"use strict";

define(['models/ItemModel', 'models/utils', 'backbone', 'underscore'], function (ItemModel, utils, Backbone, _) {

    var AccessLinesModel = ItemModel.extend({
        mutators:  _.extend(ItemModel.prototype.mutators, {
            productType: function () { return this.getNested('product.type'); },
            //
            in_lines_type_string: { 
                get: function () { return this.getString('in_lines_type_string'); }, 
                set: function (key, value, options, set) { this.setString('in_lines_type_string', value); }, 
            },
            //
            in_capex_equipment_money: { 
                get: function () { return this.getTreasure('in_capex_equipment_money'); }, 
                set: function (key, value, options, set) { this.getTreasure('in_capex_equipment_money', value); }, 
            },
            //

            out_cost_capex_onetime_money: function () { return this.getTreasure('out_cost_capex_onetime_money'); },
            out_cost_monthly_money: function () { return this.getTreasure('out_cost_monthly_money'); },
            //
            inout_required_price_payment_onetime_money: function () { return this.getTreasure('inout_required_price_payment_onetime_money'); },
            inout_required_price_payment_monthly_money: function () { return this.getTreasure('inout_required_price_payment_monthly_money'); },
            //
            out_standard_price_payback_months: function () { return this.getTreasure('out_standard_price_payback_months'); },
            out_standard_price_payment_onetime_money: function () { return this.getTreasure('out_standard_price_payment_onetime_money'); },
            out_standard_price_payment_monthly_money: function () { return this.getTreasure('out_standard_price_payment_monthly_money'); },
            //
            ref_floor_price_margin_percent: function () { return this.getTreasure('ref_floor_price_margin_percent'); },
            ref_standard_required_price_payback_months: function () { return this.getTreasure('ref_standard_required_price_payback_months'); },
        }),
    });

    AccessLinesModel.PRODUCT_CODE = "802.01";

    return AccessLinesModel;

});