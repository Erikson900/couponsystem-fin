package couponSystem.dailyTaskService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import couponSystem.repositories.CoupRepository;

/**
 * This service removes all expired coupons from the database 
 * @author Erik
 *
 */
@Service
public class DailyTask {

	@Autowired
	private CoupRepository coupRepository;
	
	public void DailyExpirationTask() {
			
		}

	@Scheduled(cron = "0 0 12 1/1 * ?")
	public void run() {
		try {
			coupRepository.removeExpieredCoupons();
		} catch (Exception e) {
		}
	}
}
