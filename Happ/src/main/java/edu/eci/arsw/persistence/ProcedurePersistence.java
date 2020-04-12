package edu.eci.arsw.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.eci.arsw.model.Procedure;

public interface ProcedurePersistence extends JpaRepository<Procedure, Integer> {
    @Query(value = "select p.* from \"procedure\" p join undergoes on p.procedure_id = undergoes.procedure_id join nurse on nurse.nurse_id = undergoes.nurse_id where nurse.nurse_id = :nurseId", nativeQuery = true)
    List<Procedure> getAllProceduresFromNurse(int nurseId);
}
