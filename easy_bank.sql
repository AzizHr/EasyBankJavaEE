-- create type called [paymentType] as an enum --
create type paymentType as enum ('DEPOSIT', 'WITHDRAWAL', 'PAYMENT');
-- create type called [paymentType] as an enum --
create type accountStatus as enum ('ACTIVE', 'FROZEN', 'CLOSED');
-- create type called [demandStatus] as an enum --
create type demandStatus as enum ('ACCEPTED', 'PENDING', 'REJECTED');


-- create table [employee] --
create table employee (
		code varchar(20) primary key,
		first_name varchar(40) not null,
		last_name varchar(40) not null,
		birth_date date not null,
		phone_number varchar(20),
		email varchar(30) not null
);

-- create table [client] --
create table client (
		code varchar(20) primary key,
		first_name varchar(40) not null,
		last_name varchar(40) not null,
		birth_date date not null,
		phone_number varchar(20),
		address varchar(40) not null,
		employee_code varchar(20) not null,
		foreign key (employee_code) references employee (code) on delete cascade on update cascade
);

-- create table [agency] --
create table agency (

	code varchar(20) primary key,
	name varchar(30) not null,
	address varchar(60) not null,
	phone_number varchar(20) not null
	
);

-- create table [recruitment_history] --
create table recruitment_history (

	agency_code varchar(20) not null,
	employee_code varchar(20) not null,
	recruited_at date not null,
	finished_at date not null,
	foreign key (agency_code) references agency (code) on delete cascade on update cascade,
	foreign key (employee_code) references employee (code) on delete cascade on update cascade
	
);

-- create table [current_account] --
create table current_account (
	
	number varchar(20) PRIMARY KEY,
	balance real not null,
	created_at date not null,
	status accountStatus default 'ACTIVE',
    overdraft real not null,
	agency_code varchar(20) not null,
	client_code varchar(20) not null,
	employee_code varchar(20) not null,
	foreign key (agency_code) references agency (code) on delete cascade on update cascade,
	foreign key (client_code) references client (code) on delete cascade on update cascade,
	foreign key (employee_code) references employee (code) on delete cascade on update cascade

);

-- create table [saving_account] --
create table saving_account (
	
	number varchar(20) PRIMARY KEY,
	balance real not null,
	created_at date not null,
	status accountStatus default 'ACTIVE',
    interest real not null,
	agency_code varchar(20) not null,
	client_code varchar(20) not null,
	employee_code varchar(20) not null,
	foreign key (agency_code) references agency (code) on delete cascade on update cascade,
	foreign key (client_code) references client (code) on delete cascade on update cascade,
	foreign key (employee_code) references employee (code) on delete cascade on update cascade
);

-- create table [operation] --
create table operation (
	number varchar(20) primary key,
	created_at timestamp not null,
	price real not null,
	payment paymentType not null,
	employee_code varchar(20) not null,
	current_account_number varchar(20),
	saving_account_number varchar(20),
	foreign key (employee_code) references employee (code) on delete cascade on update cascade,
	foreign key (current_account_number) references current_account (number) on delete cascade on update cascade,
	foreign key (saving_account_number) references saving_account (number) on delete cascade on update cascade
);

-- create table [mission] --
create table mission ( 
	
 	code varchar(20) primary key, 
 	name varchar(40)  not null, 
 	description varchar(100)  not null
	
 );
 
 -- create table [mission_history] --
create table mission_history (
	
	employee_code varchar(20) not null,
	mission_code varchar(20) not null,
	started_at date not null,
	ended_at date not null,
	foreign key (employee_code) references employee (code) on delete cascade on update cascade,
	foreign key (mission_code) references mission (code) on delete cascade on update cascade,
	primary key (employee_code, mission_code, started_at)
	
);

-- create table [payment] --
create table payment (

	number varchar(20) primary key,
	created_at timestamp not null,
	price real not null,
	payment paymentType default 'PAYMENT',
	source_current_account_number varchar(20) null,
	destination_current_account_number varchar(20) null,
	source_saving_account_number varchar(20) null,
	destination_saving_account_number varchar(20) null,
	foreign key (source_current_account_number) references current_account (number) on delete cascade on update cascade,
	foreign key (destination_current_account_number) references current_account (number) on delete cascade on update cascade,
	foreign key (source_saving_account_number) references saving_account (number) on delete cascade on update cascade,
	foreign key (destination_saving_account_number) references saving_account (number) on delete cascade on update cascade
	
);

-- create table [demand] --
create table demand (

	number serial,
	create_at date not null,
	status varchar(255) not null,
	price real not null,
	duration int not null,
	paid_monthly real not null,
	remarks text null,
	client_code varchar(20) not null,
	agency_code varchar(20) not null,
	foreign key (client_code) references client (code) on delete cascade on update cascade,
	foreign key (agency_code) references agency (code) on delete cascade on update cascade

);
