"use strict";

define(['text!templates/tabs.html', 'views/tab1', 'views/tab2', 'views/tab3', 'jquery', 'underscore', 'backbone', 'jquery_ui'], function (template, Tab1View, Tab2View, Tab3View, $, _, Backbone) {

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
            this.renderTab("#tabs-1", Tab1View);
            this.renderTab("#tabs-2", Tab2View);
            this.renderTab("#tabs-3", Tab3View);
            //
            $(function() { $("#tabs", this.$el).tabs(); });
            //
            return this;
        },
    });

});

