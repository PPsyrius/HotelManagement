# Hotel Management Application
*From Version 0.8.1a onwards, Firebase is obsolete.*
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
      "paymentType": "Visa",
      "guestStatus": "CheckedIn",
      "guestPass": {
        "userName": "943JDC12",
        "password": "E349823JFSF0DK2"
      },
      "guestRoom": {
        "roomID": "002",
        "roomType": "suite",
        "roomBeds": "single",
        "maxCap": 3,
        "posFloor": "B2",
        "posMap": "corner",
        "roomStatus": {
          "displayName": "To Clean",
          "internalCode": "C0"
        },
        "isWalking": false,
        "deviceList": [
          {
            "deviceName": "Dev1"
          },
          {
            "deviceName": "Dev2"
          },
          {
            "deviceName": "Dev3"
          },
          {
            "deviceName": "Dev4"
          },
          {
            "deviceName": "Dev5"
          }
        ],
        "roomPrice": 3500.1
      },
      "id": 0
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
      "paymentType": "Visa",
      "guestStatus": "Booked",
      "guestPass": {},
      "guestRoom": {},
      "id": 1
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
      "paymentType": "Master Card",
      "guestStatus": "Booked",
      "guestPass": {},
      "guestRoom": {},
      "id": 2
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
      "paymentType": "Cash",
      "guestStatus": "Booked",
      "guestPass": {},
      "guestRoom": {
        "roomID": "000",
        "roomType": "standard",
        "roomBeds": "single",
        "maxCap": 2,
        "posFloor": "G",
        "posMap": "corner",
        "roomStatus": {
          "displayName": "Ready",
          "internalCode": "99"
        },
        "isWalking": true,
        "deviceList": [
          {
            "deviceName": "Dev1"
          },
          {
            "deviceName": "Dev2"
          }
        ],
        "roomPrice": 1000.1
      },
      "id": 3
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
      "arrivalDate": "DD-MM-YYYY",
      "departDate": "DD-MM-YYYY",
      "paymentType": "Visa",
      "guestStatus": "N/A",
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
        "posMap": "N/A",
        "roomStatus": {
          "displayName": "N/A",
          "internalCode": "N/A"
        },
        "isWalking": false,
        "deviceList": [
          {
            "deviceName": "Dev1"
          },
          {
            "deviceName": "Dev2"
          }
        ],
        "roomPrice": 555.55
      },
      "id": 4
    },
    {
      "address": {
        "country": "",
        "firstRow": "",
        "region": "",
        "secondRow": ""
      },
      "arrivalDate": "Start Date",
      "bookingID": "",
      "departDate": "End Date",
      "firstName": "Polo",
      "guestStatus": "created",
      "lastName": "Solo",
      "paymentType": "Visa",
      "phoneNumber": "",
      "verificationID": "",
      "id": 5
    }
  ]
}
```
\
**User.json**
```json
{
  "user" : [
    {
      "userID" : "0",
      "displayName" : "Manager: Bezos",
      "password" : "legit",
      "userName" : "bezos",
      "userType" : "manager",
      "id" : 0
    },
    {
      "userID" : "10",
      "displayName" : "Front Staff: Siri",
      "password" : "hey",
      "userName" : "siri",
      "userType" : "staff",
      "id" : 1
    },
    {
      "userID" : "11",
      "displayName" : "Front Staff: Cortana",
      "password" : "hey",
      "userName" : "cortana",
      "userType" : "staff",
      "id" : 2
    }
  ]
}
```
\
**Property.json**
```json
{
  "room" : [
    {
      "roomID" : "000",
      "roomType" : "standard",
      "roomBeds" : "single",
      "maxCap" : 2,
      "posFloor" : "G",
      "posMap" : "corner",
      "roomStatus" : {
        "displayName" : "Ready",
        "internalCode" : "99"
      },
      "isWalking" : true,
      "deviceList" : [
        {"deviceName" : "Dev1"},
        {"deviceName" : "Dev2"}
      ],
      "roomPrice" : 1000.10,
      "id" : 0
    },
    {
      "roomID" : "001",
      "roomType" : "deluxe",
      "roomBeds" : "double",
      "maxCap" : 2,
      "posFloor" : "B1",
      "posMap" : "sandwich",
      "roomStatus" : {
        "displayName" : "Needs Maintenance",
        "internalCode" : "1F"
      },
      "isWalking" : false,
      "deviceList" : [
        {"deviceName" : "Dev1"},
        {"deviceName" : "Dev2"},
        {"deviceName" : "Dev3"}
      ],
      "roomPrice" : 2000.35,
      "id" : 1
    },
    {
      "roomID" : "002",
      "roomType" : "suite",
      "roomBeds" : "single",
      "maxCap" : 3,
      "posFloor" : "B2",
      "posMap" : "corner",
      "roomStatus" : {
        "displayName" : "To Clean",
        "internalCode" : "C0"
      },
      "isWalking" : false,
      "deviceList" : [
        {"deviceName" : "Dev1"},
        {"deviceName" : "Dev2"},
        {"deviceName" : "Dev3"},
        {"deviceName" : "Dev4"},
        {"deviceName" : "Dev5"}
      ],
      "roomPrice" : 3500.10,
      "id" : 2
    }
  ]
}
```

## Running the Test Environment

### User.json
Used to authenticate staff's login.
```
json-server --watch User.json --port 3000
```

### Guestbook.json
Used to manage bookings.
```
json-server --watch Guestbook.json --port 3001
```

### Property.josn
Used to manage rooms.
```
json-server --watch Property.json --port 3002
```

## Appendix
For Windows user, check the port status by using:
```
netstat -an | find "{port}"
```
*{port} is the target port number.*
