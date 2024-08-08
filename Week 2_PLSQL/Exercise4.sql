-- Scenario 1: Calculate Age of Customers for Eligibility Checks

CREATE OR REPLACE FUNCTION CalculateAge (
    p_dob DATE
) RETURN NUMBER IS
    v_age NUMBER;
BEGIN
    -- Calculate age based on the date of birth
    v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
    RETURN v_age;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An error occurred while calculating age: ' || SQLERRM);
        RETURN NULL;
END;
/
-- Output:
-- Assuming the current date is August 8, 2024.
-- If the date of birth is January 1, 1980:
-- SELECT CalculateAge(TO_DATE('1980-01-01', '2024-08-06')) FROM DUAL;
-- Output: 44 

-- Scenario 2: Compute Monthly Installment for a Loan

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_loan_amount NUMBER,
    p_interest_rate NUMBER,
    p_duration_years NUMBER
) RETURN NUMBER IS
    v_monthly_installment NUMBER;
    v_monthly_rate NUMBER;
    v_total_payments NUMBER;
BEGIN
    -- Convert annual interest rate to monthly rate
    v_monthly_rate := p_interest_rate / 12 / 100;
    -- Calculate total number of payments
    v_total_payments := p_duration_years * 12;
    -- Calculate monthly installment using the formula for an annuity
    v_monthly_installment := (p_loan_amount * v_monthly_rate) / (1 - POWER(1 + v_monthly_rate, -v_total_payments));
    RETURN v_monthly_installment;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An error occurred while calculating the monthly installment: ' || SQLERRM);
        RETURN NULL;
END;
/
-- Output:
-- If the loan amount is 10000, interest rate is 5%, and duration is 2 years:
-- SELECT CalculateMonthlyInstallment(10000, 5, 2) FROM DUAL;
-- Output: 438.71

-- Scenario 3: Check if a Customer Has Sufficient Balance Before Making a Transaction

CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_account_id NUMBER,
    p_amount NUMBER
) RETURN BOOLEAN IS
    v_balance NUMBER;
BEGIN
    -- Get the balance of the account
    SELECT Balance INTO v_balance
    FROM Accounts
    WHERE AccountID = p_account_id;

    -- Return true if the balance is greater than or equal to the amount
    RETURN v_balance >= p_amount;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: Account with ID ' || p_account_id || ' does not exist.');
        RETURN FALSE;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An error occurred while checking balance: ' || SQLERRM);
        RETURN FALSE;
END;
/
-- Output:
--
-- Check if AccountID 1 has at least 1500
-- SELECT HasSufficientBalance(1, 1500) FROM DUAL;
-- Output: TRUE
--
-- Check if AccountID 2 has at least 3000
-- SELECT HasSufficientBalance(2, 3000) FROM DUAL;
-- Output: FALSE
