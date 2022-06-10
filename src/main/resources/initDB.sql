CREATE TABLE vendor (
 id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
 name VARCHAR(100) NOT NULL
);

CREATE TABLE product (
 id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
 name  VARCHAR(100) NOT NULL,
 price numeric NOT NULL,
 vendor_id  uuid NOT NULL,
 FOREIGN KEY (vendor_id) REFERENCES vendor (id)
 ON DELETE CASCADE
);

CREATE TABLE role (
 id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
 name VARCHAR(50) NOT NULL
);

CREATE TABLE users (
 id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
 email VARCHAR(100) NOT NULL,
 role   VARCHAR(100) NOT NULL,
 password   VARCHAR(100) NOT NULL,
 first_name VARCHAR(100) NOT NULL,
 last_name  VARCHAR(100) NOT NULL,
 UNIQUE(email)
);
