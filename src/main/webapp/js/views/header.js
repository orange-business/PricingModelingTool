"use strict";
define(['text!templates/header.html', 'underscore', 'backbone'], function (template, _, Backbone) {
  return Backbone.View.extend({
    template: _.template(template),
    //
    text: "the header",
    //
    render: function () {
      this.$el.html(this.template(this));
      return this;
    }
  });
});