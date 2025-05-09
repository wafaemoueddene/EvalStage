// services/api.js
// Ce fichier serait utilisé pour les requêtes API réelles dans une application en production

const API_BASE_URL = 'https://api.evaluation-stage.example.com/api';

// Fonction utilitaire pour les requêtes
const request = async (endpoint, options = {}) => {
    const url = `${API_BASE_URL}${endpoint}`;

    // Ajouter le token d'authentification si disponible
    const token = localStorage.getItem('authToken');
    const headers = {
        'Content-Type': 'application/json',
        ...options.headers
    };

    if (token) {
        headers.Authorization = `Bearer ${token}`;
    }

    const config = {
        ...options,
        headers
    };

    try {
        const response = await fetch(url, config);

        if (!response.ok) {
            throw new Error(`Erreur HTTP: ${response.status}`);
        }

        return await response.json();
    } catch (error) {
        console.error(`API Error: ${error.message}`);
        throw error;
    }
};

// API d'authentification
export const authApi = {
    login: (credentials) => request('/auth/login', {
        method: 'POST',
        body: JSON.stringify(credentials)
    }),

    logout: () => request('/auth/logout', {
        method: 'POST'
    }),

    getCurrentUser: () => request('/auth/user')
};

// API des évaluations
export const evaluationsApi = {
    getAll: () => request('/evaluations'),

    getById: (id) => request(`/evaluations/${id}`),

    create: (evaluationData) => request('/evaluations', {
        method: 'POST',
        body: JSON.stringify(evaluationData)
    }),

    update: (id, evaluationData) => request(`/evaluations/${id}`, {
        method: 'PUT',
        body: JSON.stringify(evaluationData)
    }),

    delete: (id) => request(`/evaluations/${id}`, {
        method: 'DELETE'
    }),

    getStats: () => request('/evaluations/stats')
};

// API des stagiaires
export const stagiairesApi = {
    getAll: () => request('/stagiaires'),

    getById: (id) => request(`/stagiaires/${id}`),

    create: (stagiaireData) => request('/stagiaires', {
        method: 'POST',
        body: JSON.stringify(stagiaireData)
    }),

    update: (id, stagiaireData) => request(`/stagiaires/${id}`, {
        method: 'PUT',
        body: JSON.stringify(stagiaireData)
    }),

    delete: (id) => request(`/stagiaires/${id}`, {
        method: 'DELETE'
    })
};

// Exporter les APIs
export default {
    auth: authApi,
    evaluations: evaluationsApi,
    stagiaires: stagiairesApi
};

// Fonction de connexion
const login = async (email, password) => {
    // Simuler une requête API
    // En production, cela serait remplacé par un vrai appel API
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            // Simulation de validation (à remplacer par un appel API réel)
            if (email === 'admin@example.com' && password === 'password') {
                const user = {
                    id: 1,
                    name: 'Admin User',
                    email: 'admin@example.com',
                    role: 'admin'
                };
                setCurrentUser(user);
                localStorage.setItem('currentUser', JSON.stringify(user));
                resolve(user);
            } else if (email === 'tuteur@example.com' && password === 'password') {
                const user = {
                    id: 2,
                    name: 'Tuteur Example',
                    email: 'tuteur@example.com',
                    role: 'tutor'
                };
                setCurrentUser(user);
                localStorage.setItem('currentUser', JSON.stringify(user));
                resolve(user);
            } else {
                reject(new Error('Identifiants invalides'));
            }
        }, 1000);
    });
};

// Fonction de déconnexion
const logout = () => {
    setCurrentUser(null);
    localStorage.removeItem('currentUser');
};

const value = {
    currentUser,
    loading,
    login,
    logout
};

return (
    <AuthContext.Provider value={value}>
        {!loading && children}
    </AuthContext.Provider>
);

