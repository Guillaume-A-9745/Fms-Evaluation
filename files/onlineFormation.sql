-- ------------------------------------------------------------------------------
-- - Reconstruction de la base de données                                     ---
-- ------------------------------------------------------------------------------
DROP DATABASE IF EXISTS OnlineFormation;
CREATE DATABASE OnlineFormation;
USE OnlineFormation;

-- -----------------------------------------------------------------------------
-- - Construction de la table des utilisateurs                               ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Users (
	IdUser				int(4)		PRIMARY KEY AUTO_INCREMENT,
	Login				varchar(20)	NOT NULL UNIQUE,
	Password			varchar(20)	NOT NULL
) ENGINE = InnoDB;

INSERT INTO T_Users (IdUser, Login, Password) VALUES ( 1, 'Curie' ,	'Marie' );
INSERT INTO T_Users (IdUser, Login, Password) VALUES ( 2, 'Cesar',	'Jules' );
INSERT INTO T_Users (IdUser, Login, Password) VALUES ( 3, 'Shakespeare', 'William' );
INSERT INTO T_Users (IdUser, Login, Password) VALUES ( 4, 'Einstein', 'Albert' );
INSERT INTO T_Users (IdUser, Login, Password) VALUES ( 5, 'Ghandi', 'Indira' );

-- -----------------------------------------------------------------------------
-- - Construction de la table des clients	                                 ---
-- -----------------------------------------------------------------------------

CREATE TABLE T_Customers (
	IdCustomer				int(4)		PRIMARY KEY AUTO_INCREMENT,
	Name					varchar(30)	NOT NULL,
	FirstName				varchar(30)	NOT NULL,
	Email 					varchar(30)	NOT NULL unique,
	Phone 					varchar(20)	,
	Address					varchar(50)	,
	IdUser					int(4)		NOT NULL,
	FOREIGN KEY (IdUser)	REFERENCES T_Users(IdUser)
) ENGINE = InnoDB;


-- -----------------------------------------------------------------------------
-- - Construction de la table des catégories de formations
-- -----------------------------------------------------------------------------

CREATE TABLE T_Categories (
	IdCategory 				INT(4) 		 PRIMARY KEY AUTO_INCREMENT,
	NameCategory 			VARCHAR(30)  NOT NULL,
	Description 			VARCHAR(100) NOT NULL
) ENGINE = InnoDB;

insert into T_Categories (NameCategory, Description) values ('Developpement','Developpemnt front ou developpement back' );
insert into T_Categories (NameCategory, Description) values ('Cybersecurite','La securite informatique' );
insert into T_Categories (NameCategory, Description) values ('Gestion de projet','Organiser le bon deroulement d’un projet' );
insert into T_Categories (NameCategory, Description) values ('Bureatique','Ameliorer et automatiser les activites de bureau' );
insert into T_Categories (NameCategory, Description) values ('Data','Gestion des donnees numeriques' );
insert into T_Categories (NameCategory, Description) values ('Marketing et Communication','Ensemble des moyens utilises par l entreprise pour s adresser a ses consommateurs' );
insert into T_Categories (NameCategory, Description) values ('Soft Skills','Qualites humaines, relationnelles, emotionnelles et des comportements propres a chacun' );
insert into T_Categories (NameCategory, Description) values ('Systemes et reseaux','La maintenance du systeme informatique' );
insert into T_Categories (NameCategory, Description) values ('Autres','' );

select * from t_categories;

-- -----------------------------------------------------------------------------
-- - Construction de la table des Formations
-- -----------------------------------------------------------------------------

CREATE TABLE T_Formations (
	IdFormation 			INT(4) 		 PRIMARY KEY AUTO_INCREMENT,
	NameFormation			VARCHAR(30)  NOT NULL,
	Description 			VARCHAR(100) NOT NULL,
	Duration 				int(4)  	 NOT NULL,
	FaceToFAce  			boolean 	 NOT NULL,
	Distancial 				boolean 	 NOT NULL,
	UnitaryPrice			float(8)	 NOT NULL,
	IdCategory				int(4), 
	FOREIGN KEY (IdCategory)	REFERENCES T_Categories(IdCategory)
) ENGINE = InnoDB;

