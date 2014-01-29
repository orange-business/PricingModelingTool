package com.orange.ru.operation.lines.ref

import org.springframework.stereotype.*
import com.orange.ru.domain.product.*

@Service("ref_depreciation_build_months")
@Repository
class RefDepreciationBuildMonths {
  def calculate = { ProductItem item ->
    item.addDouble("ref_depreciation_build_months", 120)
  }
}