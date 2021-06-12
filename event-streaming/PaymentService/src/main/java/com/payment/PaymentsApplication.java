package com.payment;

import com.payment.Adapters.Messages.EventConsumer;
import com.payment.Adapters.Messages.Listeners.UserEventsConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class PaymentsApplication {

	public static void main(String[] args)  {
		ConfigurableApplicationContext context = SpringApplication.run(PaymentsApplication.class, args);
		EventConsumer userEventsConsumer = (UserEventsConsumer) context.getBean("userEventsConsumer");

		List<EventConsumer> consumers = Arrays.asList(userEventsConsumer);
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
