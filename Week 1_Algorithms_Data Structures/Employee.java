public class Employee {

    // Attributes
    String employeeId;
    String name;
    String position;
    double salary;

    // Static array and size for employee management
    private static Employee[] employees = new Employee[10];
    private static int size = 0;

    // Constructor
    public Employee(String employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    // Method to add an employee
    public static void addEmployee(Employee employee) {
        if (size == employees.length) {
            // Resize the array if needed
            resize();
        }
        employees[size++] = employee;
    }

    // Method to search for an employee by ID
    public static Employee searchEmployee(String employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].employeeId.equals(employeeId)) {
                return employees[i];
            }
        }
        return null; // Not found
    }

    // Method to traverse and print all employees
    public static void traverseEmployees() {
        for (int i = 0; i < size; i++) {
            System.out.println("ID: " + employees[i].employeeId + 
                               ", Name: " + employees[i].name + 
                               ", Position: " + employees[i].position + 
                               ", Salary: " + employees[i].salary);
        }
    }

    // Method to delete an employee by ID
    public static boolean deleteEmployee(String employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].employeeId.equals(employeeId)) {
                // Shift elements left to fill the gap
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--size] = null; // Clear the last element
                return true;
            }
        }
        return false; // Not found
    }

    // Helper method to resize the array
    private static void resize() {
        Employee[] newArray = new Employee[employees.length * 2];
        System.arraycopy(employees, 0, newArray, 0, size);
        employees = newArray;
    }

    
    public static void main(String[] args) {
        // Add employees
        addEmployee(new Employee("E001", "John Doe", "Developer", 75000));
        addEmployee(new Employee("E002", "Jane Smith", "Manager", 85000));
        addEmployee(new Employee("E003", "Emily Davis", "Analyst", 65000));

        // Traverse employees
        System.out.println("All Employees:");
        traverseEmployees();

        // Search for an employee
        Employee emp = searchEmployee("E002");
        System.out.println("\nEmployee Found:");
        if (emp != null) {
            System.out.println("ID: " + emp.employeeId + 
                               ", Name: " + emp.name + 
                               ", Position: " + emp.position + 
                               ", Salary: " + emp.salary);
        } else {
            System.out.println("Employee not found.");
        }

        // Delete an employee
        boolean deleted = deleteEmployee("E001");
        System.out.println("\nEmployee Deletion Status: " + (deleted ? "Success" : "Failed"));

        // Traverse employees again
        System.out.println("\nAll Employees After Deletion:");
        traverseEmployees();
    }
}

//OUTPUT
// All Employees:
// ID: E001, Name: John Doe, Position: Developer, Salary: 75000.0
// ID: E002, Name: Jane Smith, Position: Manager, Salary: 85000.0
// ID: E003, Name: Emily Davis, Position: Analyst, Salary: 65000.0

// Employee Found:
// ID: E002, Name: Jane Smith, Position: Manager, Salary: 85000.0

// Employee Deletion Status: Success

// All Employees After Deletion:
// ID: E002, Name: Jane Smith, Position: Manager, Salary: 85000.0
// ID: E003, Name: Emily Davis, Position: Analyst, Salary: 65000.0