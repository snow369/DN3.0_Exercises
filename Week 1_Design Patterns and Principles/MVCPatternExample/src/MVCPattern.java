public class MVCPattern {

    // Define Model Class
    static class Student {
        private String name;
        private int id;
        private String grade;

        public Student(String name, int id, String grade) {
            this.name = name;
            this.id = id;
            this.grade = grade;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }
    }

    // Define View Class
    static class StudentView {
        public void displayStudentDetails(Student student) {
            System.out.println("Student Details:");
            System.out.println("Name: " + student.getName());
            System.out.println("ID: " + student.getId());
            System.out.println("Grade: " + student.getGrade());
        }
    }

    // Define Controller Class
    static class StudentController {
        private Student model;
        private StudentView view;

        public StudentController(Student model, StudentView view) {
            this.model = model;
            this.view = view;
        }

        public void setStudentName(String name) {
            model.setName(name);
        }

        public String getStudentName() {
            return model.getName();
        }

        public void setStudentId(int id) {
            model.setId(id);
        }

        public int getStudentId() {
            return model.getId();
        }

        public void setStudentGrade(String grade) {
            model.setGrade(grade);
        }

        public String getStudentGrade() {
            return model.getGrade();
        }

        public void updateView() {
            view.displayStudentDetails(model);
        }
    }

    
    public static void main(String[] args) {
        // Create a Student model
        Student student = new Student("John Doe", 123, "A");

        // Create a StudentView
        StudentView view = new StudentView();

        // Create a StudentController
        StudentController controller = new StudentController(student, view);

        // Display initial student details
        System.out.println("Initial Student Details:");
        controller.updateView();

        // Update student details
        controller.setStudentName("Jane Doe");
        controller.setStudentId(456);
        controller.setStudentGrade("B");

        // Display updated student details
        System.out.println("\nUpdated Student Details:");
        controller.updateView();
    }
}

//OUTPUT
// Initial Student Details:
// Student Details:
// Name: John Doe
// ID: 123
// Grade: A

// Updated Student Details:
// Student Details:
// Name: Jane Doe
// ID: 456
// Grade: B