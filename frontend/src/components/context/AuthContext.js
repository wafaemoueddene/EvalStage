import React, { createContext, useContext, useState, useEffect } from 'react';

const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

// Provider du contexte
export const AuthProvider = ({ children }) => {
    const [currentUser, setCurrentUser] = useState(null);
    const [loading, setLoading] = useState(true);

    // Simuler la vérification de l'authentification au chargement
    useEffect(() => {
        // Vérifier si l'utilisateur est déjà connecté (localStorage ou cookie)
        const checkAuth = () => {
            const savedUser = localStorage.getItem('currentUser');
            if (savedUser) {
                setCurrentUser(JSON.parse(savedUser));
            }
            setLoading(false);
        };

        checkAuth();
    }, []);

    // Soumettre une nouvelle évaluation
    const submitEvaluation = useCallback(async (evaluationData) => {
        // Simuler un délai de soumission
        await new Promise(resolve => setTimeout(resolve, 1500));

        // Dans une application réelle, ce serait un appel POST à l'API
        // Pour l'instant, nous ajoutons simplement à notre état local
        const newEvaluation = {
            id: Date.now(), // ID temporaire
            ...evaluationData,
            // Récupérer les informations du stagiaire à partir de son ID
            stagiaire: { id: evaluationData.stagiaireId, nom: 'Nom du stagiaire', prenom: 'Prénom du stagiaire' }
        };

        setEvaluations(prev => [...prev, newEvaluation]);
        return newEvaluation;
    }, []);

    const value = {
        evaluations,
        fetchEvaluations,
        fetchStats,
        fetchStagiaires,
        fetchCompetences,
        submitEvaluation
    };

    return (
        <EvaluationContext.Provider value={value}>
            {children}
        </EvaluationContext.Provider>
    );
};

