// pages/TutorEvaluation.jsx
import React from 'react';
import EvaluationForm from '../components/forms/EvaluationForm';
import './TutorEvaluation.css';

const TutorEvaluation = () => {
    return (
        <div className="container mt-4">
            <div className="evaluation-header mb-4">
                <h1 className="mb-3">Évaluation de Stage</h1>
                <p className="lead">Complétez le formulaire ci-dessous pour évaluer un stagiaire.</p>
            </div>
            <div className="card shadow-sm">
                <div className="card-body">
                    <EvaluationForm/>
                </div>
            </div>
        </div>
    );
};

export default TutorEvaluation;
