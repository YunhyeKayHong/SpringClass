DROP TABLE SHELTER_TABLE;
DROP TABLE DOG_TABLE;
DROP TABLE USR_TABLE;
DROP TABLE VOLUNTEER_TABLE;

-----------------------------------------

DROP SEQUENCE SHELTER_TABLE_SEQ;
DROP SEQUENCE DOG_TABLE_SEQ;
DROP SEQUENCE USR_TABLE_SEQ;
DROP SEQUENCE VOLUNTEER_TABLE_SEQ;

-----------------------------------------

CREATE TABLE SHELTER_TABLE
(
    SHELTERNUM NUMBER PRIMARY KEY,
    SHELTERNAME VARCHAR2(100) NOT NULL,
    SHELTERADDRESS VARCHAR2(100) NOT NULL,
    SHELTERDETAILADDRESS VARCHAR2(100),
    SHELTERADMIN VARCHAR2(100) NOT NULL,
    SHELTERACCOUNT VARCHAR2(100) NOT NULL
);

CREATE TABLE DOG_TABLE
(
   DOGNUM NUMBER PRIMARY KEY,
   DOGAGE NUMBER,
   DOGNAME VARCHAR2(10) NOT NULL,
   DOGBREED VARCHAR2(20),
   DOGGENDER VARCHAR2(2) NOT NULL CHECK (DOGGENDER IN('F','M')),
   DOGDISEASE VARCHAR2(20),
   DOGENTERDATE DATE DEFAULT SYSDATE,
   DOGSPONSOR VARCHAR2(50) DEFAULT '����',
   SHELTERNUM NUMBER REFERENCES SHELTER_TABLE(SHELTERNUM)
);

CREATE TABLE USR_TABLE
(
    SHELTERNUM NUMBER REFERENCES SHELTER_TABLE(SHELTERNUM), 
    DOGNUM NUMBER REFERENCES DOG_TABLE(DOGNUM),    
    -- ���� ���� ����
   USRID VARCHAR2(20) PRIMARY KEY NOT NULL,
   USRNAME VARCHAR2(20) NOT NULL,
   USRGENDER VARCHAR2(2) CHECK (USRGENDER IN('F','M')),
   USRAGE NUMBER NOT NULL,
   USRADDRESS VARCHAR2(200) NOT NULL,
   USRPHONE VARCHAR2(20) NOT NULL,
    
    -- ��ȣ�� ���� ����
    PARENTSNAME VARCHAR2(20) default NULL,
    PARENTSAGE NUMBER default NULL,
    PARENTSADDRESS VARCHAR2(200) default NULL,
    PARENTSPHONE VARCHAR2(20) default NULL
);

CREATE TABLE VOLUNTEER_TABLE
(
    VOLUNTEERNUM NUMBER PRIMARY KEY,
    SHELTERNUM NUMBER REFERENCES SHELTER_TABLE(SHELTERNUM),
    USRID VARCHAR2(20) REFERENCES USR_TABLE(USRID) ON DELETE CASCADE,
    
    -- ��ȣ�� �� Ư�� ����߿��� �Ŀ��� �ݾ� ����
    SHELTERDONATION NUMBER,
    DOGDONATION NUMBER,
    
    -- �Ŀ� ��ǰ ����
    PAD NUMBER,
    FOOD NUMBER,
    TISSUE NUMBER,
    
    -- �Ŀ� �� ���� �Ͻø� �޾� �� SYSDATE
    VOLUNTEERDATE DATE DEFAULT SYSDATE,
    
    LUCKYDOG VARCHAR2(10) 
);

-----------------------------------------
        
CREATE SEQUENCE SHELTER_TABLE_SEQ;
CREATE SEQUENCE DOG_TABLE_SEQ;
CREATE SEQUENCE USR_TABLE_SEQ;
CREATE SEQUENCE VOLUNTEER_TABLE_SEQ;

-----------------------------------------
SELECT * FROM DOG_TABLE;
SELECT * FROM USR_TABLE;
SELECT * FROM SHELTER_TABLE;
-----------------------------------------
INSERT INTO SHELTER_TABLE VALUES (SHELTER_TABLE_SEQ.nextval, '���⵿�� ���������', '��õ������ ������', '��õ������ ������ ��� 51���� 2��', '������', '660401-01-652748');
INSERT INTO SHELTER_TABLE VALUES (SHELTER_TABLE_SEQ.nextval, '������ ����', '��⵵ ������', '������Դϴ�.', '�̼���', '695037-01-008090');
INSERT INTO SHELTER_TABLE VALUES (SHELTER_TABLE_SEQ.nextval, '��� ���� ��Ʈ��ũ', '����Ư���� ��걸', '����Ư���� ��걸 ��굿 5�� 24-1', '������', '355-0056-3278-23');
INSERT INTO SHELTER_TABLE VALUES (SHELTER_TABLE_SEQ.nextval, '����� �ٵ���', '��⵵ ������', '��⵵ ������ ������ ���긮 1068-9', '�����', '140-013-176391');
INSERT INTO SHELTER_TABLE VALUES (SHELTER_TABLE_SEQ.nextval, '�ൿ�ϴ� �������', '��⵵ ���ֽ�', '��⵵ ���ֽ� ���̵� 329-355', '������', '469901-04-199087');

INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '2', '������', '�汸', 'F', '����', '2022/04/05', '����', 1);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '2', '�����', 'Ȳ��', 'F', '����', '2022/04/05', '����', 1);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '4', 'ȣ��', '�����ͽ�', 'M', '����', '2022/01/04', '����', 1);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '7', '����', '���', 'M', '�Ӻ�', '2021/11/13', '����', 1);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '6', '����', '��縮Ʈ����', 'F', '�Ӻ�', '2021/10/15', '����', 1);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '7', '����', '���׸���', 'F', '�Ǻ� �˷�����', '2021/08/05', '����', 1);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '7', '�޷�', '�����ͽ�', 'F', '����', '2021/07/09', '����', 1);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '1', '����', '�����ͽ�', 'M', '����', '2022/01/30', '����', 1);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '1', '�޸�', '������', 'M', '����', '2022/02/24', '����', 1);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '8', '��Ű', '��縮Ʈ����', 'F', '����', '2021/07/07', '����', 1);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '4', '������', '�����ͽ�', 'F', '����', '2022/01/05', '����', 1);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '3', '����', 'Ȳ��', 'F', '����', '2021/10/08', '����', 1);

INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '3', '������', '�汸', 'M', '����', '2021/09/05', '����', 2);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '4', '������', '�㽺Ű', 'M', '�Ǻ� �˷�����', '2021/10/08', '����', 2);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '2', '������', '�����ͽ�', 'M', '����', '2021/11/24', '����', 2);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '2', '�Ʒ���', '�汸', 'F', '����', '2021/12/30', '����', 2);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '2', '�ٷ���', '�汸', 'M', '����', '2022/02/24', '����', 2);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '13', '�߻�', '����', 'F', '����', '2021/09/03', '����', 2);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '9', '����', '����', 'F', '����', '2022/03/02', '����', 2);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '10', '���ӽ�', '�̴���', 'M','����', '2021/08/29', '����',2);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '5', '������', 'ġ�Ϳ�', 'M', '����', '2022/08/29', '����', 2);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '6', '��', '�ڽ���Ʈ', 'F', '�˷�����', '2021/05/27', '����', 2);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '9', '��', '���', 'F', '����', '2022/03/01', '����', 2);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '3', '�ڸ�', '��Ƽ��', 'F', '����', '2021/11/13', '����', 2);

INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '5',  '����', '���', 'F', '����', '2022/03/09', '����',3 );
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '3', '����', '���', 'F', '����', '2021/07/11', '����',3 ); 
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '1', '����', '���', 'M', '����', '2021/05/29', '����' ,3 ); 
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '5', '����', '���', 'M', '����', '2021/10/03', '����' ,3 ); 
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '9', '����', '���', 'F', '����', '2021/05/17', '����' ,3 ); 
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '10', '����', '���', 'F', '�Ǻκ�', '2022/02/01', '����',3 ); 
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '4', '�κ�', '���', 'F', 'ȫ��', '2021/01/13', '����' ,3 ); 
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '3', 'ȣ��', '���', 'F', '������', '2021/10/20', '����' ,3 ); 
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '2', '����', '���', 'F', '����', '2021/12/29', '����' ,3 ); 
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '5', '¯��', '���', 'M', '����', '2022/03/08', '����' ,3 ); 
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL,'10', '��Ű', '���', 'M', '����', '2021/01/12', '����' ,3 );
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '5',  '�ݶ�', '���', 'F', '����', '2022/03/09', '����',3 );

INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '5',  '�Ҷ�', '���', 'F', '����', '2022/03/09', '����',4 );
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '9', '������', '��Ƽ��', 'F', '�Ӻ�', '2021/09/17', '����', 4);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '5',  '��ī', '���', 'M', '����', '2022/03/09', '����',4 );
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '5',  '����', '���', 'F', '����', '2022/03/09', '����',4 );
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '6', '������', '���ġ�Ϳ�', 'F', '����', '2021/12/23', '����', 4);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '5', '��Ű', '��ī����Ͼ�', 'M', '����', '2022/02/10', '����', 4);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '10', '�λ�', '��Ƽ��', 'M', '����', '2021/10/09', '����', 4);
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '5',  '�ϳ�', '���', 'M', '����', '2022/03/09', '����',4 );
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '5',  '�ɴ���', '���', 'F', '�Ǻκ�', '2022/03/09', '����',4 );
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '5',  '�糪', '���', 'F', 'ȫ��', '2022/03/09', '����',4 );
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '5',  '���', 'Ȳ��', 'F', '����', '2022/03/09', '����',4 );
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '3', '����', '�汸', 'F', '����', '2021/07/11', '����',4 );


INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '5',  '����', '���', 'F', '����', '2022/03/09', '����',5 );
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '5',  'ǪǪ', '���', 'F', '����', '2022/03/09', '����',5 );
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '1', '�̹�', '�����ͽ�', 'M', '����', '2021/05/29', '����' ,5 ); 
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '5', '�ʷ�', 'Ǫ��', 'M', '����', '2021/10/03', '����' ,5 ); 
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '9', '�Ƹ�', 'Ǫ��', 'F', '����', '2021/05/17', '����' ,5 ); 
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '10', '���', '��Ƽ��', 'F', '�Ǻκ�', '2022/02/01', '����',5 ); 
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '4', '����', '��ũ���׸���', 'F', 'ȫ��', '2021/01/13', '����' ,5 ); 
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '3', '����', '��ũ���׸���', 'F', '������', '2021/10/20', '����' ,5 ); 
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '2', '�̴�', 'Ǫ��', 'F', '����', '2021/12/29', '����' ,5 ); 
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL, '5', '���', 'Ǫ��', 'M', '����', '2022/03/08', '����' ,5 ); 
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL,'10', '����', '���������', 'M', '����', '2021/01/12', '����' ,5 );
INSERT INTO DOG_TABLE VALUES (DOG_TABLE_SEQ.NEXTVAL,'10', '��ġ', '��Ƽ��', 'M', '����', '2021/01/12', '����' ,5 );