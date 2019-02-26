DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO meals (date_time, description, calories, user_id) VALUES
('2019-02-24 08:00:00', 'Завтрак', 1010, 100000),
('2019-02-24 13:00:00', 'Обед', 1000, 100000),
('2019-02-24 20:00:00', 'Ужин', 500, 100001),
('2019-02-25 9:00:00', 'Завтрак', 1000, 100001),
('2019-02-25 14:00:00', 'Обед', 1000, 100001),
('2019-02-25 21:00:00', 'Ужин', 500, 100000);

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);
