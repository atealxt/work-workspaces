-------------------------------------------
-- Export file for user TEST             --
-- Created by amoi on 2005-12-1, 0:15:31 --
-------------------------------------------

spool t_file.log

prompt
prompt Creating table T_FILE
prompt =====================
prompt
create table T_FILE
(
  FILE_ID      CHAR(32) not null,
  FILE_NAME    VARCHAR2(100),
  FILE_CONTENT BLOB,
  REMARK       VARCHAR2(400)
)
tablespace TS_CHENXH
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table T_FILE
  add constraint PK_T_FILE primary key (FILE_ID)
  using index 
  tablespace TS_CHENXH
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );


spool off
