

CREATE TABLE IF NOT EXISTS account (
                                       id serial PRIMARY KEY,
                                       name varchar(64),
                                       created_at timestamp,
                                       updated_at timestamp
);

CREATE TABLE IF NOT EXISTS user_account (
                                            id serial PRIMARY KEY,
                                            email varchar(64) UNIQUE,
                                            password varchar(256),
                                            fcm_registration_token varchar(256),
                                            created_at timestamp,
                                            updated_at timestamp,
                                            account_id INTEGER NOT NULL,
                                            FOREIGN KEY (account_id) REFERENCES account (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS user_token (
                                          id serial PRIMARY KEY,
                                          token text,
                                          user_id INTEGER NOT NULL,
                                          created_at timestamp,
                                          updated_at timestamp,
                                          FOREIGN KEY (user_id) REFERENCES user_account (id) ON DELETE CASCADE
);

CREATE TYPE privilege_name as ENUM (
    'ADMIN',
    'STATION',
    'STAFF'
    );

CREATE TABLE IF NOT EXISTS role (
                                    id serial PRIMARY KEY,
                                    name varchar(64),
                                    privileges privilege_name[]
);

CREATE TABLE IF NOT EXISTS role_user (
                                         user_id INTEGER NOT NULL,
                                         role_id INTEGER NOT NULL,
                                         PRIMARY KEY (user_id, role_id),
                                         FOREIGN KEY (user_id) REFERENCES user_account (id) ON DELETE CASCADE,
                                         FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE
);


/* Security */
create table IF NOT EXISTS oauth_client_details (
                                                    client_id VARCHAR(256) PRIMARY KEY,
                                                    resource_ids VARCHAR(256),
                                                    client_secret VARCHAR(256),
                                                    scope VARCHAR(256),
                                                    authorized_grant_types VARCHAR(256),
                                                    web_server_redirect_uri VARCHAR(256),
                                                    authorities VARCHAR(256),
                                                    access_token_validity INTEGER,
                                                    refresh_token_validity INTEGER,
                                                    additional_information VARCHAR(4096),
                                                    autoapprove VARCHAR(256)
);

create table IF NOT EXISTS oauth_client_token (
                                                  token_id VARCHAR(256),
                                                  token bytea,
                                                  authentication_id VARCHAR(256),
                                                  user_name VARCHAR(256),
                                                  client_id VARCHAR(256)
);

create table IF NOT EXISTS oauth_access_token (
                                                  token_id VARCHAR(256),
                                                  token bytea,
                                                  authentication_id VARCHAR(256),
                                                  user_name VARCHAR(256),
                                                  client_id VARCHAR(256),
                                                  authentication bytea,
                                                  refresh_token VARCHAR(256)
);

create table IF NOT EXISTS oauth_refresh_token (
                                                   token_id VARCHAR(256),
                                                   token bytea,
                                                   authentication bytea
);

create table IF NOT EXISTS oauth_code (
                                          code VARCHAR(256), authentication bytea
);

create table IF NOT EXISTS oauth_approvals (
                                               userId VARCHAR(256),
                                               clientId VARCHAR(256),
                                               scope VARCHAR(256),
                                               status VARCHAR(10),
                                               expiresAt TIMESTAMP,
                                               lastModifiedAt TIMESTAMP
);
