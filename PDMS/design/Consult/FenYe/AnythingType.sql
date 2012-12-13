CREATE TABLE [dbo].[AnythingType] (
	[classID] [int] IDENTITY (1, 1) NOT NULL ,
	[className] [varchar] (50) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[classMark] [varchar] (550) COLLATE Chinese_PRC_CI_AS NULL ,
	[createDate] [datetime] NOT NULL 
) ON [PRIMARY]
GO

