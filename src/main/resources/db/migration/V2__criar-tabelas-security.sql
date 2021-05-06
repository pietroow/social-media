CREATE TABLE social_media.tb_authority (
	id UUID PRIMARY KEY,
	code VARCHAR(50),
	description VARCHAR(50) NOT NULL
);

CREATE TABLE social_media.tb_user_authority (
	user_id UUID NOT NULL,
	authority_id UUID NOT NULL,
	PRIMARY KEY (user_id, authority_id),
	FOREIGN KEY (user_id) REFERENCES tb_usuario(id),
	FOREIGN KEY (authority_id) REFERENCES tb_authority(id)
);