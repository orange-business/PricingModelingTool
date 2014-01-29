package groovy_tools.code

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.*

readExcel('myfile.txt')

/** This method shall take the cell object and convert it into a string and return. */
static def String getCellValue(Cell cell) {
  if (cell == null) { return ""; }
  else {
    switch (cell.getCellType()) {
      case Cell.CELL_TYPE_STRING: return cell.getRichStringCellValue().toString();
      case Cell.CELL_TYPE_NUMERIC:
        if (DateUtil.isCellDateFormatted(cell)) return cell.getDateCellValue().toString();
        else if (cell.getCellStyle().getDataFormatString().contains("%")) return (cell.getNumericCellValue() * 100).toString() + "%";
        else return cell.getNumericCellValue().toBigInteger().toString();
      case Cell.CELL_TYPE_BOOLEAN: return cell.getBooleanCellValue().toString();
      case Cell.CELL_TYPE_FORMULA: return cell.getCellFormula().toString();
      case Cell.CELL_TYPE_BLANK: return "";
      case Cell.CELL_TYPE_ERROR: return "";
      default: return "";
    }
  }
}
private readExcel(String out) {
  new File(out).delete(); def f = new File(out)
  def fis = new FileInputStream(new File('Коды идентификации v3.4.xlsx'))
  def book = new XSSFWorkbook(fis)

  def rng=4..160, lst=[]; rng.step(1) { it -> lst << it }
  book.sheets[0].each { row -> closure 'bvpn',row, lst, f }

  rng = 3..45; lst=[]; rng.step(1) { it -> lst << it }
  book.sheets[1].each{ row -> closure 'lines',row, lst, f }
  fis.close()

  rng = 1..4; lst=[]; rng.step(1) { it -> lst << it }
  book.sheets[2].each{ row -> closure 'common',row, lst, f }
  fis.close()
}
void closure(String namespace, XSSFRow row, ArrayList lst, File f) {
  Iterator cellIterator = row.cellIterator();
  if (lst.contains(row.rowNum)) {
    String desc = '', code = '', note = ''
    while (cellIterator.hasNext()) {
      XSSFCell cell = cellIterator.next();
      if (cell.getColumnIndex() == 2) desc = getCellValue(cell).trim() + ' '
      if (cell.getColumnIndex() == 3) {
        if (!(desc ==~ "\\s*"))  desc = desc.concat(getCellValue(cell).trim())
        else desc = getCellValue(cell).trim()
      }
      if (cell.getColumnIndex() == 4) code = getCellValue(cell).trim()
      if (cell.getColumnIndex() == 5) note = 'Значение: ' + getCellValue(cell).trim() + ', '
      if (cell.getColumnIndex() == 6) note = note.concat('Формулы: ' + getCellValue(cell).trim())
    }
    if (!code.equals("")) f.append("insert into identification(description, code, note, namespace) values('$desc','$code','$note', '$namespace');\n")
  }
}