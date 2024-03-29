﻿"use strict";

// ------------------------------------------------------------------------------
// Qunit добавлено сюда, чтобы тестовый конфиг был одинаков с рабочим конфигом
// (в случае сомнений скопировать полностью конфиг отсюда в test.js)
// ------------------------------------------------------------------------------

require.config({
    paths: {
        jquery: 'lib/jquery-2.1.0.min',
        underscore: 'lib/underscore',
        backbone: 'lib/backbone/backbone',
        //backbone_mutators: 'lib/backbone/backbone.mutators',
        text: 'lib/require/text',
        //templates: '/templates',
        //webix: 'lib-wrap/webix',
        webix: 'lib/webix/webix_debug',
        jquery_ui: 'lib/jqueryui/jquery-ui',
        QUnit: 'lib/qunit/qunit-1.13.0'
    },
    shim: {
        underscore: {
            exports: '_'
        },
        backbone: {
            deps: ['underscore', 'jquery'],
            exports: 'Backbone'
        },
        backbone_nested: {
            deps: ['underscore', 'jquery', 'backbone'],
        },
        jquery_ui: {
            deps: ['jquery']
        },
        webix: {
            deps: ['backbone']
        },
        'QUnit': {
            exports: 'QUnit',
            init: function () {
                QUnit.config.autoload = false;
                QUnit.config.autostart = false;
            }
        }
    }
});


// ------------------------------------------
//              запуск приложения:
// ------------------------------------------

require(['views/app'], function (AppView) {

    var appView = new AppView();
    appView.render();

});





