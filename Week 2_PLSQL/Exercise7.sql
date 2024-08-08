-- Scenario 1: Group All Customer-Related Procedures and Functions into a Package

-- Package Specification for CustomerManagement

CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddNewCustomer(
        p_customer_id IN NUMBER,
        p_name IN VARCHAR2,
        p_dob IN DATE,
        p_balance IN NUMBER
    );

    PROCEDURE UpdateCustomerDetails(
        p_customer_id IN NUMBER,
        p_name IN VARCHAR2,
        p_dob IN DATE
    );

    FUNCTION GetCustomerBalance(
        p_customer_id IN NUMBER
    ) RETURN NUMBER;
END CustomerManagement;
/

-- Package Body for CustomerManagement

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddNewCustomer(
        p_customer_id IN NUMBER,
        p_name IN VARCHAR2,
        p_dob IN DATE,
        p_balance IN NUMBER
    ) IS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Customer added successfully.');
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Customer with ID ' || p_customer_id || ' already exists.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
    END AddNewCustomer;

    PROCEDURE UpdateCustomerDetails(
        p_customer_id IN NUMBER,
        p_name IN VARCHAR2,
        p_dob IN DATE
    ) IS
    BEGIN
        UPDATE Customers
        SET Name = p_name, DOB = p_dob, LastModified = SYSDATE
        WHERE CustomerID = p_customer_id;
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Customer details updated successfully.');
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Error: Customer with ID ' || p_customer_id || ' does not exist.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
    END UpdateCustomerDetails;

    FUNCTION GetCustomerBalance(
        p_customer_id IN NUMBER
    ) RETURN NUMBER IS
        v_balance NUMBER;
    BEGIN
        SELECT Balance INTO v_balance
        FROM Customers
        WHERE CustomerID = p_customer_id;
        RETURN v_balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Error: Customer with ID ' || p_customer_id || ' does not exist.');
            RETURN NULL;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
            RETURN NULL;
    END GetCustomerBalance;

END CustomerManagement;
/

-- Example Usage and Output:

-- Adding a new customer
-- EXEC CustomerManagement.AddNewCustomer(4, 'Alice Johnson', TO_DATE('1985-04-12', 'YYYY-MM-DD'), 4000);
-- Output: Customer added successfully.

-- Updating customer details
-- EXEC CustomerManagement.UpdateCustomerDetails(1, 'Johnathan Doe', TO_DATE('1980-01-01', 'YYYY-MM-DD'));
-- Output: Customer details updated successfully.

-- Getting customer balance
-- SELECT CustomerManagement.GetCustomerBalance(1) FROM DUAL;
-- Output: 5000 (if the customer has 5000 balance)


-- Scenario 2: Create a Package to Manage Employee Data

-- Package Specification for EmployeeManagement

CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(
        p_employee_id IN NUMBER,
        p_name IN VARCHAR2,
        p_department_id IN NUMBER,
        p_salary IN NUMBER
    );

    PROCEDURE UpdateEmployeeDetails(
        p_employee_id IN NUMBER,
        p_name IN VARCHAR2,
        p_department_id IN NUMBER
    );

    FUNCTION CalculateAnnualSalary(
        p_employee_id IN NUMBER
    ) RETURN NUMBER;
END EmployeeManagement;
/

-- Package Body for EmployeeManagement

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_employee_id IN NUMBER,
        p_name IN VARCHAR2,
        p_department_id IN NUMBER,
        p_salary IN NUMBER
    ) IS
    BEGIN
        INSERT INTO Employees (EmployeeID, Name, DepartmentID, Salary)
        VALUES (p_employee_id, p_name, p_department_id, p_salary);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Employee hired successfully.');
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Employee with ID ' || p_employee_id || ' already exists.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
    END HireEmployee;

    PROCEDURE UpdateEmployeeDetails(
        p_employee_id IN NUMBER,
        p_name IN VARCHAR2,
        p_department_id IN NUMBER
    ) IS
    BEGIN
        UPDATE Employees
        SET Name = p_name, DepartmentID = p_department_id
        WHERE EmployeeID = p_employee_id;
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Employee details updated successfully.');
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Error: Employee with ID ' || p_employee_id || ' does not exist.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
    END UpdateEmployeeDetails;

    FUNCTION CalculateAnnualSalary(
        p_employee_id IN NUMBER
    ) RETURN NUMBER IS
        v_salary NUMBER;
    BEGIN
        SELECT Salary * 12 INTO v_salary
        FROM Employees
        WHERE EmployeeID = p_employee_id;
        RETURN v_salary;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Error: Employee with ID ' || p_employee_id || ' does not exist.');
            RETURN NULL;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
            RETURN NULL;
    END CalculateAnnualSalary;

END EmployeeManagement;
/

-- Example Usage and Output:

-- Hiring a new employee
-- EXEC EmployeeManagement.HireEmployee(4, 'Eve Williams', 20, 55000);
-- Output: Employee hired successfully.

-- Updating employee details
-- EXEC EmployeeManagement.UpdateEmployeeDetails(1, 'John Doe', 30);
-- Output: Employee details updated successfully.

-- Calculating annual salary
-- SELECT EmployeeManagement.CalculateAnnualSalary(1) FROM DUAL;
-- Output: 720000 (if the monthly salary is 60000)


-- Scenario 3: Group All Account-Related Operations into a Package

-- Package Specification for AccountOperations

CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(
        p_account_id IN NUMBER,
        p_customer_id IN NUMBER,
        p_account_type IN VARCHAR2,
        p_initial_balance IN NUMBER
    );

    PROCEDURE CloseAccount(
        p_account_id IN NUMBER
    );

    FUNCTION GetTotalBalance(
        p_customer_id IN NUMBER
    ) RETURN NUMBER;
END AccountOperations;
/

-- Package Body for AccountOperations

CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount(
        p_account_id IN NUMBER,
        p_customer_id IN NUMBER,
        p_account_type IN VARCHAR2,
        p_initial_balance IN NUMBER
    ) IS
    BEGIN
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance)
        VALUES (p_account_id, p_customer_id, p_account_type, p_initial_balance);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Account opened successfully.');
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Account with ID ' || p_account_id || ' already exists.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
    END OpenAccount;

    PROCEDURE CloseAccount(
        p_account_id IN NUMBER
    ) IS
    BEGIN
        DELETE FROM Accounts
        WHERE AccountID = p_account_id;
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Account closed successfully.');
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Error: Account with ID ' || p_account_id || ' does not exist.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
    END CloseAccount;

    FUNCTION GetTotalBalance(
        p_customer_id IN NUMBER
    ) RETURN NUMBER IS
        v_total_balance NUMBER;
    BEGIN
        SELECT SUM(Balance) INTO v_total_balance
        FROM Accounts
        WHERE CustomerID = p_customer_id;
        RETURN v_total_balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Error: Customer with ID ' || p_customer_id || ' does not exist.');
            RETURN NULL;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
            RETURN NULL;
    END GetTotalBalance;

END AccountOperations;
/

-- Example Usage and Output:

-- Opening a new account
-- EXEC AccountOperations.OpenAccount(4, 1, 'Savings', 3000);
-- Output: Account opened successfully.

-- Closing an account
-- EXEC AccountOperations.CloseAccount(4);
-- Output: Account closed successfully.

-- Getting total balance across all accounts for a customer
-- SELECT AccountOperations.GetTotalBalance(1) FROM D