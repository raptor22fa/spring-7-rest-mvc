drop table if exists beer_order_line;
drop table if exists beer_order;

create table beer_order
(
    id                 varchar(36)  not null,
    created_date       timestamp(6) default null,
    customer_ref       varchar(255) default null,
    last_modified_date timestamp(6) default null,
    version            bigint       default null,
    customer_id        varchar(36)  default null,
    primary key (id),
    constraint fk_beer_order_customer foreign key (customer_id) references customer (id)
);

create table beer_order_line
(
    id                 varchar(36)  not null,
    beer_id            varchar(36)  default null,
    created_date       timestamp(6) default null,
    last_modified_date timestamp(6) default null,
    order_quantity     integer      default null,
    quantity_allocated integer      default null,
    version            bigint       default null,
    beer_order_id      varchar(36)  default null,
    primary key (id),
    constraint fk_beer_order_line_order foreign key (beer_order_id) references beer_order (id),
    constraint fk_beer_order_line_beer foreign key (beer_id) references beer (id)
);

