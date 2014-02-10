/*********************** SELECT SINGLE USER **************************************/
SELECT USERS.ID AS user_id,
USERS.FIRST_NAME AS user_first_name,
USERS.LAST_NAME AS user_last_name,
USERS.EMAIL AS user_login, USERS.PASSWORD AS user_password,
ROLES.ID AS role_id, ROLES.RL_NAME AS role_name
FROM USERS
LEFT JOIN ROLES ON USERS.ROLE_ID = ROLES.ID
WHERE USERS.EMAIL = 'admin@gmail.com'
AND USERS.PASSWORD = '1111';

INSERT INTO USERS(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ROLE_ID) VALUES
('Anton', 'Chekhov', 'anton@gmail.com', 'aaa', 2);

UPDATE USERS SET
USERS.FIRST_NAME = 'ANTON',
USERS.LAST_NAME = 'CHEKHOV',
USERS.EMAIL = 'chechov@gmail.com',
USERS.PASSWORD = '1111',
USERS.ROLE_ID = 1
WHERE USERS.ID = 6;

DELETE FROM USERS WHERE USERS.ID = 10;

/***************** UPDATE USER **************************************************/
UPDATE ROLES SET ROLES.RL_NAME = 'ADMINISTRATOR'
WHERE ID = 1;