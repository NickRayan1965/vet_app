CREATE TABLE IF NOT EXISTS public."users" (
  "id" bigserial PRIMARY KEY ,
  "username" VARCHAR(50) NOT NULL UNIQUE,
  "password" VARCHAR(100) NOT NULL,
  "names" VARCHAR(200) NOT NULL,
  "surnames" VARCHAR(200) NOT NULL,
  "role" varchar(30) NOT NULL DEFAULT 'CUSTOMER',
  "enabled" BOOLEAN NOT NULL DEFAULT TRUE
);