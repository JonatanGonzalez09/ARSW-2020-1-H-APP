package edu.eci.arsw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.eci.arsw.model.Block;

public interface BlockPersistence extends JpaRepository<Block, Integer> {

}
