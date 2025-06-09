--create database licensedb;

create table supplier(
                         supplier_id serial primary key not null,
                         company_name varchar(255) not null,
                         cnpj varchar(255) unique not null);


create table software(
                         software_id serial primary key not null,
                         description varchar(255) not null,
                         url_site varchar(255),
                         software_version varchar(20) not null,
                         supplier_id INTEGER not null,
                         constraint fk_supplier foreign key(supplier_id) references supplier(supplier_id));

create table users(
                      user_id serial primary key not null,
                      name varchar(255) not null,
                      email varchar(255) unique not null,
                      hash_password varchar(255)  not null,
                      role varchar(255) not null);

create table renewal(
                        renewal_id serial primary key not null,
                        frequency int not null,
                        request_date date,
                        response_date date,
                        is_renovate_automatically boolean not null);


create table proof(
                      proof_id serial primary key not null,
                      file_name varchar(255) not null,
                      file_hash bit,
                      file_extension varchar not null,
                      file_content_type varchar,
                      file_url varchar);

create table license(
                        license_id serial primary key not null,
                        due_date date not null,
                        kay varchar not null,
                        renawal_id INTEGER not null,
                        proof_id INTEGER not null,
                        response_id INTEGER not null,
                        constraint fk_renewal foreign key(renawal_id) references renewal(renewal_id),
                        constraint fk_proof foreign key(proof_id) references proof(proof_id),
                        constraint fk_response foreign key(response_id) references users(user_id));
