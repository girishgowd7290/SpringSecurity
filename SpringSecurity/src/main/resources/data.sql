INSERT INTO  users(username,password,enabled) VALUES ('Girish', 'Gowda', true);
INSERT INTO  users(username,password,enabled) VALUES ('Sumana', 'Gumma', true);

INSERT INTO  authorities(username, authority) VALUES ('Girish', 'ROLE_ADMIN');
INSERT INTO  authorities(username, authority) VALUES ('Sumana', 'ROLE_USER');