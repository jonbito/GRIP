TRUNCATE user_token CASCADE;
TRUNCATE role_user CASCADE;
TRUNCATE role CASCADE;
TRUNCATE user_account CASCADE;
TRUNCATE account CASCADE;

INSERT INTO account (id, name) VALUES (1, 'Test Account') ON CONFLICT DO NOTHING;
INSERT INTO user_account (id, email, password, account_id) VALUES (1, 'r3tnull@gmail.com', '$2a$10$4ABavb9L4VCubCuroQNlveQsMpYN8Ytj7ZoR4reI0v9j6bddiG40S', 1) ON CONFLICT DO NOTHING;
INSERT INTO role (id, name, privileges) VALUES (1, 'admin', '{"ADMIN"}') ON CONFLICT DO NOTHING;
INSERT INTO role (id, name, privileges) VALUES (2, 'station', '{"STATION"}') ON CONFLICT DO NOTHING;
INSERT INTO role (id, name, privileges) VALUES (3, 'staff', '{"STAFF"}') ON CONFLICT DO NOTHING;
INSERT INTO role_user (user_id, role_id) VALUES (1, 1) ON CONFLICT DO NOTHING;

TRUNCATE oauth_client_details CASCADE;
INSERT INTO oauth_client_details (client_id, client_secret, scope, access_token_validity, authorized_grant_types)
VALUES ('mobile', '$2a$10$pClimgQ7zaXWj5k5yV4.cOb7CPPDffYMA7jaIPFgCboJOJWN5Zee2', 'read,write', 3600, 'password,refresh_token') ON CONFLICT DO NOTHING;

/* Sequence fixes */
SELECT setval('account_id_seq', 1, true);
SELECT setval('user_account_id_seq', 1, true);
SELECT setval('role_id_seq', 1, true);
