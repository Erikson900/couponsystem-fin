package couponSystem.restServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import couponSystem.entities.Company;
import couponSystem.entities.Customer;
import couponSystem.services.AdminService;
/**
 * this class holds the Rest routing to client Requests to the server.
 * @author Erik
 *
 */
@RestController
//@CrossOrigin(origins = "hyyp://locslhost:4200", allowCredentials = "true")
@RequestMapping("/Admin")
public class AdminWebService {

	@Autowired
	AdminService adminService;

	//-------------------------------------Company CRUD------------------------------------------------------------------------------
	
	// @RequestMapping(path = "/Company", method = RequestMethod.POST)
	@PostMapping("/createCompany")
	public void creatCompany(@RequestBody Company comp) throws Exception {
		adminService.createCompany(comp);
	}

	@PutMapping("/updateCompany")
	public void updateCompany(@RequestBody Company comp) throws Exception {
		adminService.updateCompany(comp);
	}

	@DeleteMapping("/removeComp/{companyId}")
	public void removeCompany(@PathVariable("companyId") long compId) throws Exception {
		adminService.deleteCompany(compId);
	}

	//-------------------------------------Company GETTERS------------------------------------------------------------------------------
	
	@GetMapping("/getCompany/{companyId}")
	public Company getCompany(@PathVariable("companyId") long id) throws Exception {
		return adminService.getCompanyById(id);
	}

	@GetMapping("/getAllComps")
	public List<Company> getAllCompanies() throws Exception {
		return adminService.getAllCompanies();
	}

	//-------------------------------------Customer CRUD------------------------------------------------------------------------------
	
	@PostMapping("/creatCustomer")
	public void createCustomer(@RequestBody Customer customer) throws Exception {
		adminService.createCustomer(customer);
	}

	@PutMapping("/updateCustomer")
	public void updateCustomer(@RequestBody Customer customer) throws Exception {
		adminService.updateCustomer(customer);
	}

	@DeleteMapping("/removeCustomer/{custId}")
	public void removeCustomer(@PathVariable("custId") long custId) throws Exception {
		adminService.deleteCustomer(custId);
	}

	//-------------------------------------Customer GETTERS------------------------------------------------------------------------------
	
	@GetMapping("/getCustomer/{custId}")
	public Customer getCustomer(@PathVariable("custId") long custId) throws Exception {
		return adminService.getCustomerById(custId);
	}

	@GetMapping("/getAllCustomer")
	public List<Customer> getAllCustomers() throws Exception {
		return adminService.getAll();
	}

}
