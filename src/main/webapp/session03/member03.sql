select * from member03;

create table member03(
    id varchar2(10) not null,
    pass varchar2(10) not null,
    name varchar2(30) not null,
    regidate date default sysdate not null,
    grade varchar2(50) not null,    --ȸ������� ��Ÿ���� �÷�
    primary key(id)
    );
    
insert into member03(id,pass,name,grade) values('admin','1234','������','vip');

insert into member03(id,pass,name,grade) values('hong','1234','ȫ�浿','gold');

insert into member03(id,pass,name,grade) values('kim','1234','������','silver');

commit;