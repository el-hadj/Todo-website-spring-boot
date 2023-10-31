-- Insert 5 random TODO items with id column
INSERT INTO TODO (id, username, description, target_Date, done)
VALUES
    (100, 'elhadj', 'Complete project A', '2023-11-10', true),
    (200, 'elhadj', 'Buy groceries', '2023-11-15', false),
    (300, 'elhadj', 'Schedule a meeting', '2023-11-18', true),
    (400, 'ranga', 'Read a book', '2023-11-22', false),
    (500, 'ranga', 'Go for a run', '2023-11-25', true);
