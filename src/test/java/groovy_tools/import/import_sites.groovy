/** Загрузка клиентов из OTB, ASR
 * база С: View с клиентами: kia.pp_customer_v, View с opportunity: kia.pp_opportunity_v, View с сайтами: kia.pp_site_v
 * Связь между view – идентификатор клиента (cl_id).
 */
def iterate1(closure) {
  it_sql = groovy.sql.Sql.newInstance('jdbc:oracle:thin:kia/kia@172.16.35.55:1521:c', 'kia', 'kia', 'oracle.jdbc.driver.OracleDriver')
  it_sql.eachRow('select cl_id, address from kia.pp_site_v order by cl_id ASC') { resultSet ->
    String address = resultSet.getAt(1)
    if (address != null && address.length() > 0) {
      BigInteger id = resultSet.getAt(0)
      String external_id = id > 0 ? id.toString() : '-' + id.abs().toString()
      if (resultSet.next()) closure(external_id, address)
    }
  }
  it_sql.close()
}
def closure = { String external_id, String address ->
  pricing_sql = groovy.sql.Sql.newInstance('jdbc:oracle:thin:pricing/pricing@s-devel.orange-ftgroup.ru:1521:b3', 'pricing', 'pricing', 'oracle.jdbc.driver.OracleDriver')
  BigInteger id;
  insertIncidenceSql = 'insert into site(address, customer_id) values (?,?)'
  def ans = pricing_sql.firstRow("select id from customer c where c.external_id = $external_id")
  if (ans != null && ans.id != null) {
    println ans.id + address
    pricing_sql.execute(insertIncidenceSql, [address, ans.id])
  }
  pricing_sql.close()
}
iterate1(closure)