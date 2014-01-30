// hello2
require.config({
  paths: {
    jquery:   'lib/jquery-2.1.0.min',
    underscore: 'lib/underscore',
    backbone: 'libs/backbone/backbone-min',
    text:     'libs/require/text'
  },
  shim: {
    underscore: {
      exports: '_'
    },
    backbone: {
      deps: [ 'underscore', 'jquery' ],
      exports: 'Backbone'
    }
  }
});
require(['/js/views/app', '/js/collections/todos'], function(AppView, AppCollection){
  var app_view = new AppView({
    collection: AppCollection
  });
});