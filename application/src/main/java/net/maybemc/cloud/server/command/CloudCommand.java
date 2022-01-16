package net.maybemc.cloud.server.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CloudCommand {

    String name();

    String[] aliases() default {};

    String description() default "no description set";

}
