create sequence hibernate_sequence start 1 increment 1;
create table books (
    id int8 not null,
    author varchar(255),
    title varchar(255),
    year_of_writing date,
    primary key (id)
);

create table books_which_users_are_reading (
    book_id int8 not null,
    date_added date,
    user_id int8 not null,
    primary key (book_id, user_id)
);

create table books_which_users_finished (
    book_id int8 not null,
    date_added date,
    user_id int8 not null,
    primary key (book_id, user_id)
);

create table books_which_users_want_to_read (
    book_id int8 not null,
    date_added date,
    user_id int8 not null,
    primary key (book_id, user_id)
);

create table user_role (
    user_id int8 not null,
    roles varchar(255)
);

create table users (
    id int8 not null,
    activation_code varchar(255),
    active boolean not null,
    email varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id)
);

create table users_books (
    book_id int8 not null,
    date_added date,
    user_id int8 not null,
    primary key (book_id, user_id)
);

alter table if exists books_which_users_are_reading
    add constraint FKfdsx3f12uf7nmbh4ku3a11vsu
    foreign key (user_id) references users;

alter table if exists books_which_users_are_reading
    add constraint FKae67m6fm5nhx1nk6jl0ek9ca8
    foreign key (book_id) references books;

alter table if exists books_which_users_finished
    add constraint FKl0mtiyoeicyrnv3dbnm9uqpj
    foreign key (user_id) references users;

alter table if exists books_which_users_finished
    add constraint FKny90hybji421gn7qjr4tdatgo
    foreign key (book_id) references books;

alter table if exists books_which_users_want_to_read
    add constraint FKkgbootmc3qp1kx6dyqy4ps3wf
    foreign key (user_id) references users;

alter table if exists books_which_users_want_to_read
    add constraint FK2ufma1dl14abc8dpq0qjp9h9t
    foreign key (book_id) references books;

alter table if exists user_role
    add constraint FKj345gk1bovqvfame88rcx7yyx
    foreign key (user_id) references users;

alter table if exists users_books
    add constraint FKddv9o0ehcbpn1xdvypcynju0u
    foreign key (user_id) references users;

alter table if exists users_books
    add constraint FKdwwhr7eeuyhofjtfn0xxqimph
    foreign key (book_id) references books;
