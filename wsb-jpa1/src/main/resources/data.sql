INSERT INTO address (id, address_line1, address_line2, city, postal_code)
VALUES (1, 'xx', 'yy', 'city', '62-030');


INSERT INTO PATIENT (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, age, version, address_id)
VALUES (1, 'Lukasz', 'Szkolarz', '123123331', 'lukasz.szkolarz@kalafior.pl', 'P420', '1912-02-09', 44, 0, 1);

INSERT INTO PATIENT (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, age, version)
VALUES (2, 'Marcin', 'Szklarski', '555666777', 'marcin.szklarski@pomidor.pl', 'P1337', '1919-11-11', 33, 0);

INSERT INTO PATIENT (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, age, version)
VALUES (3, 'Kacper', 'Pekalski', '692137666', 'kacper.pekalski@baklazan.pl', 'P2137', '1966-12-12', 99, 0);

INSERT INTO PATIENT (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, age)
VALUES (4, 'Bonifacy', 'Wesoly', '999888777', 'bonifacy.wesoly@gmail.com', 'P123', '1980-05-15', 25);

INSERT INTO PATIENT (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, age)
VALUES (5, 'Jan', 'Kowalski', '444555666', 'jan.kowalski@gmail.com', 'P456', '1995-08-20', 30);

INSERT INTO PATIENT (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, age)
VALUES (6, 'Anna', 'Wroclawska', '111222333', 'anna.wroclawska@gmail.com', 'P92929', '1992-05-17', 31);

INSERT INTO PATIENT (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, age)
VALUES (7, 'Weronika', 'Wroclawska', '444444444', 'weronika.wroclawska@gmail.com', 'P543', '1985-02-11', 38);



INSERT INTO DOCTOR (id, first_name, last_name, telephone_number, email, doctor_number, specialization)
VALUES (1, 'John', 'Wick', '987654321', 'john.wick@gmail.com', '333', 'OCULIST');

INSERT INTO DOCTOR (id, first_name, last_name, telephone_number, email, doctor_number, specialization)
VALUES (2, 'Anastazja', 'Kowalska', '545454454', 'anastazja.kowalska@gmail.com', '111', 'SURGEON');

INSERT INTO DOCTOR (first_name, last_name, telephone_number, email, doctor_number, specialization)
VALUES ('Piotr', 'Nowakowski', '123456789', 'piotr.nowak@gmail.com', '222', 'GP');



-- Wizyty z relacjami do pacjenta, doktora i zabiegu medycznego
INSERT INTO VISIT (id, description, time, patient_id, doctor_id)
VALUES (1, 'First Visit', '2024-11-30 10:00:00', 1, 1);

INSERT INTO VISIT (id, description, time, patient_id, doctor_id)
VALUES (2, 'Health Check', '2024-12-04 9:45:00', 3, 2);

INSERT INTO VISIT (id, description, time, patient_id, doctor_id)
VALUES (3, 'Control-visit', '2028-04-12 12:30:00', 2, 3);

INSERT INTO VISIT (description, time, patient_id, doctor_id)
VALUES ('Surgical consultation', '2024-11-20 14:00:00', 4, 2);

INSERT INTO VISIT (description, time, patient_id, doctor_id)
VALUES ('Second Visit', '2024-12-22 18:30:00', 1, 1);




INSERT INTO VISIT (description, time, patient_id, doctor_id)
VALUES ('Visit x', '2025-03-22 10:10:10', 1, 2);

INSERT INTO VISIT (description, time, patient_id, doctor_id)
VALUES ('Visit y', '2025-04-21 12:00:00', 1, 3);




INSERT INTO MEDICAL_TREATMENT (id, description, type, visit_id)
VALUES (1, 'Abdominal ultrasound', 'USG', 2);

INSERT INTO MEDICAL_TREATMENT (id, description, type, visit_id)
VALUES (2, 'Chest X-ray', 'RTG', 3);

INSERT INTO MEDICAL_TREATMENT (id, description, type, visit_id)
VALUES (3, 'Laying on the bed', 'USG', 1);



--Wizyty 1, 5, 6 oraz 7 mają przypisanego pacjenta 1, dlatego te
--cztery poniższe wizyty są brane pod uwagę w teście w PatientServiceTest
INSERT INTO MEDICAL_TREATMENT (description, type, visit_id)
VALUES ('General treatment', 'EKG', 1);

INSERT INTO MEDICAL_TREATMENT (description, type, visit_id)
VALUES ('Blood collection', 'USG', 5);

INSERT INTO MEDICAL_TREATMENT (description, type, visit_id)
VALUES ( 'Dental treatment','RTG', 6);

INSERT INTO MEDICAL_TREATMENT (description, type, visit_id)
VALUES ('Just buy medicines', 'USG', 7);




-- Przykład widoku, gdzie każda wizyta ma informacje o czasie,
-- imie i nazwisko lekarza oraz typy z MedicalTreatment (zadanie 1, lab2):

CREATE VIEW doctor_visits AS
SELECT
    V.time,
    D.first_name AS doctor_first_name,
    D.last_name AS doctor_last_name,
    MT.type AS medical_treatment_type,
    P.first_name ||
    ' ' || P.last_name AS patient_full_name
FROM VISIT V
         JOIN DOCTOR D ON V.doctor_id = D.id
         JOIN MEDICAL_TREATMENT MT ON MT.visit_id = V.id
         JOIN PATIENT P ON V.patient_id = P.id
ORDER BY V.time;