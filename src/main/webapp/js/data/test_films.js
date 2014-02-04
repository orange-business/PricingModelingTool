"use strict";

define(['data/test_films_raw', 'backbone', 'lib/backbone/backbone.mutators'], function (rawData, Backbone, Mutators) {

    var FilmModel = Backbone.Model.extend({
        mutators: {
            text: function () {
                return this.get('year') + ": " + this.get('title');
            }
        }
    });


    var FilmCollection = Backbone.Collection.extend({
        model: FilmModel
    });

    var data = new FilmCollection(rawData);

    return data;

});