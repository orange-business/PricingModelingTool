-- Важно! При полном восстановлении сначала запустите приложение, чтобы hibernate создал необходимые таблицы
# // заполнение таблицы customer
insert into customer(id, official) values (1, 'ИП Деряга Наталья Сергеевна');
insert into customer(id, official) values (2, 'ООО "База-3"');
insert into customer(id, official) values (3, 'ОАО "Янтарный сказ"');
insert into customer(id, official) values (4, 'ООО "КОМПАНИЯ ВЕРСАЛЬ"');
insert into customer(id, official) values (5, 'ЮгТехноПроект');
insert into customer(id, official) values (6, 'ООО "Пиком"');
insert into customer(id, official) values (7, 'ЗАО "ХК "Композит"');
insert into customer(id, official) values (8, 'ЗАО ИКБ "Европейский"');
insert into customer(id, official) values (9, 'ООО "Роза Хутор"');

# // заполнение таблицы site
insert into site(id, address, customer_id) values (1, 'г. Иваново, пр-т Ленина, д. 21, стр. 3', 1);
insert into site(id, address, customer_id) values (2, 'г. Пятигорск, ул. Акопянц, д. 11', 1);
insert into site(id, address, customer_id) values (3, 'г. Ростов-на-Дону, пр. Стачки, д. 25Б', 1);
insert into site(id, address, customer_id) values (4, 'г. Рязань, ул. Свободы, д. 14/16', 1);
insert into site(id, address, customer_id) values (5, 'г. Саратов, ул. им. Вавилова Н.И., д. 38/114', 1);
insert into site(id, address, customer_id) values (6, 'г. Великий Новгород, ул. Б. Санкт-Петербургская, д. 39', 1);
insert into site(id, address, customer_id) values (7, 'г. Мурманск, ул. Челюскинцев, д. 30', 2);
insert into site(id, address, customer_id) values (8, 'г. Новосибирск, ул. Фрунзе, д. 86', 2);
insert into site(id, address, customer_id) values (9, 'г. Ярославль, ул. Республиканская, д. 3', 2);
insert into site(id, address, customer_id) values (10, 'г. Москва, ш. Варшавское, д. 125', 2);
insert into site(id, address, customer_id) values (11, 'г. Сарапул, ул. Советская, д.17 А', 2);
insert into site(id, address, customer_id) values (12, 'г. Воткинск, 427430, ул.Мира, д.5', 2);
insert into site(id, address, customer_id) values (13, 'г. Данилов, Ярославская обл., ул.Ленина, 20-в', 2);
insert into site(id, address, customer_id) values (14, 'г. Ярославль, ул. Пятницкая,  д. 6', 2);
insert into site(id, address, customer_id) values (15, 'г. Ярославль, ул. Б. Октябрьская, д.35', 2);
insert into site(id, address, customer_id) values (16, 'г. Переславль - Залесский, ул. 50 лет Комсомолу', 3);
insert into site(id, address, customer_id) values (17, 'г. Рыбинск, ул.Пушкина д.28', 3);
insert into site(id, address, customer_id) values (18, 'г. Углич, 152620, Ярославская обл., ул. Ярославская, д.15', 3);
insert into site(id, address, customer_id) values (19, 'г. Калининград, Ленинский проспект, 30', 3);
insert into site(id, address, customer_id) values (20, 'г. Белово, ул. Ленина, дом 10', 4);
insert into site(id, address, customer_id) values (21, 'г. Кемерово, ул.  Молодежная, 7/1', 4);
insert into site(id, address, customer_id) values (22, 'г. Кемерово, ул. Красноармейская, д. 120', 4);
insert into site(id, address, customer_id) values (23, 'г. Новокузнецк, ул. Павлова, д.11а', 4);
insert into site(id, address, customer_id) values (24, 'г. Ленинск-Кузнецк, пр-т. Григорченкова, д. 41', 4);
insert into site(id, address, customer_id) values (25, 'г. Новокузнецк, 654007, ул.Кирова, д.25', 5);
insert into site(id, address, customer_id) values (26, 'г. Кирово-Чепецк, ул.Ленина, д.34/2', 5);
insert into site(id, address, customer_id) values (27, 'г. Киров, ул. Воровского, д. 119', 6);
insert into site(id, address, customer_id) values (28, 'г. Слободской, ул.Вятская, 6', 6);
insert into site(id, address, customer_id) values (29, 'г. Курган, ул. Ленина,  д. 5', 7);
insert into site(id, address, customer_id) values (30, 'г. Калуга,  ул. Ленина,  д.73', 8);
insert into site(id, address, customer_id) values (31, 'г. Москва Алтуфьевское шоссе, д.22Б', 9);
insert into site(id, address, customer_id) values (32, 'г. Москва ул.Арбат, д.1', 9);
insert into site(id, address, customer_id) values (33, 'г. Москва ул.Долгоруковская, д.19, к.1', 9);
insert into site(id, address, customer_id) values (34, 'г. Москва, ул.Электрозаводская 27', 9);
insert into site(id, address, customer_id) values (35, 'г. Москва Измайловская площадь, д.1', 9);
insert into site(id, address, customer_id) values (36, 'г. Москва ул.Кржижановского, д.14, корп.3', 9);
insert into site(id, address, customer_id) values (37, 'г. Москва Комсомольский пр., д.40/13, стр.1', 9);
insert into site(id, address, customer_id) values (38, 'г. Москва Кутузовский проспект, д.15', 9);
insert into site(id, address, customer_id) values (39, 'г. Москва, Ленинградский пр-т, д.12', 9);
insert into site(id, address, customer_id) values (40, 'г. Москва Ленинский пр-т, д.82/2', 9);
insert into site(id, address, customer_id) values (41, 'г. Москва Мичуринский пр-т, д.9', 9);
insert into site(id, address, customer_id) values (42, 'г. Москва, ул.Маши Порываевой, д.11', 9);
insert into site(id, address, customer_id) values (43, 'г. Москва, пр-т Маршала Жукова, д.48', 9);
insert into site(id, address, customer_id) values (44, 'г. Москва ул.Обручева, д.30/1, стр.1', 9);
insert into site(id, address, customer_id) values (45, 'г. Москва, Павелецкая пл., д.2/3', 9);
insert into site(id, address, customer_id) values (46, 'г. Москва ул.Пречистенка, д.40/2, стр.1', 9);
insert into site(id, address, customer_id) values (47, 'г. Москва Проспект Мира, д.124, стр.8', 9);
insert into site(id, address, customer_id) values (48, 'г. Москва, ул.Пятницкая, д.2/38, стр.2', 9);
insert into site(id, address, customer_id) values (49, 'г. Москва, ул.Русаковская, д.22, стр.1', 9);
insert into site(id, address, customer_id) values (50, 'г. Москва Страстной бульвар, д.7, стр.1', 9);
insert into site(id, address, customer_id) values (51, 'г. Москва ул.Садовая-Кудринская, д.32, стр.1', 9);
insert into site(id, address, customer_id) values (52, 'г. Москва, ул. Смольная, д.22, стр.1', 9);
insert into site(id, address, customer_id) values (53, 'г. Москва ул. Таганская, 29, стр.1', 9);
insert into site(id, address, customer_id) values (54, 'г. Москва Средний Тишинский пер., д.28, стр.1', 9);
insert into site(id, address, customer_id) values (55, 'г. Москва, ул. Пятницкая, д. 72', 9);
insert into site(id, address, customer_id) values (56, 'г. Мичуринск, ул. Филиппова,72 (бывш Советская, 281)', 9);
insert into site(id, address, customer_id) values (57, 'г. Рассказово, ул.60 лет Рассказово, 4', 9);
insert into site(id, address, customer_id) values (58, 'г. Тамбов, ул. Державинская, д. 28/13', 9);
insert into site(id, address, customer_id) values (59, 'с. Бакчар, ул.Ленина, 49', 9);
insert into site(id, address, customer_id) values (60, 'г. Колпашево, ул. Белинского, д.16/1, стр.1', 9);
insert into site(id, address, customer_id) values (61, 'с. Кривошеино, 636300, Томская обл.,ул.Кирова,22', 9);
insert into site(id, address, customer_id) values (62, 'с. Кожевниково, ул. Ленина ,д.8', 9);
insert into site(id, address, customer_id) values (63, 'г. Томск, 634003, пер.Кустарный, д.1', 9);
insert into site(id, address, customer_id) values (64, 'г. Томск, пр. Ленина, д. 40', 9);
insert into site(id, address, customer_id) values (65, 'г. Томск, пл. Ленина, 8', 9);
insert into site(id, address, customer_id) values (66, 'с. Мельниково, ул. Коммунистическая, дом 9', 9);
insert into site(id, address, customer_id) values (67, 'с. Подгорное, ул.Островского, 9а', 9);
insert into site(id, address, customer_id) values (68, 'с. Первомайское, ул.Кольцова, 6', 9);
insert into site(id, address, customer_id) values (69, 'с. Парабель, ул.Советская , 24', 9);
insert into site(id, address, customer_id) values (70, 'г. Северск, ул. Коммунистическая, д. 94', 9);
insert into site(id, address, customer_id) values (71, 'г. Томск, ул. Белинского, д. 63', 9);
insert into site(id, address, customer_id) values (72, 'с. Зырянское, ул. Советская 19а', 9);
insert into site(id, address, customer_id) values (73, 'г. Нефтеюганск, 16 микр-н, д. 76', 9);
insert into site(id, address, customer_id) values (74, 'г. Нижневартовск, ул. Ленина, дом 15/1', 9);
insert into site(id, address, customer_id) values (75, 'г. Тюмень, ул.50- летия Октября, д.34/1', 9);
insert into site(id, address, customer_id) values (76, 'г. Тюмень, ул.Первомайская, д.21', 9);
insert into site(id, address, customer_id) values (77, 'г. Тобольск, ул. Октябрьская , д 20', 9);
insert into site(id, address, customer_id) values (78, 'г. Тюмень, ул.Ямская, 76', 9);
insert into site(id, address, customer_id) values (79, 'г. Тверь, Вагжановский пер.,9', 9);
insert into site(id, address, customer_id) values (80, 'г. Кызыл, ул. ул.Тувинских Добровольцев, д.10', 9);
insert into site(id, address, customer_id) values (81, 'г. Уфа, ул. Цюрупы, д. 145', 9);
insert into site(id, address, customer_id) values (82, 'г. Уфа, ул.Карла Маркса, д.20', 9);
insert into site(id, address, customer_id) values (83, 'г. Уфа, просп. Октября, 131', 9);
insert into site(id, address, customer_id) values (84, 'г. Стерлитамак, ул. Гоголя, 3', 9);
insert into site(id, address, customer_id) values (85, 'г. Димитровоград, ул. Мориса Торезы, 2В', 9);
insert into site(id, address, customer_id) values (86, 'г. Ульяновск, просп. Ленинского Комсомола, д.43', 9);
insert into site(id, address, customer_id) values (87, 'г. Ульяновск, ул. Карла Маркса, д. 7 А', 9);
insert into site(id, address, customer_id) values (88, 'г. Волгоград, ул. 39-й Гвардейской, д.27', 9);
insert into site(id, address, customer_id) values (89, 'г. Волгоград, просп. Ленина, д.46', 9);
insert into site(id, address, customer_id) values (90, 'г. Волгоград, проспект им. В.И.Ленина, д.16', 9);
insert into site(id, address, customer_id) values (91, 'пгт. Красная Горбатка, Владимирская обл.,ул.Пролетарская, 22', 9);
insert into site(id, address, customer_id) values (92, 'г. Киржач, Владимирская обл., ул. Советская, д. 1а', 9);
insert into site(id, address, customer_id) values (93, 'г. Владимир, ул. Разина, д . 30', 9);
insert into site(id, address, customer_id) values (94, 'г. Вязники, пр.Муромский, 6', 9);
insert into site(id, address, customer_id) values (95, 'г. Вологда, ул. Герцена, д. 63', 9);
insert into site(id, address, customer_id) values (96, 'г. Воронеж, 394007, ул. Ленинградская, д.2', 9);
insert into site(id, address, customer_id) values (97, 'г. Воронеж, ул. Карла Маркса, д. 68', 9);
insert into site(id, address, customer_id) values (98, 'г. Абакан, ул.Пушкина, 165', 9);

