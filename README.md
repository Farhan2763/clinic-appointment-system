
#  Clinic Appointment Management System

A Java 19 + Spring Boot REST API to manage patients, doctors, and appointments. This project allows booking appointments between patients and doctors while preventing double-booking and unavailable slots.

---

## Tech Stack

- Java 19
- Spring Boot
- Maven
- Swagger UI
- In-Memory Repositories (for simplicity)

---

##  Setup Instructions

1. Install *Java 19* and *Maven*
2. Clone or unzip the project folder
3. Open terminal and run:

bash
mvn clean install
mvn spring-boot:run


4. Access Swagger:  
   [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

 ## Swagger UI – API Overview

The following image displays all available REST API endpoints exposed by the Clinic Appointment Management System:

###  Patient Controller
- `GET /patients`  
  Fetch all patients.

- `POST /patients`  
  Register a new patient.

###  Doctor Controller
- `GET /doctors`  
  Retrieve all doctors.

- `POST /doctors`  
  Add a new doctor.

- `GET /doctors/{id}`  
  Get doctor details by ID.  
  _Example:_ `/doctors/1`

- `GET /doctors/{id}/slots`  
  Retrieve available slots for a doctor.  
  _Example:_ `/doctors/1/slots`

###  Appointment Controller
- `GET /appointments`  
  Get all booked appointments.

- `POST /appointments`  
  Book a new appointment.

- `GET /appointments/doctor/{doctorId}`  
  Get all appointments for a given doctor ID.

- `GET /appointments/doctor/{doctorId}/date`  
  Get appointments by doctor ID and date.

---
 ### Swagger Screenshot
![image alt](https://github.com/Farhan2763/clinic-appointment-system/blob/9c8e1030471ccb637b5d9f90c48aae0401aeb958/Patientadd.jpeg?raw=true)


![image alt](https://github.com/Farhan2763/clinic-appointment-system/blob/219a8e30ec3780157850125a301ce1875ead6deb/patientget.jpeg?raw=true)

![image alt](https://github.com/Farhan2763/clinic-appointment-system/blob/219a8e30ec3780157850125a301ce1875ead6deb/DoctorAdd.jpeg?raw=true)


![image alt](https://github.com/Farhan2763/clinic-appointment-system/blob/219a8e30ec3780157850125a301ce1875ead6deb/doctorget.jpeg?raw=true)

![image alt](https://github.com/Farhan2763/clinic-appointment-system/blob/219a8e30ec3780157850125a301ce1875ead6deb/doctorgetwithid.jpeg?raw=true)

![image alt](https://github.com/Farhan2763/clinic-appointment-system/blob/219a8e30ec3780157850125a301ce1875ead6deb/doctorgetwithslot.jpeg?raw=true)

![image alt](https://github.com/Farhan2763/clinic-appointment-system/blob/219a8e30ec3780157850125a301ce1875ead6deb/doctornotavailable.jpeg?raw=true)

![image alt](https://github.com/Farhan2763/clinic-appointment-system/blob/219a8e30ec3780157850125a301ce1875ead6deb/appointmentadd.jpeg?raw=true)

![image alt](https://github.com/Farhan2763/clinic-appointment-system/blob/219a8e30ec3780157850125a301ce1875ead6deb/appointmentget.jpeg?raw=true)

![image alt](https://github.com/Farhan2763/clinic-appointment-system/blob/219a8e30ec3780157850125a301ce1875ead6deb/appointmentgetbydoctortorid.jpeg?raw=true)

![image alt](https://github.com/Farhan2763/clinic-appointment-system/blob/219a8e30ec3780157850125a301ce1875ead6deb/appointmentgetbydoctoridanddate.jpeg?raw=true)

![image alt](https://github.com/Farhan2763/clinic-appointment-system/blob/219a8e30ec3780157850125a301ce1875ead6deb/appointmentslotnotavailable.jpeg?raw=true)

![image alt](https://github.com/Farhan2763/clinic-appointment-system/blob/219a8e30ec3780157850125a301ce1875ead6deb/appointdoctornotfound.jpeg?raw=true)

![image alt](https://github.com/Farhan2763/clinic-appointment-system/blob/219a8e30ec3780157850125a301ce1875ead6deb/patientnotfoundappointment.jpeg?raw=true)

---
##  Booking Logic in Code

java
public synchronized Appointment bookAppointment(Long doctorId, Long patientId, LocalDateTime slot) {
if (!doctorRepo.findById(doctorId).isPresent())
throw new CustomException("Doctor not found");
if (!patientRepo.findById(patientId).isPresent())
throw new CustomException("Patient not found");
if (!doctorRepo.findById(doctorId).get().getAvailableSlots().contains(slot))
throw new CustomException("Slot not available for doctor");
if (appointmentRepo.isSlotBooked(doctorId, slot))
throw new CustomException("Slot already booked");

    return appointmentRepo.save(new Appointment(null, doctorId, patientId, slot));
}


This method ensures:
- Doctor and Patient exist
- Slot is available for that doctor
- Slot is not already booked
- Prevents race conditions via synchronized

---
##  Design Decisions
- Spring Boot + REST API: For fast setup and scalable architecture.

- Synchronized Method: Ensures thread safety and prevents race conditions while booking.

- Swagger UI: Integrated for easy manual API testing.

- In-Memory List Storage: For simplicity during evaluation (no DB dependency).
- CustomException: Handles error responses with descriptive messages.

 
