use banking;

create table if not exists loans(
    loan_id int not null primary key auto_increment,
    customer varchar(250) not null,
    amount double not null,
    status smallint not null,
    type smallint not null
);