/**
 * Обертка grid для услуг Access Lines.
 */
// http://requirejs.org/docs/errors.html#notloaded
// "If you are listing dependencies in the dependency array,
// make sure that *require* and *name* are in the dependency array..."

define(["require", "lib/dhtmlx/dhtmlx"], function (require) {
  require("lib/dhtmlx/dhtmlx");

  return {
    dhtmlXGridObject: dhtmlXGridObject,
    dhtmlXDataStore: dhtmlXDataStore
  };
});
