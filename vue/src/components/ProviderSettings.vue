<template>
  <head>
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css">
</head>
<body>
</body>
<!-- Side Navigation -->
<!--<nav class="w3-sidebar w3-bar-block w3-card w3-animate-left w3-center" style="display:none" id="mySidebar">
  <h1 class="w3-xxxlarge w3-text-theme">Side Navigation</h1>
  <button class="w3-bar-item w3-button" onclick="w3_close()">Close <i class="fa fa-remove"></i></button>
  <a href="#" class="w3-bar-item w3-button">Link 1</a>
  <a href="#" class="w3-bar-item w3-button">Link 2</a>
  <a href="#" class="w3-bar-item w3-button">Link 3</a>
  <a href="#" class="w3-bar-item w3-button">Link 4</a>
</nav>-->

<!-- Header -->
<header class="w3-container w3-theme w3-padding" id="myHeader">
 <!-- <i onclick="w3_open()" class="fa fa-bars w3-xlarge w3-button w3-theme"></i> -->
  <div class="w3-center">
  <h4 style="color:rgb(115, 182, 244);"> {{ doctorOffice.officeAddress }}</h4>
  <h1 class="w3-xxxlarge w3-animate-bottom" style="color:rgb(115, 182, 244);">{{ doctorOffice.officeName }}</h1>
    <div class="w3-padding-32">
      <button class="w3-btn w3-xlarge w3-dark-grey w3-hover-light-grey" id ="enter" type="submit" @click.prevent = "updateProfile" style="font-weight:900;">UPDATE PROFILE</button>
    </div>
    <div class="w3-padding-32" v-if="isUpdate">
      <button class="w3-btn w3-xlarge w3-dark-grey w3-hover-light-grey" id ="cancel" type="submit" @click.prevent = "cancelUpdate" style="font-weight:900;">CANCEL UPDATE</button>
    </div>
  </div>
</header>


<div class="w3-row-padding w3-center w3-margin-top">
<div class="w3-third">
  <div class="w3-card w3-container" style="min-height:460px">
  <h3>Profile</h3><br>
  <i class="fa fa-desktop w3-margin-bottom w3-text-theme" style="font-size:120px"></i>
  <div v-if="!isUpdate">
  <h5>First name     : {{ doctor.firstName }}</h5>
        <h5>Last name      : {{ doctor.lastName }}</h5>
        <h5>Email          : {{ doctor.email }}</h5>
        <h5>Phone Number   : {{ doctor.phoneNumber }}</h5>
        <h5>Gender         : {{ doctor.gender }}</h5>
  </div>
  
  <div v-if="isUpdate">
    <h5>First name     : <input type="text" v-model = "newDoctor.firstName"> </h5> 
        <h5>Last name      : <input type="text" v-model = "newDoctor.lastName"></h5>
        <h5>Email          : <input type="text" v-model = "newDoctor.email"></h5>
        <h5>Phone Number   : <input type="text" v-model = "newDoctor.phoneNumber"></h5>

        <h5>Gender         : 
          <select v-model="newDoctor.gender">
            <option disabled selected>Please select one</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Other">Other</option>
          </select>
        </h5>
      </div>
  </div>
</div>

<div class="w3-third" style="background-color:rgb(211, 220, 233);">
  <div class="w3-card w3-container" style="min-height:460px" >
  <h3>Hours</h3><br>
  <i class="fa fa-css3 w3-margin-bottom w3-text-theme" style="font-size:120px"></i>
  <div v-if="!isUpdate">
<h5>Open Sunday    : {{ doctor.openSunday }}</h5>
        <h5>Open Monday    : {{ doctor.openMonday }}</h5>
        <h5>Open Tuesday   : {{ doctor.openTuesday }}</h5>
        <h5>Open Wednesday : {{ doctor.openWednesday }}</h5>
        <h5>Open Thursday  : {{ doctor.openThursday }}</h5>
        <h5>Open Friday    : {{ doctor.openFriday }}</h5>
        <h5>Open Saturday  : {{ doctor.openSaturday }}</h5>
  </div>
  <div v-if="isUpdate">
    <h5>Open Sunday    : 
          <select v-model="newDoctor.openSunday">
            <option disabled selected>Please select one</option>
            <option value="True">True</option>
            <option value="False">False</option>
          </select>
        </h5>
        
        <h5>Open Monday    : 
          <select v-model="newDoctor.openMonday">
            <option disabled selected>Please select one</option>
            <option value="True">True</option>
            <option value="False">False</option>
          </select>
        </h5>

        <h5>Open Tuesday   : 
          <select v-model="newDoctor.openTuesday">
            <option disabled selected>Please select one</option>
            <option value="True">True</option>
            <option value="False">False</option>
          </select>
        </h5>

        <h5>Open Wednesday : 
          <select  v-model="newDoctor.openWednesday">
            <option disabled selected>Please select one</option>
            <option value="True">True</option>
            <option value="False">False</option>
          </select>
        </h5>

        <h5>Open Thursday  : 
          <select  v-model="newDoctor.openThursday">
            <option disabled selected>Please select one</option>
            <option value="True">True</option>
            <option value="False">False</option>
          </select>
        </h5>

        <h5>Open Friday    : 
          <select  v-model="newDoctor.openFriday">
            <option disabled selected>Please select one</option>
            <option value="True">True</option>
            <option value="False">False</option>
          </select>
        </h5>

        <h5>Open Saturday  : 
          <select  v-model="newDoctor.openSaturday">
            <option disabled selected>Please select one</option>
            <option value="True">True</option>
            <option value="False">False</option>
          </select>
        </h5>
      </div>
  </div>
