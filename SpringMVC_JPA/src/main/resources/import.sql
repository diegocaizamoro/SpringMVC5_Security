INSERT INTO `usuarios` (username, password, habilitado) VALUES ('juan','$2a$10$odG9V525rmBzfx1Yx9CyZOEW1PQAqSHQ4oEp.pXKAOmyZBexamg9O',1);
INSERT INTO `usuarios` (username, password, habilitado) VALUES ('admin','$2a$10$.8grA6uGKAQD5AXa.fp.l.nxvWuQyb0k1zzfVAS7NoE2xWJfg8gdu',1);

INSERT INTO `autorizaciones` (user_id, authority) VALUES (1,'ROLE_USER');
INSERT INTO `autorizaciones` (user_id, authority) VALUES (2,'ROLE_ADMIN');
INSERT INTO `autorizaciones` (user_id, authority) VALUES (2,'ROLE_USER');