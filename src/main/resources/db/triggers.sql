create trigger IssueTrigger 
AFTER INSERT
ON ADMIN.ISSUES
referencing new as newIssue
FOR EACH ROW MODE DB2SQL 
begin atomic
        set newIssue.create_date    = CURRENT_DATE;
        set newIssue.modify_date   = CURRENT_DATE;

end;


create trigger IssueTrigger NO CASCADE BEFORE insert 
ON ADMIN.ISSUES
REFERENCING NEW AS newRow
FOR EACH ROW MODE DB2SQL
set newRow.create_date = CURRENT_DATE;

/*This trigger work */
create trigger IssueTrigger 
AFTER INSERT
ON ADMIN.ISSUES
referencing new as newIssue
FOR EACH ROW MODE DB2SQL
UPDATE ISSUES SET CREATE_DATE = CURRENT_DATE, modify_date = CURRENT_DATE


CREATE TRIGGER ISSUETRIGGER NO CASCADE BEFORE INSERT ON ISSUES
REFERENCING NEW AS NEWISSUE
FOR EACH ROW MODE DB2SQL
(CREATE_DATE) VALUES (CURRENT_DATE);
