DROP TABLE IF EXISTS TODO;
DROP SEQUENCE IF EXISTS seq_todo;

CREATE SEQUENCE seq_todo START 1;

CREATE TABLE IF NOT EXISTS TODO (
  seq integer not null DEFAULT nextval('seq_todo'),
  title varchar(100) not null,
  contents varchar(2000) not null,
  createAt TIMESTAMP default now(),
  deadline TIMESTAMP,
  priority integer not null DEFAULT 0,
  isDone boolean DEFAULT false,
  hasDeadline boolean DEFAULT false,
  constraint pk_todo primary key (seq)
);