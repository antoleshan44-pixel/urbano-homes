-- Add version column for optimistic locking
ALTER TABLE auth_users ADD COLUMN version BIGINT NOT NULL DEFAULT 0;

-- Update existing rows
UPDATE auth_users SET version = 0 WHERE version IS NULL;