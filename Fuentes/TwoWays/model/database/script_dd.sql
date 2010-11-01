CREATE TABLE USERS
(
USR_ID VARCHAR2(10) NOT NULL,
USR_FIRST_NAME VARCHAR2(100) NOT NULL,
USR_LAST_NAME VARCHAR2(100),
USR_PHONE_NUMBER NUMBER,
USR_MOBILE_NUMBER NUMBER,
USR_OFFICE_NUMBER NUMBER,
USR_MAIL VARCHAR2(100),
USR_BIRTH DATE,
ROLES_ROL_ID NUMBER
)
;

CREATE TABLE TRANSLATORS
(
TRA_ID NUMBER NOT NULL,
TRA_SPECIALIZATION VARCHAR2(30),
LANGUAGUES_LAN_ID NUMBER,
LANGUAGUES_LAN_ID1 NUMBER,
LANGUAGUES_LAN_ID2 NUMBER,
EMPLOYEES_EMP_ID NUMBER,
TRA_EDITOR CHAR(1)
)
;

CREATE TABLE ROLES
(
ROL_ID NUMBER NOT NULL,
ROL_NAME VARCHAR2(100) NOT NULL,
ROL_DESCRIPTION VARCHAR2(100)
)
;

CREATE TABLE RATES
(
RAT_ID NUMBER NOT NULL,
RAT_TYPE VARCHAR2(30) NOT NULL,
RAT_NAME VARCHAR2(4000) NOT NULL,
RAT_DESCRIPTION VARCHAR2(4000),
RAT_VALUE VARCHAR2(4000) NOT NULL,
CURRENCY_CUR_ID NUMBER
)
;

CREATE TABLE PROJECTS_RATES
(
PROJECTS_PRO_ID NUMBER NOT NULL,
RATES_RAT_ID NUMBER NOT NULL
)
;

CREATE TABLE PROJECTS
(
PRO_ID NUMBER NOT NULL,
PRO_NAME VARCHAR2(100) NOT NULL,
PRO_DESCRIPTION VARCHAR2(150),
PRO_STATUS VARCHAR2(20),
PRO_TOTAL_CLI_WCOUNT NUMBER,
PRO_TOTAL_CLIENT NUMBER,
PRO_TOTAL_TRAN_WCOUNT NUMBER,
PRO_TOTAL_TRANSLATOR NUMBER,
PRO_START_DATE DATE NOT NULL,
PRO_DEADLINE_DATE DATE,
PRO_FINISH_DATE DATE,
CURRENCY_COTIZATIONS_CUC_ID NUMBER,
USERS_USR_ID VARCHAR2(10),
CURRENCY_CUR_ID NUMBER,
ORDERS_ORD_ID NUMBER
)
;

CREATE TABLE PROJECT_ASSIGNMENTS
(
PRA_ID NUMBER NOT NULL,
PRA_CLASSIFICATION VARCHAR2(50),
EMPLOYEES_EMP_ID NUMBER NOT NULL,
PROJECTS_PRO_ID NUMBER NOT NULL,
PRA_ASSIGN_DATE DATE NOT NULL,
PRA_DEADLINE_DATE DATE,
PRA_FINISH_DATE DATE
)
;

CREATE TABLE PAYMENTS
(
PAY_ID NUMBER NOT NULL,
PAY_DATE DATE NOT NULL,
PAY_DESCRIPTION VARCHAR2(250),
PAY_TOTAL NUMBER,
CURRENCY_CUR_ID NUMBER,
CLIENTS_CLI_ID NUMBER,
INVOICES_INV_ID NUMBER,
ACCOUNTS_ACC_ID NUMBER
)
;

CREATE TABLE ORDERS_DOCS
(
ODO_ID NUMBER NOT NULL,
ODO_NAME VARCHAR2(100) NOT NULL,
ORDERS_ORD_ID NUMBER NOT NULL
)
;

CREATE TABLE ORDERS
(
ORD_ID NUMBER NOT NULL,
ORD_NAME VARCHAR2(100) NOT NULL,
ORD_DESCRIPTION VARCHAR2(4000),
ORD_DATE DATE NOT NULL,
CLIENTS_CLI_ID NUMBER,
ORD_PROJ_ID VARCHAR2(30),
ORD_WO_NUMBER NUMBER,
ORD_JOB_ID VARCHAR2(30),
ORD_JOB_NAME VARCHAR2(100),
ORD_JOB_DESCRIPTION VARCHAR2(250)
)
;