</div>


<div class="w3-third" style="background-color:grey;">
  <div class="w3-card w3-container" style="min-height:460px">
  <h3>Office</h3><br>
  <i class="fa fa-home fa-fw w3-margin-right w3-text-theme" style="font-size:120px"></i>
  <div v-if="!isUpdate">
  <h5>Office name    : {{ doctorOffice.officeName }} </h5>
        <h5>Office address : {{ doctorOffice.officeAddress }}</h5>
        <h5>Office phone   : {{ doctorOffice.phoneNumber }}</h5>
        <h5>Hours from     : {{ doctor.hoursFrom }}</h5>
        <h5>Hours to       : {{ doctor.hoursTo }}</h5>
  </div>
  <div v-if="isUpdate">
    <h5>Office name    : <input type="text" v-model="newOffice.officeName"/> </h5>
        <h5>Office address : <input type="text" v-model="newOffice.officeAddress"/> </h5>
        <h5>Office phone   : <input type="text" v-model="newOffice.phoneNumber"/> </h5>
        <h5>Hours from     : 
          <select  v-model="newDoctor.hoursFrom" required autofocus>
            <option disabled selected>Hours From</option>
            <option value="00:00:00">12:00am</option>
            <option value="01:00:00">1:00am</option>
            <option value="02:00:00">2:00am</option>
            <option value="03:00:00">3:00am</option>
            <option value="04:00:00">4:00am</option>
            <option value="05:00:00">5:00am</option>
            <option value="06:00:00">6:00am</option>
            <option value="07:00:00">7:00am</option>
            <option value="08:00:00">8:00am</option>
            <option value="09:00:00">9:00am</option>
            <option value="10:00:00">10:00am</option>
            <option value="11:00:00">11:00am</option>
            <option value="12:00:00">12:00pm</option>
            <option value="13:00:00">1:00pm</option>
            <option value="14:00:00">2:00pm</option>
            <option value="15:00:00">3:00pm</option>
            <option value="16:00:00">4:00pm</option>
            <option value="17:00:00">5:00pm</option>
            <option value="18:00:00">6:00pm</option>
            <option value="19:00:00">7:00pm</option>
            <option value="20:00:00">8:00pm</option>
            <option value="21:00:00">9:00pm</option>
            <option value="22:00:00">10:00pm</option>
            <option value="23:00:00">11:00pm</option>
          </select>
        </h5>

        <h5>Hours to       : 
          <select  v-model="newDoctor.hoursTo" required autofocus>
            <option disabled selected>Hours From</option>
            <option value="00:00:00">12:00am</option>
            <option value="01:00:00">1:00am</option>
            <option value="02:00:00">2:00am</option>
            <option value="03:00:00">3:00am</option>
            <option value="04:00:00">4:00am</option>
            <option value="05:00:00">5:00am</option>
            <option value="06:00:00">6:00am</option>
            <option value="07:00:00">7:00am</option>
            <option value="08:00:00">8:00am</option>
            <option value="09:00:00">9:00am</option>
            <option value="10:00:00">10:00am</option>
            <option value="11:00:00">11:00am</option>
            <option value="12:00:00">12:00pm</option>
            <option value="13:00:00">1:00pm</option>
            <option value="14:00:00">2:00pm</option>
            <option value="15:00:00">3:00pm</option>
            <option value="16:00:00">4:00pm</option>
            <option value="17:00:00">5:00pm</option>
            <option value="18:00:00">6:00pm</option>
            <option value="19:00:00">7:00pm</option>
            <option value="20:00:00">8:00pm</option>
            <option value="21:00:00">9:00pm</option>
            <option value="22:00:00">10:00pm</option>
            <option value="23:00:00">11:00pm</option>
          </select>
        </h5>
      </div>
  </div>
