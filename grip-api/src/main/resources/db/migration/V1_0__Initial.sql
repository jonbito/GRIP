CREATE TYPE role_type AS ENUM (
    'GUEST', 'REPORTER', 'DEVELOPER', 'MAINTAINER', 'OWNER'
);

CREATE TYPE upload_type AS ENUM (
    'USER_AVATAR', 'PROJECT_AVATAR', 'GROUP_AVATAR'
);

CREATE TABLE IF NOT EXISTS user_account (
    id UUID PRIMARY KEY,
    username VARCHAR(128),
    first_name VARCHAR(64),
    last_name VARCHAR(64),
    role VARCHAR(256),
    created_at timestamp,
    updated_at timestamp
);

CREATE TABLE IF NOT EXISTS project_group (
     id serial PRIMARY KEY,
     name VARCHAR(64),
     key VARCHAR(64),
     description VARCHAR(256),
     created_at timestamp,
     updated_at timestamp
);

CREATE TABLE IF NOT EXISTS project (
    id serial PRIMARY KEY,
    name VARCHAR(50),
    key VARCHAR(10),
    lead_id UUID NOT NULL,
    owner_group_id INTEGER,
    created_at timestamp,
    updated_at timestamp,
    created_by UUID,
    updated_by UUID,
    FOREIGN KEY (lead_id) REFERENCES user_account (id) ON DELETE CASCADE,
    FOREIGN KEY (owner_group_id) REFERENCES project_group (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS user_permission_group (
    user_account_id UUID NOT NULL,
    group_id INTEGER NOT NULL,
    role role_type NOT NULL,
    expiration timestamp,
    created_at timestamp,
    updated_at timestamp,
    created_by UUID,
    updated_by UUID,
    FOREIGN KEY (user_account_id) REFERENCES user_account (id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES project_group (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS user_permission_project (
   user_account_id UUID NOT NULL,
   project_id integer NOT NULL,
   role role_type NOT NULL,
   expiration timestamp,
   created_at timestamp,
   updated_at timestamp,
   created_by UUID,
   updated_by UUID,
   PRIMARY KEY (user_account_id, project_id),
   FOREIGN KEY (user_account_id) REFERENCES user_account (id) ON DELETE CASCADE,
   FOREIGN KEY (project_id) REFERENCES project (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS upload (
    id serial PRIMARY KEY,
    file_name VARCHAR(64) NOT NULL,
    file_type VARCHAR(64) NOT NULL,
    upload_type upload_type NOT NULL,
    referenced_id VARCHAR(32) NOT NULL,
    created_at timestamp,
    updated_at timestamp
);

/* Sequence fixes */
SELECT setval('project_id_seq', 100, true);
SELECT setval('upload_id_seq', 100, true);
SELECT setval('project_group_id_seq', 100, true);
