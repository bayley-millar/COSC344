DROP TABLE staff_phone_number;
DROP TABLE appointment_work_to_do;
DROP TABLE responsible_for;
DROP TABLE customer_phone_number;
DROP TABLE manages;
DROP TABLE transactions;
DROP TABLE parts;
DROP TABLE appointment;
DROP TABLE car;
DROP TABLE account;
DROP TABLE staff;
DROP TABLE customer;



CREATE TABLE customer
       (c_id CHAR(9) PRIMARY KEY        NOT NULL,
        c_fname VARCHAR2(15)            NOT NULL,
        c_lname VARCHAR2(15)            NOT NULL,
        c_address VARCHAR2(30),
        c_gender CHAR
        );

        INSERT INTO customer VALUES
       ('666666666','Bilbo', 'Baggins', 'Hobbit Hole 4a', 'M' );

       INSERT INTO customer VALUES
       ('111111111','Clark', 'Kent', 'Krypton', 'M');

       INSERT INTO customer VALUES
       ('555555555','Helen', 'Clark', '1b Mount Eden Road, Auckland', 'F');

       INSERT INTO customer VALUES
       ('999999999','BC', 'Bass', '125 BC Street', 'M');

       INSERT INTO customer VALUES
       ('444444444','Wendy', 'McDonalds', '898 Curlyhoods Road, Otara', 'F')

CREATE TABLE staff
       (s_id CHAR(9) PRIMARY KEY        NOT NULL,
        s_fname VARCHAR2(15)            NOT NULL,
        s_lname VARCHAR2(15)            NOT NULL,
        salary NUMBER(6),
        s_address VARCHAR2(30),
	date_of_birth DATE);



        INSERT INTO staff VALUES
       ('987654321','Reuben', 'Poor', 100, '123 BC Street', TO_DATE('12-12-1996',
        'DD-MM-YYYY'));

        INSERT INTO staff VALUES
       ('123123123', 'Joe', 'Penn', 900000, '698 Mary Lane', TO_DATE('12-12-1964',
        'DD-MM-YYYY'));

        INSERT INTO staff VALUES
       ('875645342','Bayley', 'Pillar', 850000, 'Invercargill', TO_DATE('23-12-1970',
        'DD-MM-YYYY'));

        INSERT INTO staff VALUES
       ('777654421','Blake', 'Brah', 10, '1 Fivefoot-fiveinch Street', TO_DATE('23-12-1999',
        'DD-MM-YYYY'));

        INSERT INTO staff VALUES
       ('987000001','George', 'F-Skyscraper', 50000, 'The Moon', TO_DATE('25-11-1980',
        'DD-MM-YYYY'));


CREATE TABLE account
(acc_id CHAR(9) PRIMARY KEY             NOT NULL,
ca_id CHAR(9) REFERENCES customer(c_id) NOT NULL,
balance FLOAT DEFAULT (0.0),
last_visit DATE
);
        INSERT INTO account VALUES
        ('888888888', '666666666', 0.0, TO_DATE('14-04-1999',
        'DD-MM-YYYY'));

        INSERT INTO account VALUES
        ('888888881', '111111111', 0.0, TO_DATE('23-09-2015',
        'DD-MM-YYYY'));

        INSERT INTO account VALUES
        ('888888882', '555555555', 0.0, TO_DATE('18-10-2015',
        'DD-MM-YYYY'));

        INSERT INTO account VALUES
        ('888888883', '999999999', 0.0, TO_DATE('01-01-2015',
        'DD-MM-YYYY'));


CREATE TABLE car
(car_id CHAR(9) PRIMARY KEY             NOT NULL,
make VARCHAR2(15) NOT NULL,
model VARCHAR2(15),
mk_year VARCHAR2(15),
c_id CHAR(9) REFERENCES customer(c_id)  NOT NULL,
total_appointment INT DEFAULT(0));

     INSERT INTO car VALUES
     ('656565656', 'MINI', 'Cooper', '1998', '666666666', 0);
     INSERT INTO car VALUES
     ('656565658', 'HATCHBACK', 'skyline', '1998', '666666666', 0);
     INSERT INTO car VALUES
     ('656565654', 'RANGEROVER', '4wd', '1998', '666666666', 0);
     INSERT INTO car VALUES
     ('653565654', 'FORD', 'lasers', '1998', '555555555', 0);



CREATE TABLE appointment
       (ap_id CHAR(9) PRIMARY KEY       NOT NULL,
        pickup_date DATE           NOT NULL,
        drop_off_date DATE      NOT NULL,
        car_id CHAR(9) references car(car_id) NOT NULL 
        );

@trig.sql;

        INSERT INTO appointment VALUES
        ('555555555',
        TO_DATE('25-11-1980', 'DD-MM-YYYY'),TO_DATE('25-11-1980', 'DD-MM-YYYY') ,
        '656565656');




CREATE TABLE parts
       (part_id INT PRIMARY KEY         NOT NULL,
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
	transaction_no CHAR(9)          NOT NULL,
        account_ID CHAR(9) REFERENCES account(acc_id) NOT NULL,
        PRIMARY KEY(account_ID, transaction_no)
        );

@trig1.sql

INSERT INTO transactions VALUES
(TO_DATE('14-02-1998','DD-MM-YYYY'), 'CH', 1200.80,983642313 ,'888888888');
INSERT INTO transactions VALUES
(TO_DATE('18-02-1999','DD-MM-YYYY'), 'CH', 80,983642311 ,'888888881');
INSERT INTO transactions VALUES
(TO_DATE('14-09-1998','DD-MM-YYYY'), 'CH', 80 ,983642312 ,'888888882');
INSERT INTO transactions VALUES
(TO_DATE('19-07-1995','DD-MM-YYYY'), 'CH', 1200.80,983642314 ,'888888883');





CREATE TABLE manages
       (acc_id CHAR(9) REFERENCES account(acc_id),
       staff_id CHAR(9) REFERENCES staff(s_id),
       PRIMARY KEY(acc_id, staff_id)
       );
       INSERT INTO manages VALUES ('888888888','987654321');
       INSERT INTO manages VALUES ('888888881','123123123');
       INSERT INTO manages VALUES ('888888882','987000001');
       INSERT INTO manages VALUES ('888888883','777654421');


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



