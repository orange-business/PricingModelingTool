"use strict";

define(['text!templates/tabs.html', 'views/tab1', 'views/tab2', 'jquery', 'underscore', 'backbone', 'jquery_ui'], function (template, Tab1View, Tab2View, $, _, Backbone) {

    return Backbone.View.extend({
        template: _.template(template),
        //
        renderTab: function(containerSelector, TabView){
            var container = $(containerSelector, this.$el)[0];
            //
            var tabView = new TabView({ el: container });
            tabView.render();
        },
        //
        render: function() {
            this.$el.html(this.template(this));
            //
            this.renderTab("#tabs-4", Tab1View);
            this.renderTab("#tabs-3", Tab2View);
            //
           $(function() { $("#tabs", this.$el).tabs({ active: 2 }); });
            //
            return this;
        }
    });

});

