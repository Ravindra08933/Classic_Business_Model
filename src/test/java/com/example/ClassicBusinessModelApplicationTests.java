/*package com.example;
import com.example.cbm.entities.Customers;
import com.example.cbm.entities.Employees;
import com.example.cbm.entities.Offices;
import com.example.cbm.entities.Orders;
import com.example.cbm.repositories.CustomerRepository;
import com.example.cbm.repositories.EmployeeRepository;
import com.example.cbm.repositories.OfficeRepository;
import com.example.cbm.repositories.OrderRepository;
import com.example.cbm.services.CustomerServiceImpl;
import com.example.cbm.services.OfficeServiceImpl;
import com.example.cbm.services.ProductsServiceInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class ClassicBusinessModelApplicationTests {

	private OfficeRepository officeRepository;
	private CustomerServiceImpl customerService;
	private EmployeeRepository employeeRepository;
	private OfficeServiceImpl officeService;
	private OrderRepository orderRepository;
	ProductsServiceInterface productsServiceInterface;
	@Autowired
	public void setProductsServiceInterface(ProductsServiceInterface productsServiceInterface) {
		this.productsServiceInterface = productsServiceInterface;
	}

	@Autowired
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	@Autowired
	public void setOfficeService(OfficeServiceImpl officeService) {
		this.officeService = officeService;
	}
	@Autowired
	public void setOfficeRepository(OfficeRepository officeRepository) {
		this.officeRepository = officeRepository;
	}
	@Autowired
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	@Autowired
	public void setCustomerService(CustomerServiceImpl customerService) {
		this.customerService = customerService;
	}
	CustomerRepository customerRepository;
	@Autowired
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	@Test
	public void testFindByOfficeCode()
	{
		Offices offices1 = officeRepository.findByOfficeCode("1");
		String one = "1";
		String two = offices1.getOfficeCode();
		assertEquals(one,two);
	}
	@Test
	public void testGetCustomerByCustomerNumber_ExistingCustomer_ReturnsCustomer() {
		Customers customer = new Customers(12,"John Doe","Doe","John","1234567890","123 Main St","Apt 4B","Cityville","State","12345","Country",null,null);
		customerRepository.save(customer);
		Customers retrievedCustomer = customerRepository.findById(12).get();
		assertEquals(customer,retrievedCustomer);
	}
	@Test
	public void testFindCustomerByPhone()
	{
		String phone = "(1) 356-5555";
		Customers customer = new Customers(169, "Porto Imports Co.", "de Castro", "Isabel", "(1) 356-5555", "Estrada da saúde n. 58", null, "Lisboa", null, "1756", "Portugal", null, BigDecimal.ZERO);
		Customers customers = customerService.searchByPhoneNumber(phone);
		assertEquals(customer,customers);
	}
	@Test
	public void testUpdateCustomerName()
	{
		String name = "bV Stores, Co.";
		Customers customers = customerRepository.findById(187).get();
		customers.setCustomerName(name);
		customerRepository.save(customers);
		assertEquals(name,customers.getCustomerName());
	}
	@Test
	public void testSearchCustomersByFirstName()
	{
		Employees employees = employeeRepository.findById(1611).get();
		List<Customers> customers = new ArrayList<Customers>();
		String firstName = "Peter";
		Customers customer = new Customers(114, "Australian Collectors, Co.", "Ferguson", "Peter",
				"03 9520 4555", "636 St Kilda Road", "Level 3", "Melbourne", "Victoria", "3004",
				"Australia", employees, new BigDecimal("117300.00"));
		Customers customer1 = new Customers(273, "Franken Gifts, Co", "Franken", "Peter", "089-0877555",
				"Berliner Platz 43", null, "München",null, "80805", "Germany",null, BigDecimal.ZERO);
		customers.add(customer);
		customers.add(customer1);
		List<Customers> customersList = customerService.searchCustomersByFirstName(firstName);
		assertEquals(customers,customersList);
	}
	@Test
	public void testSearchCustomersByLastName()
	{
		List<Customers> customers = new ArrayList<Customers>();
		String LastName = "Doe";
		Customers customer = new Customers(12, "John Doe", "Doe", "John", "1234567890", "123 Main St",
				"Apt 4B", "Cityville", "State", "12345", "Country",null,null);
		customers.add(customer);
		List<Customers> customersList = customerService.searchByContactLastName(LastName);
		assertEquals(customers,customersList);
	}
	@Test
	public void testUpdateCustomerFirstName()
	{
		String FirstName = "Shipka";
		Customers customer = new Customers(169, "Porto Imports Co.", "de Castro", "Shipka", "(1) 356-5555", "Estrada da saúde n. 58", null, "Lisboa", null, "1756", "Portugal", null, BigDecimal.ZERO);
		Customers customers = customerRepository.findById(169).get();
		customers.setContactFirstName(FirstName);
		assertEquals(customer,customers);
	}
	@Test
	public void testUpdatePhoneNumberInOffice()
	{
		String phone = "12345678";
		officeService.updatePhoneNumber("4",phone);
		Offices offices = officeRepository.findByOfficeCode("4");
		assertEquals(phone,offices.getPhone());
	}
	@Test
	public void testSaveEmployee()
	{
		Employees employee = new Employees(1234,"Doe","John","12345","johndoe@example.com",officeRepository.findByOfficeCode("3"),null,"Manager");
		employeeRepository.save(employee);
		assertEquals(employee,employeeRepository.findById(1234).get());
	}
	@Test
	public void testGetEmployeeByNumber()
	{
		Employees employee = new Employees(123, "Virat", "Kohli", "x1002", "kohli@gmail.com", null, 5, "President");
		assertEquals(employee,employeeRepository.findById(123).get());
	}
	@Test
	public void testFindEmployeeByOfficeCode()
	{
		List<Employees> employeesList = new ArrayList<Employees>();
		Employees employee = new Employees(123, "Virat", "Kohli", "x1002", "kohli@gmail.com", officeRepository.findByOfficeCode("5"), null, "President");
		Employees employee1 = new Employees(1621, "Nishi", "Mami", "x101", "mnishi@classicmodelcars.com", officeRepository.findByOfficeCode("5"), 1056, "Sales Rep");
		Employees employee2 = new Employees(1625, "Kato", "Yoshimi", "x102", "ykato@classicmodelcars.com", officeRepository.findByOfficeCode("5"), 1621, "Sales Rep");
		employeesList.add(employee);
		employeesList.add(employee1);
		employeesList.add(employee2);
		List<Employees> employees = employeeRepository.findByOfficesOfficeCode("5");
		assertEquals(employeesList,employees);
	}
	@Test
	public void testUpdateRoleOfEmployee()
	{
		String role = "Manager";
		Employees employees = employeeRepository.findById(1702).get();
		employees.setJobTitle(role);
		assertEquals(role,employees.getJobTitle());
	}
	@Test
	public void testUpdateOrderShippedDate()
	{
		Date date = new Date(2012-10-10);
		Orders orders = orderRepository.findById(10227).get();
		orders.setShippedDate(date);
		assertEquals(date,orders.getShippedDate());
	}
	@Test
	public void testGetTotalOrderedQuantityForProduct()
	{
		Long total = 1030l;
		Long total1 = productsServiceInterface.getTotalOrderedQuantityForProduct("S10_4757");
		assertEquals(total,total1);
	}
	@Test
	public void testGetTotalSaleForProduct()
	{
		BigDecimal total = new BigDecimal(127924.32);
		BigDecimal roundedValue = total.setScale(2, RoundingMode.HALF_UP);
		BigDecimal total1 = productsServiceInterface.getTotalSaleForProduct("S10_4757");
		assertEquals(roundedValue,total1);
	}

}
*/
