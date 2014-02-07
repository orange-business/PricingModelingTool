"use strict";

define(['data/test_scenario', 'models/AccessLinesModel', 'backbone', 'webix'], function (scenario, AccessLinesModel, Backbone) {

    // ---------------------------------------------
    //              Tab 2
    // ---------------------------------------------

    var view_tab_2 = {
        view: "datatable",
        id: "datatable2",
        columns: [
			{ id: "itemId", header: "id", width: 50 },
			{ id: "productSiteAddress", header: "Site address", width: 150 },
			{ id: "in_lines_type_string", header: "Тип доступа", width: 100, editor:"select", options:["build","lease"] }, 
            { id: "in_capex_equipment_money", header: "Стоимость оборудования", width: 100, editor: "text" },
            { id: "onetime_money", header: "Разовые затраты", width: 100, editor: "text" },
            { id: "monthly_money", header: "Ежемесячные затраты", width: 100, editor: "text" },
            { id: "inout_required_price_payment_onetime_money", header: "Желаемый разовый", width: 100, editor: "text" },
            { id: "inout_required_price_payment_monthly_money", header: "Желаемый ежемесячный", width: 100, editor: "text"  },
            { id: "out_required_price_margin_percent", header: "Маржа", width: 100  },
            { id: "out_required_price_payback_months", header: "Срок окупаемости", width: 100 },
            { id: "out_standard_price_payment_onetime_money", header: "Стандартный разовый", width: 100 },
            { id: "out_standard_price_payment_monthly_money", header: "Стандартный ежемесячный", width: 100 },
            { id: "out_standard_price_margin_percent", header: "Маржа", width: 100 },
            { id: "out_standard_price_payback_months", header: "Срок окупаемости", width: 100 },
            { id: "customNote", header: "Комментарий", width: 150, editor: "text" },
		],
        select: "cell",
        editable: true,
        autoheight: true,
        autowidth: true,
        resizeColumn: true,
    };


    var view_scroll = {
        view:"scrollview",
        id:"verses",
        scroll:"x", 
        isolate:true,
        body:view_tab_2,
//        body:{
//            rows:[view_tab_2]
//        }
    };

    /*

gridLines.setHeader("id, Site address, Тип доступа, Стоимость оборудования, Разовые затраты, Ежемесячные затраты, " +
      "Желаемый разовый, Желаемый ежемесячный, Маржа, Срок окупаемости, Стандартный разовый, Стандартный ежемесячный, " +
      "Маржа, Срок окупаемости, Комментарий");
  gridLines.setInitWidths("60,100,70,70,70,70,100,80,70,70,70,70,70,70,120");
  gridLines.setColAlign("right,right,left,left,left,left,left,left,left,left,left,left,left,left,left");
  gridLines.setColTypes("dyn,ed,ed,ed,ed,ed,combo,ed,ed,ed,ed,ed,ed,ed,ed");
  gridLines.setColSorting("int,str,str,str,str,str,str,str,str,str,str,str,str,str,str");
[14:57:03] Зайнуллин Радик: это для Access Lines услуга

    */

    // ---------------------------------------------
    //              Backbone View:
    // ---------------------------------------------

    return Backbone.View.extend({
        render: function() {
            var webixView = new WebixView({
			    //config: view_scroll, //view_tab_2,
			    config: view_tab_2,
			    el: this.el,
                isolate:true,
		    });
            webixView.render();

            scenario.on("reset", function() {
                webixView.root.parse(scenario.accessLinesArray);
            });
            webixView.root.parse(scenario.accessLinesArray);
            //var grid = webixView.getChild("datatable2"); // .root.body;

            //webixView.root.sync(data.accessLines);
            //webixView.root.sync(scenario.accessLinesCollection);     // getItemsCollection(AccessLinesModel));
            //webixView.root.sync(scenario.getItemsCollection(AccessLinesModel));
            return this;
        },
    });

});
