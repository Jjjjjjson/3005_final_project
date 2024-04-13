-- DML for Member Table
INSERT INTO Member (Username, Password, Email, Address, FitnessGoals, HealthMetrics, ExerciseRoutines) VALUES
('john_doe', 'secure1234', 'john.doe@email.com', '101 Red Street', 'Lose 10 lbs; Build muscle', 'Weight: 180 lbs; BP: 120/80', 'Jogging, Weightlifting'),
('sarah_connor', 'terminator2', 'sarah.connor@email.com', '102 Blue Street', 'Increase stamina; Improve flexibility', 'Weight: 130 lbs; HR: 65 bpm', 'Cycling, Yoga'),
('james_bond', 'shakenNotStirred', 'james.bond@email.com', '103 Green Street', 'Total body conditioning', 'Weight: 165 lbs; BP: 110/70', 'Swimming, Boxing'),
('lara_croft', 'croft1996', 'lara.croft@email.com', '104 Yellow Street', 'Agility and strength training', 'Weight: 135 lbs; HR: 75 bpm', 'Climbing, Sprinting'),
('tony_stark', 'ironman', 'tony.stark@email.com', '105 Purple Street', 'Maintain optimal health', 'Weight: 150 lbs; BP: 130/85', 'Gym routine, Pilates'),
('bruce_wayne', 'b4tman', 'bruce.wayne@email.com', '106 Orange Street', 'Peak human conditioning', 'Weight: 190 lbs; HR: 60 bpm', 'Martial arts, Gymnastics'),
('peter_parker', 'spidey', 'peter.parker@email.com', '107 Pink Street', 'Flexibility; Muscle tone', 'Weight: 140 lbs; BP: 118/75', 'Climbing, Aerobics'),
('diana_prince', 'wonderwoman', 'diana.prince@email.com', '108 White Street', 'Enhanced strength training', 'Weight: 140 lbs; HR: 70 bpm', 'Combat training, Gym workout'),
('steve_rogers', 'cap1941', 'steve.rogers@email.com', '109 Grey Street', 'Super soldier regimen', 'Weight: 180 lbs; BP: 110/70', 'Tactical training, Heavy lifting'),
('natasha_romanoff', 'blackwidow', 'natasha.romanoff@email.com', '110 Black Street', 'High intensity interval training', 'Weight: 125 lbs; HR: 72 bpm', 'Spy training, Ballet');



-- DML for Trainer Table
INSERT INTO Trainer (Username, Password, Availability, Status, MemberID) VALUES
('logan_w', 'wolverine', 'Mon-Fri 8am-2pm', 'available', 1),
('charles_x', 'profX', 'Mon-Fri 10am-4pm', 'unavailable', 2),
('jean_grey', 'phoenix', 'Tue-Thu 9am-3pm', 'unavailable', 3),
('scott_summers', 'cyclops', 'Wed-Sat 11am-5pm', 'unavailable', 4),
('kurt_wagner', 'nightcrawler', 'Mon-Wed 8am-12pm', 'available', 5),
('ororo_munroe', 'storm', 'Fri-Sun 10am-3pm', 'available', 6),
('hank_mccoy', 'beast', 'Mon-Fri 1pm-6pm', 'unavailable', 7),
('emma_frost', 'whitequeen', 'Tue-Thu 12pm-6pm', 'available', 8),
('bobby_drake', 'iceman', 'Mon-Sun 3pm-9pm', 'available', 9),
('piotr_rasputin', 'colossus', 'Sat-Sun 6am-12pm', 'available', 10);



-- DML for Dashboard Table
INSERT INTO Dashboard (MemberID, ExerciseRoutines, FitnessAchievements, HealthStatistics) VALUES
(1, 'Running, Weightlifting', '5k completed; Bench press 200 lbs', 'BMI 22; Resting HR 55 bpm'),
(2, 'Cycling, Yoga', 'Cycled 100 miles; Mastered Vinyasa Yoga', 'BMI 20; Flexibility increased'),
(3, 'Swimming, Boxing', 'Swam 2 miles; Won amateur boxing match', 'BMI 23; Improved punch power'),
(4, 'Climbing, Sprinting', 'Climbed Grade 7a route; 100m sprint in 12s', 'BMI 21; Sprint speed increased'),
(5, 'Gym routine, Pilates', 'Lifted total of 10,000 lbs; Advanced Pilates', 'BMI 24; Core strength improved'),
(6, 'Martial arts, Gymnastics', 'Black belt achieved; Full floor routine', 'BMI 22; Agility score 9/10'),
(7, 'Climbing, Aerobics', 'Climbed a mountain; Aerobics instructor certified', 'BMI 19; Stamina level 90%'),
(8, 'Combat training, Gym workout', 'Sparring competition winner; 300 lbs deadlift', 'BMI 21; Hand-to-hand combat level up'),
(9, 'Tactical training, Heavy lifting', 'Tactical course completed; Lifted truck tire 50 times', 'BMI 25; Endurance level high'),
(10, 'Spy training, Ballet', 'Stealth course mastered; Performed Swan Lake', 'BMI 18; Flexibility score 95%');



