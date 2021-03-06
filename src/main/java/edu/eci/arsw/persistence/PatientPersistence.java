package edu.eci.arsw.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.eci.arsw.model.Patient;

public interface PatientPersistence extends JpaRepository<Patient, Integer> {
    Patient findByGovTypeAndGovId(String type, String code);
    
    @Query(value = "select p.* from patient p join stay on p.patient_id = stay.patient_id join undergoes on stay.stay_id = undergoes.stay_id join nurse on nurse.nurse_id = undergoes.nurse_id where nurse.nurse_id = :nurseId", nativeQuery = true)
    List<Patient> getAllPatientsFromNurse(int nurseId);

    @Query(value = "select p.* from patient p join stay on p.patient_id = stay.patient_id join undergoes on stay.stay_id = undergoes.stay_id join nurse on undergoes.nurse_id = nurse.nurse_id join usuarios on nurse.usuarios_user_id = usuarios.user_id where usuarios.gov_id = :nurseGovId", nativeQuery = true)
    List<Patient> getPatietntsByGovId(String nurseGovId);

    @Query(value = "select p.* from patient p left join stay s on p.patient_id = s.patient_id where s.patient_id is null", nativeQuery = true)
    List<Patient> getAllPatientsWithoutStay();

    @Query(value = "select p.* from patient p join stay s on p.patient_id = s.patient_id", nativeQuery = true)
    List<Patient> getPatientsWithStay();  
    
}