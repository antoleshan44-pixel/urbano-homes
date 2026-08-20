-- Payment Service Migration V2
CREATE TABLE IF NOT EXISTS management_fees (
                                               id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    property_id UUID NOT NULL,
    pm_account_id UUID NOT NULL,
    period DATE NOT NULL,
    amount DECIMAL(19,2) NOT NULL,
    invoiced_at TIMESTAMP,
    paid_at TIMESTAMP,
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
    );

CREATE INDEX idx_management_fees_pm_account_id ON management_fees(pm_account_id);
CREATE INDEX idx_management_fees_property_id ON management_fees(property_id);
CREATE INDEX idx_management_fees_status ON management_fees(status);
