-- Property Service Migration V1
CREATE TABLE IF NOT EXISTS properties (
                                          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    pm_account_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
    );

CREATE INDEX idx_properties_pm_account_id ON properties(pm_account_id);
CREATE INDEX idx_properties_deleted_at ON properties(deleted_at);