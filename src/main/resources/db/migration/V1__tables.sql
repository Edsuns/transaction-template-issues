CREATE TABLE child
(
    id      INT AUTO_INCREMENT NOT NULL,
    name    VARCHAR(255)       NOT NULL,
    version INT                NOT NULL,
    CONSTRAINT pk_child PRIMARY KEY (id)
);

CREATE TABLE parent
(
    id       INT AUTO_INCREMENT NOT NULL,
    child_id INT                NOT NULL,
    CONSTRAINT pk_parent PRIMARY KEY (id)
);

ALTER TABLE parent
    ADD CONSTRAINT FK_PARENT_ON_CHILD FOREIGN KEY (child_id) REFERENCES child (id);