#
insert into treasure_identification(code, description) values('business_vpn_cost_onetime_installation', 'Business VPN: единовременные затраты фирмы Orange на инсталляцию оборудования');
insert into treasure_identification(code, description) values('business_vpn_cost_monthly_recurrent_cost', 'Business VPN: ежемесячные затраты фирмы Orange(mrc)');
insert into treasure_identification(code, description) values('business_vpn_cost_monthly_recurrent_cost_fix','Business VPN: ежемесячная плата за аренду, схема тарификации fix.');
insert into treasure_identification(code, description) values('business_vpn_payment_monthly_fix','Business VPN: ежемесячные платежи клиента при схеме тарификации fix');
insert into treasure_identification(code, description) values('business_vpn_payment_monthly_ubb_minimal_subscriber_fee', 'Business VPN: ежемесячный минимальный платеж клиента за порт при схеме тарификации ubb');
insert into treasure_identification(code, description) values('business_vpn_payment_one_time_installation', 'Business VPN: единовременные затраты клиента на инсталляцию оборудования услуги');
insert into treasure_identification(code, description) values('business_vpn_ubb_tariff_for_1mb_data3', 'Business VPN: тариф за 1 мб при схеме тарификации ubb, цвет траффика data3');
insert into treasure_identification(code, description) values('business_vpn_ubb_tariff_for_1mb_data2', 'Business VPN: тариф за 1 мб при схеме тарификации ubb, цвет траффика data2');
insert into treasure_identification(code, description) values('business_vpn_ubb_tariff_for_1mb_data1', 'Business VPN: тариф за 1 мб при схеме тарификации ubb, цвет траффика data1');
insert into treasure_identification(code, description) values('business_vpn_ubb_tariff_for_1mb_voice', 'Business VPN: тариф за 1 мб при схеме тарификации ubb, цвет траффика voice');
insert into treasure_identification(code, description) values('business_vpn_ubb_tariff_for_1mb_video', 'Business VPN: тариф за 1 мб при схеме тарификации ubb, цвет траффика video');
insert into treasure_identification(code, description) values('business_vpn_cost_monthly_1mb_ubb', 'Business VPN: ежемесячный кост за 1 мб, схема тарификации ubb.');
insert into treasure_identification(code, description) values('business_vpn_cost_monthly_in_minimal_subscriber_fee_ubb','Business VPN: ежемесячный кост трафика, включенный в минимальный платеж, схема тарификации ubb.');
insert into treasure_identification(code, description) values('business_vpn_tariff_onetime', 'Business VPN: Единовременный платеж клиента.');

