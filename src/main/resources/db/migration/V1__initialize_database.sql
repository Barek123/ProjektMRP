
CREATE TABLE product (
    id bigint not null,
    product_name text not null,
    product_foq integer not null,
    product_unit smallint not null,
    product_lt integer not null,
    CONSTRAINT product_pkey PRIMARY KEY (id)
);

CREATE TABLE product_association(
    id bigint not null,
    product_parent bigint not null,
    product bigint not null,
    product_number bigint not null,
    CONSTRAINT product_association_pkey PRIMARY KEY (id),
    FOREIGN KEY (product_parent) REFERENCES product(id),
    FOREIGN KEY (product) REFERENCES product(id)
);

CREATE TABLE purchase_history (
    id bigint not null,
    start_date timestamp  not null,
    end_date timestamp  not null,
    product bigint not null,
    is_order boolean not null,
    netto integer,
    brutto integer,
    CONSTRAINT purchase_history_pkey PRIMARY KEY (id),
    FOREIGN KEY (product) REFERENCES product(id)
);


CREATE TABLE storage (
    id bigint not null,
    product bigint not null,
    value integer,
    CONSTRAINT storage_pkey PRIMARY KEY (id),
    FOREIGN KEY (product) REFERENCES product(id)
);

CREATE TABLE storage_update (
    id bigint not null,
    product bigint not null,
    date timestamp  not null,
    number integer not null,
    CONSTRAINT storage_update_pkey PRIMARY KEY (id),
    FOREIGN KEY (product) REFERENCES product(id)
);