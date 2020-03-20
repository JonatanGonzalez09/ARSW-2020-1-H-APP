package edu.eci.arsw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.eci.arsw.model.Room;

public interface RoomPersistence extends JpaRepository<Room, Integer> {

}
