create sequence if not exists project_sequence start 1 increment 1;
create sequence if not exists time_record_sequence start 1 increment 1;
create table IF NOT EXISTS user_account (
    id varchar(255) not null,
    created_at timestamp,
    email varchar(255),
    login varchar(255),
    name varchar(255),
    password varchar(255),
    updated_at timestamp,
    primary key (id)
);
create table IF NOT EXISTS project (
    id int8 not null,
    description varchar(255),
    title varchar(255),
    primary key (id)
);
create table IF NOT EXISTS projects_users (
    project_id int8 not null,
    user_id varchar(255) not null,
    constraint FKcy4byk05dsrkmdauo6vyrbd8i foreign key (user_id) references user_account,
    constraint FKntbkomdaice76nii801lnrg6t foreign key (project_id) references project
);
create table IF NOT EXISTS time_record (
    id int8 not null,
    ended_at timestamp,
    started_at timestamp,
    project_id int8,
    user_id varchar(255),
    primary key (id),
    constraint FK8uyo6pf7b2gjojjn8n4kxw8cs foreign key (project_id) references project,
    constraint FKor4fupcqqj199veesr94mn8pl foreign key (user_id) references user_account
);
create table IF NOT EXISTS project_time_records (
    project_entity_id int8 not null,
    time_records_id int8 not null,
    constraint UK_2ep4acb8avc9i12r5x38a2us1 unique (time_records_id),
    constraint FKdsjs49kpkpf51bavxfvrycd0m foreign key (time_records_id) references time_record,
    constraint FK2vf1g8it1g0nrbyftr2bp33kr foreign key (project_entity_id) references project
);