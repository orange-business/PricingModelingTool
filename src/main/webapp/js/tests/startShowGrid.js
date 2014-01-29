/**
 * Created by radik.zaynullin on 29.01.14.
 */
require.config({
  baseUrl: '/js',
  paths: {
    jquery: 'lib/jquery-2.1.0.min',
    underscore: 'lib/underscore',
    backbone: 'lib/backbone/backbone',
    storage: 'lib/backbone/backbone.localStorage',
    text: 'lib/require/text'
  },
  shim: {
    underscore: {
      exports: '_'
    },
    backbone: {
      deps: ['underscore', 'jquery'],
      exports: 'Backbone'
    }
  }
});

require(['modules/gridLines', 'lib/require/domReady', 'tests/sampleData', 'underscore'],
    function (grid, domReady, sampleData, _) {

  domReady(function () {
    var mygrid = new grid.dhtmlXGridObject('mygrid_container');
    mygrid.setImagePath("js/lib/dhtmlx/imgs/");
    mygrid.setHeader("id,type,product.code");
    mygrid.setColumnIds("id,type,product.code");
    mygrid.setInitWidths("*,150,150");
    mygrid.setColAlign("left,right,right");
    mygrid.setSkin("light");
    mygrid.init();
    //
    var myDataStore = new grid.dhtmlXDataStore();
    var itemsBusinessVPN = _.filter(sampleData.items, function (x) { return x.product.code == "115.00"; });
    //var gridItems = _.map(itemsBusinessVPN, function (x) { return {id: x.id, type: x.type, note: x.note}; });
    myDataStore.parse(itemsBusinessVPN);
    mygrid.sync(myDataStore);
  });

});