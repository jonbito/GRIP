CREATE TYPE project_role_type AS ENUM (
    'GUEST', 'REPORTER', 'DEVELOPER', 'MAINTAINER', 'OWNER'
);

CREATE TYPE org_role_type AS ENUM (
    'GUEST', 'REPORTER', 'DEVELOPER', 'MAINTAINER', 'OWNER'
    );

CREATE TYPE upload_type AS ENUM (
    'USER_AVATAR', 'PROJECT_AVATAR', 'GROUP_AVATAR'
);

CREATE TABLE IF NOT EXISTS user_account (
    id UUID PRIMARY KEY,
    email VARCHAR(128),
    first_name VARCHAR(64),
    last_name VARCHAR(64),
    role VARCHAR(256),
    starred_projects bigint[] NOT NULL DEFAULT '{}',
    created_at timestamp,
    updated_at timestamp
);

CREATE TABLE IF NOT EXISTS org (
    id bigserial PRIMARY KEY,
    name varchar(128),
    email varchar(128),
    url varchar(128),
    created_at timestamp,
    updated_at timestamp
);

CREATE TABLE IF NOT EXISTS org_invite (
    id UUID PRIMARY KEY,
    user_account_id UUID NOT NULL,
    role role_type NOT NULL,
    created_at timestamp,
    updated_at timestamp
);

CREATE TABLE IF NOT EXISTS project (
    id bigserial PRIMARY KEY,
    name VARCHAR(50),
    key VARCHAR(10),
    goal_increment BIGINT NOT NULL DEFAULT 1,
    rule_increment BIGINT NOT NULL DEFAULT 1,
    issue_increment BIGINT NOT NULL DEFAULT 1,
    lead_id UUID NOT NULL,
    org_id BIGINT NOT NULL,
    created_at timestamp,
    updated_at timestamp,
    FOREIGN KEY (lead_id) REFERENCES user_account (id) ON DELETE CASCADE,
    FOREIGN KEY (org_id) REFERENCES org (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS release (
    id bigserial PRIMARY KEY,
    project_id bigint NOT NULL,
    name VARCHAR(50),
    description VARCHAR(5000),
    planned_start date,
    planned_end date,
    actual_start date,
    actual_end date,
    created_at timestamp,
    updated_at timestamp,
    FOREIGN KEY (project_id) REFERENCES project (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS issue (
    id bigserial PRIMARY KEY,
    project_id bigint NOT NULL,
    nice_id bigint NOT NULL,
    release_id bigint,
    summary varchar(128),
    description varchar(5000),
    reporter UUID,
    assignee UUID,
    created_at timestamp,
    updated_at timestamp,
    FOREIGN KEY (project_id) REFERENCES project (id) ON DELETE CASCADE,
    FOREIGN KEY (release_id) REFERENCES release (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS goal (
     id bigserial PRIMARY KEY,
     issue_id bigint NOT NULL,
     nice_id bigint NOT NULL,
     summary varchar(128),
     description varchar(5000),
     size int,
     parent_id bigint,
     creator UUID,
     assignee UUID,
     created_at timestamp,
     updated_at timestamp,
     FOREIGN KEY (parent_id) REFERENCES goal (id) ON DELETE CASCADE,
     FOREIGN KEY (issue_id) REFERENCES issue (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS rule (
    id bigserial PRIMARY KEY,
    nice_id bigint NOT NULL,
    goal_id bigint NOT NULL,
    name varchar(128),
    description varchar(5000),
    complete_date date,
    completed_by UUID,
    created_at timestamp,
    updated_at timestamp,
    FOREIGN KEY (goal_id) REFERENCES goal (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS org_user_permission (
    user_account_id UUID NOT NULL,
    org_id BIGINT NOT NULL,
    role role_type NOT NULL,
    expiration timestamp,
    created_at timestamp,
    updated_at timestamp,
    PRIMARY KEY (user_account_id, org_id),
    FOREIGN KEY (user_account_id) REFERENCES user_account (id) ON DELETE CASCADE,
    FOREIGN KEY (org_id) REFERENCES org (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS upload (
    id UUID PRIMARY KEY,
    file_name VARCHAR(64) NOT NULL,
    file_extension VARCHAR(64) NOT NULL,
    file_type VARCHAR(64) NOT NULL,
    upload_type upload_type NOT NULL,
    user_account_id UUID,
    project_id BIGINT,
    org_id BIGINT,
    created_at timestamp,
    updated_at timestamp,
    FOREIGN KEY (user_account_id) REFERENCES user_account (id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES project (id) ON DELETE CASCADE,
    FOREIGN KEY (org_id) REFERENCES org (id) ON DELETE CASCADE
);

