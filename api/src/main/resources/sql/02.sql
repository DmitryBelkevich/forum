INSERT INTO users (username, password) VALUES
  ('user1', '1234'),
  ('user2', '1234');

INSERT INTO categories (title) VALUES
  ('поиск музыкантов'),
  ('продажа инструментов');

INSERT INTO topics (title, user_id, category_id) VALUES
  ('требуется гитарист', 1, 1),
  ('требуется бас-гитарист', 1, 1),
  ('требуется клавишник', 2, 1),
  ('требуется ударник', 2, 1),
  ('продам гитару', 1, 2),
  ('продам бас-гитару', 1, 2),
  ('продам клавишные', 2, 2),
  ('продам ударные', 2, 2);

INSERT INTO messages (text, user_id, topic_id, date) VALUES
  ('требуется гитарист в Минскую рок-группу', 1, 1, '2015-01-01 12:00:00.000000'),
  ('требуется бас-гитарист в Минскую рок-группу', 1, 2, '2015-01-02 12:00:00.000000'),
  ('требуется клавишник в Минскую рок-группу', 2, 3, '2015-01-03 01:00:00.000000'),
  ('требуется ударник в Минскую рок-группу', 2, 4, '2015-01-04 12:00:00.000000'),
  ('продам гитару в Минске', 1, 5, '2015-01-05 12:00:00.000000'),
  ('продам бас-гитару в Минске', 1, 6, '2015-01-06 12:00:00.000000'),
  ('продам клавишные в Минске', 2, 7, '2015-01-07 12:00:00.000000'),
  ('продам ударные в Минске', 2, 8, '2015-01-08 12:00:00.000000');
