SELECT
    u.username,
    r.role_name AS role_name,
    p.name AS permission_name
FROM
    users u
        INNER JOIN
    user_roles ur ON u.id = ur.user_id
        INNER JOIN
    roles r ON ur.role_id = r.id
        INNER JOIN
    role_permission rp ON r.id = rp.role_id
        INNER JOIN
    permission p ON rp.permission_id = p.id;