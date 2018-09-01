package com.lidong.threaddemo.producer_consumer.publicInte;

public interface Model {
	Runnable newRunnableConsumer();

	Runnable newRunnableProducer();
}