CREATE TABLE LANGUAGUES
(
LAN_ID NUMBER NOT NULL,
LAN_NAME VARCHAR2(100),
LAN_DESCRIPTION VARCHAR2(100)
)
;

CREATE TABLE ITEMS_INVOICES
(
ITI_ID NUMBER NOT NULL,
ITI_VALUE NUMBER,
ITEMS_ITM_ID NUMBER,
INVOICES_INV_ID NUMBER,
ACCOUNTS_ACC_ID NUMBER
)
;

CREATE TABLE ITEMS_EXPENSES
(
ITE_ID NUMBER NOT NULL,
ITE_VALUE NUMBER,
ITEMS_ITM_ID NUMBER NOT NULL,
EXPENSES_EXP_ID NUMBER NOT NULL,
CURRENCY_CUR_ID NUMBER,
ACCOUNTS_ACC_ID NUMBER
)
;

CREATE TABLE ITEMS
(
ITM_ID NUMBER NOT NULL,
ITM_NAME VARCHAR2(100) NOT NULL,
ITM_DESCRIPTION VARCHAR2(150)
)
;

CREATE TABLE INVOICES
(
INV_ID NUMBER NOT NULL,
INV_DATE DATE NOT NULL,
INV_TOTAL NUMBER,
CURRENCY_CUR_ID NUMBER,
ORDERS_ORD_ID NUMBER
)
;

CREATE TABLE EXPENSES
(
EXP_ID NUMBER NOT NULL,
EXP_TOTAL NUMBER,
EXP_DATE DATE NOT NULL,
EMPLOYEES_EMP_ID NUMBER
)
;

CREATE TABLE EMPLOYEES_RATES
(
RATES_RAT_ID NUMBER NOT NULL,
EMPLOYEES_EMP_ID NUMBER NOT NULL
)
;

CREATE TABLE EMPLOYEES
(
EMP_ID NUMBER NOT NULL,
EMP_FIRST_NAME VARCHAR2(100) NOT NULL,
EMP_LAST_NAME VARCHAR2(100),
EMP_MAIL VARCHAR2(100),
EMP_BIRTH DATE,
EMP_PHONE_NUMBER NUMBER,
EMP_MOBILE_NUMBER NUMBER,
EMP_ADDRESS VARCHAR2(150),
EMP_LOCATION VARCHAR2(100)
)
;

CREATE TABLE CURRENCY_COTIZATIONS
(
CUC_ID NUMBER NOT NULL,
CUC_DATE DATE NOT NULL,
CUC_VALUE NUMBER NOT NULL,
CURRENCY_CUR_ID NUMBER NOT NULL
)
;

CREATE TABLE CURRENCY
(
CUR_ID NUMBER NOT NULL,
CUR_NAME VARCHAR2(100) NOT NULL,
CUR_ORIGIN VARCHAR2(100)
)
;

CREATE TABLE CLIENTS
(
CLI_ID NUMBER NOT NULL,
CLI_NAME VARCHAR2(100),
CLI_DESCRIPTION VARCHAR2(250),
CLI_MAIL VARCHAR2(100),
CLI_ADDRESS VARCHAR2(250),
CLI_PHONE VARCHAR2(25),
CLI_COUNTRY VARCHAR2(100),
CLI_POSTAL_CODE NUMBER,
CURRENCY_CUR_ID NUMBER
)
;

CREATE TABLE CLIENT_RESPONSABLE
(
CRE_ID NUMBER NOT NULL,
CRE_FIRST_NAME VARCHAR2(100) NOT NULL,
CRE_LAST_NAME VARCHAR2(100),
CRE_EMAIL VARCHAR2(100),
CRE_PHONE_NUMBER NUMBER,
CRE_MOBILE_NUMBER NUMBER,
CLIENTS_CLI_ID NUMBER
)
;

CREATE TABLE ACCOUNTS
(
ACC_ID NUMBER NOT NULL,
ACC_NAME VARCHAR2(100) NOT NULL,
ACC_DESCRIPTION VARCHAR2(250),
ACC_DETAILS VARCHAR2(250)
)
;

ALTER TABLE USERS
ADD CONSTRAINT USERS_PK PRIMARY KEY
(
USR_ID
)
 ENABLE
;

