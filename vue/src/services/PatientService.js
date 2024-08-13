import axios from 'axios';

export default {

        getAppointments() {
            return axios.get("/patient/appointments");
        },

        createAppointment(appointmentObject) {
            return axios.post("/patient/appointment", appointmentObject); 
        },
        
        getOffices() {
            return axios.get("/patient/offices");
        },

        getServices(){
            return axios.get("/patient/services");
        },

        getServiceInfo(serviceName){
            return axios.get("/patient/services/" + serviceName);
        }

        


       // updateAppointment(id) {
       //        return axios.put(`patient/appointment/${id}`)
       //  },

}