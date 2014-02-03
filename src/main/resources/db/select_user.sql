/*********************** SELECT SINGLE USER **************************************/
SELECT USERS.ID AS user_id,
USERS.FIRST_NAME AS user_first_name,
USERS.LAST_NAME AS user_last_name,
USERS.EMAIL AS user_login, USERS.PASSWORD AS user_password,
ROLES.ID AS role_id, ROLES.RL_NAME AS role_name
FROM USERS
LEFT JOIN ROLES ON USERS.ROLE_ID = ROLES.ID
WHERE USERS.EMAIL = 'admin@gmail.com'
AND USERS.PASSWORD = '111';

/***************** UPDATE USER **************************************************/
UPDATE ROLES SET ROLES.RL_NAME = 'ADMINISTRATOR'
WHERE ID = 1;