-- Scenario 1: Handle Exceptions During Fund Transfers Between Accounts

CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_from_account IN NUMBER,
    p_to_account IN NUMBER,
    p_amount IN NUMBER
) IS
    v_from_balance NUMBER;
    v_to_balance NUMBER;
BEGIN
    BEGIN
        SELECT Balance INTO v_from_balance
        FROM Accounts
        WHERE AccountID = p_from_account;
        
        IF v_from_balance < p_amount THEN
            RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds in the source account.');
        END IF;
        
        UPDATE Accounts
        SET Balance = Balance - p_amount
        WHERE AccountID = p_from_account;
        
        UPDATE Accounts
        SET Balance = Balance + p_amount
        WHERE AccountID = p_to_account;
        
        COMMIT;
        
        DBMS_OUTPUT.PUT_LINE('Funds transferred successfully.');
        
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error: One or both accounts do not exist.');
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
    END;
END;
/
-- Fund Transfer from AccountID 1 to AccountID 2
-- SafeTransferFunds(1, 2, 2000);
-- Output: "Funds transferred successfully."

-- Scenario 2: Manage Errors When Updating Employee Salaries

CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_employee_id IN NUMBER,
    p_percentage IN NUMBER
) IS
    v_current_salary NUMBER;
BEGIN
    BEGIN
        SELECT Salary INTO v_current_salary
        FROM Employees
        WHERE EmployeeID = p_employee_id;
        
        UPDATE Employees
        SET Salary = Salary + (Salary * p_percentage / 100)
        WHERE EmployeeID = p_employee_id;
        
        COMMIT;
        
        DBMS_OUTPUT.PUT_LINE('Salary updated successfully.');
        
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error: Employee with ID ' || p_employee_id || ' does not exist.');
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
    END;
END;
/
-- Update Salary of EmployeeID 1 by 10%
-- UpdateSalary(1, 10);
-- Output: "Salary updated successfully."

-- Try to update salary of a non-existent EmployeeID 999
-- UpdateSalary(999, 10);
-- Output: "Error: Employee with ID 999 does not exist."

-- Scenario 3: Ensure Data Integrity When Adding a New Customer

CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_customer_id IN NUMBER,
    p_name IN VARCHAR2,
    p_dob IN DATE,
    p_balance IN NUMBER
) IS
BEGIN
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);
        
        COMMIT;
        
        DBMS_OUTPUT.PUT_LINE('Customer added successfully.');
        
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error: Customer with ID ' || p_customer_id || ' already exists.');
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
    END;
END;
/

-- Add a new customer with ID 3
-- AddNewCustomer(3, 'Alice Johnson', TO_DATE('1992-11-30', '2024-08-06'), 2000);
-- Output: "Customer added successfully."

-- Try to add a customer with an existing ID 1
-- AddNewCustomer(1, 'Bob Brown', TO_DATE('1988-03-10', '2024-08-06'), 2500);
-- Output: "Error: Customer with ID 1 already exists."