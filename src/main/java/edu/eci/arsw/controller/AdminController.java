package edu.eci.arsw.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.model.Bed;
import edu.eci.arsw.model.Block;
import edu.eci.arsw.model.Nurse;
import edu.eci.arsw.model.Room;
import edu.eci.arsw.model.User;
import edu.eci.arsw.service.AdminService;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    
    //------------------- GET User-----------------
    @GetMapping("users")
    public ResponseEntity<?> users() {
    	try {
    	      return new ResponseEntity<>(adminService.getAllUsers(), HttpStatus.OK);
    	    } catch (Exception ex) {
    	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
    	      return new ResponseEntity<>("No users on the database", HttpStatus.NOT_FOUND);
    	    }  
    }
    
    @GetMapping("users/{id}")
    public ResponseEntity<?> userId(@PathVariable ("id") int id) {
    	try {
  	      return new ResponseEntity<>(adminService.getUserById(id), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("No user exist with that id", HttpStatus.NOT_FOUND);
  	    }    	
    }
    
    @GetMapping("users/{type}/{id}")
    public ResponseEntity<?> userId(@PathVariable ("type") String type,@PathVariable ("id") String id) {
    	try {
    	      return new ResponseEntity<>(adminService.getUserByGovId(type, id), HttpStatus.OK);
    	    } catch (Exception ex) {
    	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
    	      return new ResponseEntity<>("No user exist with that goverment identification", HttpStatus.NOT_FOUND);
    	    }
    }
    
    @GetMapping("users/email/{email}")
    public ResponseEntity<?>  userEmail(@PathVariable ("email") String email) {
    	try {
  	      return new ResponseEntity<>(adminService.getUserByEmail(email), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("No user exist with that email", HttpStatus.NOT_FOUND);
  	    }
    }
    
    @GetMapping("users/active/{boolean}")
    public ResponseEntity<?>  userActive(@PathVariable ("boolean") Boolean b) {
    	try {
  	      return new ResponseEntity<>(adminService.getAllUserStatus(b), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("Not user exist with status: "+b, HttpStatus.NOT_FOUND);
  	    }
    }
    
    @GetMapping("users/nurse/{id}")
    public ResponseEntity<?>  userNurse(@PathVariable ("id") int id) {
    	try {
  	      return new ResponseEntity<>(adminService.getUserByNurse(id), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("Not nurse exist with", HttpStatus.NOT_FOUND);
  	    }
    }
    
    
  //------------------- GET Nurse-----------------
    @GetMapping("nurses")
    public ResponseEntity<?> nurses() {
    	try {
    	      return new ResponseEntity<>(adminService.getAllNurses(), HttpStatus.OK);
    	    } catch (Exception ex) {
    	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
    	      return new ResponseEntity<>("No nurses on the database", HttpStatus.NOT_FOUND);
    	    }
    }
    

    @GetMapping("nurses/admin")
    public ResponseEntity<?> bossNurses() {
    	try {
  	      return new ResponseEntity<>(adminService.getAllNursesByPosition("mngr"), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("No admin nurses on the database", HttpStatus.NOT_FOUND);
  	    }
    }

    @GetMapping("nurses/assistant")
    public ResponseEntity<?> assistantNurses() {
    	try {
    	      return new ResponseEntity<>(adminService.getAllNursesByPosition("asst"), HttpStatus.OK);
    	    } catch (Exception ex) {
    	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
    	      return new ResponseEntity<>("No assistant nurses on the database", HttpStatus.NOT_FOUND);
    	    }
    }
    
    @GetMapping("nurses/{nurseId}")
    public ResponseEntity<?> nurseId(@PathVariable ("nurseId") String nurseId) {
    	try {
  	      return new ResponseEntity<>(adminService.getNurse(Integer.parseInt(nurseId)), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("No nurses with that id on the database", HttpStatus.NOT_FOUND);
  	    }
    }
    
    @GetMapping("nurses/name/{nurseName}")
    public ResponseEntity<?> nurseName(@PathVariable ("nurseName") String nurseName) {
    	try {
  	      return new ResponseEntity<>(adminService.getAllByName(nurseName), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("No nurses with that name on the database", HttpStatus.NOT_FOUND);
  	    }
    }

    //------------------- GET Block-----------------
    @GetMapping("blocks")
    public ResponseEntity<?> blocks() {
    	try {
    	      return new ResponseEntity<>(adminService.getAllBlocks(), HttpStatus.OK);
    	    } catch (Exception ex) {
    	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
    	      return new ResponseEntity<>("No blocks on the database", HttpStatus.NOT_FOUND);
    	    }
    }
    
    @GetMapping("blocks/{id}")
    public ResponseEntity<?> blocksId(@PathVariable ("id") int id) {
    	try {
    	      return new ResponseEntity<>(adminService.getBlock(id), HttpStatus.OK);
    	    } catch (Exception ex) {
    	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
    	      return new ResponseEntity<>("No blocks with that id on the database", HttpStatus.NOT_FOUND);
    	    }
    }
    
    @GetMapping("blocks/floor/{id}")
    public ResponseEntity<?> blocksByFloor(@PathVariable ("id") int id) {
    	try {
    	      return new ResponseEntity<>(adminService.findByFloor(id), HttpStatus.OK);
    	    } catch (Exception ex) {
    	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
    	      return new ResponseEntity<>("No blocks with that floor id on the database", HttpStatus.NOT_FOUND);
    	    }
    }
    
    //------------------- GET Room-----------------
    @GetMapping("rooms")
    public ResponseEntity<?> rooms() {    	
    	try {
  	      return new ResponseEntity<>(adminService.getAllRooms(), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("No rooms on the database", HttpStatus.NOT_FOUND);
  	    }
    }
    
    @GetMapping("rooms/{id}")
    public ResponseEntity<?> roomsId(@PathVariable ("id") int id) {    	
    	try {
  	      return new ResponseEntity<>(adminService.getRoom(id), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("No rooms with that id on the database", HttpStatus.NOT_FOUND);
  	    }
    }
    
    @GetMapping("rooms/available/{boolean}")
    public ResponseEntity<?> roomsId(@PathVariable ("boolean") boolean b) {    	
    	try {
  	      return new ResponseEntity<>(adminService.getRoomByAvailable(b), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("Not room exist with available: " +b, HttpStatus.NOT_FOUND);
  	    }
    }
    
    @GetMapping("rooms/type/{type}")
    public ResponseEntity<?> roomsId(@PathVariable ("type") String type) {    	
    	try {
  	      return new ResponseEntity<>(adminService.getRoomByType(type), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("No rooms with that type on the database", HttpStatus.NOT_FOUND);
  	    }
    }
    
    //------------------- GET Bed-----------------
    @GetMapping("beds")
    public ResponseEntity<?> beds() {
    	try {
    	      return new ResponseEntity<>(adminService.getAllBed(), HttpStatus.OK);
    	    } catch (Exception ex) {
    	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
    	      return new ResponseEntity<>("No beds on the database", HttpStatus.NOT_FOUND);
    	    }
    }

    @GetMapping("beds/{id}")
    public ResponseEntity<?> beds(@PathVariable ("id") int id) {
    	try {
    	      return new ResponseEntity<>(adminService.getBed(id), HttpStatus.OK);
    	    } catch (Exception ex) {
    	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
    	      return new ResponseEntity<>("No beds with that id on the database", HttpStatus.NOT_FOUND);
    	    }
    }
    
    //------------------- POST-----------------
    @RequestMapping(method = RequestMethod.POST, value = "users")
    public ResponseEntity<?> addUser(@RequestBody User user) {
    	try {
  	      return new ResponseEntity<>(adminService.setUser(user), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("Error creationg User", HttpStatus.INTERNAL_SERVER_ERROR);
  	    }
    }

    @RequestMapping(method = RequestMethod.POST, value = "nurses")
    public Nurse addNurse(@RequestBody Nurse nurse) {
        return adminService.setNurse(nurse);
    }

    @RequestMapping(method = RequestMethod.POST, value = "blocks")
    public Block addBlock(@RequestBody Block block) {
        return adminService.setBlock(block);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "rooms")
    public Room addRooms(@RequestBody Room room) {
        return adminService.setRoom(room);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "beds")
    public Bed addBed(@RequestBody Bed bed) {
        return adminService.setBed(bed);
    }
    
    
  //------------------- PUT-----------------    
    @RequestMapping(method = RequestMethod.PUT, value = "users")
    public User updateUsers(@RequestBody User user) {
    	return adminService.updateUser(user);    	
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "nurses")
    public Nurse updateNurse(@RequestBody Nurse nurse) {
    	return adminService.updateNurse(nurse);    	
    }

    @RequestMapping(method = RequestMethod.PUT, value = "blocks")
    public Block updateBlock(@RequestBody Block block) {
    	return adminService.updateBlock(block);
    }    
    
    @RequestMapping(method = RequestMethod.PUT, value = "rooms")
    public Room updateRoom(@RequestBody Room room) {
    	return adminService.updateRoom(room);
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "beds")
    public Bed updateBed(@RequestBody Bed bed) {
    	return adminService.updateBed(bed);
    }
}