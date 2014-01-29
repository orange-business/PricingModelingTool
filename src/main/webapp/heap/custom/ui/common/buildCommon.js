dhtmlxAjax.get("/product/10",function(loader){
  var mygrid = tabbar.cells("common").attachGrid();
  mygrid.setImagePath('/js/dhtmlx/imgs/');
  mygrid.setHeader("Sales, Book Title, Author");
  mygrid.setInitWidths("70,250,*");
  mygrid.setColAlign("right,left,left");
  mygrid.setColTypes("dyn,ed,ed");
  mygrid.setColSorting("int,str,str");
  var data = loader.xmlDoc.responseText;
  mygrid.init();
  mygrid.parse(data,"json");
  mygrid.attachEvent("onRowSelect", doOnRowSelected);
});