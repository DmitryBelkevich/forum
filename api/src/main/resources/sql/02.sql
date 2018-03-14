INSERT INTO users (username, password) VALUES
  ('user1', '1234'),
  ('user2', '1234');

INSERT INTO categories (title) VALUES
  ('поиск музыкантов'),
  ('продажа инструментов');

INSERT INTO topics (title, user_id, category_id) VALUES
  ('ищу группу', 1, 1),
  ('продам гитару', 2, 1);

INSERT INTO messages (text, user_id, topic_id) VALUES
  ('ищу рок-группу в Минске', 1, 1),
  ('продам свою гитару, Минск', 2, 1);