ALTER TABLE TRANSLATORS
ADD CONSTRAINT TRANSLATORS_PK PRIMARY KEY
(
TRA_ID
)
 ENABLE
;

ALTER TABLE ROLES
ADD CONSTRAINT ROLES_PK PRIMARY KEY
(
ROL_ID
)
 ENABLE
;

ALTER TABLE RATES
ADD CONSTRAINT RATES_PK PRIMARY KEY
(
RAT_ID
)
 ENABLE
;

ALTER TABLE PROJECTS_RATES
ADD CONSTRAINT PROJECTS_RATES_PK PRIMARY KEY
(
PROJECTS_PRO_ID,
RATES_RAT_ID
)
 ENABLE
;

ALTER TABLE PROJECTS
ADD CONSTRAINT PROJECTS_PK PRIMARY KEY
(
PRO_ID
)
 ENABLE
;

ALTER TABLE PROJECT_ASSIGNMENTS
ADD CONSTRAINT PROJECT_TRANSLATORS_PK PRIMARY KEY
(
PRA_ID,
EMPLOYEES_EMP_ID,
PROJECTS_PRO_ID
)
 ENABLE
;

ALTER TABLE PAYMENTS
ADD CONSTRAINT PAYMENTS_PK PRIMARY KEY
(
PAY_ID
)
 ENABLE
;

ALTER TABLE ORDERS_DOCS
ADD CONSTRAINT ORDERS_DOCS_PK PRIMARY KEY
(
ODO_ID,
ORDERS_ORD_ID
)
 ENABLE
;

ALTER TABLE ORDERS
ADD CONSTRAINT ORDERS_PK PRIMARY KEY
(
ORD_ID
)
 ENABLE
;

ALTER TABLE LANGUAGUES
ADD CONSTRAINT LANGUAGUES_PK PRIMARY KEY
(
LAN_ID
)
 ENABLE
;

ALTER TABLE ITEMS_INVOICES
ADD CONSTRAINT ITEMS_INVOICES_PK PRIMARY KEY
(
ITI_ID
)
 ENABLE
;

ALTER TABLE ITEMS_EXPENSES
ADD CONSTRAINT ITEMS_EXPENSES_PK PRIMARY KEY
(
ITE_ID,
ITEMS_ITM_ID,
EXPENSES_EXP_ID
)
 ENABLE
;

ALTER TABLE ITEMS
ADD CONSTRAINT ITEMS_PK PRIMARY KEY
(
ITM_ID
)
 ENABLE
;

ALTER TABLE INVOICES
ADD CONSTRAINT INVOICES_PK PRIMARY KEY
(
INV_ID
)
 ENABLE
;

ALTER TABLE EXPENSES
ADD CONSTRAINT EXPENSES_PK PRIMARY KEY
(
EXP_ID
)
 ENABLE
;

ALTER TABLE EMPLOYEES_RATES
ADD CONSTRAINT EMPLOYEES_RATES_PK PRIMARY KEY
(
RATES_RAT_ID,
EMPLOYEES_EMP_ID
)
 ENABLE
;

ALTER TABLE EMPLOYEES
ADD CONSTRAINT EMPLOYEES_PK PRIMARY KEY
(
EMP_ID
)
 ENABLE
;

ALTER TABLE CURRENCY_COTIZATIONS
ADD CONSTRAINT CURRENCY_COTIZATIONS_PK PRIMARY KEY
(
CUC_ID,
CURRENCY_CUR_ID
)
 ENABLE
;

ALTER TABLE CURRENCY
ADD CONSTRAINT CURRENCY_PK PRIMARY KEY
(
CUR_ID
)
 ENABLE
;

ALTER TABLE CLIENTS
ADD CONSTRAINT CLIENTS_PK PRIMARY KEY
(
CLI_ID
)
 ENABLE
;

ALTER TABLE CLIENT_RESPONSABLE
ADD CONSTRAINT CLIENT_RESPONSABLE_PK PRIMARY KEY
(
CRE_ID
)
 ENABLE
;

ALTER TABLE ACCOUNTS
ADD CONSTRAINT ACCOUNTS_PK PRIMARY KEY
(
ACC_ID
)
 ENABLE
;

ALTER TABLE USERS
ADD CONSTRAINT USERS_ROLES_FK FOREIGN KEY
(
ROLES_ROL_ID
)
REFERENCES ROLES
(
ROL_ID
) ENABLE
;

