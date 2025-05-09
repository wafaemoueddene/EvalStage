import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import './App.css';
import TutorEvaluation from './pages/TutorEvaluation';
import AdminDashboard from './pages/AdminDashboard';
import { EvaluationProvider } from './components/context/EvaluationContext';
import EvaluationForm from './components/forms/EvaluationForm';
function App() {
    return (
        <EvaluationProvider>
            <div className="app-container">
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/tutor-evaluation" element={<TutorEvaluation />} />
                    <Route path="/evaluation-form" element={<EvaluationForm />} />
                    <Route path="/admin-dashboard" element={<AdminDashboard />} />
                </Routes>
            </div>
        </EvaluationProvider>
    );
}

export default App;