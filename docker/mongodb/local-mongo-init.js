db = db.getSiblingDB('admin');

db.auth('root', 'password');

db = db.getSiblingDB('data');

db.createUser({
    user: "dataUser",
    pwd: "password",
    roles: [
        {
            role: "readWrite",
            db: "data"
        }
    ]
});

db.hospital.insertMany([
    {
        name: "일산동물의료원",
        location: {
            type: "Point",
            coordinates: [ 126.7538462, 37.6824438 ]
        }
    },
    {
        name: "24시 동탄 이음동물의료센터",
        location: {
            type: "Point",
            coordinates: [ 127.1084327, 37.1687388 ]
        }
    },
    {
        name: "로뎀나무 동물의료센터",
        location: {
            type: "Point",
            coordinates: [ 127.0874635, 37.7913632 ]
        }
    },
    {
        name: "다솜동물메디컬센터",
        location: {
            type: "Point",
            coordinates: [ 129.0692475, 35.1374031 ]
        }
    },
    {
        name: "광주동물메디컬센터",
        location: {
            type: "Point",
            coordinates: [ 126.8579669, 35.1901743 ]
        }
    },
    {
        name: "에이드동물병원",
        location: {
            type: "Point",
            coordinates: [ 127.0276404, 37.521251 ]
        }
    },
    {
        name: "제주대학교 수의과대학 부설동물병원",
        location: {
            type: "Point",
            coordinates: [ 126.5588703, 33.4513314 ]
        }
    },
    {
        name: "서울대학교 수의대부속동물병원",
        location: {
            type: "Point",
            coordinates: [ 126.95315, 37.4680952 ]
        }
    },
    {
        name: "충남대학교 부속동물병원",
        location: {
            type: "Point",
            coordinates: [ 127.3429253, 36.3771814 ]
        }
    },
    {
        name: "24시 청주 고려동물메디컬센터",
        location: {
            type: "Point",
            coordinates: [ 127.4745744, 36.6352007 ]
        }
    },
    {
        name: "24시 청주 이음동물의료센터",
        location: {
            type: "Point",
            coordinates: [ 127.4230666, 36.6353492 ]
        }
    },
    {
        name: "천안24시스카이동물메디컬센터",
        location: {
            type: "Point",
            coordinates: [ 127.1417625, 36.8258776 ]
        }
    },
    {
        name: "울산24시 에스동물메디컬센터",
        location: {
            type: "Point",
            coordinates: [ 129.3248851, 35.5364788 ]
        }
    },
    {
        name: "원헬스동물의료센터",
        location: {
            type: "Point",
            coordinates: [ 129.2663093, 35.5520628 ]
        }
    },
    {
        name: "대구24시바른동물의료센터",
        location: {
            type: "Point",
            coordinates: [ 128.5375553, 35.8453039 ]
        }
    },
    {
        name: "24시 범어동물의료센터",
        location: {
            type: "Point",
            coordinates: [  128.6199102, 35.8592184 ]
        }
    },
    {
        name: "탑스동물메디컬센터",
        location: {
            type: "Point",
            coordinates: [128.5320901, 35.816601]
        }
    }
]);

