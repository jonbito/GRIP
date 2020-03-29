TRUNCATE TABLE user_account CASCADE;
TRUNCATE TABLE project_group CASCADE;
TRUNCATE TABLE project CASCADE;
TRUNCATE TABLE user_permission_project CASCADE;
TRUNCATE TABLE user_permission_group CASCADE;
TRUNCATE TABLE upload CASCADE;

INSERT INTO user_account (id, first_name, last_name, role, username) VALUES ('4910035c-06cc-433f-a008-e14a4aeac146', 'Jonathan', 'Bishop', 'Project Manager', 'asdf');
INSERT INTO project (id, name, key, lead_id, owner_user_id) VALUES (1, 'Seed Project 1', 'SP1', '4910035c-06cc-433f-a008-e14a4aeac146', '4910035c-06cc-433f-a008-e14a4aeac146');
INSERT INTO user_permission_project(user_account_id, project_id, role) VALUES ('4910035c-06cc-433f-a008-e14a4aeac146', 1, 'OWNER');
INSERT INTO upload (id, file_name, file_type, upload_type, referenced_id) VALUES (1, 'sephiroth.png', 'image/png', 'USER_AVATAR', '1');
