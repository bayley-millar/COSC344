DROP TABLE car;
DROP TABLE transactions;
DROP TABLE parts;
DROP TABLE account;
DROP TABLE appointment;
DROP TABLE staff;
DROP TABLE customer;



CREATE TABLE customer
       (c_fname VARCHAR2(15) NOT NULL,
        c_lname VARCHAR2(15) NOT NULL,
        c_ph_no NUMBER(10) NOT NULL,
        c_address VARCHAR2(30),
        c_gender CHAR,
        c_id CHAR(9) PRIMARY KEY);

INSERT INTO customer VALUES
       ('Bilbo', 'Baggins', 0123456789, 'Hobbit Hole 4a', 'M', '666666666');

INSERT INTO customer VALUES
       ('Clark', 'Kent', 8657456326, 'Krypton', 'M', '111111111');

INSERT INTO customer VALUES
       ('Helen', 'Clark', 0278657435, '1b Mount Eden Road, Auckland', 'F', '555555555');

INSERT INTO customer VALUES
       ('BC', 'Bass', 9876234123, '125 BC Street', 'M', '999999999');

INSERT INTO customer VALUES
       ('Wendy', 'McDonalds', 0800434434, '898 Curlyhoods Road, Otara', 'F', '444444444')

CREATE TABLE staff
       (s_fname VARCHAR2(15) NOT NULL,
        s_lname VARCHAR2(15) NOT NULL,
        s_id CHAR(9) PRIMARY KEY,
        salary NUMBER(6),
        s_ph_no NUMBER(10) NOT NULL,
        s_address VARCHAR2(30),
	date_of_birth DATE,
        age INT);

INSERT INTO staff VALUES
       ('Reuben', 'Poor','987654321', 100, 1231231234, '123 BC Street', TO_DATE('12-12-1996',
        'DD-MM-YYYY'), 20);

INSERT INTO staff VALUES
       ('Joe', 'Penn','123123123', 900000, 0278567432, '698 Mary Lane', TO_DATE('12-12-1964',
        'DD-MM-YYYY'), 20);

INSERT INTO staff VALUES
       ('Bayley', 'Pillar','875645342', 850000, 1341231234, 'Invercargill', TO_DATE('23-12-1970',
        'DD-MM-YYYY'), 20);

INSERT INTO staff VALUES
       ('Blake', 'Brah','777654421', 10, 8887774321, '1 Fivefoot-fiveinch Street', TO_DATE('23-12-1999',
        'DD-MM-YYYY'), 20);

INSERT INTO staff VALUES
       ('George', 'F-Skyscraper','987000001', 50000, 6574869853, 'The Moon', TO_DATE('25-11-1980',
        'DD-MM-YYYY'), 20);

CREATE TABLE appointment
       (pickup_time TIMESTAMP NOT NULL,
        work_to_do VARCHAR2(30),
        ap_id CHAR(9) PRIMARY KEY,
        time_booked_for TIMESTAMP,
        ap_date DATE);

INSERT INTO appointment VALUES
       (TO_TIMESTAMP('10-SEP-0214:10:10.123000','DD-MON-RRHH24:MI:SS.FF'),
         'oil change', '555555555',
         TO_TIMESTAMP('10-SEP-0214:10:32.123000','DD-MON-RRHH24:MI:SS.FF'),
         TO_DATE('25-12-2015','DD-MM-YYYY'));

         

CREATE TABLE account
       (acc_id CHAR(9) PRIMARY KEY,
        ca_id CHAR(9) NOT NULL REFERENCES customer(c_id),
        balance FLOAT,
        last_visit DATE);
INSERT INTO account VALUES
('888888888', '666666666', 543.89, TO_DATE('14-04-1999',
 'DD-MM-YYYY'));

INSERT INTO account VALUES
     ('888888881', '111111111', 5.89, TO_DATE('23-09-2015',
'DD-MM-YYYY'));

INSERT INTO account VALUES
('888888882', '555555555', 89.50, TO_DATE('18-10-2015',
'DD-MM-YYYY'));

INSERT INTO account VALUES
('888888883', '999999999', 1205.80, TO_DATE('01-01-2015',
'DD-MM-YYYY'));



CREATE TABLE parts
       (part_id INT PRIMARY KEY,
        cost FLOAT,
        part_name VARCHAR2(30),
        description VARCHAR2(30),
        quantity INT);

INSERT INTO parts VALUES
       (42, 112.99, 'XXXL FEKOFF Spoiler X-WING_9', 'Rear spoiler wing', 2);

CREATE TABLE transactions
       (t_date DATE,
        t_type CHAR(2),
        amount FLOAT,
        transaction_no CHAR(9),
        t_acc_id CHAR(9) REFERENCES account(acc_id),
        PRIMARY KEY(t_acc_id, transaction_no));

INSERT INTO transactions VALUES
(TO_DATE('14-02-1998','DD-MM-YYYY'), 'CH', 1200.80,983642313 ,'888888888');
INSERT INTO transactions VALUES
(TO_DATE('18-02-1999','DD-MM-YYYY'), 'CH', 80,983642311 ,'888888881');
INSERT INTO transactions VALUES
(TO_DATE('14-09-1998','DD-MM-YYYY'), 'CH',     .80,983642312 ,'888888882');
INSERT INTO transactions VALUES
(TO_DATE('19-07-1995','DD-MM-YYYY'), 'CH', 1200.80,983642314 ,'888888883');


CREATE TABLE car
       (car_id CHAR(9) PRIMARY KEY,
        make VARCHAR2(15) NOT NULL,
        model VARCHAR2(15),
        mk_year VARCHAR2(15),
		c_id CHAR(9) REFERENCES customer(c_id));

INSERT INTO car VALUES
       ('656565656', 'MINI', 'Cooper', '1998', '666666666');
INSERT INTO car VALUES
       ('656565658', 'HATCHBACK', 'skyline', '1998', '666666666');
INSERT INTO car VALUES
       ('656565654', 'RANGEROVER', '4wd', '1998', '666666666');
INSERT INTO car VALUES
       ('653565654', 'FORD', 'lasers', '1998', '555555555');

COMMIT;
