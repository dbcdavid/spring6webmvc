drop table if exists beer_order_line;
drop table if exists beer_order;

create table beer_order (
    id varchar(36) not null,
    created_date datetime(6) default null,
    customer_ref varchar(255) default null,
    last_modified_date datetime(6) default null,
    version bigint default null,
    customer_id varchar(36) default null,
    primary key (id),
    CONSTRAINT FOREIGN KEY (customer_id) REFERENCES customer (id)
) engine=InnoDB;

create table beer_order_line (
    id varchar(36) not null,
    beer_id varchar(36) default null,
    created_date datetime(6) default null,
    last_modified_date datetime(6) default null,
    order_quantity int default null,
    quantity_allocated int default null,
    version bigint default null,
    beer_order_id varchar(36) not null,
    primary key (id),
    CONSTRAINT FOREIGN KEY (beer_order_id) REFERENCES beer_order (id),
    CONSTRAINT FOREIGN KEY (beer_id) REFERENCES beer (id)
) engine=InnoDB;