insert into T_Formations (NameFormation, Description, Duration, FaceToFAce, Distancial, UnitaryPrice, IdCategory) values ('Java', 'Java SE 8 : Syntaxe & POO', 20, TRUE, FALSE, 799, 1);
insert into T_Formations (NameFormation, Description, Duration, FaceToFAce, Distancial, UnitaryPrice, IdCategory) values ('Java Avance', 'Exceptions, fichiers, Jdbc, thread...', 20, TRUE, TRUE, 799, 1);
insert into T_Formations (NameFormation, Description, Duration, FaceToFAce, Distancial, UnitaryPrice, IdCategory) values ('Spring', 'Spring Core/Mvc/Security', 20, FALSE, TRUE, 799, 1);
insert into T_Formations (NameFormation, Description, Duration, FaceToFAce, Distancial, UnitaryPrice, IdCategory) values ('Php frameworks', 'Symphony', 15, TRUE, FALSE, 599, 1);
insert into T_Formations (NameFormation, Description, Duration, FaceToFAce, Distancial, UnitaryPrice, IdCategory) values ('C#', 'DotNet Core', 20, TRUE, FALSE, 799, 1);
insert into T_Formations (NameFormation, Description, Duration, FaceToFAce, Distancial, UnitaryPrice, IdCategory) values ('VPN et Firewall', 'Proteger vos reseaux des tentatives d intrusion', 3, FALSE, TRUE, 199, 2);
insert into T_Formations (NameFormation, Description, Duration, FaceToFAce, Distancial, UnitaryPrice, IdCategory) values ('Cryptographie', 'Signature electronique, certificats, hacher les mots de passes...', 3, TRUE, TRUE, 199, 2);
insert into T_Formations (NameFormation, Description, Duration, FaceToFAce, Distancial, UnitaryPrice, IdCategory) values ('crum', 'Definir les roles, encadrer le travail d une equipe', 5, TRUE, TRUE, 299, 3);
insert into T_Formations (NameFormation, Description, Duration, FaceToFAce, Distancial, UnitaryPrice, IdCategory) values ('Methodologie en cascade', 'Gestion de projet avec une methodologie classique', 2, TRUE, TRUE, 199, 3);
insert into T_Formations (NameFormation, Description, Duration, FaceToFAce, Distancial, UnitaryPrice, IdCategory) values ('Excel debutant', 'Apprendre les base d excel', 1, TRUE, TRUE, 99, 4);
insert into T_Formations (NameFormation, Description, Duration, FaceToFAce, Distancial, UnitaryPrice, IdCategory) values ('Excel avance', 'Apprendre les fonctions avancees d excel', 3, TRUE, TRUE, 199, 4);
insert into T_Formations (NameFormation, Description, Duration, FaceToFAce, Distancial, UnitaryPrice, IdCategory) values ('Machine Learning', 'Decouvrer le machin learning', 10, TRUE, TRUE, 499, 5);
insert into T_Formations (NameFormation, Description, Duration, FaceToFAce, Distancial, UnitaryPrice, IdCategory) values ('L intelligence artificielle', 'Decouvrer l intelligence artificielle', 5, TRUE, TRUE, 299, 5);
insert into T_Formations (NameFormation, Description, Duration, FaceToFAce, Distancial, UnitaryPrice, IdCategory) values ('Google analytics', 'collecter et analyser les donniees utilisateurs', 2, TRUE, TRUE, 299, 6);
insert into T_Formations (NameFormation, Description, Duration, FaceToFAce, Distancial, UnitaryPrice, IdCategory) values ('Facebook Ads', 'collecter et analyser les donnees utilisateurs', 2, TRUE, TRUE, 299, 6);
insert into T_Formations (NameFormation, Description, Duration, FaceToFAce, Distancial, UnitaryPrice, IdCategory) values ('Gerer les conflits', 'Les cles pour etablir un diagnostic de la situation et la resoudre', 3, TRUE, TRUE, 399, 7);
insert into T_Formations (NameFormation, Description, Duration, FaceToFAce, Distancial, UnitaryPrice, IdCategory) values ('Developper votre resilience', 'Faire face aux changements et a continuer a se developper', 3, TRUE, TRUE, 399, 7);
insert into T_Formations (NameFormation, Description, Duration, FaceToFAce, Distancial, UnitaryPrice, IdCategory) values ('reseau TCP/IP', 'Concevez votre reseau TCP/ip', 4, TRUE, FALSE, 499, 8);
insert into T_Formations (NameFormation, Description, Duration, FaceToFAce, Distancial, UnitaryPrice, IdCategory) values ('Docker', 'Optimiser votre deploiment en creant des conteneurs avec Docker', 6, FALSE, TRUE, 699, 8);
insert into T_Formations (NameFormation, Description, Duration, FaceToFAce, Distancial, UnitaryPrice, IdCategory) values ('UX Designer', 'Ameliorer l experience utilisateur', 20, FALSE, TRUE, 999, 9);

