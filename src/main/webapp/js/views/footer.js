"use strict";

define(['text!templates/footer.html', 'underscore', 'backbone'], function (template, _, Backbone) {
  return Backbone.View.extend({
    template: _.template(template),
    //
    text: "the footer",
    //
    render: function () {
      this.$el.html(this.template(this));
      return this;
    }
  });
});