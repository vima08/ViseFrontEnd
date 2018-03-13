CREATE TABLE object
(
    object_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(40),
    description VARCHAR(40),
    parent_id INT,
    CONSTRAINT Object_Object_object_id_fk FOREIGN KEY (parent_id) REFERENCES Object (object_id)
);
CREATE UNIQUE INDEX Object_object_id_uindex ON Object (object_id);