CREATE TABLE ROLES (
	ID INT NOT NULL AUTO_INCREMENT, 
	NAME VARCHAR(60) NOT NULL,
	UNIQUE UQ_ROLE_NAME (NAME),
	PRIMARY KEY (ID)
);

CREATE TABLE USERS (
	ID INT NOT NULL AUTO_INCREMENT, 
	FIRST_NAME VARCHAR(60) NOT NULL,
	LAST_NAME VARCHAR(60) NOT NULL,
	EMAIL VARCHAR(60) NOT NULL,
	PASSWORD VARCHAR(60) NOT NULL,
	ROLE_ID INT NOT NULL,
	ENABLED BOOLEAN NOT NULL DEFAULT TRUE,
	UNIQUE UQ_USER_LOGIN (EMAIL),
	PRIMARY KEY (ID),
	FOREIGN KEY (ROLE_ID) REFERENCES PUBLIC.ROLES(ID)
);


CREATE TABLE AUTHORITIES (
	USERNAME VARCHAR(60) NOT NULL,
	AUTHORITY VARCHAR(60) NOT NULL,
	PRIMARY KEY (USERNAME, AUTHORITY),
	FOREIGN KEY (USERNAME) REFERENCES PUBLIC.USERS(EMAIL),
	FOREIGN KEY (AUTHORITY) REFERENCES PUBLIC.ROLES(NAME)
);

CREATE TABLE TYPES (
	ID INT NOT NULL AUTO_INCREMENT, 
	NAME VARCHAR(60) NOT NULL,
	UNIQUE UQ_TYPE_NAME (NAME),
	PRIMARY KEY (ID)
);

CREATE TABLE STATUSES (
	ID INT NOT NULL AUTO_INCREMENT, 
	NAME VARCHAR(60) NOT NULL,
	UNIQUE UQ_STATUS_NAME (NAME),
	PRIMARY KEY (ID)
);

CREATE TABLE RESOLUTIONS (
	ID INT NOT NULL AUTO_INCREMENT, 
	NAME VARCHAR(60) NOT NULL,
	UNIQUE UQ_RESOLUTION_NAME (NAME),
	PRIMARY KEY (ID)
);

CREATE TABLE PRIORITIES (
	ID INT NOT NULL AUTO_INCREMENT, 
	NAME VARCHAR(60) NOT NULL,
	UNIQUE UQ_PRIORITY_NAME (NAME),
	PRIMARY KEY (ID)
);

CREATE TABLE PROJECTS (
	ID INT NOT NULL AUTO_INCREMENT, 
	NAME VARCHAR(60) NOT NULL,
	DESCRIPTION VARCHAR(500) NOT NULL,
	MANAGER INT NOT NULL,
	UNIQUE UQ_PROJECT_NAME (NAME),
	PRIMARY KEY (ID),
	FOREIGN KEY (MANAGER) REFERENCES PUBLIC.USERS(ID)
);

CREATE TABLE BUILDS (
	ID INT NOT NULL AUTO_INCREMENT, 
	NAME VARCHAR(60) NOT NULL,
	PROJECT_ID INT NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (PROJECT_ID) REFERENCES PUBLIC.PROJECTS(ID)
);

CREATE TABLE ISSUES (
	ID INT NOT NULL AUTO_INCREMENT, 
	CREATE_DATE DATE NOT NULL,
	CREATE_BY INT NOT NULL,
	MODIFY_DATE DATE,
	MODIFIED_BY INT,
	SUMMARY VARCHAR(100) NOT NULL,
	DESCRIPTION VARCHAR(300) NOT NULL,
	STATUS_ID INT NOT NULL,
	RESOLUTION_ID INT,
	TYPE_ID INT NOT NULL,
	PRIORITY_ID INT NOT NULL,
	PROJECT_ID INT NOT NULL,
	BUILD_ID INT NOT NULL,
	ASSIGNEE_ID INT,
	PRIMARY KEY (ID),
	FOREIGN KEY (CREATE_BY) REFERENCES PUBLIC.USERS(ID),
	FOREIGN KEY (MODIFIED_BY) REFERENCES PUBLIC.USERS(ID),
	FOREIGN KEY (STATUS_ID) REFERENCES PUBLIC.STATUSES(ID),
	FOREIGN KEY (RESOLUTION_ID) REFERENCES PUBLIC.RESOLUTIONS(ID),
	FOREIGN KEY (TYPE_ID) REFERENCES PUBLIC.TYPES(ID),
	FOREIGN KEY (PRIORITY_ID) REFERENCES PUBLIC.PRIORITIES(ID),
	FOREIGN KEY (PROJECT_ID) REFERENCES PUBLIC.PROJECTS(ID),
	FOREIGN KEY (BUILD_ID) REFERENCES PUBLIC.BUILDS(ID),
	FOREIGN KEY (ASSIGNEE_ID) REFERENCES PUBLIC.USERS(ID),
);

CREATE TABLE COMMENTS (
	ID INT NOT NULL AUTO_INCREMENT,
	ISSUE_ID INT NOT NULL,
	CREATE_DATE DATE NOT NULL,
	CREATE_BY INT NOT NULL,
	COMMENT VARCHAR(700) NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (ISSUE_ID) REFERENCES PUBLIC.ISSUES(ID),
	FOREIGN KEY (CREATE_BY) REFERENCES PUBLIC.USERS(ID),
);

CREATE TABLE ATTACHMENTS (
	ID INT NOT NULL AUTO_INCREMENT,
	ISSUE_ID INT NOT NULL,
	CREATE_DATE DATE NOT NULL,
	CREATE_BY INT NOT NULL,
	FILE_NAME VARCHAR(200) NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (ISSUE_ID) REFERENCES PUBLIC.ISSUES(ID),
	FOREIGN KEY (CREATE_BY) REFERENCES PUBLIC.USERS(ID),
);