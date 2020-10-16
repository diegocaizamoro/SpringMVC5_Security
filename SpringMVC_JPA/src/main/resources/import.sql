INSERT INTO `usuarios` (username, password, habilitado) VALUES ('juan','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG',1);
INSERT INTO `usuarios` (username, password, habilitado) VALUES ('admin','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);

INSERT INTO `autorizaciones` (user_id, authority) VALUES (1,'ROLE_USER');
INSERT INTO `autorizaciones` (user_id, authority) VALUES (2,'ROLE_ADMIN');
INSERT INTO `autorizaciones` (user_id, authority) VALUES (2,'ROLE_USER');