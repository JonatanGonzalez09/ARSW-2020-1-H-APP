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

import edu.eci.arsw.model.Bed;
import edu.eci.arsw.model.Block;
import edu.eci.arsw.model.Nurse;
import edu.eci.arsw.model.Oncall;
import edu.eci.arsw.model.Patient;
import edu.eci.arsw.model.Procedure;
import edu.eci.arsw.model.Room;
import edu.eci.arsw.model.Stay;
import edu.eci.arsw.model.Undergoes;
import edu.eci.arsw.model.User;
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
	
	//---------------- Nurse -----------------------
	/* Today */
	@GetMapping("nurses/block/{blockId}")
	public ResponseEntity<?> getNurseByBlockId(@PathVariable("blockId") int blockId ){
		try {
			return new ResponseEntity<>(nurseManagerService.getNursesByBlockId(blockId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No nurses exist with this block id", HttpStatus.NOT_FOUND);
		} 
	}
	
	/* Today */
	@GetMapping("nurses/block/{blockId}/{date}")
	public ResponseEntity<?> getNurseByBlockId(@PathVariable("blockId") int blockId, @PathVariable("date") Date date ){
		try {
			return new ResponseEntity<>(nurseManagerService.getNursesByBlockIdAndDate(blockId, date), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No nurses exist with this block id and this date", HttpStatus.NOT_FOUND);
		} 
	}
	
	/* Today */
	@GetMapping("nurses/block/{blockId}/today")
	public ResponseEntity<?> getNurseByBlockIdToday(@PathVariable("blockId") int blockId){
		try {
			return new ResponseEntity<>(nurseManagerService.getNursesByBlockIdToday(blockId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No nurses exist with this block id and this date", HttpStatus.NOT_FOUND);
		} 
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

	/* Today */
	@GetMapping("undergoes/{blockId}/{date}")
	public ResponseEntity<?> getUndergoesByBlockIdDate(@PathVariable("blockId") int blockId, @PathVariable("date") Date date){
		try {
			return new ResponseEntity<>(nurseManagerService.getNurseUndergoesByBlockIdAndDate(blockId, date), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No undergoes exist with this block id and this date", HttpStatus.NOT_FOUND);
		} 
	}

	/* Today */
	@GetMapping("undergoes/today/nurse/{nurseId}")
	public ResponseEntity<?> getUndergoesTodayByNurseId(@PathVariable("nurseId") int nurseId){
		try {
			return new ResponseEntity<>(nurseManagerService.getUndergoesTodayByNurseId(nurseId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No undergoes exist today with this nurse id", HttpStatus.NOT_FOUND);
		} 
	}

	@GetMapping("undergoes/notdone/nurse/{nurseId}")
	public ResponseEntity<?> getUndergoesNotDoneByNurseId(@PathVariable("nurseId") int nurseId){
		try {
			return new ResponseEntity<>(nurseManagerService.getUndergoesNotDoneByNurseId(nurseId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No exist not done undergoes for this id of nurse", HttpStatus.NOT_FOUND);
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

	@GetMapping("oncalls/{onCallId}")
	public ResponseEntity<?> getOncall(@PathVariable("onCallId") int onCallId){
		try {
			return new ResponseEntity<>(nurseManagerService.getOncall(onCallId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No this oncall exist", HttpStatus.NOT_FOUND);
		} 
	}

	@GetMapping("oncalls/schedeule/after/{date}")
	public ResponseEntity<?> getOnCallsAfterToday(@PathVariable("date") Date date){
		try {
			return new ResponseEntity<>(nurseManagerService.getOnCallsAfterToday(date), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No oncalls with this date exist", HttpStatus.NOT_FOUND);
		} 
	}

	@GetMapping("oncalls/schedeule/before/{date}")
	public ResponseEntity<?> getOnCallsBeforeToday(@PathVariable("date") Date date){
		try {
			return new ResponseEntity<>(nurseManagerService.getOnCallsBeforeToday(date), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No oncalls with this date exist", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("oncalls/block/{blockId}")
	public ResponseEntity<?> getOncallByBlockId(@PathVariable("blockId") Block blockId){
		try {
			return new ResponseEntity<>(nurseManagerService.getOnCallsByBlockId(blockId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No this oncall exist", HttpStatus.NOT_FOUND);
		} 
	}

	@GetMapping("oncalls/nurse/{nurseId}")
	public ResponseEntity<?> getOncallByNurseId(@PathVariable("nurseId") Nurse nurseId){
		try {
			return new ResponseEntity<>(nurseManagerService.getOnCallsByNurseId(nurseId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No this oncall exist", HttpStatus.NOT_FOUND);
		} 
	}

	//---------------- Procedures -----------------------
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

	@GetMapping("procedure/description/{procedureId}")
	public ResponseEntity<?> getDescriptionByProcedureId(@PathVariable("procedureId") int procedureId){
		try {
			return new ResponseEntity<>(nurseManagerService.getDescriptionProcedure(procedureId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No exist procedure with this id", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("procedure/name/{procedureId}")
	public ResponseEntity<?> getNameByProcedureId(@PathVariable("procedureId") int procedureId){
		try {
			return new ResponseEntity<>(nurseManagerService.getNameProcedure(procedureId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No exist procedure with this id", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("procedure/undergoes/{undergoesId}")
	public ResponseEntity<?> getProceduresByUndergoesId(@PathVariable("undergoesId") int undergoesId){
		try {
			return new ResponseEntity<>(nurseManagerService.getProcedureByUndergoesId(undergoesId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No exist procedure with this undergoes id", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("procedures/patient/{patientId}")
	public ResponseEntity<?> getProceduresByPatientId(@PathVariable("patientId") int patientId){
		try {
			return new ResponseEntity<>(nurseManagerService.getProcedureByPatientId(patientId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No exist procedure with this patient id", HttpStatus.NOT_FOUND);
		}
	}

	//---------------- Stay -----------------------
	@GetMapping("stays")
	public ResponseEntity<?> getAllStays(){
		try {
			return new ResponseEntity<>(nurseManagerService.getStays(), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No stays exist", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("stay/{stayId}")
	public ResponseEntity<?> getStayById(@PathVariable("stayId") int stayId){
		try {
			return new ResponseEntity<>(nurseManagerService.getStay(stayId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No stays exist with this id", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("stays/end")
	public ResponseEntity<?> getStayEnd(){
		try {
			return new ResponseEntity<>(nurseManagerService.getStayEnd(), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No stays exist ended", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("stays/notend")
	public ResponseEntity<?> getStayNotEnd(){
		try {
			return new ResponseEntity<>(nurseManagerService.getStayNotEnd(), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No stays exist not ended", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("stays/patient/{patientId}")
	public ResponseEntity<?> getStayByPatientId(@PathVariable("patientId") Patient patient){
		try {
			return new ResponseEntity<>(nurseManagerService.getStaysBypatientId(patient), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No stays exist with this patient id", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("stays/bed/{bedId}")
	public ResponseEntity<?> getStayByBedId(@PathVariable("bedId") Bed bed){
		try {
			return new ResponseEntity<>(nurseManagerService.getStaysByBedId(bed), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No stays exist with this block id", HttpStatus.NOT_FOUND);
		}
	}

	//---------------- Patient -----------------------
	@GetMapping("patients")
	public ResponseEntity<?> getPatients(){
		try {
			return new ResponseEntity<>(nurseManagerService.getPatients(), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No patients exist ", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("patient/{patientId}")
	public ResponseEntity<?> getPatientById(@PathVariable("patientId") int patientId){
		try {
			return new ResponseEntity<>(nurseManagerService.getPatient(patientId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No patients exist with this id", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("patients/{type}/{patientId}")
	public ResponseEntity<?> getPatientByGovId(@PathVariable("type") String type, @PathVariable("patientId") String patientId){
		try {
			return new ResponseEntity<>(nurseManagerService.getPatientByGovId(type, patientId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No patient exist", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("patients/nurse/{nurseId}")
	public ResponseEntity<?> getPatientsByNurseId(@PathVariable("nurseId") Nurse nurse){
		try {
			return new ResponseEntity<>(nurseManagerService.getPatientsByNurseId(nurse), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No patient exist", HttpStatus.NOT_FOUND);
		}
	}

	//---------------- Bed -----------------------
	@GetMapping("beds")
	public ResponseEntity<?> getBeds(){
		try {
			return new ResponseEntity<>(nurseManagerService.getAllBed(), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No beds exist", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("beds/{bedId}")
	public ResponseEntity<?> getBedById(@PathVariable("bedId") int bedId){
		try {
			return new ResponseEntity<>(nurseManagerService.getBed(bedId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No bed exist with this id", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("beds/romnumber/available")
	public ResponseEntity<?> getBedsAvailables(){
		try {
			return new ResponseEntity<>(nurseManagerService.getBedsAvailables(), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No beds exist availables", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("beds/romnumber/unavailable")
	public ResponseEntity<?> getBedsUnavailable(){
		try {
			return new ResponseEntity<>(nurseManagerService.getBedsUnavailables(), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No beds exist unavailable", HttpStatus.NOT_FOUND);
		}
	}

	//---------------- Block-----------------------
	@GetMapping("block/patient/{patientId}")
	public ResponseEntity<?> getBlockByIdPatient(@PathVariable("patientId") int patientId){
		try {
			return new ResponseEntity<>(nurseManagerService.getBlockByPatientId(patientId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No block exist with this patient id", HttpStatus.NOT_FOUND);
		}
	}

	//---------------- Room -----------------------
	@GetMapping("room/patient/{patientId}")
	public ResponseEntity<?> getRoomByIdPatient(@PathVariable("patientId") int patientId){
		try {
			return new ResponseEntity<>(nurseManagerService.getRoomByPatientId(patientId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No room exist with this patient id", HttpStatus.NOT_FOUND);
		}
	}

	//---------------- PUT Procedures-----------------------
	@RequestMapping(method = RequestMethod.PUT, value = "procedures")
	public ResponseEntity<?> updateProcedure(@RequestBody Procedure procedure){
		try {
			return new ResponseEntity<>(nurseManagerService.updateProcedure(procedure), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error updating this procedure. ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//---------------- POST Procedures-----------------------
	@RequestMapping(method = RequestMethod.POST, value = "procedures")
    public ResponseEntity<?> addProcedure(@RequestBody Procedure procedure) {
    	try {
  	      return new ResponseEntity<>(nurseManagerService.setProcedure(procedure), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("Error creationg procedre", HttpStatus.INTERNAL_SERVER_ERROR);
  	    }
    }
	
	//----------------PUT Undergoes-----------------------
	@RequestMapping(method = RequestMethod.PUT, value = "undergoes")
	public ResponseEntity<?> updateUndergoes(@RequestBody Undergoes undergoes){
		try {
			return new ResponseEntity<>(nurseManagerService.updateUndergoes(undergoes), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error updating this undergoes. ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//----------------POST Undergoes-----------------------
	@RequestMapping(method = RequestMethod.POST, value = "undergoes")
    public ResponseEntity<?> addUnderoes(@RequestBody Undergoes undergoes) {
    	try {
  	      return new ResponseEntity<>(nurseManagerService.setUndergoes(undergoes), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("Error creationg Undergoes", HttpStatus.INTERNAL_SERVER_ERROR);
  	    }
	}
	
	//----------------PUT Stay-----------------------
	@RequestMapping(method = RequestMethod.PUT, value = "stays")
	public ResponseEntity<?> updateStay(@RequestBody Stay stay){
		try {
			return new ResponseEntity<>(nurseManagerService.updateStay(stay), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error updating this stay. ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//----------------POST Stay-----------------------
	@RequestMapping(method = RequestMethod.POST, value = "stays")
    public ResponseEntity<?> addStay(@RequestBody Stay stay) {
    	try {
  	      return new ResponseEntity<>(nurseManagerService.setStay(stay), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("Error creationg stay", HttpStatus.INTERNAL_SERVER_ERROR);
  	    }
	}

	//---------------- PUT OnCalls-----------------------
	@RequestMapping(method = RequestMethod.PUT, value = "oncalls")
	public ResponseEntity<?> updateOncalls(@RequestBody Oncall oncall){
		try {
			return new ResponseEntity<>(nurseManagerService.updateOncall(oncall), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error updating this oncall. ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//---------------- POST OnCalls-----------------------
	@RequestMapping(method = RequestMethod.POST, value = "oncalls")
    public ResponseEntity<?> addOncalls(@RequestBody Oncall oncall) {
    	try {
  	      return new ResponseEntity<>(nurseManagerService.setOncall(oncall), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("Error creationg oncalls", HttpStatus.INTERNAL_SERVER_ERROR);
  	    }
	}

	//----------------POST Room-----------------------
	@RequestMapping(method = RequestMethod.POST, value = "rooms")
    public ResponseEntity<?> addRoom(@RequestBody Room room) {
    	try {
  	      return new ResponseEntity<>(nurseManagerService.setRoom(room), HttpStatus.OK);
  	    } catch (Exception ex) {
  	      Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
  	      return new ResponseEntity<>("Error creationg rooms", HttpStatus.INTERNAL_SERVER_ERROR);
  	    }
	}

	//---------------- PUT Nurse-----------------------
	@RequestMapping(method = RequestMethod.PUT, value = "nurses")
	public ResponseEntity<?> updateNurse(@RequestBody Nurse nurse){
		try {
			return new ResponseEntity<>(nurseManagerService.updateNurse(nurse), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error updating this nurse. ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//---------------- PUT Bed-----------------------
	@RequestMapping(method = RequestMethod.PUT, value = "beds")
	public ResponseEntity<?> updateBed(@RequestBody Bed bed){
		try {
			return new ResponseEntity<>(nurseManagerService.updateBed(bed), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error updating this bed. ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//---------------- PUT User-----------------------
	@RequestMapping(method = RequestMethod.PUT, value = "users")
	public ResponseEntity<?> updateUser(@RequestBody User user){
		try {
			return new ResponseEntity<>(nurseManagerService.updateUser(user), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(ManagementController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error updating this user. ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}