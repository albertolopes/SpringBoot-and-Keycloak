drop table if exists tb_user cascade;
create table tb_user (
    cod_user bigint generated by default as identity primary key,
    txt_email varchar(255),
    txt_name varchar(255),
    txt_password varchar(255),
    cod_profile bigint,
    txt_image varchar(255)
);