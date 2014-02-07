"use strict";

// ----------------------------------------------
// config в точности такой же, как в start.js:
// ----------------------------------------------

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
//              список тестов:
// ------------------------------------------

var tests = [
    'tests/models/test_ScenarioModel',
    'tests/models/test_ItemModel',
    'tests/models/test_BusinessVPNModel',
    'tests/models/test_AccessLinesModel',
    'tests/models/test_utils',
];

// ------------------------------------------
//              запуск тестов:
// ------------------------------------------

var dependencies = ['QUnit'];
for (var i = 0; i < tests.length; i++) dependencies.push(tests[i]);

require(dependencies, function(QUnit) {
    QUnit.load();
    QUnit.start();
});
