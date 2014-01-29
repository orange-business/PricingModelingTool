package com.orange.ru.operation.lines.ref

import org.springframework.stereotype.*
import com.orange.ru.domain.product.ProductItem

@Service("ref_depreciation_equipment_months")
@Repository
class RefDepreciationEquipmentMonths {
  def calculate = { ProductItem item ->
    item.addDouble("ref_depreciation_equipment_months", 60)
  }
}