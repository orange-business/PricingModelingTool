"use strict";

define(['views/header', 'views/menu', 'views/info', 'views/tabs', 'views/footer', 'jquery', 'backbone'], function (HeaderView, MenuView, InfoView, TabsView, FooterView, $, Backbone) {

    return Backbone.View.extend({
        el: $("#app"),
        //
        initialize: function(){
            this.view_header = new HeaderView({ el: this.$('.app-header') });
            this.view_menu = new MenuView({ el: this.$('.app-menu') });
            this.view_info = new InfoView({ el: this.$('.app-info') });
            this.view_tabs = new TabsView({ el: this.$('.app-tabs') });
            this.view_footer = new FooterView({ el: this.$('.app-footer') });
        },
        //
        render: function() {
            this.view_header.render();
            this.view_menu.render();
            this.view_info.render();
            this.view_tabs.render();
            this.view_footer.render();
            return this;
        },
    });

});