-- DML for Administrator Table
INSERT INTO Administrator (Username, Password) VALUES
('nick_fury', 'shield'),
('maria_hill', 'hill123'),
('pepper_potts', 'rescue'),
('happy_hogan', 'happy2024'),
('james_rhodes', 'war_machine'),
('phil_coulson', 'coulsonAgent'),
('may_parker', 'auntMay'),
('howard_stark', 'fatherOfIronMan'),
('peggy_carter', 'agentCarter'),
('sharon_carter', 'agent13');



-- DML for Equipment Table
INSERT INTO Equipment (Name, MaintenanceStatus, AdminID) VALUES
('Treadmill', 'Good', 1),
('Elliptical Machine', 'Needs repair', 2),
('Stationary Bike', 'Good', 3),
('Rowing Machine', 'Maintenance due', 4),
('Kettlebells', 'Excellent', 5),
('Dumbbells', 'Good', 6),
('Yoga Mats', 'Replace soon', 7),
('Medicine Balls', 'Good', 8),
('Resistance Bands', 'Excellent', 9),
('Pilates Reformer', 'Maintenance due', 10);



-- DML for Room Booking Table
INSERT INTO Room_Booking (RoomName, DateTime, Status, AdminID) VALUES
('Aerobic Room', '2024-10-01 09:00', 'Booked', 1),
('Dance Studio', '2024-10-02 10:00', 'Available', 2),
('Yoga Studio', '2024-10-03 11:00', 'Booked', 3),
('Weight Room', '2024-10-04 12:00', 'Maintenance', 4),
('Cycling Studio', '2024-10-05 13:00', 'Booked', 5),
('Pilates Studio', '2024-10-06 14:00', 'Available', 6),
('Boxing Ring', '2024-10-07 15:00', 'Booked', 7),
('MMA Cage', '2024-10-08 16:00', 'Available', 8),
('Basketball Court', '2024-10-09 17:00', 'Booked', 9),
('Tennis Court', '2024-10-10 18:00', 'Maintenance', 10);



-- DML for Training_Schedule Table
INSERT INTO Training_Schedule (DateTime, TrainerID) VALUES
('2024-10-01 08:00', 1),
('2024-10-02 09:00', 2),
('2024-10-03 10:00', 3),
('2024-10-04 11:00', 4),
('2024-10-05 12:00', 5),
('2024-10-06 13:00', 6),
('2024-10-07 14:00', 7),
('2024-10-08 15:00', 8),
('2024-10-09 16:00', 9),
('2024-10-10 17:00', 10);



-- DML for Class Table
INSERT INTO Class (ClassName, DateTime, MemberID, AdminID) VALUES
('Yoga Basics', '2024-10-01 07:00', 1, 1),
('High-Intensity Interval Training', '2024-10-02 08:00', 2, 2),
('Spinning Class', '2024-10-03 09:00', 3, 3),
('Pilates Reformer Class', '2024-10-04 10:00', 4, 4),
('Zumba Dance', '2024-10-05 11:00', 5, 5),
('CrossFit', '2024-10-06 12:00', 6, 6),
('Kickboxing', '2024-10-07 13:00', 7, 7),
('Strength Training', '2024-10-08 14:00', 8, 8),
('Boot Camp', '2024-10-09 15:00', 9, 9),
('Tai Chi', '2024-10-10 16:00', 10, 10);



-- DML for Billing Table
INSERT INTO Billing (Amount, DateTime, PaymentStatus, MemberID, AdminID) VALUES
(100.00, '2024-10-12 12:00', 'Unpaid', 1, 1),
(150.00, '2024-10-13 13:00', 'Paid', 2, 2),
(200.00, '2024-10-14 14:00', 'Unpaid', 3, 3),
(250.00, '2024-10-15 15:00', 'Paid', 4, 4),
(300.00, '2024-10-16 16:00', 'Unpaid', 5, 5),
(350.00, '2024-10-17 17:00', 'Paid', 6, 6),
(400.00, '2024-10-18 18:00', 'Unpaid', 7, 7),
(450.00, '2024-10-19 19:00', 'Paid', 8, 8),
(500.00, '2024-10-20 20:00', 'Unpaid', 9, 9),
(550.00, '2024-10-21 21:00', 'Paid', 10, 10);
