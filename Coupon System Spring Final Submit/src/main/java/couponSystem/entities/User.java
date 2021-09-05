package couponSystem.entities;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import couponSystem.login.ClientType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User Entity a class contains the user data members.
 * Used to pass the user info to the client.
 * @author Erik
 *
 */

@Data

@NoArgsConstructor
public class User {

	private long id;
	private String userName;
	private String password;
	@Enumerated(EnumType.STRING)
	private ClientType clientType;
	
	public User(String userName, String password, ClientType clientType) {
		this.userName = userName;
		this.password = password;
		this.clientType = clientType;
	}
	
	public User(long id, String userName, String password, ClientType clientType) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.clientType = clientType;
	}
	
	
}
