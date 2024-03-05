CREATE TABLE shop (
     CID   		VARCHAR(9)   			 ,-- 統一編號
	 MEM_NUM	VARCHAR(36)PRIMARY KEY	 ,-- 會員編號
     NAME 		VARCHAR(50)              ,-- 商店負責人姓名
     MAIL 		VARCHAR(50)              ,-- 商店負責人電子信箱
     PHONE		VARCHAR(10)              ,-- 商店負責人電話
     BIRTH		VARCHAR(10)              ,-- 商店創立日期
     ADDR 		VARCHAR(100) 		     ,-- 商店聯絡地址
     REGIDATE 	VARCHAR(23)		    	  -- 註冊時間yyyy/MM/dd HH:mm:ss.SSS
 )

--Testing Data
INSERT INTO shop (CID, MEM_NUM, NAME, MAIL, PHONE, BIRTH, ADDR, REGIDATE)
VALUES ('88527419', '550e8400-e29b-41d4-a716-446655440000','王老闆', 'wang_comp@gmail.com', '0988123456', '1988/11/16', '台北市內湖區瑞光路', '2024/03/03')

INSERT INTO shop (CID, MEM_NUM, NAME, MAIL, PHONE, BIRTH, ADDR, REGIDATE)
VALUES ('77129958', '880e8400-e29b-41d4-a716-446655440000','大老闆', 'bigg_comp@gmail.com', '0966623456', '1977/10/27', '台北市內湖區瑞光路', '2024/03/05')

