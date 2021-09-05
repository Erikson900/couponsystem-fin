package couponSystem.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import couponSystem.entities.Company;
import couponSystem.entities.Customer;
import couponSystem.entities.User;
import couponSystem.exceptions.CompanyExistException;
import couponSystem.exceptions.CompanyNotFoundException;
import couponSystem.exceptions.CustomerExistException;
import couponSystem.exceptions.CustomerNotFoundException;
import couponSystem.exceptions.UniqueNameException;
import couponSystem.login.ClientType;
import couponSystem.repositories.CompRepository;
import couponSystem.repositories.CoupRepository;
import couponSystem.repositories.CustRepository;

/**
 * This service holds for all the methods Admin Client uses.
 * 
 * @author Erik
 *
 */

@Service
@Validated
public class AdminService {

	@Autowired
	CompRepository compRepository;

	@Autowired
	CustRepository custRepository;

	@Autowired
	CoupRepository coupRepository;

	/**
	 * Admin Login method, "Hard Coded because we don't hold Admin data in the
	 * database.
	 * 
	 * @param user_name
	 * @param password
	 * @param clientType
	 * @return
	 */
	public User login(String user_name, String password, ClientType clientType) {
		if (user_name.equalsIgnoreCase("admin") && password.equals("1234")) {
			System.out.println("Login Success");
			return new User(1, user_name, password, clientType);
		} else {
			System.err.println("Login Failed");
		}
		return null;
	}

	// ---------------------------------------Company----------------------------------------------------------

	/**
	 * This method receive a company check wither it exist or not, and reacts
	 * accordingly.
	 * 
	 * @param comp
	 * @throws CompanyExistException
	 * @throws Exception
	 */
	public void createCompany(@Valid Company comp) throws CompanyExistException {
		Optional<Company> optionalComp = compRepository.findCompanyByCompName(comp.getCompName());
		if (optionalComp.isPresent()) {
			throw new CompanyExistException(comp.getCompName());
		}
		compRepository.save(comp);
	}

	/**
	 * This method receive a company makes sure it's valid and updates it.
	 * 
	 * @param comp
	 * @throws CompanyNotFoundException
	 * @throws UniqueNameException
	 * @throws Exception
	 */
	public void updateCompany(@Valid Company comp) throws CompanyNotFoundException, UniqueNameException {
		Optional<Company> optionalComp = compRepository.findById(comp.getId());
		if (!optionalComp.isPresent()) {
			throw new CompanyNotFoundException(comp.getId());
		}
		// a double check in case we only write the server side.
		if (!comp.getCompName().equalsIgnoreCase(optionalComp.get().getCompName())) {
			throw new UniqueNameException();
		}
		compRepository.save(comp);
	}

	/**
	 * Delete a company and all it's coupons.
	 * 
	 * @param comp_id
	 * @throws Exception
	 */
	@Transactional
	public void deleteCompany(@Positive long comp_id) throws Exception {
		try {
			coupRepository.deleteCouponByCompany(compRepository.getOne(comp_id));
			compRepository.deleteById(comp_id);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * Retrieve a company from the database
	 * 
	 * @param comp_id
	 * @return Company
	 * @throws CompanyNotFoundException
	 */
	public Company getCompanyById(@Positive long comp_id) throws CompanyNotFoundException {
		Optional<Company> optionalCompany = compRepository.findById(comp_id);
		if (!optionalCompany.isPresent()) {
			throw new CompanyNotFoundException(comp_id);
		}
		return optionalCompany.get();
	}

	/**
	 * Retrieves all existing companies.
	 * 
	 * @return Company[]
	 * @throws CompanyNotFoundException
	 */
	public List<Company> getAllCompanies() throws CompanyNotFoundException {
		return compRepository.findAll();
	}

	// ---------------------------------------Customer----------------------------------------------------------

	/**
	 * This method receive a customer check wither it exist or not, and reacts
	 * accordingly.
	 * 
	 * @param cust
	 * @throws UniqueNameException
	 */
	public void createCustomer(@Valid Customer cust) throws CustomerExistException {
		Optional<Customer> optionalCustomers = custRepository.findCustomerByCustName(cust.getCustName());
		if (optionalCustomers.isPresent()) {
			throw new CustomerExistException(cust.getCustName());
		}
		custRepository.save(cust);
	}

	/**
	 * This method receive a customer makes sure he's valid and updates him.
	 * 
	 * @param cust
	 * @throws Exception
	 */
	public void updateCustomer(@Valid Customer cust) throws UniqueNameException, CustomerNotFoundException {
		Optional<Customer> optionalCust = custRepository.findById(cust.getId());
		if (!optionalCust.isPresent()) {
			throw new CustomerNotFoundException(cust.getId());
		}
		// a double check in case we only write the server side.
		if (!cust.getCustName().equalsIgnoreCase(optionalCust.get().getCustName())) {
			throw new UniqueNameException();
		}
		custRepository.save(cust);
	}

	/**
	 * Delete a customer.
	 * 
	 * @param cust_id
	 * @throws Exception
	 */
	@Transactional
	public void deleteCustomer(@Positive long cust_id) throws Exception {
		custRepository.deleteById(cust_id);
	}

	/**
	 * Retrieve a customer from the database.
	 * 
	 * @param cust_id
	 * @return
	 * @throws CustomerNotFoundException
	 */
	public Customer getCustomerById(@Positive long cust_id) throws CustomerNotFoundException {
		Optional<Customer> optionalCustomer = custRepository.findById(cust_id);
		if (!optionalCustomer.isPresent()) {
			throw new CustomerNotFoundException(cust_id);
		}
		return optionalCustomer.get();
	}

	
	/**
	 * Retrieve all existing customers in the database.
	 * 
	 * @return
	 */
	public List<Customer> getAll() {
		return custRepository.findAll();
	}

}
