INSERT INTO address (id, address_line1, address_line2, city, postal_code)
            values (1, 'xx', 'yy', 'city', '62-030');

INSERT INTO PATIENT (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, age)
VALUES (1, 'Łukasz', 'Szkolarz', '123123331', 'lukasz.szkolarz@kalafior.pl', 'P420', '1912-02-09', 44);

INSERT INTO PATIENT (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, age)
VALUES (2, 'Marcin', 'Szklarski', '555666777', 'marcin.szklarski@pomidor.pl', 'P1337', '1919-11-11', 33);

INSERT INTO PATIENT (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, age)
VALUES (3, 'Kacper', 'Pękalski', '692137666', 'kacper.pekalski@baklazan.pl', 'P2137', '1966-12-12', 99);


INSERT INTO DOCTOR (id, first_name, last_name, telephone_number, email, doctor_number, specialization) VALUES (1, 'John', 'Wick', '987654321', 'john.wick@gmail.com', '333', 'OCULIST');


INSERT INTO MEDICAL_TREATMENT (id, description, type) VALUES (1, 'General treatment', 'EKG');


-- Wizyty z relacjami do pacjenta, doktora i zabiegu medycznego
INSERT INTO VISIT (id, description, time, patient_id, doctor_id, medical_treatment_id) VALUES (1, 'First Visit', '2024-11-30 10:00:00', 1, 1, 1);

INSERT INTO VISIT (id, description, time, patient_id, doctor_id, medical_treatment_id) VALUES (2, 'Health Check', '2024-12-04 9:45:00', 3, 1, 1);

INSERT INTO VISIT (id, description, time, patient_id, doctor_id, medical_treatment_id) VALUES (3, 'Control-visit', '2028-04-12 12:30:00', 2, 1, 1);


/*
Przykład kwerendy, gdzie każda wizyta ma mieć informacje o czasie, imie i nazwisko
lekarza oraz listę typów z MedicalTreatment:

SELECT
    V.time,
    D.first_name AS doctor_first_name,
    D.last_name AS doctor_last_name,
    MT.type AS medical_treatment_type
FROM
    VISIT V
        JOIN
    DOCTOR D ON V.doctor_id = D.id
        JOIN
    MEDICAL_TREATMENT MT ON V.medical_treatment_id = MT.id;
*/