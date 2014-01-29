package com.orange.ru.operation.bvpn.ref

import org.springframework.stereotype.*
import com.orange.ru.domain.product.*

@Service("ref_mux_number")
@Repository
class RefMuxNumber {
  def calculate = { ProductItem item ->
    // ref_mux_number - Средневзвешенный коэффициент мультиплексирования. Зависит от реальных данных, рассчитываемых RBNF.
    item.addDouble("ref_mux_number",4.7)
  }
}