-- Insert Users\INSERT INTO Users (UserID, FirstName, LastName, Email, PasswordHash, CreatedAt) VALUES
(1, 'Alice', 'Smith', 'alice@example.com', 'hash1', '2024-03-01'),
(2, 'Bob', 'Johnson', 'bob@example.com', 'hash2', '2024-03-02'),
(3, 'Charlie', 'Brown', 'charlie@example.com', 'hash3', '2024-03-03');

-- Insert Budgets
INSERT INTO Budgets (budgetID, budgetName, totalIncome, totalExpenses, savingsGoal, createdAt, userID) VALUES
(1, 'Monthly Budget', 5000, 3000, 1000, '2024-03-01', 1),
(2, 'Travel Fund', 8000, 2000, 4000, '2024-03-02', 2),
(3, 'Home Renovation', 10000, 5000, 3000, '2024-03-03', 3);

-- Insert Milestones
INSERT INTO Milestones (milestoneID, goalName, goalAmount, progress, targetDate, userID, budgetID) VALUES
(1, 'Emergency Fund', 5000, 2000, '2024-06-01', 1, 1),
(2, 'Vacation Savings', 3000, 1500, '2024-09-01', 2, 2),
(3, 'Home Upgrade', 7000, 3000, '2024-12-01', 3, 3);

-- Insert Categories
INSERT INTO Categories (categoryID, categoryName) VALUES
(1, 'Food'),
(2, 'Transport'),
(3, 'Entertainment'),
(4, 'Rent');

-- Insert Transactions
INSERT INTO Transactions (transactionID, amount, transactionType, date, userID, categoryID) VALUES
(1, 200, 'Expense', '2024-03-05', 1, 1),
(2, 100, 'Expense', '2024-03-06', 2, 2),
(3, 50, 'Expense', '2024-03-07', 3, 3),
(4, 500, 'Income', '2024-03-08', 1, 4);

-- Insert Security Information
INSERT INTO Security (authID, loginAttempts, lastLogin, userID) VALUES
(1, 0, '2024-03-10', 1),
(2, 1, '2024-03-09', 2),
(3, 2, '2024-03-08', 3);

-- Insert Tracks (Many-to-Many Relationship between Budgets and Transactions)
INSERT INTO Tracks (budgetID, transactionID) VALUES
(1, 1),
(2, 2),
(3, 3),
(1, 4);
