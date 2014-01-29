-- Важно! При полном восстановлении сначала запустите приложение, чтобы hibernate создал необходимые таблицы
ALTER TABLE IDENTIFICATION ADD CONSTRAINT namespace_code_uq unique (namespace, code);

ALTER TABLE opportunity ADD CONSTRAINT IS_CLOSED_CHECK check (IS_CLOSED in ('N','Y'));
ALTER TABLE site ADD CONSTRAINT IS_LAST_MILE_CHECK check (IS_LAST_MILE in ('N','Y'));
ALTER TABLE site ADD CONSTRAINT IS_BUSINESS_CENTRE_CHECK check (IS_BUSINESS_CENTRE in ('N','Y'));

CREATE OR REPLACE TRIGGER TR_CUSTOMER BEFORE INSERT ON CUSTOMER FOR EACH ROW
WHEN (new.id IS NULL) BEGIN SELECT CUSTOMER_SEQ.NEXTVAL INTO :new.id FROM dual; END;
/
CREATE OR REPLACE TRIGGER TR_IDENTIFICATION BEFORE INSERT ON IDENTIFICATION FOR EACH ROW
WHEN (new.id IS NULL) BEGIN SELECT IDENTIFICATION_SEQ.NEXTVAL INTO :new.id FROM dual; END;
/
CREATE OR REPLACE TRIGGER TR_PRODUCT BEFORE INSERT ON PRODUCT FOR EACH ROW
WHEN (new.id IS NULL) BEGIN SELECT PRODUCT_SEQ.NEXTVAL INTO :new.id FROM dual; END;
/
CREATE OR REPLACE TRIGGER TR_PRODUCT_ITEM BEFORE INSERT ON PRODUCT_ITEM FOR EACH ROW
WHEN (new.id IS NULL) BEGIN SELECT PRODUCT_ITEM_SEQ.NEXTVAL INTO :new.id FROM dual; END;
/
CREATE OR REPLACE TRIGGER TR_WRAPPER_NUMERIC BEFORE INSERT ON WRAPPER_NUMERIC FOR EACH ROW
WHEN (new.id IS NULL) BEGIN SELECT WRAPPER_NUMERIC_SEQ.NEXTVAL INTO :new.id FROM dual; END;
/
CREATE OR REPLACE TRIGGER TR_WRAPPER_MONEY BEFORE INSERT ON WRAPPER_MONEY FOR EACH ROW
WHEN (new.id IS NULL) BEGIN SELECT WRAPPER_MONEY_SEQ.NEXTVAL INTO :new.id FROM dual; END;
/
CREATE OR REPLACE TRIGGER TR_WRAPPER_STRING BEFORE INSERT ON WRAPPER_STRING FOR EACH ROW
WHEN (new.id IS NULL) BEGIN SELECT WRAPPER_STRING_SEQ.NEXTVAL INTO :new.id FROM dual; END;
/
CREATE OR REPLACE TRIGGER TR_OPPORTUNITY BEFORE INSERT ON OPPORTUNITY FOR EACH ROW
WHEN (new.id IS NULL) BEGIN SELECT OPPORTUNITY_SEQ.NEXTVAL INTO :new.id FROM dual; END;
/
CREATE OR REPLACE TRIGGER TR_PORT BEFORE INSERT ON PORT FOR EACH ROW
WHEN (new.id IS NULL) BEGIN SELECT PORT_SEQ.NEXTVAL INTO :new.id FROM dual; END;
/
CREATE OR REPLACE TRIGGER TR_PRODUCT_ITEM BEFORE INSERT ON PRODUCT_ITEM FOR EACH ROW
WHEN (new.id IS NULL) BEGIN SELECT PRODUCT_ITEM_SEQ.NEXTVAL INTO :new.id FROM dual; END;
/
CREATE OR REPLACE TRIGGER TR_SCENARIO BEFORE INSERT ON SCENARIO FOR EACH ROW
WHEN (new.id IS NULL) BEGIN SELECT SCENARIO_SEQ.NEXTVAL INTO :new.id FROM dual; END;
/
CREATE OR REPLACE TRIGGER TR_SITE BEFORE INSERT ON SITE FOR EACH ROW
WHEN (new.id IS NULL) BEGIN SELECT SITE_SEQ.NEXTVAL INTO :new.id FROM dual; END;
/

--заполнение таблицы customer
insert into customer(official, type_id) values ('ИП Деряга Наталья Сергеевна',300);
insert into customer(official, type_id) values ('ООО "База-3"',100);
insert into customer(official, type_id) values ('ОАО "Янтарный сказ"',300);
insert into customer(official, type_id) values ('ООО "КОМПАНИЯ ВЕРСАЛЬ"',200);
insert into customer(official, type_id) values ('ЮгТехноПроект',200);
insert into customer(official, type_id) values ('ООО "Пиком"',100);
insert into customer(official, type_id) values ('ЗАО "ХК "Композит"',300);
insert into customer(official, type_id) values ('ЗАО ИКБ "Европейский"',300);
insert into customer(official, type_id) values ('ООО "Роза Хутор"',300);

