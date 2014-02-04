"use strict";
define(['data/test_films', 'backbone', 'webix'], function (films, Backbone) {
  // ---------------------------------------------
  //              Tab 3
  // ---------------------------------------------
  var view_tab_3 = {
    view: "datatable",
    columns: [
      { id: "rank", header: "", css: "rank", width: 50 },
      { id: "title", header: "Заголовок", width: 200 },
      { id: "year", header: "Год", width: 80 },
      { id: "votes", header: "голосов", width: 100 },
      { id: "text", header: "text", width: 270 },
    ],
    select: "cell",
    autoheight: true,
    autowidth: true
    //data: films
  };
  // ---------------------------------------------
  //              Backbone View:
  // ---------------------------------------------
  return Backbone.View.extend({
    render: function () {
      var webixView = new WebixView({
        config: view_tab_3,
        el: this.el
      });
      webixView.render();
      webixView.root.sync(films);
      return this;
    }
  });
});