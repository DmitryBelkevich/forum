CREATE TABLE users (
  id       BIGSERIAL PRIMARY KEY,
  username VARCHAR(256),
  password VARCHAR(256)
);

CREATE TABLE topics (
  id      BIGSERIAL PRIMARY KEY,
  title   VARCHAR(256),
  user_id BIGSERIAL NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE message (
  id       BIGSERIAL PRIMARY KEY,
  text     VARCHAR(256),
  user_id  BIGSERIAL NOT NULL,
  topic_id BIGSERIAL NOT NULL,
  FOREIGN KEY (topic_id) REFERENCES topics (id)
);