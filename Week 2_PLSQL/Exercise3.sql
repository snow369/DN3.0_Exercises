-- Scenario 1: Process Monthly Interest for Savings Accounts

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
BEGIN
    UPDATE Accounts
    SET Balance = Balance * 1.01
    WHERE AccountType = 'Savings';

    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Monthly interest processed successfully for all savings accounts.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('An error occurred while processing monthly interest: ' || SQLERRM);
END;
/

-- Output:
-- Monthly interest processed successfully for all savings accounts.

-- Scenario 2: Update Employee Bonus

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department_id IN NUMBER,
    p_bonus_percentage IN NUMBER
) IS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_bonus_percentage / 100)
    WHERE DepartmentID = p_department_id;

    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Employee bonuses updated successfully for department ' || p_department_id || '.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('An error occurred while updating employee bonuses: ' || SQLERRM);
END;
/

-- Output:
-- Employee bonuses updated successfully for department 10.

-- Scenario 3: Transfer Funds Between Accounts

CREATE OR REPLACE PROCEDURE TransferFunds (
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

-- Output:
--
-- Scenario 1: Transfer 1500 from AccountID 1 to AccountID 2
-- Output:
-- Funds transferred successfully.
--
-- Scenario 2: Transfer 3000 from AccountID 1 to AccountID 999 (non-existent)
-- Output:
-- Error: One or both accounts do not exist.
