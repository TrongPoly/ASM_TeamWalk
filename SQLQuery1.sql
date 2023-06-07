USE [master];

CREATE DATABASE [ASM_TeamWalk_V2];
GO

USE [ASM_TeamWalk_V2];
GO
-- DROP TABLE tai_khoan
CREATE TABLE tai_khoan
(
    email VARCHAR(50) PRIMARY KEY,
    mat_khau VARCHAR(10),
    phan_quyen BIT
);
GO




-- DROP TABLE hang_khach_hang
CREATE TABLE hang_khach_hang
(
    ma_hang INT IDENTITY(1, 1) PRIMARY KEY,
    ten_hang NVARCHAR(50),
    mo_ta NVARCHAR(MAX),
    diem_toi_thieu INT NOT NULL,
    trang_thai BIT
);
GO



-- DROP TABLE khach_hang
CREATE TABLE khach_hang
(
    ma_khach_hang BIGINT IDENTITY(1, 1) PRIMARY KEY,
    ten_khach_hang NVARCHAR(50),
    dia_chi NVARCHAR(MAX),
    so_dien_thoai VARCHAR(15),
    email VARCHAR(50)
        REFERENCES tai_khoan (email)
        UNIQUE,
    so_can_cuoc VARCHAR(15),
    diem_tich_luy INT
        DEFAULT 0, --Mac dinh 0

    hang_khach_hang INT
        REFERENCES hang_khach_hang (ma_hang),
);
GO

-- drop table phieu_giam_gia
/*CREATE TABLE phieu_giam_gia(
	ma_phieu			VARCHAR(10) PRIMARY KEY,
	ten_phieu		NVARCHAR(20),
	nguoi_so_huu		BIGINT  REFERENCES khach_hang(ma_khach_hang),
	ngay_bat_dau		DATE,
	ngay_ket_thuc		DATE,
	gia_tri_giam		MONEY,
	hinh_thuc_giam	BIT, -- % hay gia tien
	trang_thai		BIT,
)
GO
*/


CREATE TABLE loai_san_pham
(
    ma_loai BIGINT IDENTITY(1, 1) PRIMARY KEY,
    ten_loai NVARCHAR(MAX)
);
GO
INSERT INTO loai_san_pham
VALUES(N'Điện thoại'),
(N'Máy tính bảng'),(N'Laptop')



-- DROP TABLE san_pham
CREATE TABLE san_pham
(
    ma_san_pham BIGINT IDENTITY(1, 1) PRIMARY KEY,
    ten_san_pham NVARCHAR(100),
	anh_san_pham VARCHAR(50),
    ma_loai BIGINT
        REFERENCES dbo.loai_san_pham (ma_loai),
    don_gia INT,
    so_luong_ton INT,
    trang_thai BIT,
);
GO

INSERT INTO san_pham VALUES
(N'Iphone 11 Pro Max 64G', 'ip11-promax.jpg',1, 11900000,10, 1),
(N'Iphone 12 Pro 125G', 'ip12-pro.jpg', 1, 15300000,2, 1),
(N'Iphone 13 Pro Max 125G','ip13-promax.jpg', 1, 19900000,0, 0),
(N'iPhone 11','anh1.png', 1, 1000000,5, 0),
(N'Samsung Galaxy S22','samsung-galaxyA23.jpg', 1, 2000000,17, 1),
(N'Samsung Galaxy A23','samsung-galaxyss22.jpg', 1, 8790000,13, 1),
(N'Samsung Galaxy A54', 'samsung-A54.jpg',1, 9490000,0, 0),
(N'Apple Ipad Pro','Apple1.jpg', 2,24500000,14, 1),
(N'Apple Ipad Pro 2020', 'Apple2.jpg',2, 18900000,8, 1),
(N'Samsung Galaxy Tab A7 Lite','Apple3.jpg', 2, 9490000,1, 1),
(N'Samsung Galaxy Tab S7 T875','Apple4.jpg', 2, 14700000,0, 0),
(N'Samsung Galaxy Tab S8 5G', 'anh1.png',2, 9490000,14, 0),
(N'MSI Gaming GF63 Thin 11SC-664VN', 'laptop1.jpg',3, 14890000,10, 1),
(N'DELL Latitude 7420', 'laptop2.jpg',3, 15900000,5, 1),
(N'HP Omen 15 2017 i7','laptop3.jpg', 3, 12500000,4, 1),
(N'Macbook Pro Touchba','laptop4.jpg', 3, 23900000,1, 1),
(N'MacBook Pro 13', 'laptop5',3, 31750000,4, 1),
(N'MSI Gaming GF63 Thin 11UC-1228VN', 'laptop6',3, 19900000,6, 1),
(N'Gigabyte Gaming G5 GE', 'laptop7.jpg',3, 19990000,8, 1)



