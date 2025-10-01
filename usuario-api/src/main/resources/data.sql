INSERT INTO usuarios (email, senha, perfil) VALUES 
('admin@comex.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.', 'ROLE_ADMIN'),
('joao.silva@example.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.', 'ROLE_CLIENTE'),
('maria.oliveira@example.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.', 'ROLE_CLIENTE');

INSERT INTO clientes (cpf, nome, email, usuario_id) VALUES
('12345678900', 'João Silva', 'joao.silva@example.com', 2),
('98765432100', 'Maria Oliveira', 'maria.oliveira@example.com', 3);