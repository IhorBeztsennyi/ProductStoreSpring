CREATE TABLE vendor (
 id   uuid NOT NULL,
 name VARCHAR(100) NOT NULL,
 PRIMARY KEY (id)
);

CREATE TABLE product (
 id    uuid NOT NULL,
 name  VARCHAR(100) NOT NULL,
 price numeric NOT NULL,
 vendor_id  uuid NOT NULL,
 PRIMARY KEY (id),
 FOREIGN KEY (vendor_id) REFERENCES vendor (id)
 ON DELETE CASCADE
);

CREATE TABLE role (
 id uuid NOT NULL,
 name VARCHAR(50) NOT NULL,
 PRIMARY KEY (id)
);

CREATE TABLE users (
 id uuid NOT NULL,
 email VARCHAR(100) NOT NULL,
 role_id uuid NOT NULL,
 password   VARCHAR(100) NOT NULL,
 first_name VARCHAR(100) NOT NULL,
 last_name  VARCHAR(100) NOT NULL,
 PRIMARY KEY (id),
 FOREIGN KEY (role_id) REFERENCES role (id)
 ON DELETE CASCADE
);
