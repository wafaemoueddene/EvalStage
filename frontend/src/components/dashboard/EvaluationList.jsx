// components/dashboard/EvaluationList.jsx
import React from 'react';
import { Link } from 'react-router-dom';
import './EvaluationList.css';

const EvaluationList = ({ evaluations }) => {
    if (!evaluations || evaluations.length === 0) {
        return (
            <div className="text-center p-4">
                <i className="fas fa-clipboard-list fa-3x text-muted mb-3"></i>
                <p className="lead">Aucune évaluation disponible pour le moment.</p>
            </div>
        );
    }

    return (
        <div className="evaluation-list">
            <div className="table-responsive">
                <table className="table table-hover">
                    <thead>
                    <tr>
                        <th>Stagiaire</th>
                        <th>Entreprise</th>
                        <th>Tuteur</th>
                        <th>Période</th>
                        <th>Note</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {evaluations.map((evaluation) => {
                        // Calculer la note moyenne
                        const ratings = Object.values(evaluation.competencesRatings);
                        const averageRating = ratings.length > 0
                            ? ratings.reduce((sum, rating) => sum + rating, 0) / ratings.length
                            : 0;

                        return (
                            <tr key={evaluation.id}>
                                <td>
                                    {evaluation.stagiaire.prenom} {evaluation.stagiaire.nom}
                                </td>
                                <td>{evaluation.entreprise}</td>
                                <td>
                                    {evaluation.tuteurPrenom} {evaluation.tuteurNom}
                                </td>
                                <td>
                                    {new Date(evaluation.dateDebut).toLocaleDateString()} - {new Date(evaluation.dateFin).toLocaleDateString()}
                                </td>
                                <td>
                                    <div className="rating-stars">
                                        {[...Array(5)].map((_, i) => (
                                            <i
                                                key={i}
                                                className={`fas fa-star ${i < Math.round(averageRating) ? 'filled' : ''}`}
                                            ></i>
                                        ))}
                                        <span className="ms-1">({averageRating.toFixed(1)})</span>
                                    </div>
                                </td>
                                <td>
                                    <Link to={`/admin/evaluations/${evaluation.id}`} className="btn btn-sm btn-primary me-1">
                                        <i className="fas fa-eye"></i>
                                    </Link>
                                    <Link to={`/admin/evaluations/edit/${evaluation.id}`} className="btn btn-sm btn-warning me-1">
                                        <i className="fas fa-edit"></i>
                                    </Link>
                                    <button className="btn btn-sm btn-danger">
                                        <i className="fas fa-trash"></i>
                                    </button>
                                </td>
                            </tr>
                        );
                    })}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default EvaluationList;