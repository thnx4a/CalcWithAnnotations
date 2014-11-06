package com.suhorukov.calc;

import com.suhorukov.calc.commands.Push;

import java.lang.reflect.Field;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {

        Map<Class, Object> resources = new HashMap<>();
        resources.put(Stack.class, new Stack());
        resources.put(Map.class, new HashMap());

        Scanner scanner = new Scanner(System.in);
        Map<String, Command> commands = new HashMap<>();
        commands.put("push", initCommand(new Push(), resources));
        String line;
        while ((line = scanner.nextLine()) != null) {
            args = line.split(" ");
            Command cmd = commands.get(args[0]);
            if (cmd != null) {
                cmd.execute(args);
            } else {
                System.out.println("Unknown command: " + args[0]);
            }
        }
    }

    static Command initCommand(Command cmd, Map<Class, Object> res) throws IllegalAccessException {
        Class clazz = cmd.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            Inject inject = field.getAnnotation(Inject.class);
            if (inject != null) {
                field.setAccessible(true);
                field.set(cmd, res.get(field.getType()));
            }
        }
        return cmd;
    }
}
