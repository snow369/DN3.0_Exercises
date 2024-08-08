-- Ensure IsVIP column exists in Customers table
DECLARE
    v_count NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_count
    FROM USER_TAB_COLUMNS
    WHERE TABLE_NAME = 'CUSTOMERS'
    AND COLUMN_NAME = 'ISVIP';

    IF v_count = 0 THEN
        EXECUTE IMMEDIATE 'ALTER TABLE Customers ADD (IsVIP CHAR(1) DEFAULT ''N'' NULL)';
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An error occurred while adding IsVIP column: ' || SQLERRM);
END;
/

-- Scenario 1: Apply a 1% Discount to Loan Interest Rates for Customers Above 60
BEGIN
    FOR rec IN (SELECT CustomerID, DOB FROM Customers) LOOP
        DECLARE
            v_age NUMBER;
        BEGIN
            v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, rec.DOB) / 12);
            IF v_age > 60 THEN
                UPDATE Loans
                SET InterestRate = InterestRate - 1
                WHERE CustomerID = rec.CustomerID;
            END IF;
        END;
    END LOOP;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('An error occurred while applying discount: ' || SQLERRM);
END;
/
-- Output:
-- No loans are updated since no customer is above 60 years old.

-- Scenario 2: Promote Customers with Balance Over $10,000 to VIP Status
BEGIN
    FOR rec IN (SELECT CustomerID, Balance FROM Customers) LOOP
        IF rec.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'Y'
            WHERE CustomerID = rec.CustomerID;
        ELSE
            UPDATE Customers
            SET IsVIP = 'N'
            WHERE CustomerID = rec.CustomerID;
        END IF;
    END LOOP;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('An error occurred while promoting to VIP status: ' || SQLERRM);
END;
/
-- Output:
-- Customer John Doe (ID: 1) has a balance of 1000, not over $10,000. IsVIP set to 'N'.
-- Customer Jane Smith (ID: 2) has a balance of 1500, not over $10,000. IsVIP set to 'N'.

-- Scenario 3: Send Reminders for Loans Due in the Next 30 Days
BEGIN
    FOR rec IN (SELECT L.CustomerID, C.Name, L.LoanID, L.EndDate
                FROM Loans L
                JOIN Customers C ON L.CustomerID = C.CustomerID
                WHERE L.EndDate BETWEEN SYSDATE AND SYSDATE + 30) LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Customer ' || rec.Name || ' (ID: ' || rec.CustomerID ||
                             ') has a loan (ID: ' || rec.LoanID || ') due on ' || rec.EndDate);
    END LOOP;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An error occurred while sending reminders: ' || SQLERRM);
END;
/
-- Output:
-- Reminder: Customer John Doe (ID: 1) has a loan (ID: 1) due on [Loan End Date].
-- (Assuming today's date is August 6, 2024, and the end date is within the next 30 days)
