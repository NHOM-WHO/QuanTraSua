USE [QuanTS]
GO
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([id], [ten], [phone], [address], [ngay_sinh]) VALUES (2, N'Vĩnh Nghi', N'0987654321', N'Thành phố Hồ Chí Minh', CAST(N'2001-01-01T00:00:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[Customer] OFF
GO
SET IDENTITY_INSERT [dbo].[BanAn] ON 

INSERT [dbo].[BanAn] ([Id], [Name], [Status]) VALUES (1, N'Bàn 1', N'Đang phục vụ')
INSERT [dbo].[BanAn] ([Id], [Name], [Status]) VALUES (2, N'Bàn 2', N'Trống')
SET IDENTITY_INSERT [dbo].[BanAn] OFF
GO
SET IDENTITY_INSERT [dbo].[Bill] ON 

INSERT [dbo].[Bill] ([id], [nguoi_lap], [ban_an], [loai], [trang_thai], [ngaylap_bill], [ngaythanh_toan], [tong_cong], [da_thanhtoan], [giam_gia]) VALUES (1, N'Admin', 2, N'Tại quán', N'Đã thanh toán', CAST(N'2021-12-17T00:31:25.480' AS DateTime), CAST(N'2021-12-19T12:42:25.000' AS DateTime), 200000, 200000, 15)
INSERT [dbo].[Bill] ([id], [nguoi_lap], [ban_an], [loai], [trang_thai], [ngaylap_bill], [ngaythanh_toan], [tong_cong], [da_thanhtoan], [giam_gia]) VALUES (2, N'Vinh Nghi', 2, N'Tại quán', N'Chưa thanh toán', CAST(N'2021-12-19T13:23:36.000' AS DateTime), NULL, 0, 0, 10)
INSERT [dbo].[Bill] ([id], [nguoi_lap], [ban_an], [loai], [trang_thai], [ngaylap_bill], [ngaythanh_toan], [tong_cong], [da_thanhtoan], [giam_gia]) VALUES (3, N'Khá Bảnh', 1, N'Đặt hàng', N'Chưa thanh toán', CAST(N'2021-12-19T13:44:13.000' AS DateTime), NULL, 0, 0, 17)
SET IDENTITY_INSERT [dbo].[Bill] OFF
GO
SET IDENTITY_INSERT [dbo].[Ship] ON 

INSERT [dbo].[Ship] ([id], [bill], [customer], [shipper], [phone], [gia], [status], [ngay_batdau], [ngay_ketthuc]) VALUES (1, 1, 2, N'Khá Bảnh', N'0987654321', 0.0000, N'Đang giao', CAST(N'2021-12-19T17:00:55.800' AS DateTime), NULL)
INSERT [dbo].[Ship] ([id], [bill], [customer], [shipper], [phone], [gia], [status], [ngay_batdau], [ngay_ketthuc]) VALUES (3, 3, 2, N'Trí Tiến', N'0987654321', 100000.0000, N'Hoàn thành', CAST(N'2021-12-19T17:55:54.000' AS DateTime), CAST(N'2021-12-19T19:02:20.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[Ship] OFF
GO
SET IDENTITY_INSERT [dbo].[ProductType] ON 

INSERT [dbo].[ProductType] ([id], [ten], [slug]) VALUES (1, N'Đồ ăn', N'do_an')
INSERT [dbo].[ProductType] ([id], [ten], [slug]) VALUES (2, N'Trà sữa', N'tra_sua')
INSERT [dbo].[ProductType] ([id], [ten], [slug]) VALUES (3, N'Cà phê', N'ca_phe')
INSERT [dbo].[ProductType] ([id], [ten], [slug]) VALUES (4, N'Topping', N'topping')
SET IDENTITY_INSERT [dbo].[ProductType] OFF
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
SET IDENTITY_INSERT [dbo].[BillDetail] ON 

INSERT [dbo].[BillDetail] ([id], [bill], [san_pham], [gia], [so_luong]) VALUES (8, 1, 9, 10000.0000, 1)
INSERT [dbo].[BillDetail] ([id], [bill], [san_pham], [gia], [so_luong]) VALUES (9, 1, 1, 60000.0000, 2)
INSERT [dbo].[BillDetail] ([id], [bill], [san_pham], [gia], [so_luong]) VALUES (10, 1, 8, 35000.0000, 2)
SET IDENTITY_INSERT [dbo].[BillDetail] OFF
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
SET IDENTITY_INSERT [dbo].[BillDetail_Topping] ON 

INSERT [dbo].[BillDetail_Topping] ([id], [bill], [topping]) VALUES (7, 9, 11)
SET IDENTITY_INSERT [dbo].[BillDetail_Topping] OFF
GO

