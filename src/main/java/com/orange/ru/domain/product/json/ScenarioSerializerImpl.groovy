package com.orange.ru.domain.product.json

import com.orange.ru.domain.Scenario
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("scenarioSerializer")
@Transactional
class ScenarioSerializerImpl implements ScenarioSerializer {
  @Override
  String serialize(Scenario scenario) {
    def builder = new groovy.json.JsonBuilder()
    builder(
        id: scenario.getId(),
        ownerEmail: scenario.getOwnerEmail(),
        note: scenario.getNote(),
        contractTerm: scenario.getContractTerm(),
        lastUpdateDate: scenario.getLastUpdateDate().toString("yyyy.MM.dd hh:mm"),
        items: scenario.productItems.collect({ item ->
          builder(
              id: item.id,
              type: item.type.code,
              note: item.note,
              strings: item.strings.collect { wrapper ->
                builder(id: wrapper.id, code: wrapper.identification.code, value: wrapper.value)
              },
              numerics: item.numerics.collect { wrapper ->
                builder(id: wrapper.id, code: wrapper.identification.code, value: wrapper.value)
              },
              treasures: item.treasures.collect { wrapper ->
                builder(id: wrapper.id, code: wrapper.identification.code, currencyCode: wrapper.money.currencyUnit.code, value: wrapper.money.amount)
              },
              product: buildProduct(item.product, builder)
          )
        })
    )
    return builder.toPrettyString()
  }
  def buildProduct = { product, jsonBuilder ->
    if (Double.parseDouble(product.code) == 115.00) {
      jsonBuilder(
          id: product.id,
          name: product.name,
          code: product.code,
          town: product.town,
          site: jsonBuilder(
              id: product.site.id,
              isLastMile: product.site.isLastMile,
              isBusinessCentre: product.site.isBusinessCentre,
              address: product.site.address,
              customer: jsonBuilder(
                  id: product.site.customer.id,
                  official: product.site.customer.official,
                  externalId: product.site.customer.externalId,
                  customerType: product.site.customer.type.code
              )
          ),
          port: jsonBuilder(
              id: product.port.id,
              speed: product.port.speed,
              tarifficationScheme: product.port.tarifficationScheme.code,
              coverage: product.port.coverage.code,
              type: product.port.type.code
          )
      )
    } else {
      jsonBuilder(
          id: product.id,
          name: product.name,
          code: product.code,
          type: product.type.code,
          site: jsonBuilder(
              id: product.site.id,
              isLastMile: product.site.isLastMile,
              isBusinessCentre: product.site.isBusinessCentre,
              address: product.site.address,
              customer: jsonBuilder(
                  id: product.site.customer.id,
                  official: product.site.customer.official,
                  externalId: product.site.customer.externalId,
                  customerType: product.site.customer.type.code
              )
          )
      )
    }
  }
}