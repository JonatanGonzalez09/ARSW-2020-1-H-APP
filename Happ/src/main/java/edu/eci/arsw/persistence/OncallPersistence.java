package edu.eci.arsw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.eci.arsw.model.Oncall;

public interface OncallPersistence extends JpaRepository<Oncall, Integer> {
	Oncall findByNurseId(int nurseId);
}
