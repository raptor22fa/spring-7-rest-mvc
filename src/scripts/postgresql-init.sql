-- Drop and create database
DROP DATABASE IF EXISTS restdb;
CREATE DATABASE restdb WITH ENCODING 'UTF8' LC_COLLATE='en_US.utf8' LC_CTYPE='en_US.utf8' TEMPLATE template0;

-- Drop and create user (role)
DO $$
BEGIN
   IF EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = 'restadmin') THEN
      DROP ROLE restadmin;
   END IF;
END$$;

CREATE ROLE restadmin WITH LOGIN PASSWORD 'password';

-- Grant privileges
GRANT ALL PRIVILEGES ON DATABASE restdb TO restadmin;
