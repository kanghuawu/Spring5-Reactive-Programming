package com.khwu.java8_in_action.ch08_refactor_test_debug;

public class ChainOfResposibilityMain {

    abstract static class ProcessingObject {
        private ProcessingObject successor;
        public void setSuccessor(ProcessingObject successor) {
            this.successor = successor;
        }

        public String handle(String task) {
            String handledTask = handleWork(task);
            if (successor != null) {
                return successor.handle(handledTask);
            }
            return handledTask;
        }

        abstract protected String handleWork(String task);
    }

    static class HeaderTextProcessing extends ProcessingObject {
        @Override
        protected String handleWork(String task) {
            return "This is cool title!\n" + task;
        }
    }

    static  class SpellCheckerProcessing extends ProcessingObject {
        @Override
        protected String handleWork(String task) {
            return task.replaceAll("labda", "lambda");
        }
    }

    public static void main(String[] args) {
        ProcessingObject p1 = new HeaderTextProcessing();
        ProcessingObject p2 = new SpellCheckerProcessing();
        p1.setSuccessor(p2);
        String result = p1.handle("labda 101 in action...");
        System.out.println(result);
    }
}
