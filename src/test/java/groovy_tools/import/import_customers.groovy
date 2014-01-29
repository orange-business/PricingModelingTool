/** Загрузка клиентов из OTB, ASR
 *  база С: View с клиентами: kia.pp_customer_v, View с opportunity: kia.pp_opportunity_v, View с сайтами: kia.pp_site_v
 *  Связь между view – идентификатор клиента (cl_id).
 */
def iterate1(closure) {
  it_sql = groovy.sql.Sql.newInstance('jdbc:oracle:thin:kia/kia@172.16.35.55:1521:c', 'kia', 'kia', 'oracle.jdbc.driver.OracleDriver')
  it_sql.eachRow('select cl_id, name_r from kia.pp_customer_v order by cl_id ASC') { resultSet ->
    String name = resultSet.getAt(1)
    if (name != null && name.length() > 0) {
      BigInteger id = resultSet.getAt(0)
      String external_id = id > 0 ? id.toString() : '-' + id.abs().toString()
      if (resultSet.next()) closure(external_id, name)
    }
  }
  it_sql.close()
}
def closure = { String external_id, String name ->
  res = false
  insertIncidenceSql = 'insert into customer(official,type_id,external_id) values(?,100,?)'
  pricing_sql = groovy.sql.Sql.newInstance('jdbc:oracle:thin:pricing/pricing@s-devel.orange-ftgroup.ru:1521:b3', 'pricing', 'pricing', 'oracle.jdbc.driver.OracleDriver')
  pricing_sql.query("select count(*) as res from customer c where c.external_id = $external_id") { answer ->
    if (answer.next()) res = (answer.getInt("res") == 0)
  }
  if (res) {
    println  external_id + ' ' + name
    pricing_sql.execute(insertIncidenceSql, [name, external_id])
  }
  pricing_sql.close()
}
iterate1(closure)