-- заполнение таблицы site
insert into site(address, customer_id) values ('г. Иваново, пр-т Ленина, д. 21, стр. 3', 1);
insert into site(address, customer_id) values ('г. Пятигорск, ул. Акопянц, д. 11', 1);
insert into site(address, customer_id) values ('г. Ростов-на-Дону, пр. Стачки, д. 25Б', 1);
insert into site(address, customer_id) values ('г. Рязань, ул. Свободы, д. 14/16', 1);
insert into site(address, customer_id) values ('г. Саратов, ул. им. Вавилова Н.И., д. 38/114', 1);
insert into site(address, customer_id) values ('г. Великий Новгород, ул. Б. Санкт-Петербургская, д. 39', 1);
insert into site(address, customer_id) values ('г. Мурманск, ул. Челюскинцев, д. 30', 2);
insert into site(address, customer_id) values ('г. Новосибирск, ул. Фрунзе, д. 86', 2);
insert into site(address, customer_id) values ('г. Ярославль, ул. Республиканская, д. 3', 2);
insert into site(address, customer_id) values ('г. Москва, ш. Варшавское, д. 125', 2);
insert into site(address, customer_id) values ('г. Сарапул, ул. Советская, д.17 А', 2);
insert into site(address, customer_id) values ('г. Воткинск, 427430, ул.Мира, д.5', 2);
insert into site(address, customer_id) values ('г. Данилов, Ярославская обл., ул.Ленина, 20-в', 2);
insert into site(address, customer_id) values ('г. Ярославль, ул. Пятницкая,  д. 6', 2);
insert into site(address, customer_id) values ('г. Ярославль, ул. Б. Октябрьская, д.35', 2);
insert into site(address, customer_id) values ('г. Переславль - Залесский, ул. 50 лет Комсомолу', 3);
insert into site(address, customer_id) values ('г. Рыбинск, ул.Пушкина д.28', 3);
insert into site(address, customer_id) values ('г. Углич, 152620, Ярославская обл., ул. Ярославская, д.15', 3);
insert into site(address, customer_id) values ('г. Калининград, Ленинский проспект, 30', 3);
insert into site(address, customer_id) values ('г. Белово, ул. Ленина, дом 10', 4);
insert into site(address, customer_id) values ('г. Кемерово, ул.  Молодежная, 7/1', 4);
insert into site(address, customer_id) values ('г. Кемерово, ул. Красноармейская, д. 120', 4);
insert into site(address, customer_id) values ('г. Новокузнецк, ул. Павлова, д.11а', 4);
insert into site(address, customer_id) values ('г. Ленинск-Кузнецк, пр-т. Григорченкова, д. 41', 4);
insert into site(address, customer_id) values ('г. Новокузнецк, 654007, ул.Кирова, д.25', 5);
insert into site(address, customer_id) values ('г. Кирово-Чепецк, ул.Ленина, д.34/2', 5);
insert into site(address, customer_id) values ('г. Киров, ул. Воровского, д. 119', 6);
insert into site(address, customer_id) values ('г. Слободской, ул.Вятская, 6', 6);
insert into site(address, customer_id) values ('г. Курган, ул. Ленина,  д. 5', 7);
insert into site(address, customer_id) values ('г. Калуга,  ул. Ленина,  д.73', 8);
insert into site(address, customer_id) values ('г. Москва Алтуфьевское шоссе, д.22Б', 9);
insert into site(address, customer_id) values ('г. Москва ул.Арбат, д.1', 9);
insert into site(address, customer_id) values ('г. Москва ул.Долгоруковская, д.19, к.1', 9);
insert into site(address, customer_id) values ('г. Москва, ул.Электрозаводская 27', 9);
insert into site(address, customer_id) values ('г. Москва Измайловская площадь, д.1', 9);
insert into site(address, customer_id) values ('г. Москва ул.Кржижановского, д.14, корп.3', 9);
insert into site(address, customer_id) values ('г. Москва Комсомольский пр., д.40/13, стр.1', 9);
insert into site(address, customer_id) values ('г. Москва Кутузовский проспект, д.15', 9);
insert into site(address, customer_id) values ('г. Москва, Ленинградский пр-т, д.12', 9);
insert into site(address, customer_id) values ('г. Москва Ленинский пр-т, д.82/2', 9);
insert into site(address, customer_id) values ('г. Москва Мичуринский пр-т, д.9', 9);
insert into site(address, customer_id) values ('г. Москва, ул.Маши Порываевой, д.11', 9);
insert into site(address, customer_id) values ('г. Москва, пр-т Маршала Жукова, д.48', 9);
insert into site(address, customer_id) values ('г. Москва ул.Обручева, д.30/1, стр.1', 9);
insert into site(address, customer_id) values ('г. Москва, Павелецкая пл., д.2/3', 9);
insert into site(address, customer_id) values ('г. Москва ул.Пречистенка, д.40/2, стр.1', 9);
insert into site(address, customer_id) values ('г. Москва Проспект Мира, д.124, стр.8', 9);
insert into site(address, customer_id) values ('г. Москва, ул.Пятницкая, д.2/38, стр.2', 9);
insert into site(address, customer_id) values ('г. Москва, ул.Русаковская, д.22, стр.1', 9);
insert into site(address, customer_id) values ('г. Москва Страстной бульвар, д.7, стр.1', 9);
insert into site(address, customer_id) values ('г. Москва ул.Садовая-Кудринская, д.32, стр.1', 9);
insert into site(address, customer_id) values ('г. Москва, ул. Смольная, д.22, стр.1', 9);
insert into site(address, customer_id) values ('г. Москва ул. Таганская, 29, стр.1', 9);
insert into site(address, customer_id) values ('г. Москва Средний Тишинский пер., д.28, стр.1', 9);
insert into site(address, customer_id) values ('г. Москва, ул. Пятницкая, д. 72', 9);
insert into site(address, customer_id) values ('г. Мичуринск, ул. Филиппова,72 (бывш Советская, 281)', 9);
insert into site(address, customer_id) values ('г. Рассказово, ул.60 лет Рассказово, 4', 9);
insert into site(address, customer_id) values ('г. Тамбов, ул. Державинская, д. 28/13', 9);
insert into site(address, customer_id) values ('с. Бакчар, ул.Ленина, 49', 9);
insert into site(address, customer_id) values ('г. Колпашево, ул. Белинского, д.16/1, стр.1', 9);
insert into site(address, customer_id) values ('с. Кривошеино, 636300, Томская обл.,ул.Кирова,22', 9);
insert into site(address, customer_id) values ('с. Кожевниково, ул. Ленина ,д.8', 9);
insert into site(address, customer_id) values ('г. Томск, 634003, пер.Кустарный, д.1', 9);
insert into site(address, customer_id) values ('г. Томск, пр. Ленина, д. 40', 9);
insert into site(address, customer_id) values ('г. Томск, пл. Ленина, 8', 9);
insert into site(address, customer_id) values ('с. Мельниково, ул. Коммунистическая, дом 9', 9);
insert into site(address, customer_id) values ('с. Подгорное, ул.Островского, 9а', 9);
insert into site(address, customer_id) values ('с. Первомайское, ул.Кольцова, 6', 9);
insert into site(address, customer_id) values ('с. Парабель, ул.Советская , 24', 9);
insert into site(address, customer_id) values ('г. Северск, ул. Коммунистическая, д. 94', 9);
insert into site(address, customer_id) values ('г. Томск, ул. Белинского, д. 63', 9);
insert into site(address, customer_id) values ('с. Зырянское, ул. Советская 19а', 9);
insert into site(address, customer_id) values ('г. Нефтеюганск, 16 микр-н, д. 76', 9);
insert into site(address, customer_id) values ('г. Нижневартовск, ул. Ленина, дом 15/1', 9);
insert into site(address, customer_id) values ('г. Тюмень, ул.50- летия Октября, д.34/1', 9);
insert into site(address, customer_id) values ('г. Тюмень, ул.Первомайская, д.21', 9);
insert into site(address, customer_id) values ('г. Тобольск, ул. Октябрьская , д 20', 9);
insert into site(address, customer_id) values ('г. Тюмень, ул.Ямская, 76', 9);
insert into site(address, customer_id) values ('г. Тверь, Вагжановский пер.,9', 9);
insert into site(address, customer_id) values ('г. Кызыл, ул. ул.Тувинских Добровольцев, д.10', 9);
insert into site(address, customer_id) values ('г. Уфа, ул. Цюрупы, д. 145', 9);
insert into site(address, customer_id) values ('г. Уфа, ул.Карла Маркса, д.20', 9);
insert into site(address, customer_id) values ('г. Уфа, просп. Октября, 131', 9);
insert into site(address, customer_id) values ('г. Стерлитамак, ул. Гоголя, 3', 9);
insert into site(address, customer_id) values ('г. Димитровоград, ул. Мориса Торезы, 2В', 9);
insert into site(address, customer_id) values ('г. Ульяновск, просп. Ленинского Комсомола, д.43', 9);
insert into site(address, customer_id) values ('г. Ульяновск, ул. Карла Маркса, д. 7 А', 9);
insert into site(address, customer_id) values ('г. Волгоград, ул. 39-й Гвардейской, д.27', 9);
insert into site(address, customer_id) values ('г. Волгоград, просп. Ленина, д.46', 9);
insert into site(address, customer_id) values ('г. Волгоград, проспект им. В.И.Ленина, д.16', 9);
insert into site(address, customer_id) values ('пгт. Красная Горбатка, Владимирская обл.,ул.Пролетарская, 22', 9);
insert into site(address, customer_id) values ('г. Киржач, Владимирская обл., ул. Советская, д. 1а', 9);
insert into site(address, customer_id) values ('г. Владимир, ул. Разина, д . 30', 9);
insert into site(address, customer_id) values ('г. Вязники, пр.Муромский, 6', 9);
insert into site(address, customer_id) values ('г. Вологда, ул. Герцена, д. 63', 9);
insert into site(address, customer_id) values ('г. Воронеж, 394007, ул. Ленинградская, д.2', 9);
insert into site(address, customer_id) values ('г. Воронеж, ул. Карла Маркса, д. 68', 9);
insert into site(address, customer_id) values ('г. Абакан, ул.Пушкина, 165', 9);

