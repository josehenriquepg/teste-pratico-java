CREATE DATABASE products_db
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'pt_BR.UTF-8'
    LC_CTYPE = 'pt_BR.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    product_code VARCHAR(20) NOT NULL,
    description VARCHAR(100),
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_product_code ON products(product_code);
CREATE INDEX idx_description ON products(description);

INSERT INTO products (product_code, description) VALUES
('34','FRALDAS'),
('20','COTONETES'),
('12','DIPIRONA'),
('08','XAROPE');

SELECT * FROM products ORDER BY description;