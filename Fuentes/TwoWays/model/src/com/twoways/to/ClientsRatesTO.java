package com.twoways.to;

public class ClientsRatesTO {
        //
        private Float clrValue;
        private RatesTO ratesTO;
        private ClientsTO clientsTO;
        
        public ClientsRatesTO() {
        }

      
        public Float getClrValue() {
            return clrValue;
        }

        public void setClrValue(Float clrValue) {
            this.clrValue = clrValue;
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
