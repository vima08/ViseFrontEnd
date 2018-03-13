CREATE TABLE param
(
    param_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    object_id INT,
    attr_id INT,
    value VARCHAR(80),
    CONSTRAINT Param_object_object_id_fk FOREIGN KEY (object_id) REFERENCES object (object_id),
    CONSTRAINT Param_attribute_attr_id_fk FOREIGN KEY (attr_id) REFERENCES attribute (attr_id)
);
CREATE UNIQUE INDEX Param_param_id_uindex ON param (param_id);