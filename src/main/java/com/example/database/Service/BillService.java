package com.example.database.Service;

import com.example.database.DTO.BillRequestDTO;
import com.example.database.DTO.BillResponseDTO;
import com.example.database.Entity.Appointment;
import com.example.database.Entity.Bill;
import com.example.database.Entity.Patient;
import com.example.database.Repository.AppointmentRepository;
import com.example.database.Repository.BillRepository;
import com.example.database.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    BillRepository billRepository;
    public BillResponseDTO generateBillOfPatient(BillRequestDTO billRequestDTO) {
        System.out.println("inside service ");
        Appointment appointment = appointmentRepository.findById(billRequestDTO.getAppointment_id())
                .orElseThrow( ()-> new RuntimeException("Appointment does not exist "));
        Patient patient = patientRepository.findById(appointment.getPatient().getId())
                        .orElseThrow(()->new RuntimeException("patient not found"));
        //System.out.println("appointment_id is "+billRequestDTO.getAppointment_id());
        Bill bill = new Bill();
        bill.setAppointment(appointment);
        bill.setPatient(patient);
        bill.setMedicineCharges(billRequestDTO.getMedicine_charge());
        bill.setDoctorFee(appointment.getDoctor().getConsultationFee());
        bill.setOtherCharges(billRequestDTO.getOther_charge());
        bill.setRoomCharges(billRequestDTO.getRoom_charge());
        BigDecimal totalAmount = bill.getDoctorFee()
                        .add(billRequestDTO.getMedicine_charge())
                                .add(billRequestDTO.getOther_charge())
                                        .add(billRequestDTO.getRoom_charge())
                                            .add(billRequestDTO.getTest_charge());
        bill.setTotalAmount(totalAmount);
        bill.setPaymentStatus("UNPAID");
        bill.setBillDate(LocalDate.now());
        billRepository.save(bill);

        //Dumping data to response DTO
        BillResponseDTO responseDTO = new BillResponseDTO();
        responseDTO.setPatient_name(patient.getName());
        responseDTO.setTotal_amount(bill.getTotalAmount());
        responseDTO.setStatus(bill.getPaymentStatus());

        responseDTO.setAppointment_id(bill.getAppointment().getId());

        return responseDTO;


  }
}
