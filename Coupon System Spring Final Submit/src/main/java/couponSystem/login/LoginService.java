package couponSystem.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import couponSystem.entities.User;
import couponSystem.exceptions.LoginException;
import couponSystem.services.AdminService;
import couponSystem.services.CompService;
import couponSystem.services.CustService;

@Service
public class LoginService {

	@Autowired
	AdminService admin;
	
	@Autowired
	CompService company ;
	
	@Autowired
	CustService customer;
	
	public  User login(String name , String password , ClientType type) throws LoginException {
		switch(type) {
		case ADMIN :		
			try {
				return admin.login(name, password, type); 
			} catch (Exception e) {
				throw new LoginException(e.getMessage());
			}
		case CUSTOMER :
			try {
				return customer.login(name, password, type);
			} catch (Exception e) {
				throw new LoginException(e.getMessage());
			}
		case COMPANY :
			try {
				return company.login(name, password, type);
			} catch (Exception e) {
				throw new LoginException(e.getMessage());
			}
		default :
			return null;
		}
	}
	
}
