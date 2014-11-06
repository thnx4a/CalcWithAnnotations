package com.suhorukov.calc.commands;

import com.suhorukov.calc.Command;
import com.suhorukov.calc.Inject;
import javafx.beans.value.ObservableNumberValue;

import java.util.Map;
import java.util.Stack;

/**
 * Created by demo3 on 06.11.14.
 */
public class Push implements Command {

    @Inject
    private Stack<Double> stack;
    @Inject
    private Map<String, Double> defines;

    @Override
    public void execute(String[] args) {
        String val = args[1];
        Double numValue = defines.get(val);
        if (numValue != null) {
            stack.push(numValue);
        } else {
            stack.push(new Double(val));
        }
    }
}
