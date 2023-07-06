DELETE FROM tasks;
DELETE FROM lists;



INSERT INTO lists (id, title, owner) VALUES
(1000, 'Daily', 'laura'),
(1001, 'Mondays', 'harry'),
(1002, 'Monthly', 'anna'),
(1003, 'Fridays', 'anna');

INSERT INTO tasks (id, list_id, name, description, schedule, completed) VALUES
(1000, 1000, 'Clear backlog', 'Review and prioritize the backlog items, addressing any unresolved tasks or pending issues to bring the project up to date', 1686964458,  false),
(1001, 1001, 'Buy groceries', 'Purchase fruits, vegetables, and pantry staples from the supermarket', 1686964458, false),
(1002, 1002, 'Clean the house', 'Dust the furniture, vacuum the floors, and clean the windows', 1686964458, true),
(1003, 1002, 'Complete project report', 'Write a detailed report summarizing the project progress and findings', 1686964458, true),
(1004, 1000, 'Team meeting', 'Participate in the weekly team meeting to discuss project updates and action items', 1686964458, true),
(1005, 1000, 'Resolve pending issues', 'Trucks routine checks', 1686964458, false),
(1006, 1003, 'Pay utility bills', 'Settle electricity, water, and internet bills by the due date', 1686964458, false),
(1007, 1003, 'Organize files and folders', 'Sort and arrange digital files and documents into appropriate folders for easy access', 1686964458, true),
(1008, 1001, 'Plan a weekend getaway', 'Research destinations, book accommodations, and create an itinerary for a short vacation', 1686964458, false);

