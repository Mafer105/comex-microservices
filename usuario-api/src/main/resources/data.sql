INSERT INTO usuarios (nome, email, senha, perfil,ativo) VALUES 
('Administrador','admin@comex.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.', 'ROLE_ADMIN', true),
('Jõao Silva','joao.silva@example.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.', 'ROLE_CLIENTE', true),
('Maria Oliveira','maria.oliveira@example.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.', 'ROLE_CLIENTE', true);

INSERT INTO clientes (cpf, nome, email, usuario_id) VALUES
('12345678900', 'João Silva', 'joao.silva@example.com', 2),
('98765432100', 'Maria Oliveira', 'maria.oliveira@example.com', 3);