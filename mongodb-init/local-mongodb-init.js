db.createUser({
    user: "root",
    pwd: "password",
    roles: [
        {
            role: "readWrite",
            database: "data"
        }
    ]
})
