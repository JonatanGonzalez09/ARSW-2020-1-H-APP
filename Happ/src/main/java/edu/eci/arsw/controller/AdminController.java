package edu.eci.arsw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("users")
    public List<Object[]> users() {
        return adminService.getAllUsers();
    }
    
    @GetMapping("nurses")
    public List < Nurse > nurses() {
        return this.adminService.getAllNurses();
    }

    @GetMapping("nurses/admin")
    public List <Nurse> bossNurses() {
        return this.adminService.getAllNursesByPosition("mngr");
    }

    @GetMapping("nurses/assistant")
    public List < Nurse > assistantNurses() {
        return this.adminService.getAllNursesByPosition("asst");
    }

    @GetMapping("blocks")
    public List <Block> blocks() {
        return this.adminService.getAllBlocks();
    }
    
    @GetMapping("rooms")
    public List<Room> rooms() {
        return this.adminService.getAllRooms();
    }   
    
    @RequestMapping(method = RequestMethod.POST, value = "user")
    public User addUser(@RequestBody User user) {
        return adminService.setUser(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "nurse")
    public Nurse addNurse(@RequestBody Nurse nurse) {
        return adminService.setNurse(nurse);
    }

    @RequestMapping(method = RequestMethod.POST, value = "block")
    public void addBlock(@RequestBody Block block) {
        adminService.setBlock(block);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "beds")
    public void addBed(@RequestBody Bed bed) {
        adminService.setBed(bed);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "nurses")
    public Nurse updateNurse(@RequestBody Nurse nurse) {
    	return adminService.updateNurse(nurse);    	
    }

    @RequestMapping(method = RequestMethod.PUT, value = "blocks")
    public Block updateStateBlock(@RequestBody Block block) {
    	return adminService.updateBlock(block);
    }
}