"use strict";

define(['text!templates/info.html', 'underscore', 'backbone'], function (template, _, Backbone) {

    return Backbone.View.extend({
        template: _.template(template),
        //
        render: function() {
            this.$el.html(this.template(this));
            return this;
        },
    });

});