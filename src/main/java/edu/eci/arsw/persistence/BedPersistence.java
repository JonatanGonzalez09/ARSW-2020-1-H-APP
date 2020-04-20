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
}
