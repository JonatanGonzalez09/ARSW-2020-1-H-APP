package edu.eci.arsw.persistence;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.model.Bed;

@Repository
public interface BedPersistence extends JpaRepository<Bed, Integer> {
	

}
