package net.maybemc.cloud.server.processor;

import lombok.AllArgsConstructor;
import net.maybemc.cloud.server.CloudServerApplication;
import net.maybemc.cloud.server.command.CloudCommand;
import net.maybemc.cloud.server.command.CommandManager;
import net.maybemc.cloud.server.command.ICloudCommand;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;

@AllArgsConstructor
public class AnnotationProcessor {

    private final CommandManager commandManager;

    public void processAnnotations() {
        Reflections reflections = new Reflections(CloudServerApplication.class.getPackage().getName());
        ClasspathHelper.forPackage(CloudServerApplication.class.getPackage().getName(), CloudServerApplication.class.getClassLoader()).forEach(reflections::scan);
        provideAnnotation(reflections);
    }

    private void provideAnnotation(Reflections reflections) {
        reflections.getTypesAnnotatedWith(CloudCommand.class).forEach(this::processCommand);
    }

    private void processCommand(Class<?> clazz) {
        commandManager.registerCommand((Class<? extends ICloudCommand>) clazz);
    }


}
