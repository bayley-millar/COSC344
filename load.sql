DROP TABLE staff_phone_number;
DROP TABLE appointment_work_to_do;
DROP TABLE responsible_for;
DROP TABLE customer_phone_number;
DROP TABLE manages;
DROP TABLE car;
DROP TABLE transactions;
DROP TABLE parts;
DROP TABLE appointment;
DROP TABLE account;
DROP TABLE staff;
DROP TABLE customer;



CREATE TABLE customer
       (c_fname VARCHAR2(15) NOT NULL,
        c_lname VARCHAR2(15) NOT NULL,
        c_address VARCHAR2(30),
        c_gender CHAR,
        c_id CHAR(9) PRIMARY KEY);

INSERT INTO customer VALUES
       ('Bilbo', 'Baggins', 'Hobbit Hole 4a', 'M', '666666666');

INSERT INTO customer VALUES
       ('Clark', 'Kent', 'Krypton', 'M', '111111111');

INSERT INTO customer VALUES
       ('Helen', 'Clark', '1b Mount Eden Road, Auckland', 'F', '555555555');

INSERT INTO customer VALUES
       ('BC', 'Bass', '125 BC Street', 'M', '999999999');

INSERT INTO customer VALUES
       ('Wendy', 'McDonalds', '898 Curlyhoods Road, Otara', 'F', '444444444')

CREATE TABLE staff
       (s_fname VARCHAR2(15) NOT NULL,
        s_lname VARCHAR2(15) NOT NULL,
        s_id CHAR(9) PRIMARY KEY,
        salary NUMBER(6),
        s_address VARCHAR2(30),
	date_of_birth DATE,
        age INT);



INSERT INTO staff VALUES
       ('Reuben', 'Poor','987654321', 100, '123 BC Street', TO_DATE('12-12-1996',
        'DD-MM-YYYY'), 20);

INSERT INTO staff VALUES
       ('Joe', 'Penn','123123123', 900000, '698 Mary Lane', TO_DATE('12-12-1964',
        'DD-MM-YYYY'), 20);

INSERT INTO staff VALUES
       ('Bayley', 'Pillar','875645342', 850000, 'Invercargill', TO_DATE('23-12-1970',
        'DD-MM-YYYY'), 20);

INSERT INTO staff VALUES
       ('Blake', 'Brah','777654421', 10, '1 Fivefoot-fiveinch Street', TO_DATE('23-12-1999',
        'DD-MM-YYYY'), 20);

INSERT INTO staff VALUES
       ('George', 'F-Skyscraper','987000001', 50000, 'The Moon', TO_DATE('25-11-1980',
        'DD-MM-YYYY'), 20);


CREATE TABLE account
(acc_id CHAR(9) PRIMARY KEY,
ca_id CHAR(9) NOT NULL REFERENCES customer(c_id),
balance FLOAT,
last_visit DATE
);
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




CREATE TABLE appointment
       (pickup_time TIMESTAMP NOT NULL,
        ap_id CHAR(9) PRIMARY KEY,
        time_booked_for TIMESTAMP,
        ap_date DATE
        );

INSERT INTO appointment VALUES
       (TO_TIMESTAMP('10-SEP-0214:10:10.123000','DD-MON-RRHH24:MI:SS.FF'), '555555555',
         TO_TIMESTAMP('10-SEP-0214:10:32.123000','DD-MON-RRHH24:MI:SS.FF'),
         TO_DATE('25-12-2015','DD-MM-YYYY'));



CREATE TABLE parts
       (part_id INT PRIMARY KEY,
        cost FLOAT,
        part_name VARCHAR2(30),
        description VARCHAR2(30),
        quantity INT
        );

INSERT INTO parts VALUES
       (42, 112.99, 'XXXL FEKOFF Spoiler X-WING_9', 'Rear spoiler wing', 2);

CREATE TABLE transactions
       (t_date DATE,
        t_type CHAR(2),
        amount FLOAT,
	transaction_no CHAR(9),
        account_ID CHAR(9) REFERENCES account(acc_id),
        PRIMARY KEY(account_ID, transaction_no));

INSERT INTO transactions VALUES
(TO_DATE('14-02-1998','DD-MM-YYYY'), 'CH', 1200.80,983642313 ,'888888888');
INSERT INTO transactions VALUES
(TO_DATE('18-02-1999','DD-MM-YYYY'), 'CH', 80,983642311 ,'888888881');
INSERT INTO transactions VALUES
(TO_DATE('14-09-1998','DD-MM-YYYY'), 'CH', 80 ,983642312 ,'888888882');
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


CREATE TABLE manages
       (acc_id CHAR(9) REFERENCES account(acc_id),
       staff_id CHAR(9) REFERENCES staff(s_id),
       PRIMARY KEY(acc_id, staff_id)
       );
INSERT INTO manages VALUES (888888888,987654321);
INSERT INTO manages VALUES (888888881,123123123);
INSERT INTO manages VALUES (888888882,987000001);
INSERT INTO manages VALUES (888888883,777654421);


CREATE TABLE customer_phone_number
       (customer_id CHAR(9) REFERENCES customer(c_id),
       phone_number CHAR(9),
       PRIMARY KEY(customer_id, phone_number)
       );

INSERT INTO customer_phone_number VALUES('666666666', '032728674');
INSERT INTO customer_phone_number VALUES('666666666', '027563729');
INSERT INTO customer_phone_number VALUES('111111111', '027453729');
INSERT INTO customer_phone_number VALUES('555555555', '027563349');
INSERT INTO customer_phone_number VALUES('999999999', '027563429');
INSERT INTO customer_phone_number VALUES('999999999', '036583759');

CREATE TABLE responsible_for
(r_ap_id CHAR(9) REFERENCES appointment(ap_id),
r_s_id CHAR(9) REFERENCES staff(s_id),
PRIMARY KEY(r_ap_id, r_s_id));

INSERT INTO responsible_for VALUES('555555555','987654321');
INSERT INTO responsible_for VALUES('555555555','875645342');
INSERT INTO responsible_for VALUES('555555555','777654421');
INSERT INTO responsible_for VALUES('555555555','987000001');


CREATE TABLE appointment_work_to_do
(w_ap_id CHAR(9) REFERENCES appointment(ap_id),
work_to_do VARCHAR2(30),
PRIMARY KEY (work_to_do, w_ap_id) );

INSERT INTO appointment_work_to_do VALUES('555555555', 'oil change');
INSERT INTO appointment_work_to_do VALUES('555555555', 'engine rebuild');
INSERT INTO appointment_work_to_do VALUES('555555555', 'new wheels');
INSERT INTO appointment_work_to_do VALUES('555555555', 'New Interior');

CREATE TABLE staff_phone_number
(staff_number CHAR(9),
phone_s_id CHAR(9) REFERENCES staff(s_id),
PRIMARY KEY (staff_number, phone_s_id) );

INSERT INTO staff_phone_number VALUES
('027234567','987654321');

INSERT INTO staff_phone_number VALUES
('027098765','987654321');

INSERT INTO staff_phone_number VALUES
('021091234','123123123');

INSERT INTO staff_phone_number VALUES
('022909090','875645342');

INSERT INTO staff_phone_number VALUES
('021786756','777654421');

INSERT INTO staff_phone_number VALUES
('022737456','987000001');

INSERT INTO staff_phone_number VALUES
('027892398','987000001');


COMMIT;


