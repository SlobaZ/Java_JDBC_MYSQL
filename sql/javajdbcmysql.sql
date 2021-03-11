CREATE USER IF NOT EXISTS jwduser IDENTIFIED BY 'pass';

DROP SCHEMA IF EXISTS javajdbcmysql;
CREATE SCHEMA javajdbcmysql DEFAULT CHARACTER SET utf8;
USE javajdbcmysql;

GRANT ALL ON javajdbcmysql.* TO 'jwduser'@'%';

FLUSH PRIVILEGES;

CREATE TABLE korisnik (
	korisnik_id INT AUTO_INCREMENT,
    ime VARCHAR(20) NOT NULL,
    prezime VARCHAR(20) NOT NULL,
    grad VARCHAR(20) NOT NULL,
    drzava VARCHAR(20) NOT NULL,
    PRIMARY KEY(korisnik_id)
);

CREATE TABLE proizvod (
	proizvod_id INT AUTO_INCREMENT,
    naziv VARCHAR(20) NOT NULL,
    datum DATE NOT NULL,
    cena DECIMAL(10,2) NOT NULL,
    PRIMARY KEY(proizvod_id)
);

CREATE TABLE kupovina(
	korisnik_id INT NOT NULL,
	proizvod_id INT NOT NULL,
    kolicina INT NOT NULL,
    datumvreme DATETIME NOT NULL,
    PRIMARY KEY (korisnik_id, proizvod_id),
    FOREIGN KEY (korisnik_id) REFERENCES korisnik(korisnik_id)
		ON DELETE RESTRICT,
    FOREIGN KEY (proizvod_id) REFERENCES proizvod(proizvod_id)
		ON DELETE RESTRICT
);

INSERT INTO korisnik (ime,prezime,grad,drzava) VALUES ("Jovan","Ilic","Beograd","Srbija");
INSERT INTO korisnik (ime,prezime,grad,drzava) VALUES ("Dejan","Savic","Pariz","Francuska");
INSERT INTO korisnik (ime,prezime,grad,drzava) VALUES ("Vasa","Milic","Berlin","Nemacka");


INSERT INTO proizvod (naziv,datum,cena) VALUES ("jabuke",'2015-10-01',110.5);
INSERT INTO proizvod (naziv,datum,cena) VALUES ("kruske",'2016-08-01',160.5);
INSERT INTO proizvod (naziv,datum,cena) VALUES ("breskve",'2017-05-01',230.5);

INSERT INTO kupovina (proizvod_id,korisnik_id,kolicina,datumvreme) VALUES (1,2,10,'2020-01-01 15:00:00');
INSERT INTO kupovina (proizvod_id,korisnik_id,kolicina,datumvreme) VALUES (2,3,20,'2020-02-02 13:30:00');
INSERT INTO kupovina (proizvod_id,korisnik_id,kolicina,datumvreme) VALUES (3,1,30,'2020-03-03 11:45:00');
INSERT INTO kupovina (proizvod_id,korisnik_id,kolicina,datumvreme) VALUES (1,3,15,'2020-04-04 09:15:00');
