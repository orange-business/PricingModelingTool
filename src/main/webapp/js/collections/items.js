define(['underscore','backbone','storage'],
    function(_, Backbone, Store){
      var Items = Backbone.Collection.extend({

        url: '/path/to/data',
        // Reference to this collection's model.
        model: App.ItemFactory,

        // Save all of the todo items under the `"todos"` namespace.
        localStorage: new Store('items')

      });
      return new Items();
    });