(function buildInterface() {
  dhtmlx.image_path='/js/lib/dhtmlx/imgs/';

  var main_layout = new dhtmlXLayoutObject("contener", '4U');
  var menu = main_layout.attachMenu();
  menu.setSkin("dhx_skyblue");
  menu.addNewChild(null, 0, "opportunityId", "Opportunity");
  menu.addNewChild(null, 1, "scenarioId", "Scenario");
  menu.addNewChild(null, 2, "reportsId", "Отчеты");
  menu.addNewChild(null, 3, "newsId", "Новости");
  menu.addNewChild(null, 4, "helpId", "Помощь");
  menu.addNewChild("scenarioId", 0, "saveScenarioId", "Сохранить");
  menu.addNewChild("scenarioId", 0, "loadScenarioId", "Загрузить");

  main_layout.cells('a').setHeight(100);
  main_layout.cells("a").setWidth(500);
  main_layout.cells("a").fixSize(true, false);
  main_layout.cells('a').hideHeader();
  main_layout.cells('b').hideHeader();
  main_layout.cells('c').hideHeader();

  var form = main_layout.cells('a').attachForm([
    {type: "settings", labelWidth: "auto", inputWidth: 250, position: "label-left" },
    {type: "label", name:"form_label_1",  label:"Pricing Portal Russia"},
    {type: "input", label: "Opportunity", name: "opportunity", value: "2033"},
    {type: "input", label: "Клиент",      name: "customer", value: "ОАО \"Янтарный сказ\""}
  ]);
  form.setFontSize("18px");

  var tabbar = main_layout.cells('d').attachTabbar();
  tabbar.setImagePath("/js/lib/dhtmlx/imgs/");
  tabbar.setHrefMode("ajax-html");
  tabbar.setSkinColors("#FCFBFC","#F4F3EE");
  tabbar.addTab("common","Общее","100px");
  tabbar.addTab("taxes","Все тарифы","100px");
  tabbar.addTab("lines","Access Lines","100px");
  tabbar.setContentHTML("taxes","Не знаю пока что здесь разместить.");
  tabbar.addTab("bvpn","Business VPN","100px");
  tabbar.setTabActive("lines");
})();