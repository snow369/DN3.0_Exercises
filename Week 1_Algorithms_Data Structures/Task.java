public class Task {

    // Attributes
    String taskId;
    String taskName;
    String status;

    // Node class for singly linked list
    private static class Node {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    // Singly Linked List for managing tasks
    private static Node head;

    // Constructor
    public Task(String taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    // Method to add a task
    public static void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Method to search for a task by ID
    public static Task searchTask(String taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.taskId.equals(taskId)) {
                return current.task;
            }
            current = current.next;
        }
        return null; // Task not found
    }

    // Method to traverse and print all tasks
    public static void traverseTasks() {
        Node current = head;
        while (current != null) {
            System.out.println("Task ID: " + current.task.taskId + 
                               ", Name: " + current.task.taskName + 
                               ", Status: " + current.task.status);
            current = current.next;
        }
    }

    // Method to delete a task by ID
    public static boolean deleteTask(String taskId) {
        if (head == null) return false;

        if (head.task.taskId.equals(taskId)) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.task.taskId.equals(taskId)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false; // Task not found
    }

     
    public static void main(String[] args) {
        // Add tasks
        addTask(new Task("T001", "Complete report", "Pending"));
        addTask(new Task("T002", "Send email", "Completed"));
        addTask(new Task("T003", "Prepare presentation", "In Progress"));

        // Traverse tasks
        System.out.println("All Tasks:");
        traverseTasks();

        // Search for a task
        Task foundTask = searchTask("T002");
        System.out.println("\nTask Found:");
        if (foundTask != null) {
            System.out.println("Task ID: " + foundTask.taskId + 
                               ", Name: " + foundTask.taskName + 
                               ", Status: " + foundTask.status);
        } else {
            System.out.println("Task not found.");
        }

        // Delete a task
        boolean deleted = deleteTask("T001");
        System.out.println("\nTask Deletion Status: " + (deleted ? "Success" : "Failed"));

        // Traverse tasks again
        System.out.println("\nAll Tasks After Deletion:");
        traverseTasks();
    }
}

//OUTPUT
// All Tasks:
// Task ID: T001, Name: Complete report, Status: Pending
// Task ID: T002, Name: Send email, Status: Completed
// Task ID: T003, Name: Prepare presentation, Status: In Progress

// Task Found:
// Task ID: T002, Name: Send email, Status: Completed

// Task Deletion Status: Success

// All Tasks After Deletion:
// Task ID: T002, Name: Send email, Status: Completed
// Task ID: T003, Name: Prepare presentation, Status: In Progress