# Examen Backend Cash - Ayelen Maite Luciano
Backend Exam for CashOnline

## Getting Started - Database
```bash
$ mysql -u root -p
mysql> source ./backendcash-structure.sql
mysql> source ./backendcash-inserts.sql
mysql> quit 
```

If you have a different user, password or want to use another port please set it in src\main\resources\application.properties file

## Run

```bash
$ mvn spring-boot:run
```

## Usage

GET: http://localhost:8080/users
GET: http://localhost:8080/loans?page={pagevalue}&size={sizevalue}
GET: http://localhost:8080/users/{userid}
GET: http://localhost:8080/loans/{loanid}
POST: http://localhost:8080/users
    Body:{
    "email": "ayelenmaite2000@gmail.com",
    "firstName": "Ayelen",
    "lastName": "Luciano"
    }
POST: http://localhost:8080/loans
    Body:{
            "total": 1500
        }
    Params: userId {userid}
DELETE: http://localhost:8080/users/{userid}
DELETE: http://localhost:8080/loans/{loanid}