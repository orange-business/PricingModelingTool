-- создаем PRODUCT: конкретная услуга Access Lines, TYPE_ID=100 (build)
INSERT INTO ACCESS_LINES(ID,TYPE_ID,SITE_ID) SELECT PRODUCT_SEQ.nextval,100, 97 from dual;
-- создаем PRODUCT_ITEM: заполняем обертку для услуги TYPE_ID=100(new) и сразу связываем PRODUCT_ITEM и услугу
INSERT INTO PRODUCT_ITEM(TYPE_ID,PRODUCT_ID,NOTE) SELECT 100, PRODUCT_SEQ.currval, 'Access Lines - test product item' FROM DUAL;

-- связываем сценарий и product item.
insert into SCENARIO_PRODUCTS (PRODUCT_ITEM_ID, SCENARIO_ID) SELECT PRODUCT_ITEM_SEQ.currval, SCENARIO_SEQ.currval FROM DUAL;

-- накидаем параметров услуги Business VPN в ITEM.
-- заполним несколько строковых параметров и прицепим их к product_item
insert into WRAPPER_STRING(VALUE, IDENTIFICATION_ID) SELECT 'new', ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='in_product_item_type_string') ids;
insert into WRAPPER_STRING_PARENT(PARENT_ID, WRAPPER_STRING_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_STRING_SEQ.currval FROM DUAL;
insert into WRAPPER_STRING(VALUE, IDENTIFICATION_ID) SELECT 'build', ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='in_lines_type_string') ids;
insert into WRAPPER_STRING_PARENT(PARENT_ID, WRAPPER_STRING_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_STRING_SEQ.currval FROM DUAL;

-- заполним несколько числовых параметров и прицепим их к product_item
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 36, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='in_contract_term_months') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 4, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='ref_risk_percent') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 14, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='ref_cost_executive_salary_percent') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 30, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='ref_coefficient_onetime_payment_percent') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 20, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='ref_standard_required_price_margin_percent') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 24, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='ref_standard_required_price_payback_months') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 10, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='ref_floor_price_margin_percent') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 24, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='ref_floor_price_payback_months') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 60, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='ref_depreciation_equipment_months') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 120, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='ref_depreciation_build_months') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 22, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='out_standard_price_margin_percent') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 24, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='out_standard_price_payback_months') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 24, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='out_required_price_margin_percent') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 24, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='out_required_price_payback_months') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 24, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='out_required_price_discount_onetime_percent') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 24, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='out_required_price_discount_monthly_percent') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 24, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='out_customer_price_margin_percent') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 24, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='out_customer_price_payback_months') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 24, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='out_customer_price_discount_onetime_percent') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 24, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='out_customer_price_discount_monthly_percent') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 24, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='out_floor_price_margin_percent') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 24, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='out_floor_price_payback_months') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 24, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='out_floor_price_discount_onetime_percent') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;
insert into WRAPPER_NUMERIC(VALUE, IDENTIFICATION_ID) SELECT 24, ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='out_floor_price_discount_monthly_percent') ids;
insert into WRAPPER_NUMERIC_PARENT(PARENT_ID, WRAPPER_NUMERIC_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_NUMERIC_SEQ.currval FROM DUAL;

-- заполним несколько денежных параметров и прицепим их к product_item
insert into WRAPPER_MONEY(AMOUNT,CURRENCY,IDENTIFICATION_ID) SELECT 100000,'RUR', ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='in_capex_equipment_money') ids;
insert into WRAPPER_MONEY_PARENT(PARENT_ID, WRAPPER_MONEY_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_MONEY_SEQ.currval FROM DUAL;
insert into WRAPPER_MONEY(AMOUNT,CURRENCY,IDENTIFICATION_ID) SELECT 200000,'RUR', ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='in_capex_build_money') ids;
insert into WRAPPER_MONEY_PARENT(PARENT_ID, WRAPPER_MONEY_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_MONEY_SEQ.currval FROM DUAL;
insert into WRAPPER_MONEY(AMOUNT,CURRENCY,IDENTIFICATION_ID) SELECT 200000,'RUR', ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='in_cost_onetime_lease_money') ids;
insert into WRAPPER_MONEY_PARENT(PARENT_ID, WRAPPER_MONEY_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_MONEY_SEQ.currval FROM DUAL;
insert into WRAPPER_MONEY(AMOUNT,CURRENCY,IDENTIFICATION_ID) SELECT 200000,'RUR', ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='in_cost_monthly_build_money') ids;
insert into WRAPPER_MONEY_PARENT(PARENT_ID, WRAPPER_MONEY_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_MONEY_SEQ.currval FROM DUAL;
insert into WRAPPER_MONEY(AMOUNT,CURRENCY,IDENTIFICATION_ID) SELECT 200000,'RUR', ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='in_cost_monthly_lease_money') ids;
insert into WRAPPER_MONEY_PARENT(PARENT_ID, WRAPPER_MONEY_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_MONEY_SEQ.currval FROM DUAL;
insert into WRAPPER_MONEY(AMOUNT,CURRENCY,IDENTIFICATION_ID) SELECT 1000,'RUR', ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='out_standard_price_payment_monthly_money') ids;
insert into WRAPPER_MONEY_PARENT(PARENT_ID, WRAPPER_MONEY_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_MONEY_SEQ.currval FROM DUAL;
insert into WRAPPER_MONEY(AMOUNT,CURRENCY,IDENTIFICATION_ID) SELECT 1000,'RUR', ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='out_standard_price_payment_onetime_money') ids;
insert into WRAPPER_MONEY_PARENT(PARENT_ID, WRAPPER_MONEY_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_MONEY_SEQ.currval FROM DUAL;
insert into WRAPPER_MONEY(AMOUNT,CURRENCY,IDENTIFICATION_ID) SELECT 1000,'RUR', ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='inout_required_price_payment_onetime_money') ids;
insert into WRAPPER_MONEY_PARENT(PARENT_ID, WRAPPER_MONEY_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_MONEY_SEQ.currval FROM DUAL;
insert into WRAPPER_MONEY(AMOUNT,CURRENCY,IDENTIFICATION_ID) SELECT 1000,'RUR', ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='inout_required_price_payment_monthly_money') ids;
insert into WRAPPER_MONEY_PARENT(PARENT_ID, WRAPPER_MONEY_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_MONEY_SEQ.currval FROM DUAL;
insert into WRAPPER_MONEY(AMOUNT,CURRENCY,IDENTIFICATION_ID) SELECT 1000,'RUR', ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='out_customer_price_payment_onetime_money') ids;
insert into WRAPPER_MONEY_PARENT(PARENT_ID, WRAPPER_MONEY_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_MONEY_SEQ.currval FROM DUAL;
insert into WRAPPER_MONEY(AMOUNT,CURRENCY,IDENTIFICATION_ID) SELECT 1000,'RUR', ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='out_customer_price_payment_monthly_money') ids;
insert into WRAPPER_MONEY_PARENT(PARENT_ID, WRAPPER_MONEY_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_MONEY_SEQ.currval FROM DUAL;
insert into WRAPPER_MONEY(AMOUNT,CURRENCY,IDENTIFICATION_ID) SELECT 1000,'RUR', ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='out_floor_price_payment_onetime_money') ids;
insert into WRAPPER_MONEY_PARENT(PARENT_ID, WRAPPER_MONEY_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_MONEY_SEQ.currval FROM DUAL;
insert into WRAPPER_MONEY(AMOUNT,CURRENCY,IDENTIFICATION_ID) SELECT 1000,'RUR', ids.id from (SELECT ID as id FROM IDENTIFICATION where CODE='out_floor_price_payment_monthly_money') ids;
insert into WRAPPER_MONEY_PARENT(PARENT_ID, WRAPPER_MONEY_ID) SELECT PRODUCT_ITEM_SEQ.currval, WRAPPER_MONEY_SEQ.currval FROM DUAL;

commit;




