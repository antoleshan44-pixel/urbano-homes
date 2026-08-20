-- Auth Service Migration V2
CREATE TABLE IF NOT EXISTS pm_accounts (
                                           id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    company_name VARCHAR(255) NOT NULL,
    service_option VARCHAR(50) NOT NULL DEFAULT 'SOFTWARE_ONLY',
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
    );

CREATE INDEX idx_pm_accounts_company_name ON pm_accounts(company_name);