require.config({
  baseUrl:'/js',
  paths:{
    jquery:'lib/jquery-2.1.0.min',
    underscore:'lib/underscore',
    backbone:'lib/backbone/backbone',
    storage:'lib/backbone/backbone.localStorage',
    text:'lib/require/text',
    dhtmlx:'lib/dhtmlx/dhtmlx',
    QUnit:'lib/qunit/qunit-1.13.0'
  },
  shim:{
    underscore:{
      exports:'_'
    },
    backbone:{
      deps:['underscore', 'jquery'],
      exports:'Backbone'
    },
    'QUnit':{
      exports:'QUnit',
      init:function () {
        QUnit.config.autoload = false;
        QUnit.config.autostart = false;
      }
    }
  }
});

require(['backbone', 'QUnit', 'models/factory'], function (Backbone, QUnit, ItemFactory) {
  test("Polymorphism test", function () {
    var ItemsModel = Backbone.Collection.extend({
      model:ItemFactory
    });

    var items = new ItemsModel();

    items.add({});
    items.add({ name:"bvpn" });
    items.add({ name:"lines" });

    var item0 = items.at(0);
    var item1 = items.at(1);
    var item2 = items.at(2);

    var x = item1.get("fullName");

    QUnit.strictEqual(item0.get("fullName"), undefined);
    QUnit.strictEqual(item1.get("fullName"), "Bisiness VPN");
    QUnit.strictEqual(item2.get("fullName"), "Access Lines");
  });
  QUnit.load();
  QUnit.start();
});