insert into treasure_identification(code, description) values('access_lines_cost_equipment_procurement_price', 'Access Lines: закупочная стоимость оборудования в рублях.');
insert into treasure_identification(code, description) values('access_lines_cost_onetime', 'Access Lines: Единовременные затраты в рублях.');
insert into treasure_identification(code, description) values('access_lines_cost_monthly', 'Access Lines: Ежемесячные затраты в рублях.');
insert into treasure_identification(code, description) values('access_lines_cost_radio_legalization', 'Access Lines: radio. Стоимость легализации в рублях.');
insert into treasure_identification(code, description) values('access_lines_cost_monthly_radio_support', 'Access Lines: radio. Стоимость ежемесячной поддержки в рублях.');
insert into treasure_identification(code, description) values('access_lines_cost_radio_equipment_procurement_price', 'Access Lines: radio. Закупочная стоимость оборудования в рублях.');
insert into treasure_identification(code, description) values('access_lines_cost_radio_mounting', 'Access Lines: radio. Затраты на установку оборудования.');
insert into treasure_identification(code, description) values('access_lines_cost_radio_monthly', 'Access Lines: radio. Ежемесячная оплата.');
insert into treasure_identification(code, description) values('access_lines_cost_mounting_monthly', 'Access Lines: ежемесячные затраты.');

