CREATE TABLE IF NOT EXISTS payments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    unit_id UUID,
    tenant_id UUID,
    pm_account_id UUID NOT NULL,
    amount DECIMAL(19,2) NOT NULL,
    amount_expected DECIMAL(19,2),
    mpesa_receipt_number VARCHAR(100) NOT NULL UNIQUE,
    status VARCHAR(50) NOT NULL DEFAULT 'UNMATCHED',
    transaction_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);
