INSERT INTO karel.users(user_id, username, firstname, lastname, email, phone, password)
    OVERRIDING SYSTEM VALUE VALUES (0, 'user1', 'userFN', 'userLN', 'userEmail', '012345', 'userPass');