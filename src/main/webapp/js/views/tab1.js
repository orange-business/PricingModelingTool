"use strict";

define(['data/test_scenario', 'models/BusinessVpnModel', 'backbone', 'underscore', 'webix'], function (scenario, BusinessVpnModel, Backbone, _) {

    // ---------------------------------------------
    //              Tab 1
    // ---------------------------------------------


    /*

gridBvpn.setHeader("id, Site address, Город PE, вид порта, тип порта, скорость, схема тарификации, инсталляция, ежемесячно, D3, D2, D1, Voice, Video");
  gridBvpn.setInitWidths("60,100,70,70,70,70,100,80,70,70,70,70,70,70");
  gridBvpn.setColAlign("right,right,left,left,left,left,left,left,left,left,left,left,left,left");
  gridBvpn.setColTypes("dyn,ed,ed,ed,ed,ed,combo,ed,ed,ed,ed,ed,ed,ed");
  gridBvpn.setColSorting("int,str,str,str,str,str,str,str,str,str,str,str,str,str");
[14:57:38] Зайнуллин Радик: это для Bisiness VPN

    */
    // ----------------------------------------------------------



    // ----------------------------------------------------------

    var view_tab_1 = {
        view: "datatable",
        columns: [
			{ id: "itemId", header: "id", width: 50 },
			//{ id: "id", header: "id", width: 50 },
			{ id: "productSiteAddress", header: "Site address", width: 150},
			//{ id: "note", header: "Site address", width: 150},
			{ id: "productTown", header: "Город PE", width: 100 },
			{ id: "productPortCoverage", header: "вид порта", width: 100, editor:"select", options:["domestic","okrug","local"] },
			{ id: "productPortType", header: "тип порта", width: 100, editor:"select", options:["platinum", "gold", "silver"] },
			{ id: "productPortSpeed", header: "скорость", width: 50, editor: "text" },
			{ id: "productPortTarifficationScheme", header: "схема тарификации", width: 50, editor:"select", options:["fix", "ubb"] },
			{ id: "out_standard_price_payment_onetime_money", header: "инсталляция", width: 100, editor: "text" },
			{ id: "monthly_money", header: "ежемесячно", width: 100, editor: "text" },
			{ id: "ref_tariff_ubb_data3_1MB_money", header: "D3", width: 100 },
			{ id: "ref_tariff_ubb_data2_1MB_money", header: "D2", width: 100 },
			{ id: "ref_tariff_ubb_data1_1MB_money", header: "D1", width: 100 },
			{ id: "ref_tariff_ubb_voice_1MB_money", header: "Voice", width: 100 },
			{ id: "ref_tariff_ubb_video_1MB_money", header: "Video", width: 100 },
		],
        select: "cell",
        editable: true,
        autoheight: true,
        autowidth: true,
        resizeColumn: true,
	};

    // ---------------------------------------------
    //              Backbone View:
    // ---------------------------------------------

    return Backbone.View.extend({
        render: function() {
            var webixView = new WebixView({
			    config: view_tab_1,
			    el: this.el
		    });
            webixView.render();
            //
            //var films = getFilms();
            //webixView.root.sync(films2);
            //console.log("befo");

            scenario.on("reset", function() {
              //console.log("scenario reset");
                webixView.root.parse(scenario.businessVpnArray); //   getItemsCollection(BusinessVpnModel)); //  data.businessVpn);
            });
            webixView.root.parse(scenario.businessVpnArray); 

            //webixView.root.sync(scenario.businessVpnCollection); //   getItemsCollection(BusinessVpnModel)); //  data.businessVpn);
            //console.log("after");
            //scenario.businessVpnCollection.add({});
            //
            return this;
        },
    });

});

