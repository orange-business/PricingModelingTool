"use strict";

define(['data/webix-small_film_set', 'backbone', 'webix'], function (small_film_set, Backbone) {

    // ---------------------------------------------
    //              Tab 2
    // ---------------------------------------------

    var view_tab_2 = {
        view: "datatable",
        columns: [
			{ id: "rank", header: "", css: "rank", width: 50 },
			{ id: "title", header: "Film title", width: 200 },
			{ id: "year", header: "Released", width: 80 },
			{ id: "votes", header: "Votes", width: 100 }
		],
        select: "cell",
        autoheight: true,
        autowidth: true,
        data: small_film_set
    };

    // ---------------------------------------------
    //              Backbone View:
    // ---------------------------------------------

    return Backbone.View.extend({
        render: function() {
            var webixView = new WebixView({
			    config: view_tab_2,
			    el: this.el
		    });
            webixView.render();
            return this;
        },
    });

});
