DELETE FROM tasks;
DELETE FROM lists;



INSERT INTO lists (id, title, owner) VALUES
(1, 'Daily', 'laura'),
(2, 'Mondays', 'harry'),
(3, 'Monthly', 'anna'),
(4, 'Fridays', 'anna');

INSERT INTO tasks (id, list_id, name, description, schedule, completed) VALUES
(1, 1, 'Clear backlog', 'Review and prioritize the backlog items, addressing any unresolved tasks or pending issues to bring the project up to date', 1686964458,  false),
(2, 2, 'Buy groceries', 'Purchase fruits, vegetables, and pantry staples from the supermarket', 1686964458, false),
(3, 3, 'Clean the house', 'Dust the furniture, vacuum the floors, and clean the windows', 1686964458, true),
(4, 2, 'Complete project report', 'Write a detailed report summarizing the project progress and findings', 1686964458, true),
(5, 1, 'Team meeting', 'Participate in the weekly team meeting to discuss project updates and action items', 1686964458, true),
(6, 1, 'Resolve pending issues', 'Trucks routine checks', 1686964458, false),
(7, 3, 'Pay utility bills', 'Settle electricity, water, and internet bills by the due date', 1686964458, false),
(8, 4, 'Organize files and folders', 'Sort and arrange digital files and documents into appropriate folders for easy access', 1686964458, true),
(9, 4, 'Plan a weekend getaway', 'Research destinations, book accommodations, and create an itinerary for a short vacation', 1686964458, false);

