drop SEQUENCE WRAPPER_NUMERIC_SEQ;
drop SEQUENCE WRAPPER_MONEY_SEQ;
drop SEQUENCE WRAPPER_STRING_SEQ;
drop SEQUENCE USERS_SEQ;
drop SEQUENCE ROLES_SEQ;
drop SEQUENCE GROUPS_SEQ;
drop SEQUENCE GROUP_MEMBERS_SEQ;

CREATE SEQUENCE WRAPPER_NUMERIC_SEQ MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 2 NOORDER  NOCYCLE;
CREATE SEQUENCE WRAPPER_MONEY_SEQ MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 2 NOORDER  NOCYCLE;
CREATE SEQUENCE WRAPPER_STRING_SEQ MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 2 NOORDER  NOCYCLE;
CREATE SEQUENCE USERS_SEQ           MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 2 NOORDER  NOCYCLE;
CREATE SEQUENCE ROLES_SEQ           MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 2 NOORDER  NOCYCLE;
CREATE SEQUENCE GROUPS_SEQ          MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 2 NOORDER  NOCYCLE;
CREATE SEQUENCE GROUP_MEMBERS_SEQ   MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 2 NOORDER  NOCYCLE;
/

DROP TABLE group_roles;
DROP TABLE group_members;
DROP TABLE users;
DROP TABLE roles;
DROP TABLE groups;

-- Таблица users
CREATE TABLE users(
  id NUMBER NOT NULL ENABLE NOVALIDATE,
  email VARCHAR2(4000 BYTE) NOT NULL UNIQUE,
  firstName VARCHAR2(4000 BYTE) NOT NULL ENABLE NOVALIDATE,
  lastName VARCHAR2(4000 BYTE) NOT NULL ENABLE NOVALIDATE,
  password VARCHAR2(4000 BYTE) NOT NULL ENABLE NOVALIDATE,
  status char(1) DEFAULT '1',
  CONSTRAINT users_pk PRIMARY KEY (id)
);
COMMENT ON COLUMN users.status IS 'status=0 - пользователь отключен, status=1 - пользователь активен';

CREATE OR REPLACE TRIGGER TR_USERS
 BEFORE
  INSERT OR UPDATE
 ON USERS
    REFERENCING NEW AS NEW OLD AS OLD
 FOR EACH ROW
  Begin
   if INSERTING then select USERS_SEQ.NEXTVAL into :NEW.ID from dual;
   end if;
End;
/
ALTER TRIGGER TR_USERS ENABLE;

-- так как таблица users ни от чего не зависит, то можно сразу добавить пользователей
insert into users(email,firstName,lastName,password) values('evgeniya.kirillova@orange.com','Евгения','Кириллова', 'e6cc550b7396954fb0bf65922a2206652814fd80955513ba0cbd87bd67eab39ea84b3e443c2d0770');
insert into users(email,firstName,lastName,password) values('sergey.bogachek@orange.com',   'Сергей','Богачек',    '597c8e64f96c6d76152adbf4e70c02ceb3792a39e6f77e6b3c32860346d2564e5be3e432de4d67fb');
insert into users(email,firstName,lastName,password) values('radik.zaynullin@orange.com',   'Радик','Зайнуллин',   '27ba4592cb12a9dfc018166d138b1c336b0574a8ea02ccee8a7c6a1e4e806178036818020d342d59');
insert into users(email,firstName,lastName,password) values('nadezhda.minkina@orange.com',  'Надя','Минкина',      'ca3792cf9c5a5071ad5f5ca1414222757ba658866456467024943832ef58e4e7ea1667b8bdfa88bc');
insert into users(email,firstName,lastName,password) values('alexander.samoylov@orange.com','Александр','Самойлов','ad2e9d80f9dede52bcf49681bbb1c1631451441c6a3e0294b3e52a82fb1e57c0d6a8710461ef20ed');

-- Таблица roles
CREATE TABLE roles(
  id NUMBER NOT NULL ENABLE NOVALIDATE,
  role VARCHAR2(4000 BYTE) NOT NULL UNIQUE,
  description VARCHAR2(4000 BYTE) NOT NULL ENABLE NOVALIDATE,
  CONSTRAINT roles_pk PRIMARY KEY (id)
);

