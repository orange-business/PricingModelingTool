App.Factory = function (getter, hash, def) {
  return function () {
    var value = getter.apply(this, arguments);
    var ctor = hash[value] ? hash[value] : def;
    return new ctor(arguments[0], arguments[1]);
  };
};
App.ItemFactory = App.Factory(
    function (attr) { return attr.type; },
    {
      "bvpn": App.ItemBVpn,
      "lines": App.ItemLines
    },
    App.Item
);
