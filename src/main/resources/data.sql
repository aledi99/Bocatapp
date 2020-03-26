DROP SEQUENCE HIBERNATE_SEQUENCE;


INSERT INTO public.usuario (dtype, id, apellidos, edad, email, favoritos, fecha_creacion, nombre, password, username, validado, id_admin, last_password_changed_at, id_cliente, id_gerente, tlf_contacto, avatar_id, localizacion_id, establecimiento_id) VALUES ('A', 2, 'ADMIN', null, 'admin@admin.com', null, null, 'admin', '1234', 'admin', null, 1, null, null, null, null, null, null, null);

INSERT INTO public.categoria (id, nombre) VALUES (30, 'Bocatería');

INSERT INTO public.ubicacion (id, latitud, longitud) VALUES (200, 5, -3);

INSERT INTO public.establecimiento (id, abierto, descripcion, fav, hora_apertura, hora_cierre, nombre, presupuesto, valoracion, categoria_id, gerente_id, imagen_id, localizacion_id) VALUES (20, true, 'Badulake de Olivares', false, null, null, 'El Badulake', 50000, 2.5, 30, null, null, 200);


INSERT INTO public.producto (id, activo, descripcion, gluten, lactosa, nombre, precio, establecimiento_id, imagen_id) VALUES (55, true, 'Hola', false, false, 'Dorayaki', 25, 20, null);


create sequence hibernate_sequence start with 1 increment by 50;


INSERT INTO public.usuario (dtype, id, apellidos, edad, email, favoritos, fecha_creacion, nombre, password, username, validado, id_admin, last_password_changed_at, id_cliente, id_gerente, tlf_contacto, avatar_id, localizacion_id, establecimiento_id) VALUES ('B', 3, 'GERENTE', null, 'gerente2@gerente.com', null, null, 'gerente', '1234', 'gerente', true, 1, null, null, 10, '628796176', null, null, 20);