CREATE OR REPLACE TRIGGER TR_ROLES
 BEFORE
  INSERT OR UPDATE
 ON ROLES
    REFERENCING NEW AS NEW OLD AS OLD
 FOR EACH ROW
  Begin
   if INSERTING then select ROLES_SEQ.NEXTVAL into :NEW.ID from dual;
   end if;
End;
/
ALTER TRIGGER TR_ROLES ENABLE;

-- так как таблица roles ни от чего не зависит, то можно сразу добавить роли
insert into roles(role, description) values('ROLE_SALE', 'Простой продавец');
insert into roles(role, description) values('ROLE_PRESALE', 'Старший продавец');
insert into roles(role, description) values('ROLE_MARKETING', 'Сотрудник отдела маркетинга');
insert into roles(role, description) values('ROLE_ADMIN', 'Системный администратор');
-- Таблица groups
CREATE TABLE groups(
  id NUMBER NOT NULL ENABLE NOVALIDATE,
  group_name VARCHAR2(4000 BYTE) NOT NULL UNIQUE,
  CONSTRAINT groups_pk PRIMARY KEY (id)
);
CREATE OR REPLACE TRIGGER TR_groups
 BEFORE
  INSERT OR UPDATE
 ON groups
    REFERENCING NEW AS NEW OLD AS OLD
 FOR EACH ROW
  Begin
   if INSERTING then select groups_SEQ.NEXTVAL into :NEW.ID from dual;
   end if;
End;
/
ALTER TRIGGER TR_groups ENABLE;

-- так как таблица groups ни от чего не зависит, то можно сразу добавить группы
insert into groups(group_name) values('Administrators');
insert into groups(group_name) values('Marketing Specialists');
insert into groups(group_name) values('Sales managers');
insert into groups(group_name) values('Sales persons');

-- Таблица group_roles
CREATE TABLE group_roles(
  group_id NUMBER NOT NULL,
  role_id NUMBER NOT NULL,
  CONSTRAINT FK_group_id FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE CASCADE ENABLE NOVALIDATE,
  CONSTRAINT FK_role_id FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE ENABLE NOVALIDATE
);
-- Map the Groups to Roles
insert into group_roles(group_id, role_id) select g.id, r.id from groups g, roles r where g.group_name='Sales persons' and r.role='ROLE_SALE';
insert into group_roles(group_id, role_id) select g.id, r.id from groups g, roles r where g.group_name='Sales managers' and r.role='ROLE_SALE';
insert into group_roles(group_id, role_id) select g.id, r.id from groups g, roles r where g.group_name='Sales managers' and r.role='ROLE_PRESALE';
insert into group_roles(group_id, role_id) select g.id, r.id from groups g, roles r where g.group_name='Marketing Specialists' and r.role='ROLE_SALE';
insert into group_roles(group_id, role_id) select g.id, r.id from groups g, roles r where g.group_name='Marketing Specialists' and r.role='ROLE_PRESALE';
insert into group_roles(group_id, role_id) select g.id, r.id from groups g, roles r where g.group_name='Marketing Specialists' and r.role='ROLE_MARKETING';
insert into group_roles(group_id, role_id) select g.id, r.id from groups g, roles r where g.group_name='Administrators' and r.role='ROLE_ADMIN';

-- group_members
CREATE TABLE group_members(
  id NUMBER NOT NULL ENABLE NOVALIDATE,
  user_id NUMBER NOT NULL,
  group_id NUMBER NOT NULL,
  CONSTRAINT FK_group_members_user_id FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ENABLE NOVALIDATE,
  CONSTRAINT FK_group_members_group_id FOREIGN KEY (group_id) REFERENCES groups(id) ENABLE NOVALIDATE,
  CONSTRAINT group_members_pk PRIMARY KEY (id)
);
CREATE OR REPLACE TRIGGER TR_group_members
 BEFORE
  INSERT OR UPDATE
 ON group_members
    REFERENCING NEW AS NEW OLD AS OLD
 FOR EACH ROW
  Begin
   if INSERTING then select group_members_SEQ.NEXTVAL into :NEW.ID from dual;
   end if;
End;
/
ALTER TRIGGER TR_group_members ENABLE;

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