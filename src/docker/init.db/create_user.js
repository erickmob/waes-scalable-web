db.createUser(
    {
        user: "waes_user",
        pwd: "waes_pwd",
        roles:[
            {
                role: "readWrite",
                db:   "waes_api"
            }
        ]
    }
);