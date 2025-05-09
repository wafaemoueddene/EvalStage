
// pages/AdminDashboard.jsx
import React, {useState, useEffect} from 'react';
import EvaluationList from '../components/dashboard/EvaluationList';
import EvaluationStats from '../components/dashboard/EvaluationStats';
import { useEvaluation } from '../components/context/EvaluationContext';

const AdminDashboard = () => {
    const {evaluations, fetchEvaluations} = useEvaluation();
    const [stats, setStats] = useState({
        totalEvaluations: 0,
        pendingEvaluations: 0,
        completedEvaluations: 0,
        averageRating: 0
    });


    // useEffect(() => {
    //     const loadData = async () => {
    //         setLoading(true);
    //         await fetchEvaluations();
    //         const statsData = await fetchStats();
    //         setStats(statsData);
    //         setLoading(false);
    //     };
    //
    //     loadData();
    // }, [fetchEvaluations, fetchStats]);



    return (
        <div className="admin-dashboard">
            <h1 className="dashboard-title mb-4">
                <i className="fas fa-tachometer-alt me-2"></i>
                Tableau de Bord Administratif
            </h1>

            <div className="row">
                <div className="col-xl-3 col-md-6 mb-4">
                    <div className="card stats-card border-left-primary h-100 py-2">
                        <div className="card-body">
                            <div className="row no-gutters align-items-center">
                                <div className="col mr-2">
                                    <div className="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                        Total des Évaluations
                                    </div>
                                    <div
                                        className="h5 mb-0 font-weight-bold text-gray-800">{stats.totalEvaluations}</div>
                                </div>
                                <div className="col-auto">
                                    <i className="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="col-xl-3 col-md-6 mb-4">
                    <div className="card stats-card border-left-success h-100 py-2">
                        <div className="card-body">
                            <div className="row no-gutters align-items-center">
                                <div className="col mr-2">
                                    <div className="text-xs font-weight-bold text-success text-uppercase mb-1">
                                        Évaluations Complétées
                                    </div>
                                    <div
                                        className="h5 mb-0 font-weight-bold text-gray-800">{stats.completedEvaluations}</div>
                                </div>
                                <div className="col-auto">
                                    <i className="fas fa-check-circle fa-2x text-gray-300"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="col-xl-3 col-md-6 mb-4">
                    <div className="card stats-card border-left-warning h-100 py-2">
                        <div className="card-body">
                            <div className="row no-gutters align-items-center">
                                <div className="col mr-2">
                                    <div className="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                        Évaluations en Attente
                                    </div>
                                    <div
                                        className="h5 mb-0 font-weight-bold text-gray-800">{stats.pendingEvaluations}</div>
                                </div>
                                <div className="col-auto">
                                    <i className="fas fa-clock fa-2x text-gray-300"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="col-xl-3 col-md-6 mb-4">
                    <div className="card stats-card border-left-info h-100 py-2">
                        <div className="card-body">
                            <div className="row no-gutters align-items-center">
                                <div className="col mr-2">
                                    <div className="text-xs font-weight-bold text-info text-uppercase mb-1">
                                        Note Moyenne
                                    </div>
                                    <div className="row no-gutters align-items-center">
                                        <div className="col-auto">
                                            <div
                                                className="h5 mb-0 mr-3 font-weight-bold text-gray-800">{stats.averageRating.toFixed(1)}/5
                                            </div>
                                        </div>
                                        <div className="col">
                                            <div className="progress progress-sm mr-2">
                                                <div className="progress-bar bg-info" role="progressbar"
                                                     style={{width: `${(stats.averageRating / 5) * 100}%`}}></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="col-auto">
                                    <i className="fas fa-star fa-2x text-gray-300"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div className="row">
                <div className="col-lg-8 mb-4">
                    <div className="card shadow mb-4">
                        <div
                            className="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                            <h6 className="m-0 font-weight-bold text-primary">Dernières Évaluations</h6>
                            <a href="/admin/evaluations" className="btn btn-sm btn-primary">
                                Voir tout
                            </a>
                        </div>
                        <div className="card-body">
                            <EvaluationList evaluations={evaluations.slice(0, 5)}/>
                        </div>
                    </div>
                </div>

                <div className="col-lg-4 mb-4">
                    <div className="card shadow mb-4">
                        <div className="card-header py-3">
                            <h6 className="m-0 font-weight-bold text-primary">Statistiques d'Évaluation</h6>
                        </div>
                        <div className="card-body">
                            <EvaluationStats stats={stats}/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default AdminDashboard;
