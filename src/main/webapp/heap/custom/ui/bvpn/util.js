var findByCode = function findByCode(item, value){
  if (item.product.code === "115.00"){
    var myArray = ["ref_tariff_ubb_data3_1MB_money", "ref_tariff_ubb_data2_1MB_money", "ref_tariff_ubb_data1_1MB_money",
              "ref_tariff_ubb_voice_1MB_money", "ref_tariff_ubb_video_1MB_money"], index = indexOf.call(myArray, value);
    if (index != -1 & item.product.port.tarifficationScheme == "fix" ) return "-";
  }
  var treasures = item.treasures;
  for (var i = 0, max = treasures.length; i < max; i++){
    if (treasures[i].code == value){
      return treasures[i].value;
    }
  }
  var strings = item.strings;
  for (var i = 0, max = strings.length; i < max; i++){
    if (strings[i].code == value){
      return strings[i].value;
    }
  }
  var numerics = item.numerics;
  for (var i = 0, max = numerics.length; i < max; i++){
    if (numerics[i].code == value){
      return numerics[i].value;
    }
  }
  return "не найдено";
}
var indexOf = function(needle) {
  if(typeof Array.prototype.indexOf === 'function') {
    indexOf = Array.prototype.indexOf;
  } else {
    indexOf = function(needle) {
      var i = -1, index = -1;

      for(i = 0; i < this.length; i++) {
        if(this[i] === needle) {
          index = i;
          break;
        }
      }
      return index;
    };
  }
  return indexOf.call(this, needle);
};