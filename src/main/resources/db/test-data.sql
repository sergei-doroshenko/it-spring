INSERT INTO ROLES(NAME) VALUES ('ADMINISTRATOR'), ('USER'), ('GUEST');

INSERT INTO USERS(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ROLE_ID)
VALUES ('Sergei','Doroshenko', 'admin@gmail.com','111', 1),
('Tim', 'Tom', 'tim@gmail.com', '111', 2),
('Ivan', 'Ivanov', 'ivan@gmail.com', '111', 2),
('Rom', 'Amazon', 'rom@gmail.com', '111', 2),
('Dan', 'Brown', 'dan@gmail.com', '111', 2);

INSERT INTO STATUSES(NAME) VALUES ('NEW'),('ASSIGNED'),('IN PROGRESS'),('RESOLVED'),('CLOSED'),('REOPENED');

INSERT INTO RESOLUTIONS(NAME) VALUES ('FIXED'),('INVALID'),('WONTFIX'),('WORKFORME');

INSERT INTO PRIORITIES(NAME) VALUES ('CRITICAL'),('MAJOR'),('IMPORTANT'),('MINOR');

INSERT INTO TYPES(NAME) VALUES ('COSMETIC'),('BUG'),('FEATURE'),('PERFORMANCE');

INSERT INTO PROJECTS (NAME, DESCRIPTION, MANAGER) VALUES 
('Data Storage', 'Storage different data', 2),
('Green World', 'Ecological issues', 2);

INSERT INTO BUILDS(NAME, PROJECT_ID) VALUES 
('v.1.0.1-alpha', 1),
('v.1.0.1-beta', 1),
('v.1.1.0-alpha', 1),
('v.1.2.1-beta', 2),
('v.3.2.1-beta', 2);

INSERT INTO ISSUES(CREATE_DATE, CREATE_BY, MODIFY_DATE, MODIFIED_BY, SUMMARY, DESCRIPTION, 
STATUS_ID, TYPE_ID, PRIORITY_ID, PROJECT_ID, BUILD_ID, ASSIGNEE_ID) 
VALUES 
('2014-01-15', 2, '2014-01-17', 2, 'Bag in Data Base', 'Data Base do not auto-populete date of creation field', 
2, 2, 4, 1, 3, 3);


