-- Payment Service Migration V1
CREATE TABLE IF NOT EXISTS payments (
                                        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    unit_id UUID,
    tenant_id UUID,
    pm_account_id UUID NOT NULL,
    amount DECIMAL(19,2) NOT NULL,
    amount_expected DECIMAL(19,2),
    mpesa_receipt_number VARCHAR(100) NOT NULL UNIQUE,
    transaction_date TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'UNMATCHED',
    reconciled_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
    );

CREATE INDEX idx_payments_mpesa_receipt ON payments(mpesa_receipt_number);
CREATE INDEX idx_payments_pm_account_id ON payments(pm_account_id);
CREATE INDEX idx_payments_status ON payments(status);
CREATE INDEX idx_payments_transaction_date ON payments(transaction_date);