ALTER TABLE invitations
    ADD COLUMN token_hash VARCHAR(64) NULL;

CREATE UNIQUE INDEX uk_invitations_token_hash
    ON invitations(token_hash);