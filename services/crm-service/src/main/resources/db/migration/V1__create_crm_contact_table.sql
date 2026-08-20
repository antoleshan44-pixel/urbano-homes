-- CRM Service Migration V1
CREATE TABLE IF NOT EXISTS crm_contacts (
                                            id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    pm_account_id UUID NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL UNIQUE,
    type VARCHAR(50) NOT NULL,
    company VARCHAR(255),
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
    );

CREATE INDEX idx_crm_contacts_pm_account_id ON crm_contacts(pm_account_id);
CREATE INDEX idx_crm_contacts_email ON crm_contacts(email);
CREATE INDEX idx_crm_contacts_phone ON crm_contacts(phone);