create database if not exists aguatalv2;

use aguatalv2;


create table if not exists usuario(
	codUsuario int auto_increment,
    usuario varchar(50),
    pass varchar(50),
    nombreUsuario varchar(50),
    apellidosUsuario varchar(100),
    email varchar(100),
    direccion varchar(100),
    
    primary key (codUsuario)
);

insert into usuario values (null,'moreno27','moreno27','Alejandro','Moreno Martin','privado@gmail.com','mi casa'),(null,'vico','1234','Vico','Super Star','vico@apruebame.com','Mar Alboran'),(null,'jonivasilon','elmitico123','Joni','Chacon Mena','chaconi@gmail.com','su casa');



create table if not exists dispensadora(
	codDispensadora int auto_increment,
    tamanio enum ('grande','pequenia','mediana'),
    
    primary key (codDispensadora)
);
 insert into dispensadora values (null,'pequenia'),(null,'mediana'),(null,'grande');



create table if not exists suscripcion(
    codSuscripcion int auto_increment,
    codUsuario int,
    precioMensual decimal(5,2),
    
    primary key (codSuscripcion),
    
    foreign key (codUsuario) references usuario(codUsuario)
		on update cascade on delete cascade
);

insert into suscripcion values (null,1,8.99),(null,2,5.99),(null,3,6.99);



create table if not exists pedido(
	codPedido int auto_increment,
    codUsuario int,
    codDispensadora int,
    
    primary key (codPedido),
    
    foreign key (codUsuario) references usuario(codUsuario)
		on update cascade on delete cascade,
        
	foreign key (codDispensadora) references dispensadora(codDispensadora)
		on update cascade on delete no action
);

insert into pedido values (null,1,1),(null,1,2),(null,2,3);

