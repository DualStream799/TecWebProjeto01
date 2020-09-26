# CRUD:

  Basic to-do-list CRUD system using MySQL databases, Java Servlets and MVC architecture.

  Before run the project, open a terminal and access MySQL console as root user running the following command (by default the password is empty, otherwise use your password):

```
mysql -uroot -p
```

  In case of `ERROR 1698 (28000): Access denied for user 'root'@'localhost'` (usually happens on Linux based systems) uses the `sudo`command in order to access MySQL console as root user.

```
sudo mysql -uroot -p
```

  In order to maintain a separation between your own databases and the databases required for this project a new MySQL user will be created and also avoids the error mentioned previously. Notice that modify the user name or password is not forbidden but will require modifications in some project files and, therefore, not recommended. On MySQL console insert the following commands:

```mysql
CREATE USER 'dev01'@'localhost' IDENTIFIED BY '#D@m9Rjn?Gs_Sm5r';
GRANT ALL PRIVILEGES ON *.* TO 'dev01'@'localhost';
EXIT;
```

  After creating a new user, access MySQL console as your new user:

```
sudo mysql -udev01 -p#D@m9Rjn?Gs_Sm5r
```

  Now the database and tables required for this project will be created. In order to do so, create a new database and then access it:

```mysql
CREATE DATABASE tecweb_projeto01;
Use tecweb_projeto01;
```

  Two tables will be created, one to store the user email and password and another to store the tasks of all these users.

  First create the `Users` table and then add some data:

```mysql
CREATE TABLE Users (
id       INT          NOT NULL AUTO_INCREMENT,
email    VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
PRIMARY KEY (id)
);
```

```mysql
INSERT INTO Users (email, password) VALUES ('user01@example.com', '1234');

INSERT INTO Users (email, password) VALUES ('user02@example.com', '4567');

INSERT INTO Users (email, password) VALUES ('user03@example.com', '8910');

INSERT INTO Users (email, password) VALUES ('user04@example.com', '1112');
```

  Now create the`Tasks` table and then add some data:

```mysql
CREATE TABLE Tasks (
id           INT NOT NULL AUTO_INCREMENT,
user_email   VARCHAR(255) NOT NULL,
description  LONGTEXT NOT NULL,
tag          INT NOT NULL,
PRIMARY KEY (id)
);
```

```mysql
INSERT INTO Tasks (user_email, description, tags) VALUES ('user01@example.com', 'First Task', 1);

INSERT INTO Tasks (user_email, description, tags) VALUES ('user01@example.com', 'Second Task', 2);

INSERT INTO Tasks (user_email, description, tags) VALUES ('user01@example.com', 'Third Task', 3);

INSERT INTO Tasks (user_email, description, tags) VALUES ('user01@example.com', 'Fourth Task', 3);

INSERT INTO Tasks (user_email, description, tags) VALUES ('user01@example.com', 'Fifth Task', 1);

INSERT INTO Tasks (user_email, description, tags) VALUES ('user02@example.com', 'First Task', 2);
```

  The set up is completed and ready to be used by the project. Remember to login as one of the registered users with proper email and password.