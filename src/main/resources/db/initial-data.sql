INSERT INTO JOGGING_USER (id, login, password)
VALUES (1, 'user1', '$2a$10$U66Ph5NlUBKwQ441541PxevmF0Y977mKxZA2VYZc94JRq/dM2KiWK');

INSERT INTO JOGGING_USER (id, login, password)
VALUES (2, 'user2', '$2a$10$fAGE9aixRNJFrE5ba.g0teN1MQxI9gYDJsTYCuAiP/sNljSTABtR6');

INSERT INTO JOGGING_RACE (id, distance, date, duration, user_id)
VALUES (1, 123.4, '2019-01-01 08:00:00.00', 45, 1);

INSERT INTO JOGGING_RACE (id, distance, date, duration, user_id)
VALUES (2, 456.7, '2019-01-02 08:00:00.00', 55, 1);

INSERT INTO JOGGING_RACE (id, distance, date, duration, user_id)
VALUES (3, 123.4, '2019-01-02 09:00:00.00', 35, 2);

INSERT INTO JOGGING_RACE (id, distance, date, duration, user_id)
VALUES (4, 456.7, '2019-01-09 09:00:00.00', 40, 2);

INSERT INTO JOGGING_RACE (id, distance, date, duration, user_id)
VALUES (5, 789.0, '2019-01-10 09:00:00.00', 35, 2);