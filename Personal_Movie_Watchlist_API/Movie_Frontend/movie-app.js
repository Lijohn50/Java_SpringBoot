const API_URL = 'http://localhost:8080/api/movies';

document.getElementById('movieForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const movieData = {
        title: document.getElementById('title').value,
        director: document.getElementById('director').value,
        genre: document.getElementById('genre').value,
        releaseYear: parseInt(document.getElementById('releaseYear').value),
        watched: false
    };

    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(movieData)
        });

        if (response.ok) {
            e.target.reset();
            loadMovies();
        }
    } catch (error) {
        console.error('Error adding movie:', error);
        alert('Could not connect to Spring Boot backend!');
    }
});

async function loadMovies() {
    const grid = document.getElementById('movieGrid');
    try {
        const response = await fetch(API_URL);
        const movies = await response.json();
        
        document.getElementById('stats').textContent = `Total: ${movies.length}`;
        
        grid.innerHTML = movies.map(movie => `
            <div class="movie-card">
                <div class="genre-tag">${movie.genre}</div>
                <h3>${movie.title}</h3>
                <p><strong>Director:</strong> ${movie.director}</p>
                <p><strong>Year:</strong> ${movie.releaseYear}</p>
                <div class="card-actions">
                    <button class="btn-icon btn-watched ${movie.watched ? 'active' : ''}" 
                            onclick="toggleWatched(${movie.id}, ${movie.watched})">
                        ${movie.watched ? '✓ Watched' : 'Mark Watched'}
                    </button>
                    <button class="btn-icon btn-delete" onclick="deleteMovie(${movie.id})">
                        Delete
                    </button>
                </div>
            </div>
        `).join('');
        
        if (movies.length === 0) {
            grid.innerHTML = '<div class="loader">Your watchlist is empty. Add a movie above!</div>';
        }
    } catch (error) {
        grid.innerHTML = '<div class="loader" style="color: #ef4444">Failed to load movies. Is the backend running?</div>';
    }
}

async function toggleWatched(id, currentStatus) {
    try {
        // Fetch existing movie to maintain current values
        const res = await fetch(`${API_URL}/${id}`);
        const movie = await res.json();
        
        movie.watched = !currentStatus;

        await fetch(`${API_URL}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(movie)
        });
        loadMovies();
    } catch (error) {
        console.error('Error updating movie:', error);
    }
}

async function deleteMovie(id) {
    if (!confirm('Remove this movie from your list?')) return;
    
    try {
        await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
        loadMovies();
    } catch (error) {
        console.error('Error deleting movie:', error);
    }
}

loadMovies();