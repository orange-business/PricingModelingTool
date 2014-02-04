
define(['models/Item'], function (ItemModel) {

    var ItemLinesModel = ItemModel.extend({
        url: '/path/to/data',
        defaults: {
            name: 'lines',
            "type": null,
            "note": null,
            fullName: "Access Lines",
            "product": {
                "id": null,
                "linesType": null,
                "site": {
                    "id": null,
                    "isLastMile": null,
                    "isBusinessCentre": null,
                    "customer": {
                        "id": null,
                        "official": null,
                        "externalId": null,
                        "customerType": null
                    },
                    "address": null
                }
            },
            "strings": [],
            "numerics": [],
            "treasures": []
        }
    });


    return ItemLinesModel;
});
