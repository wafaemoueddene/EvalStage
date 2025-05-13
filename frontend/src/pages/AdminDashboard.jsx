import React, { useState, useEffect, useCallback } from 'react';
import {
    BarChart,
    Bar,
    XAxis,
    YAxis,
    Tooltip,
    Legend,
    ResponsiveContainer,
    PieChart,
    Pie,
    Cell
} from 'recharts';
import {
    Users, Briefcase, UserCheck, AlertTriangle, RefreshCw
} from 'lucide-react';

// Fonction pour formater les dates
const formatDate = (dateString) => {
    if (!dateString) return '';
    const date = new Date(dateString);
    return date.toLocaleDateString('fr-FR');
};

// Composant principal du tableau de bord administrateur
const AdminDashboard = () => {
    // États pour stocker les différentes données du tableau de bord
    const [stats, setStats] = useState({
        totalStagiaires: 0,
        totalTuteurs: 0,
        totalEntreprises: 0
    });

    const [competencesData, setCompetencesData] = useState([]);
    const [evaluationData, setEvaluationData] = useState([]);
    const [evaluationCategoriesData, setEvaluationCategoriesData] = useState({});
    const [entreprisesData, setEntreprisesData] = useState([]);
    const [stagesData, setStagesData] = useState([]);
    const [refreshKey, setRefreshKey] = useState(0); // Clé pour forcer le rafraîchissement
    const [competenceStats, setCompetenceStats] = useState([]);
    const [evaluationStats, setEvaluationStats] = useState([]);
    const [competenceCategoryValues, setCompetenceCategoryValues] = useState([]);
    const [evaluationCategoryValues, setEvaluationCategoryValues] = useState([]);
    const [competenceCategorieDetails, setCompetenceCategorieDetails] = useState([]);
    const [evaluationCategorieDetails, setEvaluationCategorieDetails] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Couleurs pour les graphiques
    const COLORS = ['#4f46e5', '#8884d8', '#9333ea', '#6366f1', '#4338ca'];

    // Fonction pour formater le nom des catégories (tronquer si trop long)
    const formatCategoryName = (name) => {
        if (!name) return '';
        return name.length > 20 ? name.substring(0, 17) + '...' : name;
    };

    // Filtrer les catégories de compétences pour le camembert (prendre les 5 principales)
    const filteredCompetenceCategories = competenceCategoryValues
        .sort((a, b) => b.value - a.value)
        .slice(0, 5);

    // Fonction pour gérer les appels API avec gestion d'erreur
    const fetchData = useCallback(async (endpoint) => {
        try {
            const BASE_URL = 'http://localhost:9091';
            const response = await fetch(`${BASE_URL}${endpoint}`, {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });

            if (!response.ok) {
                const errorText = await response.text();
                console.error(`Erreur détaillée: ${errorText}`);
                throw new Error(`Erreur réseau: ${response.status} ${response.statusText} - ${errorText}`);
            }

            return await response.json();
        } catch (error) {
            console.error(`Erreur complète lors de l'appel à ${endpoint}:`, error);
            throw error;
        }
    }, []);

    // Fonction principale pour charger toutes les données du tableau de bord
    const loadDashboardData = useCallback(async () => {
        setLoading(true);
        setError(null);

        try {
            const [
                statsData,
                competencesData,
                evaluationData,
                evaluationCategoriesData,
                stagesData,
                entreprisesData,
                evaluationCategoryValuesData,
                competenceCategoryValuesData
            ] = await Promise.all([
                fetchData('/api/admin/dashboard/stats'),
                fetchData('/api/admin/dashboard/competences'),
                fetchData('/api/admin/dashboard/evaluations'),
                fetchData('/api/admin/dashboard/evaluation-categories'),
                fetchData('/api/admin/dashboard/stages'),
                fetchData('/api/admin/dashboard/entreprises'),
                fetchData('/api/admin/dashboard/evaluation-category-values'),
                fetchData('/api/admin/dashboard/competence-category-values')
            ]);

            // Mise à jour des états individuels
            setStats(statsData);
            setCompetencesData(competencesData);
            setEvaluationData(evaluationData);
            setEvaluationCategoriesData(evaluationCategoriesData);
            setStagesData(stagesData);
            setEntreprisesData(entreprisesData);
            setEvaluationCategoryValues(evaluationCategoryValuesData);
            setCompetenceCategoryValues(competenceCategoryValuesData);

            // Générer des statistiques dérivées pour les autres graphiques
            setCompetenceStats(generateCompetenceStats(competencesData));
            setEvaluationStats(generateEvaluationStats(evaluationData));
            setCompetenceCategorieDetails(generateCompetenceCategorieDetails(competenceCategoryValuesData));
            setEvaluationCategorieDetails(generateEvaluationCategorieDetails(evaluationCategoryValuesData));

        } catch (error) {
            setError(`Une erreur est survenue: ${error.message}`);
        } finally {
            setLoading(false);
        }
    }, [fetchData]);

    // Fonction pour générer des statistiques sur les compétences
    const generateCompetenceStats = (competencesData) => {
        if (!competencesData || competencesData.length === 0) return [];

        // Créer une copie pour éviter de modifier l'original
        return competencesData.map(item => ({
            name: item.name,
            value: item.value
        }));
    };

    // Fonction pour générer des statistiques sur les évaluations
    const generateEvaluationStats = (evaluationData) => {
        if (!evaluationData || evaluationData.length === 0) return [];

        // Créer une copie pour éviter de modifier l'original
        return evaluationData.map(item => ({
            name: item.name,
            value: item.value
        }));
    };

    // Fonction pour générer des détails de catégories de compétences
    const generateCompetenceCategorieDetails = (categoryData) => {
        if (!categoryData || categoryData.length === 0) return [];

        return categoryData.map(item => ({
            intituleCategorie: item.category,
            valeurMoyenne: item.value
        }));
    };

    // Fonction pour générer des détails de catégories d'évaluation
    const generateEvaluationCategorieDetails = (categoryData) => {
        if (!categoryData || categoryData.length === 0) return [];

        return categoryData.map(item => ({
            categorie: item.category,
            valeurMoyenne: item.value
        }));
    };

    // Charger les données au montage du composant et configurer un intervalle de rafraîchissement
    useEffect(() => {
        loadDashboardData();

        // Mettre à jour les données toutes les 5 minutes
        const intervalId = setInterval(() => {
            loadDashboardData();
        }, 300000); // 300000 ms = 5 minutes

        // Nettoyer l'intervalle lors du démontage du composant
        return () => clearInterval(intervalId);
    }, [loadDashboardData, refreshKey]); // Rafraîchir les données quand refreshKey change

    // Fonction pour rafraîchir manuellement les données
    const handleRefresh = () => {
        setRefreshKey(prevKey => prevKey + 1);
    };

    // Composant pour afficher un message d'erreur
    const ErrorDisplay = () => (
        <div className="bg-red-50 border-l-4 border-red-500 p-4 rounded shadow mb-6">
            <div className="flex items-center">
                <AlertTriangle className="text-red-500 mr-3" size={24} />
                <div>
                    <h3 className="text-lg font-medium text-red-800">Erreur de chargement</h3>
                    <p className="text-red-700">{error}</p>
                    <button
                        onClick={loadDashboardData}
                        className="mt-2 px-4 py-2 bg-red-600 text-white rounded hover:bg-red-700 transition"
                    >
                        Réessayer
                    </button>
                </div>
            </div>
        </div>
    );

    // Composant pour les cartes statistiques
    const StatCard = ({ title, value, icon, color }) => (
        <div className={`bg-white rounded-lg shadow p-4 border-l-4 border-${color}-500`}>
            <div className="flex items-center justify-between">
                <div>
                    <p className={`text-xs font-medium text-${color}-600 uppercase`}>{title}</p>
                    <div className="flex items-center">
                        {loading ? (
                            <div className="h-8 w-16 bg-gray-200 animate-pulse rounded"></div>
                        ) : (
                            <p className="text-2xl font-bold text-gray-800">{value}</p>
                        )}
                    </div>
                </div>
                <div className={`bg-${color}-100 p-2 rounded-full`}>
                    {icon}
                </div>
            </div>
        </div>
    );

    // Rendu en cas d'erreur
    if (error && !loading) {
        return (
            <div className="p-6 bg-gray-50 min-h-screen">
                <ErrorDisplay />
            </div>
        );
    }

    return (
        <div className="p-6 bg-gray-50 min-h-screen">
            {/* En-tête du tableau de bord */}
            <div className="mb-6 flex items-center justify-between">
                <h1 className="text-2xl font-bold text-gray-800">
                    Tableau de Bord Administratif
                </h1>
                <div className="flex items-center gap-4">
                    <button
                        onClick={handleRefresh}
                        disabled={loading}
                        className={`px-3 py-2 bg-indigo-100 text-indigo-700 rounded-md hover:bg-indigo-200 transition-colors flex items-center ${
                            loading ? 'opacity-50 cursor-not-allowed' : ''
                        }`}
                    >
                        {loading ? (
                            <>
                                <div className="animate-spin h-4 w-4 border-2 border-indigo-600 border-t-transparent rounded-full mr-2" />
                                Chargement...
                            </>
                        ) : (
                            <>
                                <RefreshCw className="h-4 w-4 mr-1" />
                                Actualiser
                            </>
                        )}
                    </button>
                    <div className="bg-white shadow rounded-lg px-3 py-2 flex items-center text-sm">
                        <span className="font-medium text-gray-600">Date:</span>
                        <span className="ml-2 text-gray-800">{new Date().toLocaleDateString('fr-FR')}</span>
                    </div>
                </div>
            </div>

            {/* Statistiques complémentaires */}
            <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-6">
                <StatCard
                    title="Stagiaires"
                    value={stats.totalStagiaires}
                    icon={<Users className="text-indigo-600" size={20} />}
                    color="indigo"
                />
                <StatCard
                    title="Tuteurs"
                    value={stats.totalTuteurs}
                    icon={<UserCheck className="text-indigo-600" size={20} />}
                    color="indigo"
                />
                <StatCard
                    title="Entreprises"
                    value={stats.totalEntreprises}
                    icon={<Briefcase className="text-indigo-600" size={20} />}
                    color="indigo"
                />
            </div>

            {/* Graphiques en deux colonnes */}
            <div className="grid grid-cols-1 lg:grid-cols-2 gap-6 mb-6">
                {/* Graphique des catégories d'évaluation */}
                <div className="bg-white rounded-lg shadow p-4">
                    <h2 className="text-lg font-medium text-gray-800 mb-4">Répartition par Catégorie d'Évaluation</h2>
                    {loading ? (
                        <div className="flex justify-center items-center h-64">
                            <div className="animate-spin rounded-full h-10 w-10 border-b-2 border-indigo-600"></div>
                        </div>
                    ) : Object.keys(evaluationCategoriesData).length > 0 ? (
                        <ResponsiveContainer width="100%" height={300}>
                            <BarChart data={Object.entries(evaluationCategoriesData).map(([name, value]) => ({ name, value }))}>
                                <XAxis dataKey="name" />
                                <YAxis />
                                <Tooltip />
                                <Legend />
                                <Bar dataKey="value" fill="#4f46e5" />
                            </BarChart>
                        </ResponsiveContainer>
                    ) : (
                        <div className="flex justify-center items-center h-64 text-gray-500">
                            Aucune donnée d'évaluation disponible
                        </div>
                    )}
                </div>

                {/* Graphique des catégories de compétences */}
                <div className="bg-white rounded-lg shadow p-4">
                    <h2 className="text-lg font-medium text-gray-800 mb-4">Moyenne des Compétences par Catégorie</h2>
                    {loading ? (
                        <div className="flex justify-center items-center h-64">
                            <div className="animate-spin rounded-full h-10 w-10 border-b-2 border-indigo-600"></div>
                        </div>
                    ) : competencesData && competencesData.length > 0 ? (
                        <ResponsiveContainer width="100%" height={300}>
                            <PieChart>
                                <Pie
                                    data={competencesData}
                                    cx="50%"
                                    cy="50%"
                                    labelLine={false}
                                    outerRadius={80}
                                    fill="#8884d8"
                                    dataKey="value"
                                    label={({name, value}) => `${name}: ${value.toFixed(1)}`}
                                >
                                    {competencesData.map((entry, index) => (
                                        <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                                    ))}
                                </Pie>
                                <Tooltip formatter={(value) => value.toFixed(1)} />
                            </PieChart>
                        </ResponsiveContainer>
                    ) : (
                        <div className="flex justify-center items-center h-64 text-gray-500">
                            Aucune donnée de compétence disponible
                        </div>
                    )}
                </div>
            </div>

            {/* Tableau des stages avec informations détaillées */}
            <div className="bg-white rounded-lg shadow p-4 mb-6">
                <h2 className="text-lg font-medium text-gray-800 mb-4">Détails des Stages</h2>
                {loading ? (
                    <div className="flex justify-center items-center h-64">
                        <div className="animate-spin rounded-full h-10 w-10 border-b-2 border-indigo-600"></div>
                    </div>
                ) : stagesData && stagesData.length > 0 ? (
                    <div className="overflow-x-auto">
                        <table className="w-full table-auto border-collapse">
                            <thead>
                            <tr className="bg-blue-100">
                                <th className="px-4 py-2 text-left border border-gray-300">Entreprise</th>
                                <th className="px-4 py-2 text-left border border-gray-300">Objectif</th>
                                <th className="px-4 py-2 text-left border border-gray-300">Description</th>
                                <th className="px-4 py-2 text-left border border-gray-300">Stagiaire</th>
                                <th className="px-4 py-2 text-left border border-gray-300">Email Stagiaire</th>
                                <th className="px-4 py-2 text-left border border-gray-300">Tuteur</th>
                                <th className="px-4 py-2 text-left border border-gray-300">Email Tuteur</th>
                                <th className="px-4 py-2 text-left border border-gray-300">Période</th>
                            </tr>
                            </thead>
                            <tbody>
                            {stagesData.map((stage, index) => (
                                <tr key={index} className={index % 2 === 0 ? 'bg-white' : 'bg-gray-100'}>
                                    <td className="px-4 py-2 border border-gray-300">{stage.entreprise || 'N/A'}</td>
                                    <td className="px-4 py-2 border border-gray-300">{stage.objectif || 'N/A'}</td>
                                    <td className="px-4 py-2 border border-gray-300">{stage.description || 'N/A'}</td>
                                    <td className="px-4 py-2 border border-gray-300">
                                        {stage.stagiaireNom && stage.stagiairePrenom
                                            ? `${stage.stagiaireNom} ${stage.stagiairePrenom}`
                                            : 'N/A'}
                                    </td>
                                    <td className="px-4 py-2 border border-gray-300">{stage.stagiaireEmail || 'N/A'}</td>
                                    <td className="px-4 py-2 border border-gray-300">
                                        {stage.tuteurNom && stage.tuteurPrenom
                                            ? `${stage.tuteurNom} ${stage.tuteurPrenom}`
                                            : 'N/A'}
                                    </td>
                                    <td className="px-4 py-2 border border-gray-300">{stage.tuteurEmail || 'N/A'}</td>
                                    <td className="px-4 py-2 border border-gray-300">
                                        {stage.dateDebut && stage.dateFin
                                            ? `${formatDate(stage.dateDebut)} - ${formatDate(stage.dateFin)}`
                                            : 'N/A'}
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                ) : (
                    <div className="flex justify-center items-center h-64 text-gray-500">
                        Aucune donnée de stage disponible
                    </div>
                )}
            </div>
        </div>
    );
};

export default AdminDashboard;