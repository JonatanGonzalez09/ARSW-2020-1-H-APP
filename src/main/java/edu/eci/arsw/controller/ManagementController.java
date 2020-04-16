package edu.eci.arsw.controller;

import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.model.Nurse;
import edu.eci.arsw.model.Procedure;
import edu.eci.arsw.model.Stay;
import edu.eci.arsw.model.Undergoes;
import edu.eci.arsw.service.NurseManagerService;

@RestController
@RequestMapping("nurse")
@CrossOrigin(origins = "*")
public class ManagementController {

    @Autowired
	private NurseManagerService nurseManagerService;

	private String loginUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
    }
    
    //----------------User-----------------------
    @GetMapping("users")
	public ResponseEntity<?> getUsers(){
		try {
			return new ResponseEntity<>(nurseManagerService.getAllUsers(), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No exist users", HttpStatus.INTERNAL_SERVER_ERROR);
		} 
    }

	@GetMapping("user")
	public ResponseEntity<?> getUser(){
		try {
			return new ResponseEntity<>(nurseManagerService.getUser(this.loginUser()), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No user exist", HttpStatus.NOT_FOUND);
		} 
    }

    @GetMapping("user/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable("userId") int userId){
		try {
			return new ResponseEntity<>(nurseManagerService.getUserById(userId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No user exist with this id", HttpStatus.NOT_FOUND);
		} 
	}

	@GetMapping("user/{type}/{code}")
	public ResponseEntity<?> getUserByGovId(@PathVariable("type") String type, @PathVariable("code") String code ){
		try {
			return new ResponseEntity<>(nurseManagerService.getUserByGovId(type,code), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No user exist with this type or code", HttpStatus.NOT_FOUND);
		} 
    }

    @GetMapping("users/active/{bool}")
	public ResponseEntity<?> getUserByGovId(@PathVariable("bool") Boolean bool){
		try {
			return new ResponseEntity<>(nurseManagerService.getAllActivesUser(bool), HttpStatus.OK);
		}catch (Exception ex) { 
            Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
            String s = bool.toString();
			return new ResponseEntity<>("No active "+s+" user exist ", HttpStatus.NOT_FOUND);
		} 
    }

    @GetMapping("users/email/{correo}")
    public ResponseEntity<?>  userEmail(@PathVariable ("correo") String correo) {
    	try {
  	      return new ResponseEntity<>(nurseManagerService.getUserByEmail(correo), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("No user exist with this email", HttpStatus.NOT_FOUND);
  	    }
	}
	
	//----------------Undergoes-----------------------
	@GetMapping("undergoes")
    public ResponseEntity<?> getAllUndergoes() {
    	try {
  	      return new ResponseEntity<>(nurseManagerService.getUndergoes(), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("No undergoes exist", HttpStatus.NOT_FOUND);
  	    }
	}

	@GetMapping("undergoes/{underId}")
	public ResponseEntity<?> getAllUndergoesById(@PathVariable("underId") int underId){
		try {
			return new ResponseEntity<>(nurseManagerService.getUndergoes(underId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No this undergoes exist", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("undergoes/today")
    public ResponseEntity<?> getAllUndergoesToday() {
    	try {
			Nurse tmpNurse = nurseManagerService.getUser(this.loginUser()).getNurses().get(0);
  	      	return new ResponseEntity<>(nurseManagerService.getUndergoesToday(tmpNurse), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("No undergoes today exist", HttpStatus.NOT_FOUND);
  	    }
	}

	@GetMapping("undergoes/after/{date}")
	public ResponseEntity<?> getUndergoesAfterToday(@PathVariable("date") Date date){
		try {
			return new ResponseEntity<>(nurseManagerService.getUndergoesAfterToday(date), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("This not undergoes exist with this after date", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("undergoes/before/{date}")
	public ResponseEntity<?> getUndergoesBeforeToday(@PathVariable("date") Date date){
		try {
			return new ResponseEntity<>(nurseManagerService.getUndergoesBeforeToday(date), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("This not undergoes exist with this before date", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("undergoes/nurse/{nurseId}")
	public ResponseEntity<?> getUndergoesByNurseId(@PathVariable("nurseId") Nurse nurse){
		try {
			return new ResponseEntity<>(nurseManagerService.getUndergoesByNurseId(nurse), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No exist undergoes for this id of nurse", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("undergoes/procedure/{procedureId}")
	public ResponseEntity<?> getUnderByProcedureId(@PathVariable("procedureId") Procedure procedure){
		try {
			return new ResponseEntity<>(nurseManagerService.getUndergoesByProcedureId(procedure), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No exist undergoes for this id of procedure", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("undergoes/stay/{stayId}")
	public ResponseEntity<?> getUndergoesByStayId(@PathVariable("stayId") Stay stay){
		try {
			return new ResponseEntity<>(nurseManagerService.getUndergoesByStayId(stay), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No exist undergoes for this id of stay", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("undergoes/done")
	public ResponseEntity<?> getUndergoesDone(){
		try {
			return new ResponseEntity<>(nurseManagerService.getUndergoesDone(), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No exist undergoes for this id of stay", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("undergoes/notdone")
	public ResponseEntity<?> getUndergoesNotDone(){
		try {
			return new ResponseEntity<>(nurseManagerService.getUndergoesNotDone(), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No exist undergoes for this id of stay", HttpStatus.NOT_FOUND);
		}
	}
	
	//----------------OnCalls-----------------------
	@GetMapping("oncalls")
	public ResponseEntity<?> getAllOnCalls(){
		try {
			return new ResponseEntity<>(nurseManagerService.getAllOncalls(), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No exist oncalls", HttpStatus.NOT_FOUND);
		}
	}

	//----------------Procedures-----------------------
	@GetMapping("procedures")
	public ResponseEntity<?> getAllProcedures(){
		try {
			return new ResponseEntity<>(nurseManagerService.getProcedures(), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No exist oncalls", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("procedure/{procedureId}")
	public ResponseEntity<?> getProcedureById(@PathVariable("procedureId") int procedureId){
		try {
			return new ResponseEntity<>(nurseManagerService.getProcedure(procedureId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No exist oncalls", HttpStatus.NOT_FOUND);
		}
	}

	//---------------- PUT Procedures-----------------------
	@RequestMapping(method = RequestMethod.PUT, value = "procedures")
	public ResponseEntity<?> updateUsers(@RequestBody Procedure procedure){
		try {
			return new ResponseEntity<>(nurseManagerService.updateProcedure(procedure), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error updating this procedure. ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//---------------- POST Procedures-----------------------
	@RequestMapping(method = RequestMethod.POST, value = "procedures")
    public ResponseEntity<?> addUser(@RequestBody Procedure procedure) {
    	try {
  	      return new ResponseEntity<>(nurseManagerService.setProcedure(procedure), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("Error creationg User", HttpStatus.INTERNAL_SERVER_ERROR);
  	    }
    }
	
	//----------------PUT Undergoes-----------------------
	@RequestMapping(method = RequestMethod.PUT, value = "undergoes")
	public ResponseEntity<?> updateUsers(@RequestBody Undergoes undergoes){
		try {
			return new ResponseEntity<>(nurseManagerService.updateUndergoes(undergoes), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error updating this undergoes. ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//----------------POST Undergoes-----------------------
	@RequestMapping(method = RequestMethod.POST, value = "undergoes")
    public ResponseEntity<?> addUser(@RequestBody Undergoes undergoes) {
    	try {
  	      return new ResponseEntity<>(nurseManagerService.setUndergoes(undergoes), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("Error creationg User", HttpStatus.INTERNAL_SERVER_ERROR);
  	    }
    }

}