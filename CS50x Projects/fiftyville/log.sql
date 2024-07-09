-- Keep a log of any SQL queries you execute as you solve the mystery.
-- Survey the database's structure
--.schema
-- Find description of crime

SELECT description FROM crime_scene_reports
    WHERE month = 7 AND day = 28
    AND street = 'Humphrey Street';
    -- Discovered crime took place at 10:15 AM, had three witness interviews taken that day that mention bakery

-- Investigate interview leads
SELECT transcript FROM interviews
    WHERE month = 7 AND day = 28
    AND transcript LIKE "%bakery%";
    -- Discovered bakery has license plate number leaving within 10 min after crime
    -- Discovered ATM transactions has thief's activity at ATM that day before time of crime
    -- Discovered thief made phone call
        -- Phone call less that minute
        -- Within ten minutes of crime
        -- Person on other end purchased plane ticket for thief, earliest flight tomorrow

-- Investigate ATM lead
SELECT * FROM atm_transactions
    WHERE month = 7 AND day = 28
    AND atm_location = "Leggett Street"
    AND transaction_type = "withdraw";
    -- Discovered 11 transactions with corresponding acct numbers

-- Investigate bank acccounts connected to ATM transactions
SELECT * FROM bank_accounts
    JOIN atm_transactions ON bank_accounts.account_number = atm_transactions.account_number
    WHERE month = 7 AND day = 28
    AND atm_location = "Leggett Street"
    AND transaction_type = "withdraw";

-- Investigate bakery security leads
SELECT activity, license_plate, hour, minute FROM bakery_security_logs
    WHERE month = 7 AND day = 28
    AND hour = 10 AND minute > 15 AND minute < 25;
    -- Discovered list of entries with corresponding license plate numbers

-- Investigate phone call lead
SELECT * FROM phone_calls
    WHERE month = 7 AND day = 28
    AND duration < 60;
    -- Discovered list of phone-calls matching description

-- Investigate people table based on previous leads, output passport number since this is what can identify them at airport
SELECT DISTINCT passport_number FROM people
    JOIN bakery_security_logs
        ON people.license_plate = bakery_security_logs.license_plate
    JOIN phone_calls
        ON people.phone_number = phone_calls.caller
    WHERE phone_calls.duration < 60
    AND phone_calls.month = 7 AND phone_calls.day = 28
    AND bakery_security_logs.hour = 10
    AND bakery_security_logs.minute > 15 AND bakery_security_logs.minute < 25;
    -- Dicovered small list of passport numbers

-- Determine flight that the thief took based on phone call
SELECT * FROM flights
    JOIN airports ON flights.origin_airport_id = airports.id
    WHERE city = "Fiftyville"
    AND flights.month = 7 AND flights.day = 29
    ORDER BY flights.hour, flights.minute
    LIMIT 1;
    -- Discovered flight id is 36
    -- Discovered destination airport id is 4

-- Determine destination thief fled to
SELECT * FROM airports
    WHERE id = 4;
    -- Discovered thief fled to NYC

-- Search for passport numbers in the flight number discovered above
SELECT passengers.passport_number FROM passengers
    JOIN people ON passengers.passport_number = people.passport_number
    WHERE flight_id = 36
    AND passengers.passport_number IN
        (SELECT DISTINCT passport_number FROM people
        JOIN bakery_security_logs
            ON people.license_plate = bakery_security_logs.license_plate
        JOIN phone_calls
            ON people.phone_number = phone_calls.caller
        WHERE phone_calls.duration < 60
        AND phone_calls.month = 7 AND phone_calls.day = 28
        AND bakery_security_logs.hour = 10
        AND bakery_security_logs.minute > 15 AND bakery_security_logs.minute < 25);
        -- Discovered narrowed list of suspects.

-- Narrow list based on ATM access list
SELECT * FROM people
    JOIN passengers
        ON people.passport_number = passengers.passport_number
    JOIN bank_accounts
        ON people.id = bank_accounts.person_id
    WHERE bank_accounts.person_id  IN
        -- List of person_id's who are suspected based on ATM
        (SELECT person_id FROM bank_accounts
            JOIN atm_transactions ON bank_accounts.account_number = atm_transactions.account_number
            WHERE month = 7 AND day = 28
            AND atm_location = "Leggett Street"
            AND transaction_type = "withdraw")
    AND people.passport_number IN
        -- Narrowed list of suspect passport numbers
        (SELECT passengers.passport_number FROM passengers
            JOIN people ON passengers.passport_number = people.passport_number
            WHERE flight_id = 36
            AND passengers.passport_number IN
                (SELECT DISTINCT passport_number FROM people
                    JOIN bakery_security_logs
                        ON people.license_plate = bakery_security_logs.license_plate
                    JOIN phone_calls
                        ON people.phone_number = phone_calls.caller
                    WHERE phone_calls.duration < 60
                    AND phone_calls.month = 7 AND phone_calls.day = 28
                    AND bakery_security_logs.hour = 10
                    AND bakery_security_logs.minute > 15 AND bakery_security_logs.minute < 25));
                    -- Discovered 'Bruce'

-- Search for accomplice by finding out who was on the receiving end of Bruce's call
SELECT name FROM people
    WHERE phone_number =
        (SELECT receiver FROM phone_calls
            JOIN people
                ON phone_calls.caller = people.phone_number
            WHERE people.name = "Bruce"
            AND month = 7 AND day = 28
            AND duration < 60);
            -- Discovered receiver of call was Robin