package priv.thinkam.rent.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FirstTask {

	@Scheduled(cron="0 0/5 * * * ?")
	public void run() {
		System.out.println("任务调度：每5分钟执行一次");
	}
}
