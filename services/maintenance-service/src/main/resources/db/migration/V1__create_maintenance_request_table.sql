-- Maintenance Service Migration V1
CREATE TABLE IF NOT EXISTS maintenance_requests (
                                                    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    unit_id UUID NOT NULL,
    tenant_id UUID NOT NULL,
    pm_account_id UUID NOT NULL,
    description TEXT NOT NULL,
    photo_url TEXT,
    status VARCHAR(50) NOT NULL DEFAULT 'SUBMITTED',
    resolved_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
    );

CREATE INDEX idx_maintenance_requests_unit_id ON maintenance_requests(unit_id);
CREATE INDEX idx_maintenance_requests_tenant_id ON maintenance_requests(tenant_id);
CREATE INDEX idx_maintenance_requests_pm_account_id ON maintenance_requests(pm_account_id);
CREATE INDEX idx_maintenance_requests_status ON maintenance_requests(status);