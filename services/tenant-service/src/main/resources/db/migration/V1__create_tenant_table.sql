-- Tenant Service Migration V1
CREATE TABLE IF NOT EXISTS tenants (
                                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID,
    pm_account_id UUID NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL UNIQUE,
    unit_id UUID,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
    );

CREATE INDEX idx_tenants_email ON tenants(email);
CREATE INDEX idx_tenants_phone ON tenants(phone);
CREATE INDEX idx_tenants_pm_account_id ON tenants(pm_account_id);