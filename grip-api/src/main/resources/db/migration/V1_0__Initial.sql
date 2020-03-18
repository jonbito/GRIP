CREATE TYPE role AS ENUM (
    'GUEST', 'REPORTER', 'DEVELOPER', 'MAINTAINER', 'ADMIN', 'OWNER'
);

CREATE TABLE IF NOT EXISTS user_account (
    id UUID PRIMARY KEY,
    username VARCHAR(128),
    role VARCHAR(256),
    created_at timestamp,
    updated_at timestamp
);

CREATE TABLE IF NOT EXISTS project (
    id serial PRIMARY KEY,
    name VARCHAR(50),
    key VARCHAR(10),
    created_at timestamp,
    updated_at timestamp,
    created_by UUID,
    updated_by UUID
);

CREATE TABLE IF NOT EXISTS user_permission_project (
   user_account_id UUID NOT NULL,
   project_id integer NOT NULL,
   role role NOT NULL,
   expiration timestamp,
   created_at timestamp,
   updated_at timestamp,
   created_by UUID,
   updated_by UUID,
   PRIMARY KEY (user_account_id, project_id),
   FOREIGN KEY (user_account_id) REFERENCES user_account (id) ON DELETE CASCADE,
   FOREIGN KEY (project_id) REFERENCES project (id) ON DELETE CASCADE
);

/* Sequence fixes */
SELECT setval('project_id_seq', 1, true);
