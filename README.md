
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

---

##  Sample API Requests (use in Swagger)

### ➕ Add Patient (POST /patients)
json
{
"id": 1,
"name": "John Doe",
"age": 30,
"gender": "Male"
}


###  Add Doctor (POST /doctors)
json
{
"id": 1,
"name": "Dr. Smith",
"specialization": "Cardiology",
"availableSlots": [
"2025-08-04T10:00:00",
"2025-08-04T11:00:00"
]
}


###  Book Appointment (POST /appointments/book)
json
{
"doctorId": 1,
"patientId": 1,
"slot": "2025-08-04T10:00:00"
}


---

##  Test Error Scenarios

| Condition               | What to Do                                                | Expected Message                 |
|------------------------|-----------------------------------------------------------|----------------------------------|
| Doctor not found       | Use invalid "doctorId"                                  | Doctor not found                 |
| Patient not found      | Use invalid "patientId"                                 | Patient not found                |
| Slot not available     | Use slot not present in doctor’s availableSlots         | Slot not available for doctor    |
| Slot already booked    | Try booking same doctor/slot again                        | Slot already booked              |

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

  ## 📸 Swagger UI – API Overview

The following image displays all available REST API endpoints exposed by the Clinic Appointment Management System:

### ✅ Patient Controller
- `GET /patients`  
  Fetch all patients.

- `POST /patients`  
  Register a new patient.

### ✅ Doctor Controller
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

### ✅ Appointment Controller
- `GET /appointments`  
  Get all booked appointments.

- `POST /appointments`  
  Book a new appointment.

- `GET /appointments/doctor/{doctorId}`  
  Get all appointments for a given doctor ID.

- `GET /appointments/doctor/{doctorId}/date`  
  Get appointments by doctor ID and date.

---

### 📷 Swagger Screenshot

https://github.com/Farhan2763/clinic-appointment-system/blob/main/Patientadd.jpeg?raw=true