DROP TABLE IF EXISTS group_roles;
DROP TABLE IF EXISTS group_members;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS groups;

-- Таблица users
CREATE TABLE users(
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  email CHAR(255) NOT NULL,
  firstName VARCHAR(400) NOT NULL,
  lastName VARCHAR(400) NOT NULL,
  password VARCHAR(400) NOT NULL,
  status TINYINT(1) NOT NULL DEFAULT '1',
  UNIQUE KEY UK_EMAIL(email),
  PRIMARY KEY (id)
);
-- так как таблица users ни от чего не зависит, то можно сразу добавить пользователей
insert into users(email,firstName,lastName,password) values('evgeniya.kirillova@orange.com','Евгения','Кириллова', 'e6cc550b7396954fb0bf65922a2206652814fd80955513ba0cbd87bd67eab39ea84b3e443c2d0770');
insert into users(email,firstName,lastName,password) values('sergey.bogachek@orange.com',   'Сергей','Богачек',    '597c8e64f96c6d76152adbf4e70c02ceb3792a39e6f77e6b3c32860346d2564e5be3e432de4d67fb');
insert into users(email,firstName,lastName,password) values('radik.zaynullin@orange.com',   'Радик','Зайнуллин',   '27ba4592cb12a9dfc018166d138b1c336b0574a8ea02ccee8a7c6a1e4e806178036818020d342d59');
insert into users(email,firstName,lastName,password) values('nadezhda.minkina@orange.com',  'Надя','Минкина',      'ca3792cf9c5a5071ad5f5ca1414222757ba658866456467024943832ef58e4e7ea1667b8bdfa88bc');
insert into users(email,firstName,lastName,password) values('alexander.samoylov@orange.com','Александр','Самойлов','ad2e9d80f9dede52bcf49681bbb1c1631451441c6a3e0294b3e52a82fb1e57c0d6a8710461ef20ed');
-- Таблица roles
CREATE TABLE roles(
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  role VARCHAR(50) NOT NULL,
  description VARCHAR(1024) NOT NULL,
  PRIMARY KEY (id)
);
-- так как таблица roles ни от чего не зависит, то можно сразу добавить роли
insert into roles(role, description) values('ROLE_SALE', 'Простой продавец');
insert into roles(role, description) values('ROLE_PRESALE', 'Старший продавец');
insert into roles(role, description) values('ROLE_MARKETING', 'Сотрудник отдела маркетинга');
insert into roles(role, description) values('ROLE_ADMIN', 'Системный администратор');
-- Таблица groups
CREATE TABLE groups (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  group_name VARCHAR(1024) NOT NULL,
  PRIMARY KEY (id)
);
-- так как таблица groups ни от чего не зависит, то можно сразу добавить группы
insert into groups(group_name) values('Administrators');
insert into groups(group_name) values('Marketing Specialists');
insert into groups(group_name) values('Sales managers');
insert into groups(group_name) values('Sales persons');
-- Таблица group_roles
CREATE TABLE group_roles (
  group_id BIGINT(20) NOT NULL,
  role_id BIGINT(20) NOT NULL,
  FOREIGN KEY (group_id) REFERENCES groups(id),
  FOREIGN KEY (role_id) REFERENCES roles(id)
);
-- Map the Groups to Roles
insert into group_roles(group_id, role_id) select g.id, r.id from groups g, roles r where g.group_name='Sales persons' and r.role='ROLE_SALE';
insert into group_roles(group_id, role_id) select g.id, r.id from groups g, roles r where g.group_name='Sales managers' and r.role='ROLE_SALE';
insert into group_roles(group_id, role_id) select g.id, r.id from groups g, roles r where g.group_name='Sales managers' and r.role='ROLE_PRESALE';
insert into group_roles(group_id, role_id) select g.id, r.id from groups g, roles r where g.group_name='Marketing Specialists' and r.role='ROLE_SALE';
insert into group_roles(group_id, role_id) select g.id, r.id from groups g, roles r where g.group_name='Marketing Specialists' and r.role='ROLE_PRESALE';
insert into group_roles(group_id, role_id) select g.id, r.id from groups g, roles r where g.group_name='Marketing Specialists' and r.role='ROLE_MARKETING';
insert into group_roles(group_id, role_id) select g.id, r.id from groups g, roles r where g.group_name='Administrators' and r.role='ROLE_ADMIN';

