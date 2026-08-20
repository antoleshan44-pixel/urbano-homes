-- Property Service Migration V2
CREATE TABLE IF NOT EXISTS units (
                                     id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    property_id UUID NOT NULL,
    label VARCHAR(255) NOT NULL,
    bedrooms INTEGER DEFAULT 0,
    rent_amount DECIMAL(19,2),
    status VARCHAR(50) NOT NULL DEFAULT 'DRAFT',
    description TEXT,
    published BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
    );

CREATE INDEX idx_units_property_id ON units(property_id);
CREATE INDEX idx_units_status ON units(status);
CREATE INDEX idx_units_published ON units(published);
CREATE INDEX idx_units_deleted_at ON units(deleted_at);