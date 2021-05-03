CREATE TABLE social_media.tb_usuario (
	id UUID PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	senha VARCHAR(100) NOT NULL,
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	deleted_at TIMESTAMP
);

CREATE TABLE social_media.tb_publicacao (
	id UUID PRIMARY KEY,
	texto VARCHAR(2000) NOT NULL,
	criador_id UUID NOT NULL,
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	deleted_at TIMESTAMP,
	FOREIGN KEY (criador_id) REFERENCES tb_usuario(id)
);

CREATE TABLE social_media.tb_comentario (
	id UUID PRIMARY KEY,
	texto VARCHAR(100) NOT NULL,
	criador_id UUID NOT NULL,
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	deleted_at TIMESTAMP,
	FOREIGN KEY (criador_id) REFERENCES tb_usuario(id)
);

CREATE TABLE social_media.tb_publicacao_comentario (
    publicacao_id UUID NOT NULL,
    comentario_id UUID NOT NULL,
    PRIMARY KEY (publicacao_id, comentario_id)
);

CREATE TABLE social_media.tb_imagem (
	id UUID PRIMARY KEY,
	imagem VARCHAR NOT NULL,
	criador_id UUID NOT NULL,
	FOREIGN KEY (criador_id) REFERENCES tb_usuario(id)
);

CREATE TABLE social_media.tb_publicacao_imagem (
    publicacao_id UUID NOT NULL,
    imagem_id UUID NOT NULL,
    PRIMARY KEY (publicacao_id, imagem_id)
);

CREATE TABLE social_media.tb_album_foto (
	id UUID PRIMARY KEY,
	criador_id UUID NOT NULL,
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	deleted_at TIMESTAMP
);
