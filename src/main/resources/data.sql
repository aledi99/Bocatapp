<<<<<<< HEAD
INSERT INTO public.usuario (id,apellidos, edad, email, favoritos, fecha_creacion, last_password_changed_at, nombre, password, username, validado, avatar_id, localizacion_id) VALUES (3,'DIAZ SANTOS', 20, 'ALEDIAZUNI@GMAIL.COM', null, null, null, 'ALEJANDRO', '1234', 'ALEDI99', true, null, null);
=======
DROP SEQUENCE HIBERNATE_SEQUENCE;

INSERT INTO public.usuario (dtype, id, apellidos, edad, email, favoritos, fecha_creacion, nombre, password, username, validado, id_admin, last_password_changed_at, id_cliente, id_gerente, avatar_id, localizacion_id) VALUES ('A', 2, 'ADMIN', null, 'admin@admin.com', null, null, 'admin', '1234', 'admin', null, 1, null, null, null, null, null);
>>>>>>> master
