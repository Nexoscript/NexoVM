package com.nexoscript.nexoscript.runner.instructions;

import java.util.Arrays;
import java.util.Objects;

import com.nexoscript.nexoscript.runner.NexoRunner;
import com.nexoscript.nexoscript.runner.code.Instruction;
import com.nexoscript.nexoscript.runner.code.Variable;
import com.nexoscript.nexoscript.runner.variable.ArrayVariable;
import com.nexoscript.nexoscript.util.StringUtil;

public class CoutInstruction implements Instruction {
    private final String[] args;

    public CoutInstruction(String line) {
        args = line.split(" ");
    }

    @Override
    public boolean execute() {
        if (Objects.equals(args[1], "0xA1")) {
            if (!args[2].startsWith("*")) {
                if (args[2].startsWith("\"")) {
                    StringBuilder consoleData = new StringBuilder();
                    for (int i = 2; i < args.length; i++) {
                        consoleData.append(args[i]).append(" ");
                    }
                    String contentBetween = StringUtil.getContentBetween(new String(consoleData), "\"", "\"");
                    System.out.print(contentBetween);
                    return true;
                }
                StringBuilder consoleData = new StringBuilder();
                for (int i = 2; i < args.length; i++) {
                    consoleData.append(args[i]).append(" ");
                }
                System.out.print(consoleData);
                return true;
            } else {
                for (Variable<?> variable : NexoRunner.get().getVariables()) {
                    if (variable.key().equals(args[2].substring(1))) {
                        System.out.print(variable.getValue());
                        return true;
                    }
                }
                for (ArrayVariable<?> variable : NexoRunner.get().getArrays()) {
                    if (variable.key().equals(args[2].substring(1))) {
                        System.out.print(Arrays.toString(variable.getValue()));
                        return true;
                    }
                }
            }
        }
        if (Objects.equals(args[1], "0xA2")) {
            if (!args[2].startsWith("*")) {
                if (args[2].startsWith("\"")) {
                    StringBuilder consoleData = new StringBuilder();
                    for (int i = 2; i < args.length; i++) {
                        consoleData.append(args[i]).append(" ");
                    }
                    String contentBetween = StringUtil.getContentBetween(new String(consoleData), "\"", "\"");
                    System.out.println(contentBetween);
                    return true;
                }
                StringBuilder consoleData = new StringBuilder();
                for (int i = 2; i < args.length; i++) {
                    consoleData.append(args[i]).append(" ");
                }
                System.out.println(consoleData);
                return true;
            }
            for (Variable<?> variable : NexoRunner.get().getVariables()) {
                if (variable.key().equals(args[2].substring(1))) {
                    System.out.println(variable.getValue());
                    return true;
                }
            }
            for (ArrayVariable<?> variable : NexoRunner.get().getArrays()) {
                if (variable.key().equals(args[2].substring(1))) {
                    System.out.println(Arrays.toString(variable.getValue()));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getKeyWord() {
        return "0x01";
    }
}