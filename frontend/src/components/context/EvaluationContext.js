import React, { createContext, useContext, useState, useCallback } from 'react';

// Create the context
const EvaluationContext = createContext();

// Custom hook to use the context
export const useEvaluation = () => useContext(EvaluationContext);

// Provider for the context
export const EvaluationProvider = ({ children }) => {
    const [evaluations, setEvaluations] = useState([]);

    const fetchEvaluations = useCallback(async () => {
        await new Promise(resolve => setTimeout(resolve, 800));
        const mockEvaluations = [
            { id: 1, stagiaire: { id: 1, nom: 'wafae', prenom: 'Marie' }, entreprise: 'Tech Solutions' },
            { id: 2, stagiaire: { id: 2, nom: 'Bernard', prenom: 'Lucas' }, entreprise: 'Digital Agency' }
        ];
        setEvaluations(mockEvaluations);
        return mockEvaluations;
    }, []);

    return (
        <EvaluationContext.Provider value={{ evaluations, setEvaluations, fetchEvaluations }}>
            {children}
        </EvaluationContext.Provider>
    );
};

const EvaluationForm = () => {
    const [noteEntreprise, setNoteEntreprise] = useState('');
    const [noteScientifique, setNoteScientifique] = useState('');
    const [generalFeedback, setGeneralFeedback] = useState('');

    return (
        <div className="max-w-4xl mx-auto bg-white shadow-lg rounded-lg p-8">
            <h2 className="text-2xl font-bold text-blue-600 mb-6">Formulaire d'Évaluation</h2>

            {/* Note globale compétences entreprise */}
            <div className="mb-6">
                <label className="block text-lg font-medium text-gray-700 mb-2">
                    Note globale compétences entreprise
                </label>
                <input
                    type="number"
                    value={noteEntreprise}
                    onChange={(e) => setNoteEntreprise(e.target.value)}
                    className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:outline-none"
                    min="0"
                    max="20"
                />
            </div>

            {/* Note globale compétences scientifiques */}
            <div className="mb-6">
                <label className="block text-lg font-medium text-gray-700 mb-2">
                    Note globale compétences scientifiques
                </label>
                <input
                    type="number"
                    value={noteScientifique}
                    onChange={(e) => setNoteScientifique(e.target.value)}
                    className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:outline-none"
                    min="0"
                    max="20"
                />
            </div>


            {/* Avis général sur le stagiaire */}
            <div className="mb-6">
                <label className="block text-lg font-medium text-gray-700 mb-2">
                    Avis général sur le stagiaire
                </label>
                <textarea
                    value={generalFeedback}
                    onChange={(e) => setGeneralFeedback(e.target.value)}
                    className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:outline-none"
                    rows="4"
                    placeholder="Donnez votre avis général sur le stagiaire..."
                ></textarea>
            </div>

            {/* Bouton d'envoi */}
            <div className="text-center">
                <button className="bg-blue-500 text-white py-2 px-6 rounded-lg hover:bg-blue-600 transition duration-200">
                    Envoyer l'évaluation
                </button>
            </div>
        </div>
    );
};

export default EvaluationForm;