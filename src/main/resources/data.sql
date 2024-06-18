INSERT INTO Customer (user_name, first_name, last_name, address, email, phone)
VALUES ('aa', 'Anna', 'Andersson', 'matnäs 3', 'annaandersson@gmail.com', '070 123 45 67'),
       ('bb', 'Bengt', 'Björk', 'Lundvägen 31', 'bb@gmail.com', '070 11 22 333'),
       ('cc', 'Chatrine', 'Carlsson', 'Bäckvägen 8', 'cc@gmail.com', '070 44 55 666'),
       ('dd', 'David', 'Drake', 'Sunnanbäck 22', 'dd@gmail.com', '070 77 88 999'),
       ('ee', 'Erik', 'Ek', 'Drottninggatan 5', 'ee@gmail.com', '070 10 10 100 ')
;

INSERT INTO Room (max_number_of_guests, equipment) --har inte lagt till bookings, det får läggas till med booking id..
VALUES (4, 'tv'),
       (8, 'karaoke'),
       (4, 'karaoke'),
       (12, 'display and microphone'),
       (6, '');

INSERT INTO Dish(dish_name, price)
VALUES ('California 11', 99),
       ('Hudik Special 20', 219),
       ('Familje sushi 50', 425),
       ('vegetarisk 11', 129),
       ('Lax och maki 10', 125),
       ('Mamma sushi 9', 109),
       ('Dumpling', 119),
       ('Poke bowl', 119),
       ('Kyckling teriyaky', 119),
       ('Bento', 129);



INSERT INTO Takeaway (time_for_pickup, customer_id)
VALUES ('2024-06-21-22:22', 1),
       ('2024-06-22-16:22', 3);

INSERT INTO Takeaway_Dishes(takeaway_id, dishes_id)
VALUES (1, 1),
       (1, 2),
       (1, 2),
       (1, 4),
       (2, 3),
       (2, 7),
       (2, 8);


INSERT INTO Booking (start_time, end_time, number_of_guests, customer_id, room_id) --ska lägga till dishes och fixa totalpris
VALUES ('nu', 'sen', 4, 1, 1),
       ('sen', 'senare', 4, 4, 2);

INSERT INTO Booking_Dishes(booking_id, dishes_id)
values (1, 1),
       (1, 4),
       (1, 7),
       (1, 6),
       (1, 9),
       (2, 2),
       (2, 3);
