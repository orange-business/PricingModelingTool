"use strict";

require.config({
    paths: {
        jquery: 'lib/jquery-2.1.0.min',
        underscore: 'lib/underscore',
        backbone: 'lib/backbone/backbone',
        text: 'lib/require/text',
        //templates: '/templates',
        //webix: 'lib-wrap/webix',
        webix: 'lib/webix/webix_debug',
        jquery_ui: 'lib/jqueryui/jquery-ui'
    },
    shim: {
        underscore: {
            exports: '_'
        },
        backbone: {
            deps: ['underscore', 'jquery'],
            exports: 'Backbone'
        },
        jquery_ui: {
            deps: ['jquery']
        },
        webix: {
            deps: ['backbone']
        }
    }
});


require(['views/app'], function (AppView) {

    var appView = new AppView();
    appView.render();

});