--
CREATE TABLE group_members (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  user_id BIGINT(20) NOT NULL,
  group_id BIGINT(20) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id) on delete cascade,
  FOREIGN KEY (group_id) REFERENCES groups(id),
  PRIMARY KEY (id)
);
insert into group_members(user_id, group_id) select u.id, g.id  from users u, groups g where g.group_name='Marketing Specialists' and u.email='evgeniya.kirillova@orange.com';
insert into group_members(user_id, group_id) select u.id, g.id  from users u, groups g where g.group_name='Marketing Specialists' and u.email='sergey.bogachek@orange.com';
insert into group_members(user_id, group_id) select u.id, g.id  from users u, groups g where g.group_name='Marketing Specialists' and u.email='radik.zaynullin@orange.com';
insert into group_members(user_id, group_id) select u.id, g.id  from users u, groups g where g.group_name='Marketing Specialists' and u.email='nadezhda.minkina@orange.com';
insert into group_members(user_id, group_id) select u.id, g.id  from users u, groups g where g.group_name='Marketing Specialists' and u.email='alexander.samoylov@orange.com';
insert into group_members(user_id, group_id) select u.id, g.id  from users u, groups g where g.group_name='Marketing Specialists' and u.email='marketing@orange.com';
insert into group_members(user_id, group_id) select u.id, g.id  from users u, groups g where g.group_name='Administrators' and u.email='sergey.bogachek@orange.com';
insert into group_members(user_id, group_id) select u.id, g.id  from users u, groups g where g.group_name='Administrators' and u.email='radik.zaynullin@orange.com';
insert into group_members(user_id, group_id) select u.id, g.id  from users u, groups g where g.group_name='Administrators' and u.email='admin@orange.com';
insert into group_members(user_id, group_id) select u.id, g.id  from users u, groups g where g.group_name='Sales managers' and u.email='presale@orange.com';
insert into group_members(user_id, group_id) select u.id, g.id  from users u, groups g where g.group_name='Sales persons' and u.email='sale@orange.com';