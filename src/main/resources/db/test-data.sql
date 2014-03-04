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
('Simple Project', 'Sales company project. For reducing costs and issues.', 2),
('New customer support.', 'Web project to support customers fro UK.', 3),
('Green World', 'Ecological issues', 2);

INSERT INTO BUILDS(NAME, PROJECT_ID) VALUES 
('v.1.0.1-alpha', 1),
('v.1.0.1-beta', 1),
('v.1.1.0-alpha', 1),
('v.1.2.1-beta', 2),
('v.2.2.1-beta', 2),
('v.3.1.1-beta', 3),
('v.3.2.1-beta', 3);

INSERT INTO ISSUES(CREATE_DATE, CREATE_BY, MODIFY_DATE, MODIFIED_BY, SUMMARY, DESCRIPTION, 
STATUS_ID, TYPE_ID, PRIORITY_ID, PROJECT_ID, BUILD_ID, ASSIGNEE_ID) 
VALUES 
('2014-02-15', 3, '2014-02-17', 4, 'Simple Bag', 'Validation issue in users form', 
1, 3, 4, 3, 6, 2),
('2014-01-11', 4, '2014-01-27', 2, 'Test Bag', 'Connection issue in users form', 
2, 2, 3, 2, 4, 3),
('2014-01-15', 2, '2014-01-17', 3, 'Bag in Data Base', 'Data Base do not auto-populete date of creation field', 
3, 1, 2, 1, 3, 4);

INSERT INTO COMMENTS (ISSUE_ID, CREATE_DATE, CREATE_BY, COMMENT)
VALUES
(1, CURRENT_DATE, 4, 'This is my first test comment. Hello, everybody!'),
(2, CURRENT_DATE, 3, 'This is my second test comment. Hello, everybody!'),
(3, CURRENT_DATE, 5, 'Don''t write spam into comments. Please.');
