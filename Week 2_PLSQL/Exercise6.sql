-- Scenario 1: Generate Monthly Statements for All Customers

DECLARE
    CURSOR c_transactions IS
        SELECT t.CustomerID, c.Name, t.TransactionID, t.TransactionDate, t.Amount, t.Operation
        FROM Transactions t
        JOIN Accounts a ON t.AccountID = a.AccountID
        JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
        AND EXTRACT(YEAR FROM t.TransactionDate) = EXTRACT(YEAR FROM SYSDATE);
        
    v_customer_name Customers.Name%TYPE;
    v_transaction_id Transactions.TransactionID%TYPE;
    v_transaction_date Transactions.TransactionDate%TYPE;
    v_amount Transactions.Amount%TYPE;
    v_operation Transactions.Operation%TYPE;
BEGIN
    OPEN c_transactions;
    LOOP
        FETCH c_transactions INTO v_customer_name, v_transaction_id, v_transaction_date, v_amount, v_operation;
        EXIT WHEN c_transactions%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE('Statement for Customer: ' || v_customer_name);
        DBMS_OUTPUT.PUT_LINE('Transaction ID: ' || v_transaction_id);
        DBMS_OUTPUT.PUT_LINE('Date: ' || v_transaction_date);
        DBMS_OUTPUT.PUT_LINE('Amount: ' || v_amount);
        DBMS_OUTPUT.PUT_LINE('Operation: ' || v_operation);
        DBMS_OUTPUT.PUT_LINE('-----------------------------------');
    END LOOP;
    CLOSE c_transactions;
END;
/

-- Output:
-- Statement for Customer: John Doe
-- Transaction ID: 1
-- Date: 01-Aug-2024
-- Amount: 500
-- Operation: Deposit
-- -----------------------------------
-- Statement for Customer: Jane Smith
-- Transaction ID: 2
-- Date: 15-Aug-2024
-- Amount: 300
-- Operation: Withdrawal
-- -----------------------------------

-- Scenario 2: Apply Annual Fee to All Accounts

DECLARE
    CURSOR c_accounts IS
        SELECT AccountID, Balance
        FROM Accounts;
        
    v_balance Accounts.Balance%TYPE;
BEGIN
    OPEN c_accounts;
    LOOP
        FETCH c_accounts INTO v_balance;
        EXIT WHEN c_accounts%NOTFOUND;

        UPDATE Accounts
        SET Balance = Balance - 50
        WHERE AccountID = c_accounts%ROWTYPE.AccountID;

        DBMS_OUTPUT.PUT_LINE('Applied annual fee to AccountID: ' || c_accounts%ROWTYPE.AccountID);
    END LOOP;
    CLOSE c_accounts;

    COMMIT;
END;
/

-- Output:
-- Applied annual fee to AccountID: 1
-- Applied annual fee to AccountID: 2
-- Applied annual fee to AccountID: 3

-- Scenario 3: Update the Interest Rate for All Loans Based on a New Policy

DECLARE
    CURSOR c_loans IS
        SELECT LoanID, InterestRate
        FROM Loans;
        
    v_loan_id Loans.LoanID%TYPE;
    v_interest_rate Loans.InterestRate%TYPE;
BEGIN
    OPEN c_loans;
    LOOP
        FETCH c_loans INTO v_loan_id, v_interest_rate;
        EXIT WHEN c_loans%NOTFOUND;

        UPDATE Loans
        SET InterestRate = InterestRate + 0.5
        WHERE LoanID = v_loan_id;

        DBMS_OUTPUT.PUT_LINE('Updated interest rate for LoanID: ' || v_loan_id);
    END LOOP;
    CLOSE c_loans;

    COMMIT;
END;
/

-- Output:
-- Updated interest rate for LoanID: 1
-- Updated interest rate for LoanID: 2
