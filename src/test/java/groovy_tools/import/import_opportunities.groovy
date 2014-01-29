/** Загрузка клиентов из OTB, ASR
 * база С: View с клиентами: kia.pp_customer_v, View с opportunity: kia.pp_opportunity_v, View с сайтами: kia.pp_site_v
 * Связь между view – идентификатор клиента (cl_id).
 */
def iterate1(closure) {
  it_sql = groovy.sql.Sql.newInstance('jdbc:oracle:thin:kia/kia@172.16.35.55:1521:c', 'kia', 'kia', 'oracle.jdbc.driver.OracleDriver')
  it_sql.eachRow('select cl_id, opp_id from kia.pp_opportunity_v order by cl_id ASC') { resultSet ->

    BigInteger opp_id = resultSet.getAt(1)
    if (opp_id != null) {
      if (resultSet.next()) closure(resultSet.getAt(0).toString(), opp_id.toString())
    }
  }
  it_sql.close()
}
def closure = { String external_id, String opp_id ->
  pricing_sql = groovy.sql.Sql.newInstance('jdbc:oracle:thin:pricing/pricing@s-devel.orange-ftgroup.ru:1521:b3', 'pricing', 'pricing', 'oracle.jdbc.driver.OracleDriver')
  BigInteger id;

  def ans1, ans2
  pricing_sql.query("select count(*) as num from opportunity c where c.external_id = $opp_id") { answer ->
    if (answer.next()) ans1 = (answer.getInt("num") == 0)
  }
  pricing_sql.query("select count(*) as num from customer c where c.external_id = $external_id") { answer ->
    if (answer.next()) ans2 = (answer.getInt("num") >= 1)
  }

  if (ans1&ans2) {
    def ans = pricing_sql.firstRow("select id from customer c where c.external_id = $external_id")
    println "customer id = " + ans.id + ", opportunity id = " + opp_id

    pricing_sql.execute('insert into opportunity(external_id, customer_id) values (?,?)', [opp_id, ans.id])
  }
  pricing_sql.close()
  //println external_id + ' ' + opp_id
}
iterate1(closure)