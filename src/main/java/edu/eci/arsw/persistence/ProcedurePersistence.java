package edu.eci.arsw.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.eci.arsw.model.Procedure;

public interface ProcedurePersistence extends JpaRepository<Procedure, Integer> {
    @Query(value = "select p.* from \"procedure\" p join undergoes on p.procedure_id = undergoes.procedure_id join nurse on nurse.nurse_id = undergoes.nurse_id where nurse.nurse_id = :nurseId", nativeQuery = true)
    List<Procedure> getAllProceduresFromNurse(int nurseId);

    @Query(value = "select p.description from \"procedure\" p where p.procedure_id = :idProcedure", nativeQuery = true)
    String getDescriptionByProcedureId(int idProcedure);
    
    @Query(value = "select p.name from \"procedure\" p where p.procedure_id = :idProcedure", nativeQuery = true)
	String getNameByProcedureId(int idProcedure);

    @Query(value = "select p.* from \"procedure\" p join undergoes on p.procedure_id = undergoes.procedure_id where undergoes.undergoes_id = :undergoesId", nativeQuery = true)
    Procedure getNameUndergoesId(int undergoesId);

    @Query(value = "select p.* from \"procedure\" p join undergoes on p.procedure_id = undergoes.procedure_id join stay on undergoes.stay_id = stay.stay_id where stay.patient_id = :patientId", nativeQuery = true)
    List<Procedure> getProcedureByPatientId(int patientId);
}
