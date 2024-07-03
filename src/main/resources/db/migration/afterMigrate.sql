DELETE FROM product;
DELETE FROM supplier;
DELETE FROM consumer;

ALTER SEQUENCE supplier_id_seq RESTART WITH 1;
ALTER SEQUENCE consumer_id_seq RESTART WITH 1;
ALTER SEQUENCE product_id_seq RESTART WITH 1;

INSERT INTO supplier (name) VALUES
    ('Sensual Pleasures'),
    ('Passionate Desires'),
    ('Intimate Moments'),
    ('Eros Essentials'),
    ('Silk Seductions'),
    ('Desire Delights'),
    ('Pleasure Palace'),
    ('Lustful Lingerie'),
    ('Fetish Fantasy'),
    ('Seductive Secrets');

INSERT INTO consumer (
    name, gender, sexual_orientation, date_of_birth, phone_number, email, cpf,
    address_cep, address_public_place, address_number, address_complement,
    address_district, address_city, address_state
) VALUES

('Alice', 'WOMAN', 'STRAIGHT', '1990-05-15', '(11) 98765-4321',
'alice@example.com', '123.456.789-01', '12345-678', 'Rua das Flores', '123',
'Apto 101', 'Centro', 'São Paulo', 'SP'),

('Bob', 'MAN', 'GAY', '1985-08-20', '(21) 91234-5678', 'bob@example.com', '987.654.321-00',
 '54321-876', 'Avenida Principal', '456', 'Casa', 'Centro', 'Rio de Janeiro', 'RJ'),

('Carol', 'WOMAN', 'BISEXUAL', '1992-03-10', '(31) 92345-6789', 'carol@example.com', '234.567.890-12',
 '67890-123', 'Travessa das Palmeiras', '789', '', 'Jardins', 'Belo Horizonte', 'MG'),

('David', 'MAN', 'STRAIGHT', '1988-11-25', '(41) 93456-7890', 'david@example.com', '345.678.901-23',
 '98765-432', 'Rua das Pedras', '987', '', 'Centro', 'Curitiba', 'PR'),

('Emma', 'WOMAN', 'PANSEXUAL', '1995-07-05', '(51) 94567-8901', 'emma@example.com', '456.789.012-34',
 '65432-109', 'Alameda das Árvores', '234', 'Bloco C', 'Itaim', 'Porto Alegre', 'RS'),

('Frank', 'MAN', 'STRAIGHT', '1980-01-30', '(61) 95678-9012', 'frank@example.com', '567.890.123-45',
 '21098-765', 'Praça da Liberdade', '567', 'Apto 501', 'Centro', 'Brasília', 'DF'),

('Gina', 'WOMAN', 'LESBIAN', '1998-09-12', '(71) 96789-0123', 'gina@example.com', '678.901.234-56',
 '10987-654', 'Rua dos Pinheiros', '876', '', 'Jardim América', 'Salvador', 'BA'),

('Henry', 'MAN', 'STRAIGHT', '1983-04-18', '(81) 97890-1234', 'henry@example.com', '789.012.345-67',
 '32109-876', 'Estrada do Sol', '321', '', 'Boa Viagem', 'Recife', 'PE'),

('Iris', 'WOMAN', 'BISEXUAL', '1993-06-22', '(91) 98901-2345', 'iris@example.com', '890.123.456-78',
 '87654-321', 'Avenida dos Ipês', '543', 'Bloco D', 'Setor Oeste', 'Goiânia', 'GO'),

('Jack', 'MAN', 'STRAIGHT', '1987-02-14', '(99) 99012-3456', 'jack@example.com', '901.234.567-89',
 '54321-210', 'Rua das Mangueiras', '987', '', 'Centro', 'Manaus', 'AM');

INSERT INTO product (
    name, description, color, brand, flavor, sensation, category, sub_category, target_audience,
    size, fabric, additional_information, quantity, stock_quantity, cost_price, sale_price,
    supplier_id
) VALUES

('Vibrador Clássico', 'Vibrador clássico com várias velocidades.', 'Rosa', 'Lovetoys', NULL,
'Vibração', 'Brinquedos', 'Vibradores', 'Adultos', 'Médio', 'Silicone', 'À prova dagua', 50, 40,
29.90, 59.90, 1),

('Gel Lubrificante', 'Gel lubrificante com sabor morango.', 'Transparente', 'Durex', 'Morango',
'Refrescante', 'Lubrificantes', 'Comestíveis', 'Adultos', '100ml', NULL, 'Comestível', 100, 85,
9.90, 19.90, 2),

('Camisinha Texturizada', 'Camisinha com textura para maior prazer.', 'Transparente', 'Jontex',
NULL, 'Textura', 'Preservativos', 'Texturizados', 'Adultos', 'Padrão', 'Látex', 'Lubrificada', 200,
150, 4.50, 7.90, 3),

('Plug Anal', 'Plug anal com base larga para segurança.', 'Preto', 'Sensualove', NULL, 'Prazer',
'Brinquedos', 'Plugs', 'Adultos', 'Pequeno', 'Silicone',
'Compatível com lubrificantes à base de água', 30, 20, 19.90, 39.90, 1),

('Calcinha comestível', 'Calcinha comestível sabor cereja.', 'Vermelho', 'LoveHoney', 'Cereja',
'Doce', 'Roupas Íntimas', 'Comestíveis', 'Adultos', 'Único', 'Gelatina', 'Sabor natural', 25, 15,
14.90, 29.90, 2),

('Anel Peniano', 'Anel peniano com vibração.', 'Azul', 'Durex', NULL, 'Vibração', 'Brinquedos',
'Anéis', 'Adultos', 'Ajustável', 'Silicone', 'Bateria incluída', 60, 45, 9.90, 19.90, 3),

('Fantasia de Enfermeira', 'Fantasia de enfermeira para jogos sensuais.', 'Branco', 'Fantasy',
NULL, 'Roleplay', 'Roupas Íntimas', 'Fantasias', 'Adultos', 'Médio', 'Poliéster', 'Com acessórios',
15, 10, 49.90, 99.90, 1),

('Vela de Massagem', 'Vela de massagem aromática.', 'Roxo', 'Kissable', 'Lavanda', 'Relaxante',
'Cosméticos', 'Velas', 'Adultos', '200g', NULL, 'Aromaterapia', 20, 10, 24.90, 49.90, 2),

('Bolinhas Tailandesas', 'Bolinhas tailandesas para exercícios íntimos.', 'Rosa', 'Fun Factory',
 NULL, 'Prazer', 'Brinquedos', 'Bolinhas', 'Adultos', 'Pequeno', 'Silicone', 'Fácil de limpar', 35,
 25, 12.90, 25.90, 3),

('Fetiche Algema', 'Algemas para práticas de fetiche.', 'Prata', 'Secret Love', NULL, 'Dominação',
'Acessórios', 'Fetiches', 'Adultos', 'Ajustável', 'Metal', 'Com chaves de segurança', 40, 30,
15.90, 31.90, 1);
