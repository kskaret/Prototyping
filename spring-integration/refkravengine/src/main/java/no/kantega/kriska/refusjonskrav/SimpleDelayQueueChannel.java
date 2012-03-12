package no.kantega.kriska.refusjonskrav;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.springframework.integration.Message;
import org.springframework.integration.MessageHeaders;
import org.springframework.integration.channel.QueueChannel;

/**
 * @author Andrew Skiba skibaa@gmail.com Use for any purpose at your own risk.
 */
public class SimpleDelayQueueChannel extends QueueChannel {
	private long delay;
	private TimeUnit timeUnit;

	public SimpleDelayQueueChannel(long delay, TimeUnit timeUnit) {
		/*
		 * QueueChannel expects a queue capable of holding any Message, but
		 * DelayQueue requires its elements to implement Delayed interface. So
		 * we MUST override doSend so we control what is inserted into the
		 * queue.
		 */
		super((BlockingQueue) new DelayQueue());
		this.delay = delay;
		this.timeUnit = timeUnit;
	}

	@Override
	protected boolean doSend(Message<?> message, long timeout) {
		return super.doSend(new DelayedMessage(message), timeout);
	}

	/**
	 * QueueChannel requires its elements to implement Message, and DelayQueue
	 * requires its elements to implement Delayed. This class implements both to
	 * satisfy these requirements. For Mesage interface it acts as a proxy and
	 * forwards all calls to the wrapped Message.
	 */
	protected class DelayedMessage implements Delayed, Message {
		long createdClock;
		Message wrappedMessage;

		public DelayedMessage(Message wrappedMessage) {
			this.wrappedMessage = wrappedMessage;
			createdClock = System.currentTimeMillis();
		}

		public long getDelay(TimeUnit unit) {
			long millisPassed = System.currentTimeMillis() - createdClock;
			long unitsPassed = unit
					.convert(millisPassed, TimeUnit.MILLISECONDS);
			long delayInGivenUnits = unit.convert(delay, timeUnit);
			return delayInGivenUnits - unitsPassed;
		}

		public int compareTo(Delayed o) {
			if (o instanceof DelayedMessage)
				return new Long(createdClock)
						.compareTo(((DelayedMessage) o).createdClock);

			return new Long(getDelay(TimeUnit.NANOSECONDS)).compareTo(o
					.getDelay(TimeUnit.NANOSECONDS));
		}

		public MessageHeaders getHeaders() {
			return wrappedMessage.getHeaders();
		}

		public Object getPayload() {
			return wrappedMessage.getPayload();
		}

	}

}