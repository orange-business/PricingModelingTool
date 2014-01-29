define(['models/Item', 'models/itemBVpn', 'models/itemLines', ], function (ItemModel, ItemBVpnModel, ItemLinesModel) {
  /**
   * Factory of polymorphic classes
   * @param {Function} getter returns condition value
   * @param {Object} hash contains all constructors
   * @param {Function} defaultType default constructor
   * @returns {Function}
   * @constructor
   */
  var Factory = function (getter, hash, defaultType) {
    return function () {
      var value = getter.apply(this, arguments);
      var ctor = value && hash[value] ? hash[value] : defaultType;
      return new ctor(arguments[0], arguments[1]);
    };
  };
  var ItemFactory = Factory(
      function (attributes) {
        return attributes.name;
      },
      {
        "bvpn":ItemBVpnModel,
        "lines":ItemLinesModel
      },
      ItemModel
  );
  return ItemFactory;
});