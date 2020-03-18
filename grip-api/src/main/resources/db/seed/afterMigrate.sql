TRUNCATE TABLE user_account CASCADE;
TRUNCATE TABLE project CASCADE;
TRUNCATE TABLE user_permission_project CASCADE;

INSERT INTO user_account (id, role, username) VALUES ('4910035c-06cc-433f-a008-e14a4aeac146', 'Project Manager', 'asdf');
