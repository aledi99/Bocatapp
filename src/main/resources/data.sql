DROP SEQUENCE HIBERNATE_SEQUENCE;

INSERT INTO public.usuario (dtype, id, apellidos, edad, email, favoritos, fecha_creacion, nombre, password, username, validado, id_admin, last_password_changed_at, id_cliente, id_gerente, avatar_id, localizacion_id) VALUES ('A', 2, 'ADMIN', null, 'admin@admin.com', null, null, 'admin', '1234', 'admin', null, 1, null, null, null, null, null);

INSERT INTO public.usuario (dtype, id, apellidos, edad, email, favoritos, fecha_creacion, nombre, password, username, validado, id_admin, last_password_changed_at, id_cliente, id_gerente, avatar_id, localizacion_id) VALUES ('B', 3, 'GERENTE', null, 'gerente@gerente.com', null, null, 'gerente', '1234', 'gerente', true, 1, null, null, 10, null, null);

INSERT INTO public.categoria (id, nombre) VALUES (30, 'Bocatería');

INSERT INTO public.establecimiento (id, abierto, descripcion, hora_apertura, hora_cierre, nombre, presupuesto, valoracion, categoria_id, gerente_id, imagen_id, localizacion_id) VALUES (20, true, 'Badulake de Olivares', null, null, 'El Badulake', 50000, 2.5, 30, 3, null, null);