ALTER TABLE TRANSLATORS
ADD CONSTRAINT TRANSLATORS_NATIVE_LANG_FK FOREIGN KEY
(
LANGUAGUES_LAN_ID
)
REFERENCES LANGUAGUES
(
LAN_ID
) ENABLE
;

ALTER TABLE TRANSLATORS
ADD CONSTRAINT TRANSLATORS_LANG1_FK FOREIGN KEY
(
LANGUAGUES_LAN_ID1
)
REFERENCES LANGUAGUES
(
LAN_ID
) ENABLE
;

ALTER TABLE TRANSLATORS
ADD CONSTRAINT TRANSLATORS_LANG2_FK FOREIGN KEY
(
LANGUAGUES_LAN_ID2
)
REFERENCES LANGUAGUES
(
LAN_ID
) ENABLE
;

ALTER TABLE TRANSLATORS
ADD CONSTRAINT TRANSLATORS_EMPLOYEES_FK FOREIGN KEY
(
EMPLOYEES_EMP_ID
)
REFERENCES EMPLOYEES
(
EMP_ID
) ENABLE
;

ALTER TABLE RATES
ADD CONSTRAINT RATES_CURRENCY_FK FOREIGN KEY
(
CURRENCY_CUR_ID
)
REFERENCES CURRENCY
(
CUR_ID
) ENABLE
;

ALTER TABLE PROJECTS_RATES
ADD CONSTRAINT PROJECTS_RATES_PROJECTS_FK FOREIGN KEY
(
PROJECTS_PRO_ID
)
REFERENCES PROJECTS
(
PRO_ID
) ENABLE
;

ALTER TABLE PROJECTS_RATES
ADD CONSTRAINT PROJECTS_RATES_RATES_FK FOREIGN KEY
(
RATES_RAT_ID
)
REFERENCES RATES
(
RAT_ID
) ENABLE
;

ALTER TABLE PROJECTS
ADD CONSTRAINT PROJECTS_CURRENCY_FK FOREIGN KEY
(
CURRENCY_CUR_ID
)
REFERENCES CURRENCY
(
CUR_ID
) ENABLE
;

ALTER TABLE PROJECTS
ADD CONSTRAINT PROJECTS_ORDERS_FK FOREIGN KEY
(
ORDERS_ORD_ID
)
REFERENCES ORDERS
(
ORD_ID
) ENABLE
;

ALTER TABLE PROJECT_ASSIGNMENTS
ADD CONSTRAINT PROJECT_ASSIGNMENTS_FK FOREIGN KEY
(
EMPLOYEES_EMP_ID
)
REFERENCES EMPLOYEES
(
EMP_ID
) ENABLE
;

ALTER TABLE PROJECT_ASSIGNMENTS
ADD CONSTRAINT PROJECT_ASSIGNMENTS_FK1 FOREIGN KEY
(
PROJECTS_PRO_ID
)
REFERENCES PROJECTS
(
PRO_ID
) ENABLE
;

ALTER TABLE PAYMENTS
ADD CONSTRAINT PAYMENTS_CURRENCY_FK FOREIGN KEY
(
CURRENCY_CUR_ID
)
REFERENCES CURRENCY
(
CUR_ID
) ENABLE
;

ALTER TABLE PAYMENTS
ADD CONSTRAINT PAYMENTS_CLIENTS_FK FOREIGN KEY
(
CLIENTS_CLI_ID
)
REFERENCES CLIENTS
(
CLI_ID
) ENABLE
;

ALTER TABLE PAYMENTS
ADD CONSTRAINT PAYMENTS_INVOICES_FK FOREIGN KEY
(
INVOICES_INV_ID
)
REFERENCES INVOICES
(
INV_ID
) ENABLE
;

ALTER TABLE PAYMENTS
ADD CONSTRAINT PAYMENTS_ACCOUNTS_FK FOREIGN KEY
(
ACCOUNTS_ACC_ID
)
REFERENCES ACCOUNTS
(
ACC_ID
) ENABLE
;

ALTER TABLE ORDERS_DOCS
ADD CONSTRAINT ORDERS_DOCS_ORDERS_FK FOREIGN KEY
(
ORDERS_ORD_ID
)
REFERENCES ORDERS
(
ORD_ID
) ENABLE
;

