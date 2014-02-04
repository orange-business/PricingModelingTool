"use strict";

define(['data/webix-small_film_set', 'backbone', 'webix'], function (small_film_set2, Backbone) {

    // ---------------------------------------------
    //              Tab 3
    // ---------------------------------------------

    var view_tab_3 = {
        view: "datatable",
        columns: [
			{ id: "rank", header: "", css: "rank", width: 50 },
			{ id: "title", header: "Заголовок", width: 200 },
			{ id: "year", header: "Год", width: 80 },
			{ id: "votes", header: "голосов", width: 100 }
		],
        select: "cell",
        autoheight: true,
        autowidth: true,
        data: small_film_set2
    };

    // ---------------------------------------------
    //              Backbone View:
    // ---------------------------------------------

    return Backbone.View.extend({
        render: function() {
            var webixView = new WebixView({
			    config: view_tab_3,
			    el: this.el
		    });
            webixView.render();
            return this;
        },
    });

});
