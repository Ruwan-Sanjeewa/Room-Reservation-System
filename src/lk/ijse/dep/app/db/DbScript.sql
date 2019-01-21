CREATE  DATABASE  HotelManagement;

CREATE TABLE Customer(customer_name VARCHAR(100),
customer_nic VARCHAR(100),
telephone int,
address VARCHAR (100),
CONSTRAINT PRIMARY KEY (customer_nic) );

CREATE TABLE Room(room_no VARCHAR(100),
room_type VARCHAR(100),
CONSTRAINT PRIMARY KEY (room_no),
CONSTRAINT FOREIGN KEY (room_type) references roomtype(room_type_name));

CREATE TABLE MealType(meal_type_id VARCHAR(100),
meal_type_name VARCHAR(100),
price DECIMAL(10,2),
CONSTRAINT PRIMARY KEY (meal_type_id));

CREATE TABLE Payment(booking_id varchar (100),
payment_id varchar(100),
total_price DECIMAL(10,2),
advance DECIMAL(10,2),
balance DECIMAL(10,2),
status VARCHAR(100),
CONSTRAINT PRIMARY KEY (payment_id),
CONSTRAINT FOREIGN KEY (booking_id)REFERENCES booking(booking_id));

CREATE TABLE Booking(customer_nic VARCHAR(100),
booking_id VARCHAR(100),
check_in_date DATE,
check_out_date DATE,
duration int ,
CONSTRAINT PRIMARY KEY (booking_id),
  CONSTRAINT FOREIGN KEY (customer_nic)REFERENCES customer(customer_nic)
);

CREATE TABLE RoomBookingDetail(booking_id VARCHAR (100),
room_no VARCHAR (100),
meal_type_id VARCHAR (100),
CONSTRAINT PRIMARY KEY (booking_id,room_no),
CONSTRAINT FOREIGN KEY (booking_id)REFERENCES Booking(booking_id),
CONSTRAINT FOREIGN KEY (room_no)REFERENCES Room(room_no),
CONSTRAINT FOREIGN KEY (meal_type_id)REFERENCES MealType(meal_type_id));




SELECT c.customer_nic,c.customer_name,b.booking_id,ro.room_no FROM Customer c INNER JOIN Booking B on c.customer_nic = B.customer_nic
INNER JOIN RoomBookingDetail ro on B.booking_id = ro.booking_id;