# Hotel Management Application
*From Version 0.8.6 onwards, data model structure improvement.*
## Setting Up Testing Environment
Install ***json-server*** through npm.
```
npm install -g json-server
```
Then create the json files:
\
\
**Guestbook.json**
```json
{
    "booking": [
        {
            "bookingID": "1639872139204",
            "firstName": "John",
            "lastName": "Wicked",
            "phoneNumber": "0980000011",
            "address": {
                "country": "Thailand",
                "firstRow": "25 Broadway",
                "region": "Bangkok",
                "secondRow": "10000"
            },
            "verificationID": "91203812411",
            "arrivalDate": "01-02-2023",
            "departDate": "02-03-2025",
            "adultCount": 2,
            "childCount": 0,
            "paymentType": "CA",
            "guestStatus": "RSC",
            "guestPass": {
                "password": "E349823JFSF0DK2",
                "userName": "943JDC12"
            },
            "guestRoom": {
                "roomID": "000",
                "roomType": "DX",
                "roomBeds": "DB",
                "maxCap": 2,
                "posFloor": "G",
                "features": [
                    "AL",
                    "CR"
                ],
                "addonBed": false,
                "breakfast": true,
                "smoking": false,
                "roomStatus": "RS",
                "isWalking": true,
                "deviceList": [
                    "Serial_1",
                    "Serial_2"
                ],
                "roomPrice": 1000.10
            }
        },
        {
            "bookingID": "1639924905347",
            "firstName": "John",
            "lastName": "Cena",
            "phoneNumber": "+991001000",
            "address": {
                "country": "Dreamland",
                "firstRow": "Icecream Rd.",
                "region": "Soda Island",
                "secondRow": "POYO"
            },
            "verificationID": "ABC123456789",
            "arrivalDate": "01-05-2023",
            "departDate": "02-05-2023",
            "adultCount": 2,
            "childCount": 1,
            "paymentType": "CD",
            "guestStatus": "RSC",
            "guestPass": {},
            "guestRoom": {}
        },
        {
            "bookingID": "1639926914880",
            "firstName": "Arisu",
            "lastName": "Tendou",
            "phoneNumber": "0894547145",
            "address": {
                "country": "Greece",
                "firstRow": "17/999 Kivotos",
                "region": "Thessaloniki",
                "secondRow": "MI114"
            },
            "verificationID": "XX10293102381",
            "arrivalDate": "01-01-2022",
            "departDate": "08-01-2022",
            "adultCount": 3,
            "childCount": 0,
            "paymentType": "CD",
            "guestStatus": "RSC",
            "guestPass": {},
            "guestRoom": {}
        },
        {
            "bookingID": "1640008163515",
            "firstName": "Zenos",
            "lastName": "yae Galvus",
            "phoneNumber": "0862662626",
            "address": {
                "country": "DE",
                "firstRow": "18/6 Babil Towers",
                "region": "Garlemaldstadt",
                "secondRow": "ST 06918"
            },
            "verificationID": "FFX14715348A",
            "arrivalDate": "29-12-2021",
            "departDate": "01-01-2022",
            "adultCount": 1,
            "childCount": 0,
            "paymentType": "EP",
            "guestStatus": "RSC",
            "guestPass": {},
            "guestRoom": {
                "roomID": "000",
                "roomType": "SD",
                "roomBeds": "SB",
                "maxCap": 2,
                "posFloor": "G",
                "roomStatus": "",
                "isWalking": true,
                "deviceList": [
                    "Serial_10",
                    "Serial_20"
                ],
                "roomPrice": 1000.1
            }
        },
        {
            "bookingID": "1644245558580",
            "firstName": "Fname",
            "lastName": "Lname",
            "phoneNumber": "1234567890",
            "address": {
                "country": "country",
                "firstRow": "firstrow",
                "region": "region",
                "secondRow": "secondrow"
            },
            "verificationID": "123456789ABC",
            "arrivalDate": "10-10-1900",
            "departDate": "10-10-1910",
            "adultCount": 2,
            "childCount": 2,
            "paymentType": "",
            "guestStatus": "ERR",
            "guestPass": {
                "userName": "gpass_username",
                "password": "gpass_pwd"
            },
            "guestRoom": {
                "roomID": "00X",
                "roomType": "N/A",
                "roomBeds": "N/A",
                "maxCap": 0,
                "posFloor": "N/A",
                "roomStatus": "N/A",
                "isWalking": false,
                "deviceList": [
                    "Serial_0X",
                    "Serial_00"
                ],
                "roomPrice": 555.55
            }
        },
        {
            "address": {
                "country": "",
                "firstRow": "",
                "region": "",
                "secondRow": ""
            },
            "arrivalDate": "00-00-0000",
            "bookingID": "",
            "departDate": "00-00-0000",
            "adultCount": 0,
            "childCount": 0,
            "firstName": "Polo",
            "guestStatus": "CRE",
            "lastName": "Solo",
            "paymentType": "Visa",
            "phoneNumber": "",
            "verificationID": ""
        }
    ]
}
```
\
**User.json**
```json
{
    "user": [
        {
            "userID": "0",
            "displayName": "Manager: Bezos",
            "password": "legit",
            "userName": "bezos",
            "userType": "manager"
        },
        {
            "userID": "10",
            "displayName": "Front Staff: Siri",
            "password": "hey",
            "userName": "siri",
            "userType": "staff"
        },
        {
            "userID": "11",
            "displayName": "Front Staff: Cortana",
            "password": "hey",
            "userName": "cortana",
            "userType": "staff"
        }
    ]
}
```
\
**Property.json**
```json
{
    "room": [
        {
            "roomID": "000",
            "roomType": "SD",
            "roomBeds": "SB",
            "maxCap": 2,
            "posFloor": "G",
            "features": [
                "AL",
                "CR"
            ],
            "addonBed": false,
            "breakfast": true,
            "smoking": false,
            "roomStatus": "RS",
            "isWalking": false,
            "deviceList": [
                "Serial_1",
                "Serial_2"
            ],
            "roomPrice": 1000.10
        },
        {
            "roomID": "001",
            "roomType": "DX",
            "roomBeds": "DB",
            "maxCap": 2,
            "posFloor": "B1",
            "features": [
                "AL",
                "CX"
            ],
            "addonBed": false,
            "breakfast": true,
            "smoking": false,
            "roomStatus": "NF",
            "isWalking": false,
            "deviceList": [
                "Serial_1",
                "Serial_2",
                "Serial_3"
            ],
            "roomPrice": 2000.35
        },
        {
            "roomID": "002",
            "roomType": "SE",
            "roomBeds": "SB",
            "maxCap": 3,
            "posFloor": "B2",
            "features": [
                "CX"
            ],
            "addonBed": false,
            "breakfast": true,
            "smoking": false,
            "roomStatus": "NC",
            "isWalking": false,
            "deviceList": [
                "Serial_20",
                "Serial_0"
            ],
            "roomPrice": 3500.10
        }
    ]
}
```

## Running the Test Environment

### User.json
Used to authenticate staff's login.
```
json-server --watch User.json --id userID --port 3000
```

### Guestbook.json
Used to manage bookings.
```
json-server --watch Guestbook.json --id bookingID --port 3001
```

### Property.josn
Used to manage rooms.
```
json-server --watch Property.json --id roomID --port 3002
```

## Appendix
For Windows user, check the port status by using:
```
netstat -an | find "{port}"
```
*{port} is the target port number.*
