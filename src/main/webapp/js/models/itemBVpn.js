define(['models/Item'], function (ItemModel) {
  var ItemBVpnModel = ItemModel.extend({
    url:'/path/to/data',
    defaults:{
      name:"bvpn",
      fullName:"Bisiness VPN",
      "type":"upgrade",
      "note":null,
      "product":{
        "id":null,
        "town":null,
        "site":{
          "id":null,
          "isLastMile":null,
          "isBusinessCentre":null,
          "customer":{
            "id":null,
            "official":null,
            "externalId":null,
            "customerType":null
          },
          "address":null
        },
        "port":{
          "id":null,
          "speed":null,
          "tarifficationScheme":null,
          "coverage":null,
          "type":null
        }
      },
      "strings":[],
      "numerics":[],
      "treasures":[]
    }
  });
  return ItemBVpnModel;
});