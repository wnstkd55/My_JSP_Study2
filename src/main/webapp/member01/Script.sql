create table member01(
    id varchar2(50) not null primary key,	--�����ּ�
    passwd varchar2(60) not null,			--��ȣȭ�ؼ� DB�� ����
    name varchar2(20) not null,
    reg_date date default sysdate not null,
    address varchar2(100) not null,
    tel varchar2(20) not null
);