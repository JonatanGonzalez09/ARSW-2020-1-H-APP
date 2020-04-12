package edu.eci.arsw.persistence;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.eci.arsw.model.Block;
import edu.eci.arsw.model.Nurse;
import edu.eci.arsw.model.Oncall;

public interface OncallPersistence extends JpaRepository<Oncall, Integer> {
	List<Oncall> findByNurse(Nurse nurse);
	List<Oncall> findAllByOncallstartAfter(Date date);
	List<Oncall> findAllByOncallstartBefore(Date date);
	List<Oncall> findByBlock(Block block);
}
