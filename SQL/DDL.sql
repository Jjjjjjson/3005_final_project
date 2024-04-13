-- DDL for Members Table
CREATE TABLE Member (
    MemberID SERIAL PRIMARY KEY,
    Username VARCHAR(255) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    Email VARCHAR(255) UNIQUE,
    Address TEXT,
    FitnessGoals TEXT,
    HealthMetrics TEXT,
    ExerciseRoutines TEXT
);

-- DDL for Trainer Table
CREATE TABLE Trainer (
    TrainerID SERIAL PRIMARY KEY,
    Username VARCHAR(255) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    Availability TEXT,
    Status VARCHAR(12) CHECK (Status IN ('available', 'unavailable')),
    MemberID INT REFERENCES Member(MemberID)
);

-- DDL for Dashboard Table
CREATE TABLE Dashboard (
    DashboardID SERIAL PRIMARY KEY,
    MemberID INT REFERENCES Member(MemberID),
    ExerciseRoutines VARCHAR(255),
    FitnessAchievements VARCHAR(255),
    HealthStatistics VARCHAR(255)
);

-- DDL for Administrator Table
CREATE TABLE Administrator (
    AdminID SERIAL PRIMARY KEY,
    Username VARCHAR(255) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL
);

-- DDL for Equipment Table
CREATE TABLE Equipment (
    EquipmentID SERIAL PRIMARY KEY,
    Name VARCHAR(255),
    MaintenanceStatus VARCHAR(255),
    AdminID INT REFERENCES Administrator(AdminID)
);

-- DDL for Room Booking Table
CREATE TABLE Room_Booking (
    BookingID SERIAL PRIMARY KEY,
    RoomName VARCHAR(255),
    DateTime TIMESTAMP NOT NULL,
    Status VARCHAR(255),
    AdminID INT REFERENCES Administrator(AdminID)
);

-- DDL for Training_Schedule Table
CREATE TABLE Training_Schedule (
    ScheduleID SERIAL PRIMARY KEY,
    DateTime TIMESTAMP NOT NULL,
    TrainerID INT REFERENCES Trainer(TrainerID)
);

-- DDL for Class Table
CREATE TABLE Class (
    ClassID SERIAL PRIMARY KEY,
    ClassName VARCHAR(255),
    DateTime TIMESTAMP NOT NULL,
    MemberID INT REFERENCES Member(MemberID),
    AdminID INT REFERENCES Administrator(AdminID)
);

-- DDL for Billing Table
CREATE TABLE Billing (
    BillingID SERIAL PRIMARY KEY,
    Amount DECIMAL(10,2),
    DateTime TIMESTAMP NOT NULL,
    PaymentStatus VARCHAR(255),
    MemberID INT REFERENCES Member(MemberID),
    AdminID INT REFERENCES Administrator(AdminID)
);

-- DDL for classRegistrations
CREATE TABLE ClassRegistrations (
    ClassID INT,
    MemberID INT,
    PRIMARY KEY (ClassID, MemberID),
    FOREIGN KEY (ClassID) REFERENCES Class(ClassID) ON DELETE CASCADE,
    FOREIGN KEY (MemberID) REFERENCES Member(MemberID) ON DELETE CASCADE
);
