CREATE TABLE C_LOGIN_STATUS
(
	user varchar(40) NOT NULL PRIMARY KEY,
	sid varchar(40),
	updDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
DEFAULT CHARSET=utf8;