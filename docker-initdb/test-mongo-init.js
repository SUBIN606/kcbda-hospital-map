db = db.getSiblingDB('admin');

db.auth('root', 'password');

db = db.getSiblingDB('test');

db.createUser({
    user: "testUser",
    pwd: "password",
    roles: [
        {
            role: "readWrite",
            database: "test"
        }
    ]
})