ALTER TABLE ORDERS
ADD CONSTRAINT ORDERS_CLIENTS_FK FOREIGN KEY
(
CLIENTS_CLI_ID
)
REFERENCES CLIENTS
(
CLI_ID
) ENABLE
;

ALTER TABLE ITEMS_INVOICES
ADD CONSTRAINT ITEMS_INVOICES_ITEMS_FK FOREIGN KEY
(
ITEMS_ITM_ID
)
REFERENCES ITEMS
(
ITM_ID
) ENABLE
;

ALTER TABLE ITEMS_INVOICES
ADD CONSTRAINT ITEMS_INVOICES_INVOICES_FK FOREIGN KEY
(
INVOICES_INV_ID
)
REFERENCES INVOICES
(
INV_ID
) ENABLE
;

ALTER TABLE ITEMS_INVOICES
ADD CONSTRAINT ITEMS_INVOICES_ACCOUNTS_FK FOREIGN KEY
(
ACCOUNTS_ACC_ID
)
REFERENCES ACCOUNTS
(
ACC_ID
) ENABLE
;

ALTER TABLE ITEMS_EXPENSES
ADD CONSTRAINT ITEMS_EXPENSES_ITEMS_FK FOREIGN KEY
(
ITEMS_ITM_ID
)
REFERENCES ITEMS
(
ITM_ID
) ENABLE
;

ALTER TABLE ITEMS_EXPENSES
ADD CONSTRAINT ITEMS_EXPENSES_EXPENSES_FK FOREIGN KEY
(
EXPENSES_EXP_ID
)
REFERENCES EXPENSES
(
EXP_ID
) ENABLE
;

ALTER TABLE ITEMS_EXPENSES
ADD CONSTRAINT ITEMS_EXPENSES_CURRENCY_FK FOREIGN KEY
(
CURRENCY_CUR_ID
)
REFERENCES CURRENCY
(
CUR_ID
) ENABLE
;

ALTER TABLE ITEMS_EXPENSES
ADD CONSTRAINT ITEMS_EXPENSES_ACCOUNTS_FK FOREIGN KEY
(
ACCOUNTS_ACC_ID
)
REFERENCES ACCOUNTS
(
ACC_ID
) ENABLE
;

ALTER TABLE INVOICES
ADD CONSTRAINT INVOICES_CURRENCY_FK FOREIGN KEY
(
CURRENCY_CUR_ID
)
REFERENCES CURRENCY
(
CUR_ID
) ENABLE
;

ALTER TABLE INVOICES
ADD CONSTRAINT INVOICES_ORDERS_FK FOREIGN KEY
(
ORDERS_ORD_ID
)
REFERENCES ORDERS
(
ORD_ID
) ENABLE
;

ALTER TABLE EXPENSES
ADD CONSTRAINT EXPENSES_EMPLOYEES_FK FOREIGN KEY
(
EMPLOYEES_EMP_ID
)
REFERENCES EMPLOYEES
(
EMP_ID
) ENABLE
;

ALTER TABLE EMPLOYEES_RATES
ADD CONSTRAINT TRANSLATORS_RATES_RATES_FK FOREIGN KEY
(
RATES_RAT_ID
)
REFERENCES RATES
(
RAT_ID
) ENABLE
;

ALTER TABLE EMPLOYEES_RATES
ADD CONSTRAINT EMPLOYEES_RATES_EMPLOYEES_FK FOREIGN KEY
(
EMPLOYEES_EMP_ID
)
REFERENCES EMPLOYEES
(
EMP_ID
) ENABLE
;

ALTER TABLE CURRENCY_COTIZATIONS
ADD CONSTRAINT CURRENCY_COTIZATIONS_FK FOREIGN KEY
(
CURRENCY_CUR_ID
)
REFERENCES CURRENCY
(
CUR_ID
) ENABLE
;

ALTER TABLE CLIENTS
ADD CONSTRAINT CLIENTS_CURRENCY_FK FOREIGN KEY
(
CURRENCY_CUR_ID
)
REFERENCES CURRENCY
(
CUR_ID
) ENABLE
;

ALTER TABLE CLIENT_RESPONSABLE
ADD CONSTRAINT CLIENT_RESPONSABLE_CLIENTS_FK FOREIGN KEY
(
CLIENTS_CLI_ID
)
REFERENCES CLIENTS
(
CLI_ID
) ENABLE
;

