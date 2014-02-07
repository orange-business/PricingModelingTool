"use strict";

define(['backbone', 'webix'], function (Backbone) {

    // ---------------------------------------------
    //              Webix Menu:
    // ---------------------------------------------

//    var menuData = [
//            	{ id: "1", value: "Translate...", submenu: ["English", { value: "Slavic...", submenu: ["Belarus", "Russian", "Ukrain"] }, "German"] },
//				{ id: "2", value: "Post...", submenu: ["Facebook", "Google+", "Twitter"] },
//				{ id: "3", value: "Info...", submenu: ["About", "Help"] }
//			];

    var menuData = [
            	{ id: "1", value: "Opportunity" },
				{ id: "2", value: "Сценарий" },
				{ id: "3", value: "Отчеты" },
				{ id: "4", value: "Новости" },
				{ id: "5", value: "Помощь" },
				{ id: "6", value: "search" }
			];

    var view_menu = {
        view: "menu",
        data: menuData,
        openAction: "click",
        //on: { onMenuItemClick: function (id) { webix.message("Click: " + this.getMenuItem(id).value); } },
        type: { subsign: true }
    };

    // ---------------------------------------------
    //              Backbone View:
    // ---------------------------------------------

    return Backbone.View.extend({
        render: function() {
            var webixView = new WebixView({
			    config: view_menu,
			    el: this.el
		    });
            webixView.render();
            return this;
        },
    });

});


