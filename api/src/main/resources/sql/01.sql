CREATE TABLE users (
  id       BIGSERIAL PRIMARY KEY,
  username VARCHAR(256),
  password VARCHAR(256)
);

CREATE TABLE categories (
  id    BIGSERIAL PRIMARY KEY,
  title VARCHAR(256)
);

CREATE TABLE topics (
  id          BIGSERIAL PRIMARY KEY,
  title       VARCHAR(256),
  user_id     BIGSERIAL NOT NULL,
  category_id BIGSERIAL NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE messages (
  id       BIGSERIAL PRIMARY KEY,
  text     VARCHAR(256),
  user_id  BIGSERIAL NOT NULL,
  topic_id BIGSERIAL NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (topic_id) REFERENCES topics (id)
);