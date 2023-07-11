package com.example.cbm.services;
import com.example.cbm.entities.Customers;
import com.example.cbm.entities.Offices;
import java.util.List;
public interface OfficeServiceInterface {
    Offices saveOffice(Offices offices);
    Offices findByOfficeCode(String officeCode);
    String updatePhoneNumber(String officeCode, String phone);
    String updateAddress(String officeCode, String address);
    List<Customers> getCustomersByOfficeCode(String officeCode);
    List<Offices> getOfficesByCities(String... cities);
}
