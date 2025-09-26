ALTER TABLE task ALTER COLUMN id RESTART WITH 1;

INSERT INTO task (title, description, status, due_date) VALUES 
('Task 1', 'Description of Task 1', 'IN_PROGRESS', '2025-09-26'),
('Task 2', 'Description of Task 2', 'IN_PROGRESS', '2025-09-27'),
('Task 3', 'Description of Task 3', 'COMPLETED', '2025-09-28'),
('Task 4', 'Description of Task 4', 'IN_PROGRESS', '2025-09-29'),
('Task 5', 'Description of Task 5', 'COMPLETED', '2025-09-30'),
('Task 6', 'Description of Task 6', 'IN_PROGRESS', '2025-10-01'),
('Task 7', 'Description of Task 7', 'COMPLETED', '2025-10-02'),
('Task 8', 'Description of Task 8', 'IN_PROGRESS', '2026-09-03'),
('Task 9', 'Description of Task 9', 'COMPLETED', '2025-10-04'),
('Task 10', 'Description of Task 10', 'IN_PROGRESS', '2026-04-05');
