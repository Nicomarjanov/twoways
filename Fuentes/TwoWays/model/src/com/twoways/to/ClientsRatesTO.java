package com.twoways.to;

public class ClientsRatesTO {
        //
        private Long clientsCliId;
        private Float clrValue;
        private Long ratesRatId;
        private RatesTO ratesTO;
        private ClientsTO clientsTO;
        
        public ClientsRatesTO() {
        }

        public Long getClientsEmpId() {
            return clientsCliId;
        }

        public void setClientCliId(Long clientsCliId) {
            this.clientsCliId = clientsCliId;
        }

        public Float getClrValue() {
            return clrValue;
        }

        public void setClrValue(Float clrValue) {
            this.clrValue = clrValue;
        }

        public Long getRatesRatId() {
            return ratesRatId;
        }

        public void setRatesRatId(Long ratesRatId) {
            this.ratesRatId = ratesRatId;
        }

        public RatesTO getRatesTO() {
            return ratesTO;
        }

        public void setRatesTO(RatesTO ratesTO) {
            this.ratesTO = ratesTO;
        }

        public ClientsTO getClientsTO() {
            return clientsTO;
        }

        public void setClientsTO(ClientsTO clientsTO) {
            this.clientsTO = clientsTO;
        }
}
