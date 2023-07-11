package com.example.cbm.services;
import com.example.cbm.entities.Customers;
import com.example.cbm.entities.Offices;
import com.example.cbm.exceptions.EmployeeNotFoundException;
import com.example.cbm.exceptions.OfficeNotFoundException;
import com.example.cbm.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class OfficeServiceImpl implements OfficeServiceInterface {
    OfficeRepository officeRepository;
    @Autowired
    public void setOfficeRepository(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }
    public Offices saveOffice(Offices offices)
    {
        return officeRepository.save(offices);
    }
    public Offices findByOfficeCode(String officeCode) {

        Offices offices = officeRepository.findByOfficeCode(officeCode);
        if(offices == null)
            throw new OfficeNotFoundException("Office details not found");
        return offices;
    }
    public String updatePhoneNumber(String officeCode, String phone) {
        Offices offices = officeRepository.findByOfficeCode(officeCode);
        String s = new String();
        if(offices!=null)
        {
            offices.setPhone(phone);
            officeRepository.save(offices);
            s = "office phone number updated successfully";
        }
        else
            throw  new OfficeNotFoundException("Office details not found");
        return s;
    }
    public String updateAddress(String officeCode, String address) {
        Offices offices = officeRepository.findByOfficeCode(officeCode);
        String s = new String();
        if(offices!=null)
        {
            offices.setAddressLine1(address);
            officeRepository.save(offices);
            s = "office address updated successfully";
        }
        else
            throw  new OfficeNotFoundException("Office details not found");
        return s;
    }
    public List<Customers> getCustomersByOfficeCode(String officeCode) {
        List<Customers> customers = officeRepository.getCustomersByOfficeCode(officeCode);
        if(customers.isEmpty())
            throw new EmployeeNotFoundException("Office details not found");
        return customers;
    }
    public List<Offices> getOfficesByCities(String... cities) {
        List<Offices> offices = officeRepository.findByCityIn(cities);
        if(offices.isEmpty())
            throw new EmployeeNotFoundException("Office details not found");
        return offices;
    }
}
