CREATE TABLE CLIENTS_RATES
(
CLIENTS_CLI_ID NUMBER,
CLR_VALUE NUMBER,
RATES_RAT_ID NUMBER
)
;

ALTER TABLE CLIENTS_RATES
ADD CONSTRAINT CLIENTS_RATES_CLIENTS_FK FOREIGN KEY
(
CLIENTS_CLI_ID
)
REFERENCES MYSCHEMA.CLIENTS
(
CLI_ID
) ENABLE
;

ALTER TABLE CLIENTS_RATES
ADD CONSTRAINT CLIENTS_RATES_RATES_FK FOREIGN KEY
(
RATES_RAT_ID
)
REFERENCES RATES
(
RAT_ID
) ENABLE
;

