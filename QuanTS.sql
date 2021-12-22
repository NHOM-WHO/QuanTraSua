USE [QuanTraSua]
GO
/****** Object:  Table [dbo].[Employees]    Script Date: 12/14/2021 12:26:31 AM ******/
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
/****** Object:  Table [dbo].[Food_Category]    Script Date: 12/14/2021 12:26:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Food_Category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[slug] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Food_Item]    Script Date: 12/14/2021 12:26:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Food_Item](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[description] [nvarchar](500) NULL,
	[urlImage] [varbinary](max) NULL,
	[unitName] [nvarchar](20) NOT NULL,
	[unitPrice] [bigint] NOT NULL,
	[idCategory] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[Employees] ADD  DEFAULT (NULL) FOR [PHONENUMBER]
GO
ALTER TABLE [dbo].[Food_Item] ADD  DEFAULT (NULL) FOR [description]
GO
ALTER TABLE [dbo].[Food_Item] ADD  DEFAULT (NULL) FOR [urlImage]
GO
ALTER TABLE [dbo].[Food_Item]  WITH CHECK ADD  CONSTRAINT [fk_Fitem_idCategory] FOREIGN KEY([idCategory])
REFERENCES [dbo].[Food_Category] ([id])
GO
ALTER TABLE [dbo].[Food_Item] CHECK CONSTRAINT [fk_Fitem_idCategory]
GO
