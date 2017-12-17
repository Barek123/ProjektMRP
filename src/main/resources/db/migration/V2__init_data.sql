INSERT INTO product (id, product_foq, product_lt, product_name, product_unit)
VALUES (-1, 10, 1, 'Deska',2);
INSERT INTO product (id, product_foq, product_lt, product_name, product_unit)
VALUES (-5, 20, 1, 'Noga od stolu',2);
INSERT INTO product (id, product_foq, product_lt, product_name, product_unit)
VALUES (-7, 1000, 1, 'Bejca biala',4);
INSERT INTO product (id, product_foq, product_lt, product_name, product_unit)
VALUES (-9, 200, 1, 'Wkret',2);
INSERT INTO product (id, product_foq, product_lt, product_name, product_unit)
VALUES (-16, 10, 10, 'Stol',2);
INSERT INTO product (id, product_foq, product_lt, product_name, product_unit)
VALUES (-18, 10, 2, 'Blat',2);

INSERT INTO product_association (id, product_number, product, product_parent)
VALUES (-15, 2, -1, -5);
INSERT INTO product_association (id, product_number, product, product_parent)
VALUES (-20, 1, -18, -16);
INSERT INTO product_association (id, product_number, product, product_parent)
VALUES (-21, 10, -9, -16);
INSERT INTO product_association (id, product_number, product, product_parent)
VALUES (-22, 10, -7, -16);
INSERT INTO product_association (id, product_number, product, product_parent)
VALUES (-23, 4, -5, -16);
INSERT INTO product_association (id, product_number, product, product_parent)
VALUES (-24, 4, -1, -18);

INSERT INTO storage (id, value, product)
VALUES (-2, 0, -1);
INSERT INTO storage (id, value, product)
VALUES (-6, 40, -5);
INSERT INTO storage (id, value, product)
VALUES (-8, 10000, -7);
INSERT INTO storage (id, value, product)
VALUES (-10, 100, -9);
INSERT INTO storage (id, value, product)
VALUES (-17, 10, -16);
INSERT INTO storage (id, value, product)
VALUES (-19, 100, -18);
INSERT INTO storage_update (id, date, number, product)
VALUES (-26,'2017-06-16 00:00:00', 5, -1);
INSERT INTO purchase_history (id, brutto, end_date, is_order, netto,  start_date, product )
VALUES(-25, 5, '2017-06-16 00:00:00', TRUE, 5, '2017-06-15 00:00:00', -1);