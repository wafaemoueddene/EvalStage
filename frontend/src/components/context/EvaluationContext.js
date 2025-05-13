import React, { createContext, useContext, useState, useCallback } from 'react';

// Create the context
const EvaluationContext = createContext();

// Custom hook to use the context
export const useEvaluation = () => useContext(EvaluationContext);

// Provider for the context
export const EvaluationProvider = ({ children }) => {
    const [evaluations, setEvaluations] = useState([]);

    return (
        <EvaluationContext.Provider value={{ evaluations, setEvaluations }}>
            {children}
        </EvaluationContext.Provider>
    );
};
