import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ExpenseTracker{
    public static void main(String[] args){
        Interface CLI = new Interface();

        CLI.mainMenu();

    }
}

class Utility{ //Reserved for export, and basic algorithms.
    public Utility(){}

    //To be implemented - CSV file output
    public void exportToCSV(List<ExpenseInformation> expenses) {
        try (PrintWriter writer = new PrintWriter(new File("CC13---ExpenseTracker/ExpenseInformation.csv"))) {
            StringBuilder sb = new StringBuilder();
            sb.append("Category,Merchant,Amount,Date,PaymentMethod,Description\n");

            for (ExpenseInformation expense : expenses) {
                sb.append(expense.category).append(",");
                sb.append(expense.merchant).append(",");
                sb.append(expense.amount).append(",");
                sb.append(expense.date).append(",");
                sb.append(expense.paymentMethod).append(",");
                sb.append(expense.description).append("\n");
            }

            writer.write(sb.toString());
            System.out.println("CSV file created successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while writing to the CSV file.");
            e.printStackTrace();
        }
    }
}


class ExpenseInformation {
    String category;
    String merchant;
    double amount;
    String date;
    String paymentMethod;
    String description;

    public ExpenseInformation(String category, String merchant, double amount, String date, String paymentMethod, String description) {
        this.category = category;
        this.merchant = merchant;
        this.amount = amount;
        this.date = date;
        this.paymentMethod = paymentMethod;
        this.description = description;
    }
}

class Interface{
    Stack recentTransactions = new Stack();

    Scanner input = new Scanner(System.in);

    public Interface(){}

    public void mainMenu(){
        while (true){
            System.out.println("-----------MAIN MENU-----------");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Remove Expense");
            System.out.println("4. Undo last entry");
            System.out.println("5. Export Expenses to CSV");
            System.out.println("6. Exit");
            System.out.println("-------------------------------");
            System.out.print("Enter option: ");
            int Command = input.nextInt();

            input.nextLine();

            System.out.println("-------------------------------");

            switch (Command){
                case 1:
                    addExpense();
                    break;
                
                case 2:
                    viewExpenses();  
                    break;
                
                case 3:
                    //removeExpense();
                    break;
                
                case 4:
                    undoLastAction();
                    break;
                
                case 5:
                    exportToCSV(recentTransactions);
                    break;
                
                case 6:
                    System.out.println("Exiting!");
                    return;
                
                default:
                    System.out.println("Invalid Statement");
            }
        }
    }

    
    // 1. Add Expense
   public void addExpense() {
        System.out.print("Transaction category (Food, Transportation, Tuition, etc...): ");
        String category = input.nextLine();
        
        System.out.print("Merchant Name: ");
        String merchantName = input.nextLine();

        System.out.print("Amount Total: ");
        double amountTotal = input.nextDouble();

        input.nextLine();

        System.out.print("Date of expense (DD/MM/YYYY): ");
        String date = input.nextLine();

        System.out.print("Payment method (e.g., Credit Card, Cash, PayPal): ");
        String paymentMethod = input.nextLine();

        System.out.print("Short Description: ");
        String shortDescription = input.nextLine();

        System.out.println("-------------------------------");

        ExpenseInformation expense = new ExpenseInformation(category,merchantName,amountTotal,date,paymentMethod,shortDescription);

        recentTransactions.push(expense); //Push to stack

        System.out.println("Expense added successfully!");
    }
    // 2. View Expenses
    public void viewExpenses()  {
        if (recentTransactions.isEmpty()) {
            System.out.println("No recent transactions found.");
            return;
        }
        else {
        System.out.println("Recent Transactions:");
        for (int i = 0; i < recentTransactions.stack.size(); i++) {
            ExpenseInformation expense = recentTransactions.stack.getValue(i);
            System.out.println("Expense " + (i + 1) + ":");
            System.out.println("Category: " + expense.category);
            System.out.println("Merchant: " + expense.merchant);
            System.out.println("Amount: " + expense.amount);
            System.out.println("Date: " + expense.date);
            System.out.println("Payment Method: " + expense.paymentMethod);
            System.out.println("Description: " + expense.description);
            System.out.println("-------------------------------");
            }
        }
    }
    // 5. Export Expenses to CSV
    public void exportToCSV(Stack recentTransactions) {
        Utility utility = new Utility();
        List<ExpenseInformation> expenses = new ArrayList<>();
        for (int i = 0; i < recentTransactions.stack.size(); i++) {
            expenses.add(recentTransactions.stack.getValue(i));
        }
        utility.exportToCSV(expenses);
    }

    // 4. Undo last entry
    public void undoLastAction(){
        recentTransactions.pop();
        System.out.println("Last transaction removed!");
    }

}

class Stack {
    LinkedList stack; // Stack is represented by linked list.

    public Stack() { // Stack constructor
        this.stack = new LinkedList();
    }

    public void push(ExpenseInformation data) { // Pushes an ExpenseInformation object to the top of the stack.
        stack.prepend(data);
    }

    public void pop() { // Removes an ExpenseInformation object from the stack.
        stack.removeHead();
    }

    public ExpenseInformation peek() { // Gets the ExpenseInformation object at the top of the stack.
        return (ExpenseInformation) stack.getValue(0);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}

class LinkedList { // For other necessary data structures.

    Node head; // Starting part of the linked list.

    public LinkedList() {
        this.head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public ExpenseInformation getValue(int index) { // Returns the ExpenseInformation object at a given index.
        Node current = head;
        int counter = 0;

        while (current != null && counter < index) {
            current = current.next;
            counter++;
        }

        if (current == null) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }

        return current.Data; 
    }

    public void append(ExpenseInformation data) { // Adds an ExpenseInformation object to the end of the linked list (tail)
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

    public void prepend(ExpenseInformation data) { // Adds an ExpenseInformation object to the starting point of the linked list (head)
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

    public void printList() { // To print all the elements in the linked list
        Node current = head;
        int index = 0;
        while (current != null) {
            System.out.println("Index " + index + ": " + current.Data);
            current = current.next;
            index++;
        }
    }

    int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    
}

class Node { // For linked list data structure.
    ExpenseInformation Data; // Store ExpenseInformation objects
    Node next;

    public Node(ExpenseInformation data) {
        this.Data = data;
        this.next = null;
    }
}
