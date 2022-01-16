package net.maybemc.cloud.service.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ServiceProvider {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServiceProvider.class);
    private final static Map<Class<? extends IServiceProvider>, IServiceProvider> SERVICE_PROVIDER_MAP = new HashMap<>();

    public static void registerProvider(Class<? extends IServiceProvider> clazz, IServiceProvider serviceProvider) {
        if (SERVICE_PROVIDER_MAP.containsKey(clazz))
            throw new KeyAlreadyExistsException(String.format("Service provider %s is already registered!", clazz.getName()));
        if (!clazz.isInstance(serviceProvider))
            throw new ClassCastException(String.format("Service provider %s must implement the IServiceProvider interface!", clazz.getName()));
        SERVICE_PROVIDER_MAP.put(clazz, serviceProvider);
        LOGGER.info("registered provider " + clazz.getName());
    }

    public static <T> T getService(Class<? extends IServiceProvider> clazz) {
        if (!SERVICE_PROVIDER_MAP.containsKey(clazz))
            throw new NoSuchElementException(String.format("The provider %s is not registered!", clazz.getName()));
        return (T) SERVICE_PROVIDER_MAP.get(clazz);
    }

}
