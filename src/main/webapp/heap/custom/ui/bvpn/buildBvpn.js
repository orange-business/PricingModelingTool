var initBvpn = function(){
  var gridBvpn = tabbar.cells("bvpn").attachGrid();
  gridBvpn.setImagePath('/js/dhtmlx/imgs/');
  gridBvpn.setHeader("id, Site address, Город PE, вид порта, тип порта, скорость, схема тарификации, инсталляция, ежемесячно, D3, D2, D1, Voice, Video");
  gridBvpn.setInitWidths("60,100,70,70,70,70,100,80,70,70,70,70,70,70");
  gridBvpn.setColAlign("right,right,left,left,left,left,left,left,left,left,left,left,left,left");
  gridBvpn.setColTypes("dyn,ed,ed,ed,ed,ed,combo,ed,ed,ed,ed,ed,ed,ed");
  gridBvpn.setColSorting("int,str,str,str,str,str,str,str,str,str,str,str,str,str");
  gridBvpn.init();

  var bvpnItems = [];
  for (var i = 0; i < loaded.items.length; i++){
    if (loaded.items[i].product.code == "115.00"){
      bvpnItems.push(loaded.items[i]);
    }
  }
  var data = { rows:[] };
  for (var i = 0; i < bvpnItems.length; i++){
    var elem = {id: i, data:[]};
    elem.data.push(i+1);
    elem.data.push(bvpnItems[i].product.site.address);
    elem.data.push(bvpnItems[i].product.town);
    elem.data.push(bvpnItems[i].product.port.coverage);
    elem.data.push(bvpnItems[i].product.port.type);
    elem.data.push(bvpnItems[i].product.port.speed);
    elem.data.push(bvpnItems[i].product.port.tarifficationScheme);
    elem.data.push(findByCode(bvpnItems[i], "out_standard_price_payment_onetime_money"));

    if (bvpnItems[i].product.port.tarifficationScheme == "fix"){
      elem.data.push(findByCode(bvpnItems[i], "out_standard_price_fix_payment_monthly_money"));
    } else {
      elem.data.push(findByCode(bvpnItems[i], "in_required_price_ubb_payment_monthly_money"));
    }

    elem.data.push(findByCode(bvpnItems[i], "ref_tariff_ubb_data3_1MB_money"));
    elem.data.push(findByCode(bvpnItems[i], "ref_tariff_ubb_data2_1MB_money"));
    elem.data.push(findByCode(bvpnItems[i], "ref_tariff_ubb_data1_1MB_money"));
    elem.data.push(findByCode(bvpnItems[i], "ref_tariff_ubb_voice_1MB_money"));
    elem.data.push(findByCode(bvpnItems[i], "ref_tariff_ubb_video_1MB_money"));
    data.rows.push(elem);
  }
  gridBvpn.parse(data,"json");
}