-- DROP TABLE hoa_don
CREATE TABLE hoa_don
(
    ma_hoa_don BIGINT IDENTITY(1, 1) PRIMARY KEY,
    ngay_lap DATE,
    ghi_chu NVARCHAR(MAX),
    ngay_thanh_toan DATE,
    trang_thai BIT,
    nguoi_mua BIGINT
        REFERENCES khach_hang (ma_khach_hang)
);
GO

-- DROP TABLE hoa_don_chi_tiet
CREATE TABLE hoa_don_chi_tiet
(
	  ma_hoa_don BIGINT
        REFERENCES hoa_don (ma_hoa_don),
    ma_san_pham BIGINT
        REFERENCES san_pham (ma_san_pham),
    so_luong INT,
    don_gia MONEY,
    ghi_chu NVARCHAR(MAX),
    PRIMARY KEY (
                    ma_hoa_don,
                    ma_san_pham
                )
  
);
GO


-- DROP TABLE giohang
CREATE TABLE gio_hang
(
    ma_gio_hang UNIQUEIDENTIFIER
        DEFAULT NEWID() PRIMARY KEY,
    ngay_tao DATE,
    ngay_cap_nhat DATE,
    nguoi_so_huu BIGINT
        REFERENCES khach_hang (ma_khach_hang),
);
GO
-- DROP TABLE gio_hang_chi_tiet
CREATE TABLE gio_hang_chi_tiet
(
    ma_gio_hang UNIQUEIDENTIFIER
        REFERENCES gio_hang (ma_gio_hang),
    ma_san_pham BIGINT
        REFERENCES san_pham (ma_san_pham),
    so_luong INT,
    PRIMARY KEY (
                    ma_gio_hang,
                    ma_san_pham
                )
);
GO


INSERT INTO tai_khoan
VALUES
('duong0609@gmail.com', '123', 1),
('thanh0102@gmail.com', '321', 0),
('dai0405@gmail.com', '012', 0);

INSERT INTO hang_khach_hang
VALUES
(N'Khách hàng tiềm năng', NULL, 0, NULL),
(N'Khách hàng thân thiết', NULL, 500, NULL),
(N'Khách hàng bạc', NULL, 1500, NULL),
(N'Khách hàng vàng', NULL, 3000, NULL),
(N'Khách hàng kim cương', NULL, 9000, NULL);


INSERT INTO khach_hang
VALUES
(N'Nguyễn Trùng Dương', N'Ninh Kiều, Cần Thơ', '0373655612', 'duong0609@gmail.com',  '888352617345',  DEFAULT, 1),
(N'Lê Hồng Thanh', N'Ninh Kiều, Cần Thơ', '0919029428', 'thanh0102@gmail.com',  '888352617345', DEFAULT, 1);

SELECT * FROM dbo.hang_khach_hang
SELECT * FROM dbo.khach_hang
SELECT * FROM dbo.san_pham
SELECT * FROM dbo.loai_san_pham
SELECT * FROM dbo.tai_khoan