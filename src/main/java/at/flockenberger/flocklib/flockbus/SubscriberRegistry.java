package at.flockenberger.flocklib.flockbus;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

import at.flockenberger.flocklib.flockutil.ObjectUtils;
import at.flockenberger.flocklib.flockutil.ReflectUtils;

class SubscriberRegistry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4378030437139323482L;
	/**
	 * all registered subscribers
	 */
	private Map<Class<?>, CopyOnWriteArraySet<Subscriber>> subscribers;

	protected SubscriberRegistry() {
		this.subscribers = new ConcurrentHashMap<Class<?>, CopyOnWriteArraySet<Subscriber>>();
	}

	protected <T extends Object> void register(T object) {
		doRegisterStuff(object, (type, method) -> {
			if (subscribers.containsKey(type))
				subscribers.get(type).add(new Subscriber(method, object));
			else {
				CopyOnWriteArraySet<Subscriber> temp = new CopyOnWriteArraySet<Subscriber>();
				temp.add(new Subscriber(method, object));
				subscribers.put(type, temp);
			}
		});

	}

	protected <T extends Object> void unregister(T object) {
		doRegisterStuff(object, (type, method) -> {
			if (subscribers.containsKey(type)) {
				Set<Subscriber> subscriberSet = subscribers.get(type);
				subscriberSet.remove(new Subscriber(method, object));

				if (subscriberSet.isEmpty())
					subscribers.remove(type);
			}
		});
	}

	private <T extends Object> void doRegisterStuff(T object, RegistryHandle rh) {
		ObjectUtils.isNullThrow(object);
		List<Class<?>> classes = ReflectUtils.getSuperClasses(object);
		for (Class<?> cls : classes)
			for (Method method : findSubscriptionMethods(cls))
				rh.handle(method.getParameterTypes()[0], method);

	}

	protected <T extends EventBase> void postEvent(T event) {
		ObjectUtils.isNullThrow(event, "Given event must not be null!");
		Class<?> clazz = event.getClass();
		if (subscribers.containsKey(clazz))
			subscribers.get(clazz).forEach(subscriber -> subscriber.invoke(event));
	}

	private List<Method> findSubscriptionMethods(Class<?> type) {
		List<Method> subscribeMethods = Arrays.stream(type.getDeclaredMethods())
				.filter(method -> method.isAnnotationPresent(Subscribe.class)).collect(Collectors.toList());
		checkSubscriberMethods(subscribeMethods);
		return subscribeMethods;
	}

	private void checkSubscriberMethods(List<Method> subscribeMethods) {
		boolean hasMoreThanOneParameter = subscribeMethods.stream().anyMatch(method -> method.getParameterCount() != 1);
		if (hasMoreThanOneParameter) {
			throw new IllegalArgumentException("Method annotated with @Susbscribe has more than one parameter!");
		}
	}

	static interface RegistryHandle {
		public void handle(Class<?> type, Method m);
	}
}
