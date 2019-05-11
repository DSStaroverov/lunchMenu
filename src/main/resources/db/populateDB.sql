DELETE FROM user_roles;
DELETE FROM LUNCH_ITEMS;
DELETE FROM VOTES;
DELETE FROM MENUS;
DELETE FROM users;
DELETE FROM RESTAURANT;


ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', '{noop}password'),
  ('User2', 'user2@yandex.ru', '{noop}password'),
  ('User3', 'user3@yandex.ru', '{noop}password'),
  ('User4', 'user4@yandex.ru', '{noop}password'),
  ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_USER', 100002),
  ('ROLE_USER', 100003),
  ('ROLE_USER', 100004),
  ('ROLE_ADMIN', 100004);

INSERT INTO RESTAURANT(name, address) VALUES
('Vacabi','Pionerskay st. 25'),--100005
('TokioCity','Pionerskay st. 12');--100006

INSERT INTO MENUS(RESTAURANT_ID, CREATE_DATE,PRICE) VALUES
(100005,'2019-04-29',200),--100007
(100006,now,250);--100008

INSERT INTO LUNCH_ITEMS(NAME, MENU_ID, CALORIES) VALUES
('Soap Vasabi Sunday',100007,230),--100009
('Bread Vasabi Sunday',100007,150),--100010
('Tea Vasabi Sunday',100007,20),--100011
('Roll Vasabi Sunday',100007,420),--100012
('Soap TokioCity Sunday',100008,350),--100013
('Coffee TokioCity Sunday',100008,10),--100014
('Udon TokioCity Sunday',100008,550),--100015
('Roll TokioCity Sunday',100008,280);--100016

INSERT INTO VOTES(USER_ID, MENU_ID,VOTE_DATE) VALUES
(100000,100007,'2019-04-29'),--100017
(100001,100007,'2019-04-29'),--100018
(100002,100007,'2019-04-29'),--100020
(100003,100008,now);--100021