define(['backbone'], function (Backbone) {
  var ItemModel = Backbone.Model.extend({
    defaults:{
      name:'lines',
      state:'new'
    },
    initialize:function () {
      this.on('change', function () {
        console.log('model is changed!');
      });
    }
  });
  return ItemModel;
});