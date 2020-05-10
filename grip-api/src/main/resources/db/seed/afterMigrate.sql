TRUNCATE TABLE user_account CASCADE;
TRUNCATE TABLE project_group CASCADE;
TRUNCATE TABLE project CASCADE;
TRUNCATE TABLE issue CASCADE;
TRUNCATE TABLE release CASCADE;
TRUNCATE TABLE goal CASCADE;
TRUNCATE TABLE user_permission_project CASCADE;
TRUNCATE TABLE user_permission_group CASCADE;
TRUNCATE TABLE upload CASCADE;

/* Sequence fixes */
SELECT setval('project_id_seq', 100, true);
SELECT setval('project_group_id_seq', 100, true);
SELECT setval('release_id_seq', 100, true);
SELECT setval('issue_id_seq', 100, true);
SELECT setval('goal_id_seq', 100, true);
SELECT setval('rule_id_seq', 100, true);

INSERT INTO user_account (id, first_name, last_name, role, username) VALUES ('4910035c-06cc-433f-a008-e14a4aeac146', 'Jonathan', 'Bishop', 'Project Manager', 'asdf');
INSERT INTO user_account (id, first_name, last_name, role, username) VALUES ('a2b05945-422f-4207-85d5-33f055e28049', 'Jonathan2', 'Bishop2', 'Project Manager', 'asdf2');

/* USER 1 */
INSERT INTO project (id, name, key, goal_increment, rule_increment, lead_id) VALUES (1, 'Seed Project 1', 'SP1', 100, 100, '4910035c-06cc-433f-a008-e14a4aeac146');
INSERT INTO project (id, name, key, goal_increment, rule_increment, lead_id) VALUES (2, 'Seed Project 2', 'SP2', 100, 100, 'a2b05945-422f-4207-85d5-33f055e28049');
INSERT INTO user_permission_project(user_account_id, project_id, role) VALUES ('4910035c-06cc-433f-a008-e14a4aeac146', 2, 'OWNER');
insert into issue (id, nice_id, summary, description, project_id, reporter) VALUES (1, 1, 'Test Summary', 'Test Description', 1, '4910035c-06cc-433f-a008-e14a4aeac146');
insert into issue (id, nice_id, summary, description, project_id, reporter) VALUES (2, 2, 'Test Summary 2', 'Test Description', 1, '4910035c-06cc-433f-a008-e14a4aeac146');
insert into goal (id, nice_id, issue_id, summary, description, size) values (1, 1, 1, 'Test Summary', 'Test Description', null);
insert into rule (id, nice_id, name, description, goal_id) values (1, 1, 'Should do something', 'GIVEN blah', 1);
INSERT INTO user_permission_project(user_account_id, project_id, role) VALUES ('4910035c-06cc-433f-a008-e14a4aeac146', 1, 'OWNER');
INSERT INTO upload (id, file_name, file_extension, file_type, upload_type, user_account_id) VALUES ('4910035c-06cc-433f-a008-e14a4aeac573', 'sephiroth', 'jpg', 'image/jpeg', 'USER_AVATAR', '4910035c-06cc-433f-a008-e14a4aeac146');

/* USER 2 */
INSERT INTO project (id, name, key, lead_id) VALUES (3, 'Seed Project 1', 'SP1', 'a2b05945-422f-4207-85d5-33f055e28049');
INSERT INTO user_permission_project(user_account_id, project_id, role) VALUES ('a2b05945-422f-4207-85d5-33f055e28049', 3, 'OWNER');
