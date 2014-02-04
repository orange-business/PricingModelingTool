"use strict";

define(['data/tab1data', 'backbone', 'underscore', 'webix'], function (tabData, Backbone, _) {

    // ---------------------------------------------
    //              Tab 1
    // ---------------------------------------------

    var getFilms = function(){
        var FilmRecord = Backbone.Model.extend({});
        var FilmList = Backbone.Collection.extend({
            model: FilmRecord,
            url: "/js/data/webix-films.json"
        });

        var films = new FilmList();
        films.fetch(); //getting collection data
        return films;
    };

    // ----------------------------------------------------------



    // ----------------------------------------------------------


    var view_tab_1 = {
        view: "datatable",
        columns: [
			{ id: "rank", header: "", css: "rank", width: 50 },
			{ id: "id", header: "id", width: 50 },
			{ id: "title", header: "Фильм", width: 200 },
			{ id: "type", header: "type", width: 80 },
			{ id: "product.code", header: "product.code", width: 100 }
		],
        autoheight: true,
        autowidth: true,
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
            webixView.root.sync(tabData);
            //
            return this;
        },
    });

});

