var initLines = function(){
  var data = { rows:[] }
  var gridLines = tabbar.cells("lines").attachGrid();
  gridLines.setImagePath('/js/dhtmlx/imgs/');
  gridLines.setHeader("id, Site address, Тип доступа, Стоимость оборудования, Разовые затраты, Ежемесячные затраты, " +
      "Желаемый разовый, Желаемый ежемесячный, Маржа, Срок окупаемости, Стандартный разовый, Стандартный ежемесячный, " +
      "Маржа, Срок окупаемости, Комментарий");
  gridLines.setInitWidths("60,100,70,70,70,70,100,80,70,70,70,70,70,70,120");
  gridLines.setColAlign("right,right,left,left,left,left,left,left,left,left,left,left,left,left,left");
  gridLines.setColTypes("dyn,ed,ed,ed,ed,ed,combo,ed,ed,ed,ed,ed,ed,ed,ed");
  gridLines.setColSorting("int,str,str,str,str,str,str,str,str,str,str,str,str,str,str");
  gridLines.init();

  var linesItems = [];
  for (var i = 0, max = loaded.items.length; i < max; i++){
    if (loaded.items[i].product.code == "802.01") {
      linesItems.push(loaded.items[i]);
    }
  }
  for (var i = 0, max = linesItems.length; i < max; i++){
    var elem = {id: i, data:[]};
    elem.data.push(i+1);
    elem.data.push(linesItems[i].product.site.address);
    // тип доступа
    elem.data.push(findByCode(linesItems[i], "in_lines_type_string"));
    // стоимость оборудования
    elem.data.push(findByCode(linesItems[i], "in_capex_equipment_money"));
    // разовые затраты
    if (linesItems[i].product.type === "build") {
      elem.data.push(findByCode(linesItems[i], "in_capex_build_money"));
    }
    if (linesItems[i].product.type === "lease") {
      elem.data.push(findByCode(linesItems[i], "in_cost_onetime_lease_money"));
    }
    // ежемесячные затраты
    if (linesItems[i].product.type === "build") {
      elem.data.push(findByCode(linesItems[i], "in_cost_monthly_build_money"));
    }
    if (linesItems[i].product.type === "lease") {
      elem.data.push(findByCode(linesItems[i], "in_cost_monthly_lease_money"));
    }
    // желаемый разовый платеж
    elem.data.push(findByCode(linesItems[i], "inout_required_price_payment_onetime_money"));
    // желаемый ежемесячный платеж
    elem.data.push(findByCode(linesItems[i], "inout_required_price_payment_monthly_money"));
    elem.data.push(findByCode(linesItems[i], "out_required_price_margin_percent"));
    elem.data.push(findByCode(linesItems[i], "out_required_price_payback_months"));
    elem.data.push(findByCode(linesItems[i], "out_standard_price_payment_onetime_money"));
    elem.data.push(findByCode(linesItems[i], "out_standard_price_payment_monthly_money"));
    elem.data.push(findByCode(linesItems[i], "out_standard_price_margin_percent"));
    elem.data.push(findByCode(linesItems[i], "out_standard_price_payback_months"));
    elem.data.push("Обследования не требуется.");
    data.rows.push(elem);
  }
  gridLines.parse(data,"json");
}