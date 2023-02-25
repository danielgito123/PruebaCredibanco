CREATE USER 'credibanco'@'localhost' IDENTIFIED BY 'credibanco';

GRANT ALL PRIVILEGES ON * . * TO 'credibanco'@'localhost';

FLUSH PRIVILEGES;

ALTER USER 'credibanco'@'localhost' IDENTIFIED WITH mysql_native_password
BY 'credibanco';  

create database credibanco;

use credibanco;