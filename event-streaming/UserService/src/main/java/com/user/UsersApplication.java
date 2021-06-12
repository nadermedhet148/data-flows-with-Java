package com.user;

import com.user.Adapters.Messages.EventConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class UsersApplication {

	public static void main(String[] args)  {
		ConfigurableApplicationContext context = SpringApplication.run(UsersApplication.class, args);
		EventConsumer transactionEventsConsumer = (EventConsumer) context.getBean("transactionEventsConsumer");

		List<EventConsumer> consumers = Arrays.asList(transactionEventsConsumer);
		consumers.forEach(c->{
			try {
				c.eventConsume();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			}
		});

	}

}
