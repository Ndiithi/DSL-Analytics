/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthit.dslweb.resources;

import com.healthit.dslservice.dao.FacilityDao;
import com.healthit.dslservice.dto.adminstrationlevel.Facility;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author duncan
 */
@Controller
public class kmfl {
    @ResponseBody
    @RequestMapping(value = "/facilities_ty", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllFacilities(@RequestParam String msisdn) {
        
       
        if (true) {
            return new ResponseEntity<String>("No Content found for this number", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(HttpStatus.OK);
        }
    }
    
    @ResponseBody
    @RequestMapping(value = "/facilities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getAllContentCategorySubType() {
        FacilityDao facilityDao=new FacilityDao();
        List<Facility> facilityList = facilityDao.getFacilities();
        return new ResponseEntity<List>(facilityList, HttpStatus.OK);

    }
    
}