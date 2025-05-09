import React from 'react';
import { Pie } from 'recharts';
import './EvaluationStats.css';

const EvaluationStats = ({ stats }) => {
    // Données pour le graphique camembert
    const chartData = [
        { name: 'Complétées', value: stats.completedEvaluations, fill: '#2ecc71' },
        { name: 'En attente', value: stats.pendingEvaluations, fill: '#f39c12' }
    ];

    return (
        <div className="evaluation-stats">
            <div className="chart-container mb-4">
                <Pie
                    data={chartData}
                    cx="50%"
                    cy="50%"
                    labelLine={false}
                    outerRadius={80}
                    dataKey="value"
                    label={({ name, percent }) => `${name} ${(percent * 100).toFixed(0)}%`}
                />
            </div>

            <div className="stats-details">
                <div className="stat-item">
                    <div className="stat-label">Taux de complétion</div>
                    <div className="stat-value">
                        {stats.totalEvaluations > 0
                            ? ((stats.completedEvaluations / stats.totalEvaluations) * 100).toFixed(1)
                            : 0}%
                    </div>
                    <div className="progress">
                        <div
                            className="progress-bar bg-success"
                            role="progressbar"
                            style={{ width: `${stats.totalEvaluations > 0 ? (stats.completedEvaluations / stats.totalEvaluations) * 100 : 0}%` }}
                        ></div>
                    </div>
                </div>

                <div className="stat-item">
                    <div className="stat-label">Satisfaction moyenne</div>
                    <div className="stat-value">
                        {stats.averageRating.toFixed(1)}/5
                    </div>
                    <div className="progress">
                        <div
                            className="progress-bar"
                            role="progressbar"
                            style={{
                                width: `${(stats.averageRating / 5) * 100}%`,
                                backgroundColor: `hsl(${(stats.averageRating / 5) * 120}, 70%, 45%)`
                            }}
                        ></div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default EvaluationStats;