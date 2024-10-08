package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDto {

        private String doctorFirstName;
        private String doctorLastName;
        private String serviceDescription;
        private String officeName;
        private String officeAddress;
        private String officePhone;
        private int appointmentId;
        private int serviceId;
        private int officeId;
        private int patientId;
        private  int doctorId;
        private boolean notified;
        private boolean approved;
        private String apptDate;

        private String gender;

        private String email;

        private String phone_number;

        private LocalDate dateOfBirth;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public LocalDate getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

    public AppointmentDto () {}

//        public AppointmentDto(String doctorName, String serviceDescription, String officeName, String officeAddress, String officePhone, int appointmentId, int serviceId, int officeId, int patientId, int doctorId, LocalTime apptFrom, LocalTime apptTo, boolean openMonday, boolean openTuesday, boolean openWednesday, boolean openThursday, boolean openFriday, boolean openSaturday, boolean openSunday, boolean notified, boolean approved) {
//        this.doctorName = doctorName;
//        this.serviceDescription = serviceDescription;
//        this.officeName = officeName;
//        this.officeAddress = officeAddress;
//        this.officePhone = officePhone;
//        this.appointmentId = appointmentId;
//        this.serviceId = serviceId;
//        this.officeId = officeId;
//        this.patientId = patientId;
//        this.doctorId = doctorId;
//        this.apptFrom = apptFrom;
//        this.apptTo = apptTo;
//        this.openMonday = openMonday;
//        this.openTuesday = openTuesday;
//        this.openWednesday = openWednesday;
//        this.openThursday = openThursday;
//        this.openFriday = openFriday;
//        this.openSaturday = openSaturday;
//        this.openSunday = openSunday;
//        this.notified = notified;
//        this.approved = approved;


    public boolean isNotified() {
        return notified;
    }

    public boolean isApproved() {
        return approved;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getApptDate() {
        return apptDate;
    }

    public void setApptDate(String apptDate) {
        this.apptDate = apptDate;
    }

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public int getAppointmentId() {
            return appointmentId;
        }

        public void setAppointmentId(int appointmentId) {
            this.appointmentId = appointmentId;
        }

        public int getServiceId() {
            return serviceId;
        }

        public void setServiceId(int serviceId) {
            this.serviceId = serviceId;
        }

        public int getOfficeId() {
            return officeId;
        }

        public void setOfficeId(int officeId) {
            this.officeId = officeId;
        }

        public int getPatientId() {
            return patientId;
        }

        public void setPatientId(int patientId) {
            this.patientId = patientId;
        }

        public int getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(int doctorId) {
            this.doctorId = doctorId;
        }


        public boolean notified() {
            return notified;
        }

        public void setNotified(boolean notified) {
            this.notified = notified;
        }

        public boolean approved() {
            return approved;
        }

        public void setApproved(boolean approved) {
            this.approved = approved;
        }
    }

