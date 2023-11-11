package DSA;

import javax.swing.JTextArea;

public class AzpStack {
    private String[] array;
    private int top;

    public AzpStack() {
        this.array = new String[10];
        this.top = -1;
    }

    public void push(String item) {
        if (!checkForDuplicates(item)) {
            if (top == array.length - 1) {
                System.out.println("Stack Overflow");
            } else {
                array[++top] = item;
                System.out.println(item + " pushed into the stack");
            }
        } else {
            System.out.println("Duplicate found. Item not added to the stack.");
        }
    }

    public String pop() {
        if (top == -1) {
            System.out.println("Stack Underflow");
            return null;
        } else {
            String popped = array[top--];
            System.out.println(popped + " popped from the stack");
            return popped;
        }
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void displayData(JTextArea textArea) {
        for (String url : array) {
            if (url != null) {
                textArea.append(url + "\n");
            }
        }
    }

    public boolean contains(String item) {
        for (String data : array) {
            if (item.equals(data)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkForDuplicates(String input) {
        for (String item : array) {
            if (item != null && item.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
}
