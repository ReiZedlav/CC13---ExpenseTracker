import java.util.Scanner;

class ExpenseTracker{
    public static void main(String[] args){
        Interface menu = new Interface();


    }
}

class Utility{ //Reserved for export, and basic algorithms.
    public Utility(){}

    //To be implemented - CSV file output

}

class Interface{
    LinkedList ExpenseData = new LinkedList();
    Stack recentTransactions = new Stack();

    boolean running;
    
    public Interface(){
        this.running = true;
    }

    //To be implemented - Main menu
}


//Hashtable here

class Stack{
    LinkedList stack; //Stack is represented by linkedlist.

    public Stack(){ //Stack constructor
        this.stack = new LinkedList();
    }

    public void push(int[] Data){ //Pushes data in to the top of the stack.
        stack.prepend(Data);
    }

    public void pop(){ //Removes data from the stack.
        stack.removeHead();
    }

    public int[] peek(){ //Gets the value of whats in top of the stack.
        return stack.getValue(0);
    }
}

class LinkedList{ //For other necessary data structures.

    Node head; //Starting part of the linkedlist.

    public LinkedList(){ //Constructor
        this.head = null;
    }

    public boolean isEmpty(){ //Checks if linkedlist is empty.
        if (head == null){
            return true;
        } else{
            return false;
        }
    }

    public int[] getValue(int index){ //Returns the value of data in the linkedlist from a given index.
        Node current = head;

        int counter = 0;

        while (current.next != null && counter < index){
            current = current.next;
            counter++;
        }

        return current.Data;
    }

    public void append(int[] Data){ //Adds data to the end of the linkedlist (tail)
        Node newNode = new Node(Data);

        if (isEmpty()){
            head = newNode;
        } else{
            Node current = head;

            while (current.next != null){
                current = current.next;
            }

            current.next = newNode;
        }
    }

    public void prepend(int[] Data){ //Adds data to the starting point of the linkedlist (head)
        Node newNode = new Node(Data);

        newNode.next = head;

        head = newNode;
    }

    public void removeHead(){ //Removes the first part of the linkedlist.
        if (isEmpty()){
            return;
        } else{
            head = head.next;
        }
    }

    public void removeTail(){ //Removes the last part of the linkedlist.
        if (isEmpty()){
            return;
        } else{
            Node current = head;

            while (current.next != null && current.next.next != null){
                current = current.next;
            }

            current.next = null;
        }
    }
}

class Node{ //For linkedlist data structure.
    int[] Data;
    Node next;

    public Node(int[] Data){
        this.Data = Data;
        this.next = null;
    }
}