/
insert into identification(description, code, note, namespace) values('Адрес сайта','in_site_address_string','Значение: , Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Город PE','in_town_string','Значение: ввод из списка, Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Вид порта','in_port_coverage_string','Значение: Domestic/Okrug/Local/International(для него нет UBB), Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Тип порта','in_port_type_string','Значение: Silver/Gold/Platinum, Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Скорость Kbps, список стандартных значений 64Кбит/сек-1 Гбит/сек, возможно произвольное значение в этом диапазоне','in_speed_number','Значение: Ввод оператора, Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Схема тарификации','in_tariffication_scheme_string','Значение: FIX/UBB, Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Скидка на разовый платеж','in_discount_onetime_payment_percent','Значение: , Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Скидка на ежемесяный платеж','in_discount_monthly_payment_percent','Значение: , Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Скидка на цену трафика','in_discount_traffic_percent','Значение: , Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('FIX Минимальная стоимость порта FIX','ref_payment_min_for_port_fix_money','Значение: 1000 рублей, Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('UBB Минимальная стоимость порта UBB','ref_payment_min_for_port_ubb_money','Значение: 500 рублей, Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Новый доход клиента по IPVPN','out_revenue_ipvpn_monthly_money','Значение: , Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Количество сайтов (в запросе), вычисляемое значение в зависимости от скидки которое просит клиент.','out_sites_number','Значение: , Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Минимальная маржа для расчета стандартной цены IP VPN','ref_margin_min_for_standard_price_percent','Значение: 25.0%, Формулы: GMSTDCalc', 'bvpn');
insert into identification(description, code, note, namespace) values('Стоимость 1 человеко-часа работы специалиста FO','ref_cost_hour_FO_money','Значение: Расчет СМ, руб., Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Стоимость 1 человеко-часа работы  специалиста HD','ref_cost_hour_HD_money','Значение: Расчет СМ, руб., Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Installation порта количество человеко-часов работы','ref_cost_onetime_installation_port_hours','Значение: 2, Формулы: HourInstallationFO', 'bvpn');
insert into identification(description, code, note, namespace) values('Downgrade порта количество человеко-часов работы','ref_cost_onetime_downgrade_port_hours','Значение: 1, Формулы: HourDowngradeFO', 'bvpn');
insert into identification(description, code, note, namespace) values('Upgrade порта количество  человеко-часов работы','ref_cost_onetime_upgrade_port_hours','Значение: 1, Формулы: HourUpgradeFO', 'bvpn');
insert into identification(description, code, note, namespace) values('Реальное количество человеко-часов работы при (new/downgrade/degrade) порта','in_cost_onetime_make_port_hours','Значение: , Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Поддержка HELP DESK количество  человеко-часов работы (на порт)','ref_cost_monthly_helpdesk_support_port_hour','Значение: 0, Формулы: HourSupportHD', 'bvpn');
insert into identification(description, code, note, namespace) values('Поддержка HELP DESK стоимость на порт ежемесячно','out_cost_monthly_helpdesk_support_port_money','Значение: , Формулы: HourSupportHD', 'bvpn');
insert into identification(description, code, note, namespace) values('Ежемесячный кост: затраты на обслуживание сети сторонними подрядчиками, в процентах от стандартной цены за порт','ref_cost_monthly_port_support_percent','Значение: 1.25 процента, Формулы: CoeffExtNetworkSup', 'bvpn');
insert into identification(description, code, note, namespace) values('Ежемесячный кост: амортизация PE роутера (на 1 kbit/сек)','ref_cost_monthly_depreciation_router_1kb_sec_money','Значение: 0.029 рубля, Формулы: MRCDepRouter1kbit', 'bvpn');
insert into identification(description, code, note, namespace) values('Ежемесячный кост: амортизация сети доступа (на порт)','ref_cost_monthly_depreciation_port_money','Значение: 76.48 рублей, Формулы: MRC_dep_AccessPort', 'bvpn');
insert into identification(description, code, note, namespace) values('Коэффициент утилизации.','ref_utilization_number','Значение: 0.55, Формулы: Util', 'bvpn');
insert into identification(description, code, note, namespace) values('Средневзвешенный коэффициент мультиплексирования. Зависит от реальных данных, рассчитываемых RBNF.','ref_mux_number','Значение: 4.7 число, Формулы: MUX', 'bvpn');
insert into identification(description, code, note, namespace) values('Ежемесячный кост: transmission кост (на 1 kbit/сек)','ref_cost_monthly_transmission_cost_1kb_money','Значение: , Формулы: MRCBB1kbit', 'bvpn');
insert into identification(description, code, note, namespace) values('Расчет коста IP VPN за 1kbit','out_cost_monthly_transmission_cost_1kb_money','Значение: , Формулы: MRC_VPN_1Kbit', 'bvpn');
insert into identification(description, code, note, namespace) values('Расчет коста IP VPN за 1 Мегабайт','out_cost_monthly_transmission_cost_1MB_money','Значение: , Формулы: MRC_VPN_1MB', 'bvpn');
insert into identification(description, code, note, namespace) values('Фактор по ежемесячному доходу от портов','ref_discount_by_monthly_revenue_percent','Значение: , Формулы: F2', 'bvpn');
insert into identification(description, code, note, namespace) values('Фактор  по типу запроса','ref_discount_by_request_type_percent','Значение: , Формулы: F1', 'bvpn');
insert into identification(description, code, note, namespace) values('Фактор по количеству сайтов','ref_discount_by_sites_amount_percent','Значение: , Формулы: F3', 'bvpn');
insert into identification(description, code, note, namespace) values('Фактор по текущему доходу от клиента (все услуги)','ref_discount_by_total_revenue_percent','Значение: , Формулы: F4', 'bvpn');
insert into identification(description, code, note, namespace) values('Скидка для расчета пороговых тарифов для ip vpn','ref_discount_tariff_floor_percent','Значение: 70.0%, Формулы: DFL', 'bvpn');
insert into identification(description, code, note, namespace) values('Маржа для расчета порогового тарифа IP VPN','ref_margin_floor_tariff_percent','Значение: 15.0%, Формулы: GMFL', 'bvpn');
insert into identification(description, code, note, namespace) values('Стандартная цена (FIX/UBB) разовый платеж для текущей скорости от 64K до 1000M, равен одному из ref_payment_onetime_speed_64K_2M_money, ref_payment_onetime_speed_2M_10M_money,  ref_payment_onetime_speed_10M_100M_money, ref_payment_onetime_speed_100M_1000M_money','refout_payment_onetime_speed_current_money','Значение: , Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Стандартная цена (FIX/UBB) разовый платеж для скорости от 64K до 2048К','ref_payment_onetime_speed_64K_2M_money','Значение: , Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Стандартная цена (FIX/UBB) разовый платеж для скорости более 2048К до 10М','ref_payment_onetime_speed_2M_10M_money','Значение: , Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Стандартная цена (FIX/UBB) разовый платеж для скорости более 10М до 100М','ref_payment_onetime_speed_10M_100M_money','Значение: , Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Стандартная цена (FIX/UBB) разовый платеж для скорости более 100М до 1000М','ref_payment_onetime_speed_100M_1000M_money','Значение: , Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Стандартная цена (FIX) ежемесячный платеж (для скорости 2048К)','ref_standard_price_payment_2M_monthly_money','Значение: , Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Стандартная цена (UBB) минимальная сумма счета (для скорости 2048К)','ref_payment_monthly_min_fee_2M_ubb_money','Значение: , Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Стандартная цена (UBB) коэффициент пересчета стандартного тарифа для выбранной скорости: mrp(fix) mcc (ubb)','ref_coeff_tariff_calculation_for_speed_number','Значение: , Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Стандартная цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), data3','ref_tariff_ubb_data3_1MB_money','Значение: , Формулы: D3', 'bvpn');
insert into identification(description, code, note, namespace) values('Стандартная цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), data2','ref_tariff_ubb_data2_1MB_money','Значение: , Формулы: D2', 'bvpn');
insert into identification(description, code, note, namespace) values('Стандартная цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), data1','ref_tariff_ubb_data1_1MB_money','Значение: , Формулы: D1', 'bvpn');
insert into identification(description, code, note, namespace) values('Стандартная цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), voice','ref_tariff_ubb_voice_1MB_money','Значение: , Формулы: Vo', 'bvpn');
insert into identification(description, code, note, namespace) values('Стандартная цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), video','ref_tariff_ubb_video_1MB_money','Значение: , Формулы: Vi', 'bvpn');
insert into identification(description, code, note, namespace) values('Город PE','ref_town_pe_string','Значение: , Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Категория города PE A/B/C/D','ref_town_pe_type_string','Значение: A/ B/ C/ D, Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Возможность предоставления  порта типа Domestic','ref_town_pe_domestic_string','Значение: есть/нет, Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Возможность предоставления  порта типа Okrug','ref_town_pe_okrug_string','Значение: <город МPЦ>/нет, Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Возможность предоставления  порта типа Local','ref_town_pe_local_string','Значение: есть/нет, Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Возможность предоставления  порта типа International','ref_town_pe_international_string','Значение: есть/нет, Формулы: ', 'bvpn');
insert into identification(description, code, note, namespace) values('Косты IP VPN Затраты за порт (разово), равняется суммарной трате на работу специалиста = количество часов * стоимость труда специалиста','out_cost_onetime_money','Значение: , Формулы: OTCVPN', 'bvpn');
insert into identification(description, code, note, namespace) values('Косты IP VPN Затраты за порт (ежемесячно), стандартная цена','out_standard_cost_monthly_money','Значение: , Формулы: MRCVPN', 'bvpn');
insert into identification(description, code, note, namespace) values('Косты IP VPN Затраты за порт (ежемесячно), желаемая цена','out_required_cost_monthly_money','Значение: , Формулы: MRCVPN', 'bvpn');
insert into identification(description, code, note, namespace) values('Косты IP VPN Затраты за порт (ежемесячно), клиентская цена','out_customer_cost_monthly_money','Значение: , Формулы: MRCVPN', 'bvpn');
insert into identification(description, code, note, namespace) values('Косты IP VPN Затраты за порт (ежемесячно), пороговая цена','out_floor_cost_monthly_money','Значение: , Формулы: MRCVPN', 'bvpn');
insert into identification(description, code, note, namespace) values('Косты IP VPN Затраты за порт (ежемесячно), рекомендованная цена','out_recomm_cost_monthly_money','Значение: , Формулы: MRCVPN', 'bvpn');
insert into identification(description, code, note, namespace) values('Косты IP VPN Затраты на 1МБ трафика','out_cost_traffic_1MB_money','Значение: , Формулы: MRCVPNUBB, MRC_VPN_1MB', 'bvpn');
insert into identification(description, code, note, namespace) values('Стандартная цена разовый платеж (удалены out_standard_price_fix_payment_onetime_money и out_standard_price_ubb_payment_onetime_money)','out_standard_price_payment_onetime_money','Значение: , Формулы: OTPSTD', 'bvpn');
insert into identification(description, code, note, namespace) values('Стандартная цена (FIX) ежемесячный платеж','out_standard_price_fix_payment_monthly_money','Значение: , Формулы: MRPSTD', 'bvpn');
insert into identification(description, code, note, namespace) values('Стандартная цена (FIX) Маржа','out_standard_price_fix_margin_percent','Значение: , Формулы: GMSTD', 'bvpn');
insert into identification(description, code, note, namespace) values('Запрошенная цена (FIX) разовый платеж, он ВСЕГДА вводится, если его не ввели, то подставить стандартную цену','in_required_price_fix_payment_onetime_money','Значение: , Формулы: OTPREQ', 'bvpn');
insert into identification(description, code, note, namespace) values('Запрошенная цена (FIX) ежемесячный платеж, он ВСЕГДА вводится, если его не ввели, то подставить стандартную цену','in_required_price_fix_payment_monthly_money','Значение: , Формулы: MRPREQ', 'bvpn');
insert into identification(description, code, note, namespace) values('Запрошенная цена (FIX) Маржа','out_required_price_fix_margin_percent','Значение: , Формулы: GMREQ', 'bvpn');
insert into identification(description, code, note, namespace) values('Запрошенная цена (FIX) Скидка от стандартной цены, разово','out_required_price_fix_discount_onetime_percent','Значение: , Формулы: DISCREQOTP', 'bvpn');
insert into identification(description, code, note, namespace) values('Запрошенная цена (FIX) Скидка от стандартной цены, ежемесячно','out_required_price_fix_discount_monthly_percent','Значение: , Формулы: DISCREQMRP', 'bvpn');
insert into identification(description, code, note, namespace) values('Клиентская цена (FIX) разовый платеж','out_customer_price_fix_payment_onetime_money','Значение: , Формулы: OTPCUST', 'bvpn');
insert into identification(description, code, note, namespace) values('Клиентская цена (FIX) ежемесячный платеж','out_customer_price_fix_payment_monthly_money','Значение: , Формулы: MRPCUST', 'bvpn');
insert into identification(description, code, note, namespace) values('Клиентская цена (FIX) Маржа','out_customer_price_fix_margin_percent','Значение: , Формулы: GMCUST', 'bvpn');
insert into identification(description, code, note, namespace) values('Клиентская цена (FIX) Скидка от стандартной цены, разово','out_customer_price_fix_discount_onetime_percent','Значение: , Формулы: DISCCUSTOTP', 'bvpn');
insert into identification(description, code, note, namespace) values('Клиентская цена (FIX) Скидка от стандартной цены, ежемесячно','out_customer_price_fix_discount_monthly_percent','Значение: , Формулы: DISCCUSTMRP', 'bvpn');
insert into identification(description, code, note, namespace) values('Рекомендованная цена (FIX) разовый платеж','out_recomm_price_fix_payment_onetime_money','Значение: , Формулы: OTPREC', 'bvpn');
insert into identification(description, code, note, namespace) values('Рекомендованная цена (FIX) ежемесячный платеж','out_recomm_price_fix_payment_monthly_money','Значение: , Формулы: MRPREC', 'bvpn');
insert into identification(description, code, note, namespace) values('Рекомендованная цена (FIX) Маржа','out_recomm_price_fix_margin_percent','Значение: , Формулы: GMREC', 'bvpn');
insert into identification(description, code, note, namespace) values('Рекомендованная цена (FIX) Скидка от стандартной цены, разово','out_recomm_price_fix_discount_onetime_percent','Значение: , Формулы: DISCRECOTP', 'bvpn');
insert into identification(description, code, note, namespace) values('Рекомендованная цена (FIX) Скидка от стандартной цены, ежемесячно','out_recomm_price_fix_discount_monthly_percent','Значение: , Формулы: DISCRECMRP', 'bvpn');
insert into identification(description, code, note, namespace) values('Пороговая цена (FIX) разовый платеж','out_floor_price_fix_payment_onetime_money','Значение: , Формулы: OTPFL', 'bvpn');
insert into identification(description, code, note, namespace) values('Пороговая цена (FIX) ежемесячный платеж','out_floor_price_fix_payment_monthly_money','Значение: , Формулы: MRPFL', 'bvpn');
insert into identification(description, code, note, namespace) values('Пороговая цена (FIX) маржа','out_floor_price_fix_margin_percent','Значение: , Формулы: GMFL', 'bvpn');
insert into identification(description, code, note, namespace) values('Пороговая цена (FIX) Скидка от стандартной цены, разово','out_floor_price_fix_discount_onetime_percent','Значение: , Формулы: DISCFLOTP', 'bvpn');
insert into identification(description, code, note, namespace) values('Пороговая цена (FIX) Скидка от стандартной цены, ежемесячно','out_floor_price_fix_discount_monthly_percent','Значение: , Формулы: DISCFLMRP', 'bvpn');
insert into identification(description, code, note, namespace) values('Стандартная цена (UBB) минимальная сумма счета','out_standard_price_ubb_payment_monthly_money','Значение: , Формулы: MRPSTD', 'bvpn');
insert into identification(description, code, note, namespace) values('Стандартная цена (UBB) Маржа на трафик (на дата 3)','out_standard_price_ubb_margin_data3_percent','Значение: , Формулы: GMSTD', 'bvpn');
insert into identification(description, code, note, namespace) values('Стандартная цена (UBB) Маржа на трафик включенный в минимальную сумму счета','out_standard_traffic_price_ubb_margin_percent','Значение: , Формулы: GMSTDTraffic', 'bvpn');
insert into identification(description, code, note, namespace) values('Запрошенная цена (UBB) разовый платеж','in_required_price_ubb_payment_onetime_money','Значение: , Формулы: OTPREQ', 'bvpn');
insert into identification(description, code, note, namespace) values('Запрошенная цена (UBB) минимальная сумма счета','in_required_price_ubb_payment_monthly_money','Значение: , Формулы: MRPREQ', 'bvpn');
insert into identification(description, code, note, namespace) values('Запрошенная цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), data3','inout_required_tariff_ubb_data3_1MB_money','Значение: , Формулы: D3', 'bvpn');
insert into identification(description, code, note, namespace) values('Запрошенная цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), data2','inout_required_tariff_ubb_data2_1MB_money','Значение: , Формулы: D2', 'bvpn');
insert into identification(description, code, note, namespace) values('Запрошенная цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), data1','inout_required_tariff_ubb_data1_1MB_money','Значение: , Формулы: D1', 'bvpn');
insert into identification(description, code, note, namespace) values('Запрошенная цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), video','inout_required_tariff_ubb_video_1MB_money','Значение: , Формулы: Vi', 'bvpn');
insert into identification(description, code, note, namespace) values('Запрошенная цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), voice','inout_required_tariff_ubb_voice_1MB_money','Значение: , Формулы: Vo', 'bvpn');
insert into identification(description, code, note, namespace) values('Запрошенная цена (UBB) Маржа Маржа на трафик (на дата 3)','out_required_price_ubb_margin_data3_percent','Значение: , Формулы: GMREQ', 'bvpn');
insert into identification(description, code, note, namespace) values('Запрошенная цена (UBB) Маржа на трафик включенный в минимальную сумму счета','out_required_traffic_price_ubb_margin_percent','Значение: , Формулы: GMREQTraffic', 'bvpn');
insert into identification(description, code, note, namespace) values('Запрошенная цена (UBB) Скидка от стандартной цены, разово','out_required_price_ubb_discount_onetime_percent','Значение: , Формулы: DISCREQOTP', 'bvpn');
insert into identification(description, code, note, namespace) values('Запрошенная цена (UBB) Скидка от стандартной цены, ежемесячно','out_required_price_ubb_discount_monthly_percent','Значение: , Формулы: DISCREQMRP', 'bvpn');
insert into identification(description, code, note, namespace) values('Клиентская цена (UBB) разовый платеж','out_customer_price_ubb_payment_onetime_money','Значение: , Формулы: OTPCUST', 'bvpn');
insert into identification(description, code, note, namespace) values('Клиентская цена (UBB) минимальная сумма счета','out_customer_price_ubb_payment_monthly_money','Значение: , Формулы: MRPCUST', 'bvpn');
insert into identification(description, code, note, namespace) values('Клиентская цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), data3','out_customer_tariff_ubb_data3_1MB_money','Значение: , Формулы: D3', 'bvpn');
insert into identification(description, code, note, namespace) values('Клиентская цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), data2','out_customer_tariff_ubb_data2_1MB_money','Значение: , Формулы: D2', 'bvpn');
insert into identification(description, code, note, namespace) values('Клиентская цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), data1','out_customer_tariff_ubb_data1_1MB_money','Значение: , Формулы: D1', 'bvpn');
insert into identification(description, code, note, namespace) values('Клиентская цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), video','out_customer_tariff_ubb_video_1MB_money','Значение: , Формулы: Vi', 'bvpn');
insert into identification(description, code, note, namespace) values('Клиентская цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), voice','out_customer_tariff_ubb_voice_1MB_money','Значение: , Формулы: Vo', 'bvpn');
insert into identification(description, code, note, namespace) values('Клиентская цена (UBB) Маржа на трафик (на дата 3)  было out_customer_price_ubb_margin_percent','out_customer_price_ubb_margin_data3_percent','Значение: , Формулы: GMCUST', 'bvpn');
insert into identification(description, code, note, namespace) values('Запрошенная цена (UBB) Маржа на трафик включенный в минимальную сумму счета','out_customer_traffic_price_ubb_margin_percent','Значение: , Формулы: GMCUSTTraffic', 'bvpn');
insert into identification(description, code, note, namespace) values('Клиентская цена (UBB) Скидка от стандартной цены, разово','out_customer_price_ubb_discount_onetime_percent','Значение: , Формулы: DISCCUSTOTP', 'bvpn');
insert into identification(description, code, note, namespace) values('Клиентская цена (UBB) Скидка от стандартной цены, ежемесячно','out_customer_price_ubb_discount_monthly_percent','Значение: , Формулы: DISCCUSTMRP', 'bvpn');
insert into identification(description, code, note, namespace) values('Рекомендованная цена (UBB) разовый платеж','out_recomm_price_ubb_payment_onetime_money','Значение: , Формулы: OTPREC', 'bvpn');
insert into identification(description, code, note, namespace) values('Рекомендованная цена (UBB) минимальная сумма счета','out_recomm_price_ubb_payment_monthly_money','Значение: , Формулы: MRPREC', 'bvpn');
insert into identification(description, code, note, namespace) values('Рекомендованная цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), data1','out_recomm_tariff_ubb_data1_1MB_money','Значение: , Формулы: D1', 'bvpn');
insert into identification(description, code, note, namespace) values('Рекомендованная цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), data2','out_recomm_tariff_ubb_data2_1MB_money','Значение: , Формулы: D2', 'bvpn');
insert into identification(description, code, note, namespace) values('Рекомендованная цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), data3','out_recomm_tariff_ubb_data3_1MB_money','Значение: , Формулы: D3', 'bvpn');
insert into identification(description, code, note, namespace) values('Рекомендованная цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), video','out_recomm_tariff_ubb_video_1MB_money','Значение: , Формулы: Vi', 'bvpn');
insert into identification(description, code, note, namespace) values('Рекомендованная цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), voice','out_recomm_tariff_ubb_voice_1MB_money','Значение: , Формулы: Vo', 'bvpn');
insert into identification(description, code, note, namespace) values('Рекомендованная цена (UBB) Маржа на трафик (на дата 3)  было out_recomm_price_ubb_margin_percent','out_recomm_price_ubb_margin_data3_percent','Значение: , Формулы: GMREC', 'bvpn');
insert into identification(description, code, note, namespace) values('Рекомендованная цена (UBB) Маржа на трафик включенный в минимальную сумму счета','out_recomm_traffic_price_ubb_margin_percent','Значение: , Формулы: GMRECTraffic', 'bvpn');
insert into identification(description, code, note, namespace) values('Рекомендованная цена (UBB) Скидка от стандартной цены, разово','out_recomm_price_ubb_discount_onetime_percent','Значение: , Формулы: DISCRECOTP', 'bvpn');
insert into identification(description, code, note, namespace) values('Рекомендованная цена (UBB) Скидка от стандартной цены, ежемесячно','out_recomm_price_ubb_discount_monthly_percent','Значение: , Формулы: DISCRECMRP', 'bvpn');
insert into identification(description, code, note, namespace) values('Пороговая цена (UBB) разовый платеж','out_floor_price_ubb_payment_onetime_money','Значение: , Формулы: OTPFL', 'bvpn');
insert into identification(description, code, note, namespace) values('Пороговая цена (UBB) минимальная сумма счета','out_floor_price_ubb_payment_monthly_money','Значение: , Формулы: MRPFL', 'bvpn');
insert into identification(description, code, note, namespace) values('Пороговая цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), data1','out_floor_tariff_ubb_data1_1MB_money','Значение: , Формулы: D1', 'bvpn');
insert into identification(description, code, note, namespace) values('Пороговая цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), data2','out_floor_tariff_ubb_data2_1MB_money','Значение: , Формулы: D2', 'bvpn');
insert into identification(description, code, note, namespace) values('Пороговая цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), data3','out_floor_tariff_ubb_data3_1MB_money','Значение: , Формулы: D3', 'bvpn');
insert into identification(description, code, note, namespace) values('Пороговая цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), video','out_floor_tariff_ubb_video_1MB_money','Значение: , Формулы: Vi', 'bvpn');
insert into identification(description, code, note, namespace) values('Пороговая цена (UBB) Тариф (цена) за 1 MB трафика (scheme ubb), voice','out_floor_tariff_ubb_voice_1MB_money','Значение: , Формулы: Vo', 'bvpn');
insert into identification(description, code, note, namespace) values('Пороговая цена (UBB) Маржа на трафик (на дата 3)  было out_floor_price_ubb_margin_percent','out_floor_price_ubb_margin_data3_percent','Значение: , Формулы: GMFL', 'bvpn');
insert into identification(description, code, note, namespace) values('Пороговая цена (UBB) Маржа на трафик включенный в минимальную сумму счета','out_floor_traffic_price_ubb_margin_percent','Значение: , Формулы: GMCUSTTraffic', 'bvpn');
insert into identification(description, code, note, namespace) values('Пороговая цена (UBB) Скидка от стандартной цены, разово','out_floor_price_ubb_discount_onetime_percent','Значение: , Формулы: DISCFLOTP', 'bvpn');
insert into identification(description, code, note, namespace) values('Пороговая цена (UBB) Скидка от стандартной цены, ежемесячно','out_floor_price_ubb_discount_monthly_percent','Значение: , Формулы: DISCFLMRP', 'bvpn');
insert into identification(description, code, note, namespace) values('Вид строительства услуги Access Lines','in_lines_type_string','Значение: fiber, copper, radio, Формулы: ', 'lines');
insert into identification(description, code, note, namespace) values('Капитальные затраты на оборудование','in_capex_equipment_money','Значение: Ввод оператора, Формулы: Capex_eq', 'lines');
insert into identification(description, code, note, namespace) values('Капитальные затраты на строительство','in_capex_build_money','Значение: Ввод оператора, Формулы: Сapex_build', 'lines');
insert into identification(description, code, note, namespace) values('Суммарные капитальные затраты (оборудование и строительство) out_cost_capex_onetime_money=in_capex_equipment_money+in_capex_build_money','out_cost_capex_onetime_money','Значение: Capex_eq + Сapex_build, Формулы: Capex', 'lines');
insert into identification(description, code, note, namespace) values('Разовый затраты на аренду канала','in_cost_onetime_lease_money','Значение: Ввод оператора, Формулы: OTC_LM_Rent', 'lines');
insert into identification(description, code, note, namespace) values('Ежемесячные затраты на строительство последней мили','in_cost_monthly_build_money','Значение: Ввод оператора, Формулы: MRC_LM_Build', 'lines');
insert into identification(description, code, note, namespace) values('Ежемесячные затраты на аренду канала','in_cost_monthly_lease_money','Значение: Ввод оператора, Формулы: MRC_LM_Rent', 'lines');
insert into identification(description, code, note, namespace) values('Стандартная цена Суммарные ежемесячные затраты (out_cost_monthly_money=in_cost_monthly_build_money+in_cost_monthly_lease_money)','out_cost_monthly_money','Значение: , Формулы: MRC', 'lines');
insert into identification(description, code, note, namespace) values('Процент для формирования единовременного платежа на последнюю милю, применяется для расчета out_standard_price_payment_onetime_money','ref_coefficient_onetime_payment_percent','Значение: 30.0%, ', 'lines');
insert into identification(description, code, note, namespace) values('Маржа для расчета стандарной и запрошенной цены','ref_standard_required_price_margin_percent','Значение: 20.0%, Формулы: GM_STD_Calc', 'lines');
insert into identification(description, code, note, namespace) values('Срок окупаемости для расчета стандарной и запрошенной цены','ref_standard_required_price_payback_months','Значение: 12/18/24, Формулы: PB_STD_Calc', 'lines');
insert into identification(description, code, note, namespace) values('Маржа для расчета пороговой цены','ref_floor_price_margin_percent','Значение: 10.0%, Формулы: GM_FL_Calc', 'lines');
insert into identification(description, code, note, namespace) values('Срок окупаемости для расчета пороговой цены','ref_floor_price_payback_months','Значение: 8/18/24, Формулы: PB_FL_Calc', 'lines');
insert into identification(description, code, note, namespace) values('Срок амортизации оборудования в месяцах','ref_depreciation_equipment_months','Значение: 60, Формулы: AmEq', 'lines');
insert into identification(description, code, note, namespace) values('Срок амортизации (срок полезного использования) на строительство LM в месяцах','ref_depreciation_build_months','Значение: 120, Формулы: AmLMBuild', 'lines');
insert into identification(description, code, note, namespace) values('Стандартная цена ежемесячный платеж','out_standard_price_payment_monthly_money','Формулы: MRP_std', 'lines');
insert into identification(description, code, note, namespace) values('Стандартная цена разовый платеж','out_standard_price_payment_onetime_money','Формулы: OTP_std', 'lines');
insert into identification(description, code, note, namespace) values('Стандартная цена Маржа','out_standard_price_margin_percent','Формулы: GM_std', 'lines');
insert into identification(description, code, note, namespace) values('Стандартная цена Срок окупаемости','out_standard_price_payback_months','Формулы: PB_std', 'lines');
insert into identification(description, code, note, namespace) values('Запрошенная(желаемая) цена разовый платеж, может быть вычисленным, а может быть заданным.','inout_required_price_payment_onetime_money','Формулы: OTP_req', 'lines');
insert into identification(description, code, note, namespace) values('Запрошенная(желаемая) цена ежемесячный платеж, может быть вычисленным, а может быть заданным.','inout_required_price_payment_monthly_money','Формулы: MRP_req', 'lines');
insert into identification(description, code, note, namespace) values('Запрошенная(желаемая) цена Маржа','out_required_price_margin_percent','Формулы: GM_req', 'lines');
insert into identification(description, code, note, namespace) values('Запрошенная(желаемая) цена Срок окупаемости','out_required_price_payback_months','Формулы: PB_req', 'lines');
insert into identification(description, code, note, namespace) values('Запрошенная(желаемая) цена Скидка от стандартной цены, разово','out_required_price_discount_onetime_percent','Формулы: D_req', 'lines');
insert into identification(description, code, note, namespace) values('Запрошенная(желаемая) цена Скидка от стандартной цены, ежемесячно','out_required_price_discount_monthly_percent','Формулы: D_req', 'lines');
insert into identification(description, code, note, namespace) values('Клиентская цена разовый платеж','out_customer_price_payment_onetime_money','Формулы: OTP_cust', 'lines');
insert into identification(description, code, note, namespace) values('Клиентская цена ежемесячный платеж','out_customer_price_payment_monthly_money','Формулы: MRP_cust', 'lines');
insert into identification(description, code, note, namespace) values('Клиентская цена Маржа','out_customer_price_margin_percent','Формулы: GM_cust', 'lines');
insert into identification(description, code, note, namespace) values('Клиентская цена Срок окупаемости','out_customer_price_payback_months','Формулы: PB_cust', 'lines');
insert into identification(description, code, note, namespace) values('Клиентская цена Скидка от стандартной цены, разово','out_customer_price_discount_onetime_percent','Формулы: DISC_CUST_OTP', 'lines');
insert into identification(description, code, note, namespace) values('Клиентская цена Скидка от стандартной цены, ежемесячно','out_customer_price_discount_monthly_percent','Формулы: DISC_CUST_MRP', 'lines');
insert into identification(description, code, note, namespace) values('Пороговая цена разовый платеж','out_floor_price_payment_onetime_money','', 'lines');
insert into identification(description, code, note, namespace) values('Пороговая цена ежемесячный платеж','out_floor_price_payment_monthly_money','', 'lines');
insert into identification(description, code, note, namespace) values('Пороговая цена Маржа','out_floor_price_margin_percent','', 'lines');
insert into identification(description, code, note, namespace) values('Пороговая цена Срок окупаемости','out_floor_price_payback_months','', 'lines');
insert into identification(description, code, note, namespace) values('Пороговая цена Скидка от стандартной цены, разово','out_floor_price_discount_onetime_percent','', 'lines');
insert into identification(description, code, note, namespace) values('Пороговая цена Скидка от стандартной цены, ежемесячно','out_floor_price_discount_monthly_percent','', 'lines');
insert into identification(description, code, note, namespace) values('Тип заказа','in_product_item_type_string','Значение: new/upgrade/downgrade, Формулы: ', 'common');
insert into identification(description, code, note, namespace) values('Срок контракта в месяцах (12/24/36)','in_contract_term_months','Значение: Ввод оператора, Формулы: N', 'common');
insert into identification(description, code, note, namespace) values('Процент на риски, используемый в формулах','ref_risk_percent','Значение: 4 процентов, Формулы: Risk', 'common');
insert into identification(description, code, note, namespace) values('Управленческие и коммерческие расходы','ref_cost_executive_salary_percent','Значение: 14 процентов, Формулы: SGA', 'common');
