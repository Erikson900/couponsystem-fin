package couponSystem.login;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import couponSystem.entities.User;
import couponSystem.exceptions.LoginException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
//@CrossOrigin(origins = "hyyp://locslhost:4200", allowCredentials = "true")
@RequestMapping("Login")
public class LoginWebService {

	@Autowired
	LoginService loginService;

	/**
	 * the logging destroy previous session if exist and create a new one with the
	 * current user plus sets a cookie.
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 * @throws LoginException
	 */
	@PostMapping
	public User login (@RequestBody User user , HttpServletRequest request, HttpServletResponse response) throws LoginException {
	
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		session = request.getSession();
		User newUser = loginService.login(user.getUserName(), user.getPassword(), user.getClientType());
		
		session.setAttribute("userName", newUser.getUserName());
		session.setAttribute("userType", newUser.getClientType().toString());
		session.setAttribute("userId", newUser.getId());
		session.setAttribute("user", newUser);

		Cookie userName = new Cookie("userName", newUser.getUserName());
		Cookie userType = new Cookie("userType", newUser.getClientType().toString());
		Cookie userId = new Cookie("userId", Long.toString(newUser.getId()));
		
		response.addCookie(userName);
		response.addCookie(userType);
		response.addCookie(userId);
		response.setStatus(202);

		return newUser ;
	}
	
	/**
	 * logout the current user and nullify it;s cookies.
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping("/logout")
	public ResponseEntity<?> logout( HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession(false);
		Cookie[] cookies = request.getCookies();
		
		if(session!=null) {
			session.invalidate();		
		}
		
		if (cookies != null) {

			for (Cookie cookie : request.getCookies()) {
				cookie = new Cookie(cookie.getName(), null);
				cookie.setPath("/api");
				cookie.setHttpOnly(true);
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}

		}
		
		request.getSession().invalidate();
		System.err.println("logged out succsefuly");
		return new ResponseEntity<String>("logged out succsefuly", HttpStatus.OK);
		
	}

}