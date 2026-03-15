-- Grant privileges on public schema
GRANT ALL ON SCHEMA public TO restadmin;
ALTER SCHEMA public OWNER TO restadmin;
-- Note: Table-level privileges must be granted after tables are created.
