--all passwords -> 123mudar
INSERT INTO tb_usuario (id, nome, email, senha, created_at, updated_at, deleted_at) VALUES
('f9196784-68c2-4c77-925f-697736bfa4be', 'PIETRO', 'ADMIN@SECURITY.COM', '$2a$10$HbkcKvHkftuxfuvufZ/dy.YSrBc2F.aQxmT4VzFD1aE2XPQC6Nviu', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL)
ON CONFLICT DO NOTHING;