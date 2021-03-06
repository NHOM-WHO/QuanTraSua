USE [QuanTS]
GO
ALTER TABLE [dbo].[Ship] DROP CONSTRAINT [FK__Ship__customer__412EB0B6]
GO
ALTER TABLE [dbo].[Ship] DROP CONSTRAINT [FK__Ship__bill__403A8C7D]
GO
ALTER TABLE [dbo].[Product_Topping] DROP CONSTRAINT [FK__Product_T__toppi__3F466844]
GO
ALTER TABLE [dbo].[Product_Topping] DROP CONSTRAINT [FK__Product_T__san_p__3E52440B]
GO
ALTER TABLE [dbo].[Product] DROP CONSTRAINT [FK__Product__loai_sp__3D5E1FD2]
GO
ALTER TABLE [dbo].[BillDetail_Topping] DROP CONSTRAINT [FK__BillDetail__bill__3C69FB99]
GO
ALTER TABLE [dbo].[BillDetail_Topping] DROP CONSTRAINT [FK__BillDetai__toppi__3B75D760]
GO
ALTER TABLE [dbo].[BillDetail] DROP CONSTRAINT [FK__BillDetail__bill__3A81B327]
GO
ALTER TABLE [dbo].[BillDetail] DROP CONSTRAINT [FK__BillDetai__san_p__398D8EEE]
GO
ALTER TABLE [dbo].[Bill] DROP CONSTRAINT [FK__Bill__ban_an__38996AB5]
GO
ALTER TABLE [dbo].[Employees] DROP CONSTRAINT [DF__Employees__PHONE__440B1D61]
GO
/****** Object:  Table [dbo].[Topping]    Script Date: 12/22/2021 2:59:24 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Topping]') AND type in (N'U'))
DROP TABLE [dbo].[Topping]
GO
/****** Object:  Table [dbo].[Ship]    Script Date: 12/22/2021 2:59:24 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Ship]') AND type in (N'U'))
DROP TABLE [dbo].[Ship]
GO
/****** Object:  Table [dbo].[ProductType]    Script Date: 12/22/2021 2:59:24 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ProductType]') AND type in (N'U'))
DROP TABLE [dbo].[ProductType]
GO
/****** Object:  Table [dbo].[Product_Topping]    Script Date: 12/22/2021 2:59:24 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Product_Topping]') AND type in (N'U'))
DROP TABLE [dbo].[Product_Topping]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 12/22/2021 2:59:24 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Product]') AND type in (N'U'))
DROP TABLE [dbo].[Product]
GO
/****** Object:  Table [dbo].[Employees]    Script Date: 12/22/2021 2:59:24 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Employees]') AND type in (N'U'))
DROP TABLE [dbo].[Employees]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 12/22/2021 2:59:24 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Customer]') AND type in (N'U'))
DROP TABLE [dbo].[Customer]
GO
/****** Object:  Table [dbo].[BillDetail_Topping]    Script Date: 12/22/2021 2:59:24 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[BillDetail_Topping]') AND type in (N'U'))
DROP TABLE [dbo].[BillDetail_Topping]
GO
/****** Object:  Table [dbo].[BillDetail]    Script Date: 12/22/2021 2:59:24 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[BillDetail]') AND type in (N'U'))
DROP TABLE [dbo].[BillDetail]
GO
/****** Object:  Table [dbo].[Bill]    Script Date: 12/22/2021 2:59:24 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Bill]') AND type in (N'U'))
DROP TABLE [dbo].[Bill]
GO
/****** Object:  Table [dbo].[BanAn]    Script Date: 12/22/2021 2:59:24 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[BanAn]') AND type in (N'U'))
DROP TABLE [dbo].[BanAn]
GO
/****** Object:  Table [dbo].[BanAn]    Script Date: 12/22/2021 2:59:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BanAn](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NULL,
	[Status] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Bill]    Script Date: 12/22/2021 2:59:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bill](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nguoi_lap] [nvarchar](50) NULL,
	[ban_an] [int] NULL,
	[loai] [nvarchar](50) NULL,
	[trang_thai] [nvarchar](50) NULL,
	[ngaylap_bill] [datetime] NULL,
	[ngaythanh_toan] [datetime] NULL,
	[tong_cong] [int] NULL,
	[da_thanhtoan] [int] NULL,
	[giam_gia] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BillDetail]    Script Date: 12/22/2021 2:59:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BillDetail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[bill] [int] NULL,
	[san_pham] [int] NULL,
	[gia] [money] NULL,
	[so_luong] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BillDetail_Topping]    Script Date: 12/22/2021 2:59:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BillDetail_Topping](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[bill] [int] NULL,
	[topping] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 12/22/2021 2:59:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ten] [nvarchar](50) NULL,
	[phone] [varchar](100) NULL,
	[address] [nvarchar](200) NULL,
	[ngay_sinh] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Employees]    Script Date: 12/22/2021 2:59:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employees](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[USERNAME] [varchar](50) NOT NULL,
	[PASSWORD] [varchar](50) NOT NULL,
	[NAME] [nvarchar](20) NOT NULL,
	[PHONENUMBER] [varchar](20) NULL,
	[PERMISSION] [varchar](20) NOT NULL,
	[SALARY] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 12/22/2021 2:59:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ten] [nvarchar](50) NULL,
	[loai_sp] [int] NULL,
	[gia] [money] NULL,
	[don_vi] [nvarchar](50) NULL,
	[hinh_anh] [varchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product_Topping]    Script Date: 12/22/2021 2:59:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product_Topping](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[san_pham] [int] NULL,
	[topping] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductType]    Script Date: 12/22/2021 2:59:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductType](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ten] [nvarchar](50) NULL,
	[slug] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ship]    Script Date: 12/22/2021 2:59:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ship](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[bill] [int] NULL,
	[customer] [int] NULL,
	[shipper] [nvarchar](50) NULL,
	[phone] [varchar](50) NULL,
	[gia] [money] NULL,
	[status] [nvarchar](50) NULL,
	[ngay_batdau] [datetime] NULL,
	[ngay_ketthuc] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Topping]    Script Date: 12/22/2021 2:59:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Topping](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ten] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[BanAn] ON 

INSERT [dbo].[BanAn] ([Id], [Name], [Status]) VALUES (1, N'Bàn 1', N'Đang phục vụ')
INSERT [dbo].[BanAn] ([Id], [Name], [Status]) VALUES (2, N'Bàn 2', N'Trống')
SET IDENTITY_INSERT [dbo].[BanAn] OFF
GO
SET IDENTITY_INSERT [dbo].[Bill] ON 

INSERT [dbo].[Bill] ([id], [nguoi_lap], [ban_an], [loai], [trang_thai], [ngaylap_bill], [ngaythanh_toan], [tong_cong], [da_thanhtoan], [giam_gia]) VALUES (1, N'Admin', 2, N'Tại quán', N'Đã thanh toán', CAST(N'2021-12-17T00:31:25.480' AS DateTime), CAST(N'2021-12-19T12:42:25.000' AS DateTime), 260000, 0, 15)
INSERT [dbo].[Bill] ([id], [nguoi_lap], [ban_an], [loai], [trang_thai], [ngaylap_bill], [ngaythanh_toan], [tong_cong], [da_thanhtoan], [giam_gia]) VALUES (2, N'Vinh Nghi', 2, N'Tại quán', N'Đã thanh toán', CAST(N'2021-12-19T13:23:36.000' AS DateTime), CAST(N'2021-12-22T11:41:35.000' AS DateTime), 55000, 0, 10)
INSERT [dbo].[Bill] ([id], [nguoi_lap], [ban_an], [loai], [trang_thai], [ngaylap_bill], [ngaythanh_toan], [tong_cong], [da_thanhtoan], [giam_gia]) VALUES (3, N'Khá Bảnh', 1, N'Đặt hàng', N'Chưa thanh toán', CAST(N'2021-12-19T13:44:13.000' AS DateTime), NULL, 0, 0, 17)
SET IDENTITY_INSERT [dbo].[Bill] OFF
GO
SET IDENTITY_INSERT [dbo].[BillDetail] ON 

INSERT [dbo].[BillDetail] ([id], [bill], [san_pham], [gia], [so_luong]) VALUES (8, 1, 9, 10000.0000, 1)
INSERT [dbo].[BillDetail] ([id], [bill], [san_pham], [gia], [so_luong]) VALUES (9, 1, 1, 60000.0000, 2)
INSERT [dbo].[BillDetail] ([id], [bill], [san_pham], [gia], [so_luong]) VALUES (10, 1, 8, 35000.0000, 2)
INSERT [dbo].[BillDetail] ([id], [bill], [san_pham], [gia], [so_luong]) VALUES (11, 1, 1, 60000.0000, 1)
INSERT [dbo].[BillDetail] ([id], [bill], [san_pham], [gia], [so_luong]) VALUES (12, 2, 4, 55000.0000, 1)
SET IDENTITY_INSERT [dbo].[BillDetail] OFF
GO
SET IDENTITY_INSERT [dbo].[BillDetail_Topping] ON 

INSERT [dbo].[BillDetail_Topping] ([id], [bill], [topping]) VALUES (7, 9, 11)
INSERT [dbo].[BillDetail_Topping] ([id], [bill], [topping]) VALUES (8, 11, 10)
INSERT [dbo].[BillDetail_Topping] ([id], [bill], [topping]) VALUES (9, 12, 11)
SET IDENTITY_INSERT [dbo].[BillDetail_Topping] OFF
GO
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([id], [ten], [phone], [address], [ngay_sinh]) VALUES (2, N'Vĩnh Nghi', N'0987654321', N'Thành phố Hồ Chí Minh', CAST(N'2001-01-01T00:00:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[Customer] OFF
GO
SET IDENTITY_INSERT [dbo].[Employees] ON 

INSERT [dbo].[Employees] ([ID], [USERNAME], [PASSWORD], [NAME], [PHONENUMBER], [PERMISSION], [SALARY]) VALUES (1, N'admin', N'admin', N'Thái', N'0342964072', N'manager', 20000000)
INSERT [dbo].[Employees] ([ID], [USERNAME], [PASSWORD], [NAME], [PHONENUMBER], [PERMISSION], [SALARY]) VALUES (2, N'thai', N'thai', N'Thái', N'0123456', N'employee', 290000)
SET IDENTITY_INSERT [dbo].[Employees] OFF
GO
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([id], [ten], [loai_sp], [gia], [don_vi], [hinh_anh]) VALUES (1, N'Trà sữa trân châu', 2, 50000.0000, N'Ly', N'src/Public/tra-hoa-hong-da.png')
INSERT [dbo].[Product] ([id], [ten], [loai_sp], [gia], [don_vi], [hinh_anh]) VALUES (2, N'Trà sữa sương sáo', 2, 45000.0000, N'Ly', N'src/Public/tra-hoa-hong-da.png')
INSERT [dbo].[Product] ([id], [ten], [loai_sp], [gia], [don_vi], [hinh_anh]) VALUES (3, N'Trà sữa matcha(L)', 2, 50000.0000, N'Ly', N'src/Public/tra-hoa-hong-da.png')
INSERT [dbo].[Product] ([id], [ten], [loai_sp], [gia], [don_vi], [hinh_anh]) VALUES (4, N'Sữa tươi trân châu đường đen', 2, 45000.0000, N'Ly', N'src/Public/tra-hoa-hong-da.png')
INSERT [dbo].[Product] ([id], [ten], [loai_sp], [gia], [don_vi], [hinh_anh]) VALUES (5, N'Trà sữa matcha(XL)', 2, 25000.0000, N'Ly', N'src/Public/tra-hoa-hong-da.png')
INSERT [dbo].[Product] ([id], [ten], [loai_sp], [gia], [don_vi], [hinh_anh]) VALUES (6, N'Trà sữa ô long', 2, 20000.0000, N'Ly', N'src/Public/tra-hoa-hong-da.png')
INSERT [dbo].[Product] ([id], [ten], [loai_sp], [gia], [don_vi], [hinh_anh]) VALUES (7, N'Trà đào', 2, 40000.0000, N'Ly', N'src/Public/tra-hoa-hong-da.png')
INSERT [dbo].[Product] ([id], [ten], [loai_sp], [gia], [don_vi], [hinh_anh]) VALUES (8, N'Cafe truyền thống', 3, 35000.0000, N'Cốc', N'src/Public/tra-hoa-hong-da.png')
INSERT [dbo].[Product] ([id], [ten], [loai_sp], [gia], [don_vi], [hinh_anh]) VALUES (9, N'Bánh flan', 1, 10000.0000, N'Cái', N'src/Public/tra-hoa-hong-da.png')
INSERT [dbo].[Product] ([id], [ten], [loai_sp], [gia], [don_vi], [hinh_anh]) VALUES (10, N'Trân châu tuyết sợi', 4, 10000.0000, N'Ly', N'src/Public/tra-hoa-hong-da.png')
INSERT [dbo].[Product] ([id], [ten], [loai_sp], [gia], [don_vi], [hinh_anh]) VALUES (11, N'Trân châu đen', 4, 10000.0000, N'Ly', N'src/Public/tra-hoa-hong-da.png')
INSERT [dbo].[Product] ([id], [ten], [loai_sp], [gia], [don_vi], [hinh_anh]) VALUES (12, N'Trà châu trắng', 4, 10000.0000, N'Ly', N'src/Public/tra-hoa-hong-da.png')
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
SET IDENTITY_INSERT [dbo].[Product_Topping] ON 

INSERT [dbo].[Product_Topping] ([id], [san_pham], [topping]) VALUES (1, 1, 10)
INSERT [dbo].[Product_Topping] ([id], [san_pham], [topping]) VALUES (2, 1, 11)
INSERT [dbo].[Product_Topping] ([id], [san_pham], [topping]) VALUES (3, 1, 12)
INSERT [dbo].[Product_Topping] ([id], [san_pham], [topping]) VALUES (4, 2, 10)
INSERT [dbo].[Product_Topping] ([id], [san_pham], [topping]) VALUES (5, 2, 11)
INSERT [dbo].[Product_Topping] ([id], [san_pham], [topping]) VALUES (6, 2, 12)
INSERT [dbo].[Product_Topping] ([id], [san_pham], [topping]) VALUES (7, 3, 10)
INSERT [dbo].[Product_Topping] ([id], [san_pham], [topping]) VALUES (8, 3, 11)
INSERT [dbo].[Product_Topping] ([id], [san_pham], [topping]) VALUES (9, 3, 12)
INSERT [dbo].[Product_Topping] ([id], [san_pham], [topping]) VALUES (10, 4, 10)
INSERT [dbo].[Product_Topping] ([id], [san_pham], [topping]) VALUES (11, 4, 11)
INSERT [dbo].[Product_Topping] ([id], [san_pham], [topping]) VALUES (12, 4, 12)
INSERT [dbo].[Product_Topping] ([id], [san_pham], [topping]) VALUES (13, 5, 10)
INSERT [dbo].[Product_Topping] ([id], [san_pham], [topping]) VALUES (14, 5, 11)
INSERT [dbo].[Product_Topping] ([id], [san_pham], [topping]) VALUES (15, 5, 12)
INSERT [dbo].[Product_Topping] ([id], [san_pham], [topping]) VALUES (16, 6, 10)
INSERT [dbo].[Product_Topping] ([id], [san_pham], [topping]) VALUES (17, 6, 11)
INSERT [dbo].[Product_Topping] ([id], [san_pham], [topping]) VALUES (18, 6, 12)
INSERT [dbo].[Product_Topping] ([id], [san_pham], [topping]) VALUES (19, 7, 10)
INSERT [dbo].[Product_Topping] ([id], [san_pham], [topping]) VALUES (20, 7, 11)
INSERT [dbo].[Product_Topping] ([id], [san_pham], [topping]) VALUES (21, 7, 12)
SET IDENTITY_INSERT [dbo].[Product_Topping] OFF
GO
SET IDENTITY_INSERT [dbo].[ProductType] ON 

INSERT [dbo].[ProductType] ([id], [ten], [slug]) VALUES (1, N'Đồ ăn', N'do_an')
INSERT [dbo].[ProductType] ([id], [ten], [slug]) VALUES (2, N'Trà sữa', N'tra_sua')
INSERT [dbo].[ProductType] ([id], [ten], [slug]) VALUES (3, N'Cà phê', N'ca_phe')
INSERT [dbo].[ProductType] ([id], [ten], [slug]) VALUES (4, N'Topping', N'topping')
SET IDENTITY_INSERT [dbo].[ProductType] OFF
GO
SET IDENTITY_INSERT [dbo].[Ship] ON 

INSERT [dbo].[Ship] ([id], [bill], [customer], [shipper], [phone], [gia], [status], [ngay_batdau], [ngay_ketthuc]) VALUES (1, 1, 2, N'Khá Bảnh', N'0987654321', 0.0000, N'Hoàn thành', CAST(N'2021-12-19T17:00:55.800' AS DateTime), CAST(N'2021-12-22T11:42:45.000' AS DateTime))
INSERT [dbo].[Ship] ([id], [bill], [customer], [shipper], [phone], [gia], [status], [ngay_batdau], [ngay_ketthuc]) VALUES (3, 3, 2, N'Trí Tiến', N'0987654321', 100000.0000, N'Hoàn thành', CAST(N'2021-12-19T17:55:54.000' AS DateTime), CAST(N'2021-12-19T19:02:20.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[Ship] OFF
GO
ALTER TABLE [dbo].[Employees] ADD  DEFAULT (NULL) FOR [PHONENUMBER]
GO
ALTER TABLE [dbo].[Bill]  WITH CHECK ADD FOREIGN KEY([ban_an])
REFERENCES [dbo].[BanAn] ([Id])
GO
ALTER TABLE [dbo].[BillDetail]  WITH CHECK ADD FOREIGN KEY([san_pham])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[BillDetail]  WITH CHECK ADD FOREIGN KEY([bill])
REFERENCES [dbo].[Bill] ([id])
GO
ALTER TABLE [dbo].[BillDetail_Topping]  WITH CHECK ADD FOREIGN KEY([topping])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[BillDetail_Topping]  WITH CHECK ADD FOREIGN KEY([bill])
REFERENCES [dbo].[BillDetail] ([id])
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD FOREIGN KEY([loai_sp])
REFERENCES [dbo].[ProductType] ([id])
GO
ALTER TABLE [dbo].[Product_Topping]  WITH CHECK ADD FOREIGN KEY([san_pham])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[Product_Topping]  WITH CHECK ADD FOREIGN KEY([topping])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[Ship]  WITH CHECK ADD FOREIGN KEY([bill])
REFERENCES [dbo].[Bill] ([id])
GO
ALTER TABLE [dbo].[Ship]  WITH CHECK ADD FOREIGN KEY([customer])
REFERENCES [dbo].[Customer] ([id])
GO
