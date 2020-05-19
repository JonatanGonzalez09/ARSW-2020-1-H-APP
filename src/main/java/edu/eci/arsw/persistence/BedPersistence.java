package edu.eci.arsw.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.model.Bed;

@Repository
public interface BedPersistence extends JpaRepository<Bed, Integer> {
    Bed findByBedId(int bedId);

    @Query(value= "select b.* from bed b join room on b.roomnumber = room.roomnumber where room.unavailable = false", nativeQuery = true)
    List<Bed> getBedAviables();

    @Query(value= "select b.* from bed b join room on b.roomnumber = room.roomnumber where room.unavailable = true", nativeQuery = true)
    List<Bed> getBedUnavailable();

    @Query(value= "select b.* from bed b join stay on b.bed_id = stay.bed_id join patient on stay.patient_id = patient.patient_id where patient.patient_id = :patientId and stay.stay_id = (select max(stay.stay_id) from bed b join stay on b.bed_id = stay.bed_id join patient on stay.patient_id = patient.patient_id where patient.patient_id = :patientId)", nativeQuery = true)
    Bed getBedByPatientId(int patientId);
}
