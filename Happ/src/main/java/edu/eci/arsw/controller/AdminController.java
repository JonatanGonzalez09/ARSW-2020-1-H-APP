package edu.eci.arsw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.model.Block;
import edu.eci.arsw.model.Nurse;
import edu.eci.arsw.model.User;
import edu.eci.arsw.service.AdminService;

@RestController
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;

	@GetMapping("users")
    public List<User> users(){		
        return this.adminService.getAllUsers();
    }

    @GetMapping("boss-nurses")
    public List<Nurse> bossNurses(){		
        return this.adminService.getAllNurses();
    }

    @GetMapping("assistant-nurses")
    public List<Nurse> assistantNurses(){		
        return this.adminService.getAllNurses();
    }

    @GetMapping("blocks")
    public List<Block> blocks(){
        return this.adminService.getAllBlocks();
    }

    @RequestMapping(method = RequestMethod.POST, value="/new-boss-nurses")
    public void addNurse(@RequestBody Nurse nurse){
        adminService.setNurse(nurse.getUsers(), nurse);
    }

    @RequestMapping(method = RequestMethod.POST, value="/new-block")
    public void addBlock(@RequestBody Block block){
        adminService.setBlock(block);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/nurses/{nurseId}")
    public void updateNurse(@RequestBody Nurse nurse, @PathVariable("nurseId")  int nurseId){
        List<Nurse> nurses = this.adminService.getAllNurses();
        for (int i=0; i < nurses.size();i++){
            if (nurses.get(i).getNurseId() == nurseId){
                this.adminService.updateNurse(nurse);
            }
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/stateBlock/{block_code}")
    public void updateStateBlock(@RequestBody Block block, @PathVariable("block_code") int block_code){
        List<Block> blocks = this.adminService.getAllBlocks();
        for (int i=0; i < blocks.size();i++){
            if (blocks.get(i).getBlockCode() == block_code){
                this.adminService.updateBlock(block);
            }
        }
    }	
}
