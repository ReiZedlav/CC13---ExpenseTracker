import java.util.Scanner;
import java.util.HashMap;

class ExpenseTracker{
    public static void main(String[] args){
        Interface CLI = new Interface();

        CLI.mainMenu();


    }
}

class Utility{ //Reserved for export, and basic algorithms.
    public Utility(){}

    //To be implemented - CSV file output

}

class Interface{
    HashMap<Integer, LinkedList> table = new HashMap<>();
    LinkedList ExpenseData = new LinkedList();
    Stack recentTransactions = new Stack();

    Scanner input = new Scanner(System.in);

    public Interface(){}

    public void mainMenu(){
        while (true){
            System.out.println("-----------MAIN MENU-----------");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Remove Expense");
            System.out.println("4. Undo Last Action");
            System.out.println("5. Export Expenses to CSV");
            System.out.println("6. Exit");
            System.out.println("-------------------------------");
            System.out.print("Enter option: ");
            int Command = input.nextInt();

            switch (Command){
                case 1:
                    //addExpense();
                    break;
                
                case 2:
                    //viewExpenses();  
                    break;
                
                case 3:
                    //removeExpense();
                    break;
                
                case 4:
                    //undoLastAction();
                    break;
                
                case 5:
                    //Utility.exportToCSV(expenseData);
                    break;
                
                case 6:
                    System.out.println("Exiting!");
                    return;
                
                default:
                    System.out.println("Invalid Statement");
            }
        }
    }
    
}

class Stack {
    LinkedList stack; // Stack is represented by linked list.

    public Stack() { // Stack constructor
        this.stack = new LinkedList();
    }

    public void push(Object[] data) { // Pushes data to the top of the stack.
        stack.prepend(data);
    }

    public void pop() { // Removes data from the stack.
        stack.removeHead();
    }

    public Object[] peek() { // Gets the value of what's in top of the stack.
        return stack.getValue(0);
    }
}

class LinkedList { // For other necessary data structures.

    Node head; // Starting part of the linked list.

    public LinkedList() { // Constructor
        this.head = null;
    }

    public boolean isEmpty() { // Checks if linked list is empty.
        return head == null;
    }

    public Object[] getValue(int index) { // Returns the value of data in the linked list from a given index.
        Node current = head;
        int counter = 0;

        while (current != null && counter < index) {
            current = current.next;
            counter++;
        }

        // If index is out of bounds, return null or throw an exception
        if (current == null) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }

        return current.Data; // Return the data as Object[]
    }

    public void append(Object[] data) { // Adds data to the end of the linked list (tail)
        Node newNode = new Node(data);

        if (isEmpty()) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void prepend(Object[] data) { // Adds data to the starting point of the linked list (head)
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public void removeHead() { // Removes the first part of the linked list.
        if (!isEmpty()) {
            head = head.next;
        }
    }

    public void removeTail() { // Removes the last part of the linked list.
        if (isEmpty()) {
            return;
        }

        if (head.next == null) { // If there’s only one element
            head = null;
            return;
        }

        Node current = head;
        while (current.next != null && current.next.next != null) {
            current = current.next;
        }

        current.next = null; // Remove the last node
    }

    public void removeAt(int index) {
        if (isEmpty()) return;

        if (index == 0) {
            removeHead();
            return;
        }

        Node current = head;
        int counter = 0;
        while (current != null && counter < index - 1) {
            current = current.next;
            counter++;
        }

        if (current != null && current.next != null) {
            current.next = current.next.next;
        }
    }

}

class Node { // For linked list data structure.
    Object[] Data; // Use Object[] for flexibility in storing any type of data
    Node next;

    public Node(Object[] data) {
        this.Data = data;
        this.next = null;
    }
}
