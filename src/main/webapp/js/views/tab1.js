"use strict";
define(['data/test_pmt', 'backbone', 'underscore', 'webix'], function (data, Backbone, _) {
  // ---------------------------------------------
  //              Tab 1
  // ---------------------------------------------
  var view_tab_1 = {
    view: "datatable",
    columns: [
      { id: "itemId", header: "id", width: 50 },
      //{ id: "product.site.address", header: "Site address", width: 150 },  // - так не понимает
      { id: "productSiteAddress", header: "Site address", width: 150 },
      { id: "productTown", header: "Город PE", width: 100 },
      { id: "portCoverage", header: "вид порта", width: 100 },
      { id: "portType", header: "тип порта", width: 100 },
      { id: "portSpeed", header: "скорость", width: 50 },
      { id: "portTarifficationScheme", header: "схема тарификации", width: 50 },
      { id: "out_standard_price_payment_onetime_money", header: "инсталляция", width: 100 },
      { id: "out_standard_cost_monthly_money", header: "ежемесячно", width: 100 },
      { id: "ref_tariff_ubb_data3_1MB_money", header: "D3", width: 100 },
      { id: "ref_tariff_ubb_data2_1MB_money", header: "D2", width: 100 },
      { id: "ref_tariff_ubb_data1_1MB_money", header: "D1", width: 100 },
      { id: "ref_tariff_ubb_voice_1MB_money", header: "Voice", width: 100 },
      { id: "ref_tariff_ubb_video_1MB_money", header: "Video", width: 100 },
    ],
    autoheight: true,
    autowidth: false
  };
  // ---------------------------------------------
  //              Backbone View:
  // ---------------------------------------------
  return Backbone.View.extend({
    render: function () {
      var webixView = new WebixView({
        config: view_tab_1,
        el: this.el
      });
      webixView.render();
      //
      //var films = getFilms();
      //webixView.root.sync(films2);
      webixView.root.sync(data.businessVpn);
      //
      return this;
    }
  });
});