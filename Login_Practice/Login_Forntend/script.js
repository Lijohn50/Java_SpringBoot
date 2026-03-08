document.addEventListener('DOMContentLoaded', () => {
    // API BASE URL (Change this to your Spring Boot server address)
    const API_BASE_URL = 'http://localhost:8080/api';

    // === LOGIN (POST MAPPING) ===
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            const email = loginForm.querySelector('input[type="email"]').value;
            const password = loginForm.querySelector('input[type="password"]').value;

            try {
                // This matches @PostMapping("/auth/login") in Spring Boot
                const response = await fetch(`${API_BASE_URL}/auth/login`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ email, password })
                });

                if (response.ok) {
                    const data = await response.json(); // Usually contains a JWT token
                    localStorage.setItem('token', data.token);
                    localStorage.setItem('currentUser', email.split('@')[0]);
                    window.location.href = 'dashboard.html';
                } else {
                    alert('Login failed! Check your credentials.');
                }
            } catch (err) {
                console.error('Connection error:', err);
                // For demo purposes, we still allow redirect if backend isn't running yet
                localStorage.setItem('currentUser', email.split('@')[0]);
                window.location.href = 'dashboard.html';
            }
        });
    }

    // === REGISTER (POST MAPPING) ===
    const registerForm = document.getElementById('registerForm');
    if (registerForm) {
        registerForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            const fullName = registerForm.querySelector('input[placeholder="Full Name"]').value;
            const email = registerForm.querySelector('input[type="email"]').value;
            const password = document.getElementById('password').value;

            try {
                // This matches @PostMapping("/auth/register") in Spring Boot
                const response = await fetch(`${API_BASE_URL}/auth/register`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ fullName, email, password })
                });

                if (response.ok) {
                    alert('Registration successful!');
                    window.location.href = 'index.html';
                }
            } catch (err) {
                console.error('Error:', err);
            }
        });
    }

    // === DASHBOARD INITIALIZATION (GET MAPPING) ===
    const userNameDisplay = document.getElementById('userNameDisplay');
    const taskList = document.getElementById('taskList');

    if (userNameDisplay) {
        const user = localStorage.getItem('currentUser') || 'User';
        userNameDisplay.textContent = user.charAt(0).toUpperCase() + user.slice(1);
        
        // Fetch existing tasks from @GetMapping("/tasks")
        loadTasks();
    }

    async function loadTasks() {
        if (!taskList) return;
        try {
            const response = await fetch(`${API_BASE_URL}/tasks`, {
                headers: { 'Authorization': `Bearer ${localStorage.getItem('token')}` }
            });
            const tasks = await response.json();
            // ... Logic to render tasks from backend ...
        } catch (err) { console.log('Tasks will be loaded from backend once API is ready'); }
    }

    // === ADD TASK (POST MAPPING) ===
    const addTaskForm = document.getElementById('addTaskForm');
    if (addTaskForm && taskList) {
        addTaskForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            const title = addTaskForm.querySelector('input[type="text"]').value;
            const dueDate = addTaskForm.querySelector('input[type="date"]').value;

            // In a real app, you would POST this to @PostMapping("/tasks")
            const taskData = { title, dueDate };
            
            // For now, we update the UI immediately
            renderTask(title, dueDate);
            addTaskForm.reset();
        });
    }

    function renderTask(name, date) {
        const taskItem = document.createElement('div');
        taskItem.className = 'activity-item';
        taskItem.innerHTML = `
            <div class="activity-icon"><i class="fas fa-list-check"></i></div>
            <div style="flex-grow: 1;">
                <div style="font-weight: 500;">${name}</div>
                <div style="font-size: 0.8rem; color: var(--text-muted);">Due: ${date}</div>
            </div>
            <button class="button" style="padding: 8px 15px; font-size: 0.8rem; margin: 0;">Done</button>
        `;
        
        taskItem.querySelector('button').addEventListener('click', () => {
            taskItem.style.opacity = '0.5';
            taskItem.querySelector('button').textContent = 'Completed';
            taskItem.querySelector('button').disabled = true;
        });

        taskList.prepend(taskItem);
    }
});
