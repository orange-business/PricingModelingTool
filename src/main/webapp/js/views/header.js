﻿"use strict";

define(['text!templates/header.html', 'underscore', 'backbone'], function (template, _, Backbone) {

    return Backbone.View.extend({
        template: _.template(template),
        //
        text: "Pricing Portal Russia",
        //
        render: function() {
            this.$el.html(this.template(this));
            return this;
        },
    });

});