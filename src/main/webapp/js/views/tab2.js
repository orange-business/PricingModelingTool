"use strict";

define(['data/test_pmt', 'backbone', 'webix'], function (data, Backbone) {
  // ---------------------------------------------
  //              Tab 2
  // ---------------------------------------------
  var view_tab_2 = {
    view: "datatable",
    columns: [
      { id: "itemId", header: "id", width: 50 },
      { id: "productSiteAddress", header: "Site address", width: 150 },
      { id: "productType", header: "Тип доступа", width: 100 },
      { id: "in_capex_equipment_money", header: "Стоимость оборудования", width: 100 },
      { id: "out_cost_capex_onetime_money", header: "Разовые затраты", width: 100 },
      { id: "out_cost_monthly_money", header: "Ежемесячные затраты", width: 100 },
      { id: "inout_required_price_payment_onetime_money", header: "Желаемый разовый", width: 100 },
      { id: "inout_required_price_payment_monthly_money", header: "Желаемый ежемесячный", width: 100 },
      { id: "out_standard_price_payback_months", header: "Срок окупаемости", width: 100 },
      { id: "out_standard_price_payment_onetime_money", header: "Стандартный разовый", width: 100 },
      { id: "out_standard_price_payment_monthly_money", header: "Стандартный ежемесячный", width: 100 },
      { id: "ref_floor_price_margin_percent", header: "Маржа", width: 100 },
      { id: "ref_standard_required_price_payback_months", header: "Срок окупаемости", width: 100 },
    ],
    select: "cell",
    autoheight: true,
    autowidth: false,
  };
  // ---------------------------------------------
  //              Backbone View:
  // ---------------------------------------------
  return Backbone.View.extend({
    render: function () {
      var webixView = new WebixView({
        config: view_tab_2,
        el: this.el
      });
      webixView.render();
      webixView.root.sync(data.accessLines);
      return this;
    }
  });
});