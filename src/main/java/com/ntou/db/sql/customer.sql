CREATE TABLE customer (
     CID  		VARCHAR(20) PRIMARY KEY  ,-- 使用者身分證(外國護照預留欄位長度)
     CH_NAME 	VARCHAR(50)              ,-- 使用者中文姓名
	 EN_NAME 	VARCHAR(50)              ,-- 使用者英文姓名
     MAIL 		VARCHAR(50)              ,-- 使用者電子信箱
     PHONE 		VARCHAR(10)              ,-- 使用者電話
     BIRTH 		VARCHAR(10)              ,-- 使用者生日yyyy/mm/dd
     ADDR 		VARCHAR(100) 		     ,-- 使用者地址
     REGIDATE 	VARCHAR(23)		    	  -- 註冊時間yyyy/MM/dd HH:mm:ss.SSS
 )

--Testing Data
INSERT INTO customer (CID, CH_NAME, EN_NAME, MAIL, PHONE, BIRTH, ADDR, REGIDATE)
VALUES ('A123456789', '劉公長', 'Jenny Luo', 'myemail@gmail.com', '0933123456', '1992/11/16', '台北市內湖區瑞光路', '2024/02/24 09:20:33.000')

INSERT INTO customer (CID, CH_NAME, EN_NAME, MAIL, PHONE, BIRTH, ADDR, REGIDATE)
VALUES ('B223456789', '郭東陳', 'Judy Kuo', 'Judysmail@gmail.com', '0977123456', '1998/10/27', '台北市內湖區瑞光路', '2024/02/20 10:27:07.020')

