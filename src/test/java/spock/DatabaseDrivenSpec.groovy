package spock

import groovy.sql.Sql
import spock.lang.Shared
import spock.lang.Specification

class DatabaseDrivenSpec extends Specification {
  @Shared sql = Sql.newInstance('jdbc:oracle:thin:pricing/pricing@s-devel.orange-ftgroup.ru:1521:b3', 'pricing', 'pricing', 'oracle.jdbc.driver.OracleDriver')

  // insert data (usually the database would already contain the data)
  def setupSpec() {
    sql.execute("create table maxdata(id NUMBER(10,0), a NUMBER(10,0), b NUMBER(10,0), c NUMBER(10,0))")
    sql.execute("insert into maxdata values (1, 3, 7, 7)")
    sql.execute("insert into maxdata values (2, 5, 4, 5)")
    sql.execute("insert into maxdata values (3, 9, 9, 9)")
  }

  def "maximum of two numbers"() {
    expect:
    Math.max(a, b) == c

    where:
    [a, b, c] << sql.rows("select a, b, c from maxdata")
  }
}