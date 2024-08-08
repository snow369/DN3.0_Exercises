-- Scenario 1: Automatically Update the Last Modified Date When a Customer's Record is Updated

CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/
-- Assume we update the Name for CustomerID 1.
-- UPDATE Customers SET Name = 'Johnathan Doe' WHERE CustomerID = 1;
-- Output:
-- LastModified for CustomerID 1 updated to 07-Aug-2024 06:00 PM

-- Scenario 2: Maintain an Audit Log for All Transactions

CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, CustomerID, TransactionDate, Amount, Operation)
    VALUES (:NEW.TransactionID, :NEW.CustomerID, :NEW.TransactionDate, :NEW.Amount, 'INSERT');
END;
/
-- Assume we insert a new transaction with TransactionID 3.
-- INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, Operation) VALUES (3, 1, SYSDATE, 2000, 'Deposit');

-- Output:
-- New entry added to AuditLog with TransactionID 3, CustomerID 1, Operation 'INSERT'.

-- Scenario 3: Enforce Business Rules on Deposits and Withdrawals

CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    -- Check if the transaction is a withdrawal
    IF :NEW.Operation = 'Withdrawal' THEN
        -- Get the current balance of the account
        SELECT Balance INTO v_balance
        FROM Accounts
        WHERE AccountID = :NEW.AccountID;

        -- Ensure withdrawal does not exceed the balance
        IF :NEW.Amount > v_balance THEN
            RAISE_APPLICATION_ERROR(-20002, 'Insufficient funds for withdrawal.');
        END IF;

    -- Check if the transaction is a deposit
    ELSIF :NEW.Operation = 'Deposit' THEN
        -- Ensure deposit amount is positive
        IF :NEW.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20003, 'Deposit amount must be positive.');
        END IF;
    END IF;
END;
/
-- Assume we perform the following transactions:
-- 1. Withdraw 1500 from AccountID 1.
-- 2. Deposit 1000 into AccountID 2.
-- 3. Attempt to withdraw 3000 from AccountID 1, which has insufficient funds.

-- Output:
-- Withdrawal transaction for AccountID 1 processed successfully.
-- Deposit transaction for AccountID 2 processed successfully.
-- Error: Insufficient funds for withdrawal.