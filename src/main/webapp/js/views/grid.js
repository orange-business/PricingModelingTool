/**
 * Это элемент-грид
 */
var GridView = Backbone.View.extend({
  events: {
    'dblclick label': 'edit'
  },
  render: function(){
    this.$el.html(this.gridTpl(this.model.toJSON()));
    this.input = this.$('.edit');
    return this;
  },
  edit: function(){

  },
  updateOnEnter: function(){

  }
});