</div>
</div>
  <section id = "page">
    <div id = "leftColumn">

      <!--for formatting to seperate display and value-->
      <div></div>
      <div></div>


    </div>
    
    <div id = "middleColumn">

        <!--<input id ="enter" type="submit" @click.prevent = "updateProfile" value ="Update Profile" />-->

    </div>
    
    <div id = "rightColumn">


    </div>


  </section>
</template>

<script>

import DoctorService from '../services/DoctorService';

export default {
  data() {
    return {

      isUpdate: false,

      doctor : {},

      newDoctor : {},

      doctorOffice: {},

      newOffice: {}
    };
  },
  methods:{
    cancelUpdate(){
    this.isUpdate = false;
    },
    updateProfile(){
      if(!this.isUpdate){
        this.isUpdate = true;
        return;
      }

      if( Object.keys(this.newDoctor).length === 0 && Object.keys(this.newOffice).length === 0){
        alert("No values were updated");
        return;
      }
      
      if(Object.keys(this.newDoctor).length != 0){

        if(this.newDoctor.hasOwnProperty('hoursFrom')){
          if(!this.newDoctor.hoursFrom){
            this.newDoctor.hoursFrom = this.doctor.hoursFrom;
            
          }else{
            //if exist update office hours too
            this.newOffice.hoursFrom = this.newDoctor.hoursTo;
          }
        }else{
          this.newDoctor.hoursFrom = this.doctor.hoursFrom;
        }

        if(this.newDoctor.hasOwnProperty('hoursTo')){
          if(!this.newDoctor.hoursTo){
            this.newDoctor.hoursTo = this.doctor.hoursTo;
          }
          else{
            //if exist update office hours too
            this.newOffice.hoursTo = this.newDoctor.hoursTo;
          }
        }else{
          this.newDoctor.hoursTo = this.doctor.hoursTo;
        }

        if(this.newDoctor.hoursFrom > this.newDoctor.hoursTo){
          alert("Hours from must be smaller than hours to");
          this.newDoctor = {};
          this.newOffice = {};
          return;
        }

        
        if(this.newDoctor.hoursFrom === this.newDoctor.hoursTo){
          alert("Hours from must not be equal to hours to");
          this.newDoctor = {};
          this.newOffice = {};
          return;
        }

        DoctorService.updateDoctor(this.newDoctor).then(
          (response)=>{
            if(response.status == 200){
              this.$store.commit('SET_USER', response.data);
              this.doctor = response.data;
              this.newDoctor = {};
            }
          }
        ).catch(
          (error)=>{
            console.log("Unable to update user");
          }

        );
      }

      if(Object.keys(this.newOffice).length != 0){
        if(this.newOffice.hasOwnProperty('officeName')){
          if(!this.newOffice.officeName){
            this.newOffice.officeName = this.doctorOffice.officeName;
          }
        }else{
          this.newOffice.officeName = this.doctorOffice.officeName;
        }

        if(this.newOffice.hasOwnProperty('officeAddress')){
          if(!this.newOffice.officeAddress){
            this.newOffice.officeAddress = this.doctorOffice.officeAddress;
          }
        }else{
          this.newOffice.officeAddress = this.doctorOffice.officeAddress;
        }

        if(this.newOffice.hasOwnProperty('phoneNumber')){
          if(!this.newOffice.phoneNumber){
            this.newOffice.phoneNumber = this.doctorOffice.phoneNumber;
          }
        }else{
          this.newOffice.phoneNumber = this.doctorOffice.phoneNumber;
        }

        this.newOffice.officeId = this.doctorOffice.officeId;

        DoctorService.updateOffice(this.newOffice).then(
          (response)=>{
            if(response.status == 200){
              this.doctorOffice = response.data;
              this.newOffice = {};
            }
          }
        ).catch(
          (error)=>{
            console.log("Unable to update office");
          }
        );
      }
        
      
    }
  },

  created() {

    this.doctor = this.$store.state.user,

    DoctorService.getOfficeByDoctor().then(
      (response) => {
        if (response.status == 200) {
          this.doctorOffice = response.data;
        }
      }
    ).catch(
      (error) => {
        console.log("Unable to get doctor office");
      }
    );
  },
}
</script>

<style scoped>
#myHeader{
  background-image: url('../assets/officebase1img.jpg');
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}

  #page{
    display: flex;
  }
  #leftColumn{
    width: 40%;
    margin-left: 5%;
    margin: 2%;

  }
  #leftColumn h5{
    margin: 4%;
  }
  #middleColumn{
    width: 55%;
    margin: 2%;
  }
  
  #rightColumn{
    width: 5%;
    margin: 2%;
  }
  h5{
    margin: 2%;
  }
  h4{
    font-weight: 900;
  }
  h1{
    font-weight: 900;
  }
  


</style>