select * from T_Formations;

-- -----------------------------------------------------------------------------
-- - Construction de la table des commandes
-- -----------------------------------------------------------------------------
CREATE TABLE T_Orders (
	IdOrder				int(4)		PRIMARY KEY AUTO_INCREMENT,
	Amount				float(4)	NOT NULL DEFAULT 0,
	DateOrder 			DATE		NOT NULL DEFAULT NOW(),
	IdCustomer          INT(4)   	NOT NULL,
	FOREIGN KEY(IdCustomer) REFERENCES T_Customers(IdCustomer)
) ENGINE = InnoDB;


-- -----------------------------------------------------------------------------
-- - Construction de la table des formations commandées
-- -----------------------------------------------------------------------------
CREATE TABLE T_Order_Items (
	IdOrderItem			int(4)	    PRIMARY KEY AUTO_INCREMENT,
	IdFormation         INT(4)      NOT NULL,
	FOREIGN KEY(IdFormation) REFERENCES T_Formations(IdFormation),
	Quantity			FLOAT(4)    NOT NULL DEFAULT 1,
	UnitaryPrice		FLOAT(4)	NOT NULL DEFAULT 0,
	IdOrder             INT(4)      NOT NULL,
	FOREIGN KEY(IdOrder) REFERENCES T_Orders(IdOrder)
) ENGINE = InnoDB;


-- -----------------------------------------------------------------------------
-- - Restreindre l'accès à la bdd
-- -----------------------------------------------------------------------------

-- CREATE USER 'Guillaume'@'Localhost' IDENTIFIED BY 'Wing*993';
-- GRANT ALL PRIVILEGES ON OnlineFormation.* TO 'Guillaume'@'localhost';
-- FLUSH PRIVILEGES;


-- -----------------------------------------------------------------------------
-- - Construction de la table rôle                              ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Roles (
	IdRole	 		int(4) 	 	 PRIMARY KEY AUTO_INCREMENT,
	Role 	    	VARCHAR(30)  NOT NULL,
	Advantage  		FLOAT(4) 	 NOT NULL DEFAULT 0
) ENGINE = InnoDB;

insert into T_Roles (IdRole, Role, Advantage) values (1, 'Client', 0);
insert into T_Roles (IdRole, Role, Advantage) values (2, 'Client premium', 10);
insert into T_Roles (IdRole, Role, Advantage) values (3, 'Client golden', 15);
insert into T_Roles (IdRole, Role, Advantage) values (4, 'Admin', 15);
insert into T_Roles (IdRole, Role, Advantage) values (5, 'SuperAdmin', 15);

select * from T_Roles;

-- -----------------------------------------------------------------------------
-- - Construction de la table user-rôle                              ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_UserRoles (
	IdUser	  		int(4)  	NOT NULL,
	FOREIGN KEY(IdUser) REFERENCES T_Users(IdUser),
	IdRole	 		int(4) 		NOT NULL DEFAULT 1,
	FOREIGN KEY(IdRole) REFERENCES T_Roles(IdRole)
) ENGINE = InnoDB;

INSERT INTO T_UserRoles (IdUser, IdRole) VALUES ( 1, 5);
INSERT INTO T_UserRoles (IdUser, IdRole) VALUES ( 2, 4 );
INSERT INTO T_UserRoles (IdUser, IdRole) VALUES ( 3, 1 );
INSERT INTO T_UserRoles (IdUser, IdRole) VALUES ( 4, 2 );
INSERT INTO T_UserRoles (IdUser, IdRole) VALUES ( 5, 3 );

select * from T_UserRoles;