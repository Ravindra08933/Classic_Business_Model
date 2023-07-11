package com.example.cbm.controllers;
import com.example.cbm.entities.Customers;
import com.example.cbm.entities.Offices;
import com.example.cbm.services.OfficeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping(value = "/api/v1/office")
public class OfficeController {
    private OfficeServiceInterface officeServiceInterface;
    @Autowired
    public void setOfficeServiceInterface(OfficeServiceInterface officeServiceInterface) {
        this.officeServiceInterface = officeServiceInterface;
    }
    @PostMapping(value = "/")
    public ResponseEntity<String> addOffice(@RequestBody Offices offices)
    {
        officeServiceInterface.saveOffice(offices);
        return new ResponseEntity<String>("office details added successfully", HttpStatus.CREATED);
    }
    @GetMapping(value = "/find/{office_code}")
    public ResponseEntity<Offices> findOfficeByOfficeCode(@PathVariable String office_code)
    {
        Offices offices1 = officeServiceInterface.findByOfficeCode(office_code);
        return new ResponseEntity<Offices>(offices1, HttpStatus.CREATED);
    }
    @PutMapping(value = "{office_code}/{phone}")
    public ResponseEntity<String> updatePhoneNumber(@PathVariable String office_code,@PathVariable String phone)
    {
        String s = officeServiceInterface.updatePhoneNumber(office_code,phone);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
    @PutMapping(value = "{office_code}")
    public ResponseEntity<String> updateAddress(@PathVariable String office_code,@RequestParam String address)
    {
        String s = officeServiceInterface.updateAddress(office_code,address);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
    @GetMapping("customers/{office_code}")
    public ResponseEntity<List<Customers>> getCustomersByOfficeCode(@PathVariable String office_code) {
        List<Customers> customer =  officeServiceInterface.getCustomersByOfficeCode(office_code);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }
    @GetMapping("/a/{city1}/{city2}")
    public List<Offices> getOfficesByCities(@PathVariable String city1,@PathVariable String city2) {
        return officeServiceInterface.getOfficesByCities(city1,city2);
    }
}

