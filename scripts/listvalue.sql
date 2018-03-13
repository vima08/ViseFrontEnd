CREATE TABLE listvalue
(
    list_value_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    number INT,
    value VARCHAR(80),
    attr_id INT,
    CONSTRAINT listvalues_attributes_attr_id_fk FOREIGN KEY (attr_id) REFERENCES attribute (attr_id)
);
CREATE UNIQUE INDEX listvalue_list_value_id_uindex ON listvalue (list_value_id);