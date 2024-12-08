CREATE TABLE IF NOT EXISTS todo_item (
   id UUID NOT NULL,
   created_at date,
   updated_at date,
   title VARCHAR(255),
   content VARCHAR(255),
   completed BOOLEAN NOT NULL,
   CONSTRAINT PK_TODO_ITEM PRIMARY KEY (id)
);