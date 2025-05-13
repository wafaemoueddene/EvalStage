import React, { useState } from 'react';
import {
    ClipboardCheck,
    BarChart2,
    Users,
    ArrowRight,
    LogIn,
    Briefcase,
    ChevronRight
} from 'lucide-react';
import { Link, useNavigate } from 'react-router-dom';

// Composant principal de la page d'accueil
export default function Home() {
    const navigate = useNavigate();

    const handleLoginClick = () => {
        navigate('/admin-dashboard');
    };

    const [hoverCard, setHoverCard] = useState(null);

    const features = [
        {
            id: 1,
            icon: <ClipboardCheck size={32} className="text-indigo-600" />,
            title: "Évaluations Simplifiées",
            description: "Les tuteurs peuvent remplir rapidement des évaluations structurées pour chaque stagiaire. Facile, rapide, efficace."
        },
        {
            id: 2,
            icon: <BarChart2 size={32} className="text-indigo-600" />,
            title: "Suivi des Progrès",
            description: "Suivez les progrès des stagiaires à travers un tableau de bord interactif et des graphiques clairs."
        },
        {
            id: 3,
            icon: <Users size={32} className="text-indigo-600" />,
            title: "Gestion Centralisée",
            description: "Administrez stagiaires, tuteurs et évaluations de manière intuitive et centralisée."
        },
        {
            id: 4,
            icon: <Briefcase size={32} className="text-indigo-600" />,
            title: "Entreprises Partenaires",
            description: "Gérez facilement les relations avec les entreprises d'accueil et centralisez les informations."
        }
    ];

    return (
        <div className="min-h-screen bg-gray-50">
            {/* Header avec nav */}
            <header className="bg-white shadow-sm">
                <div className="container mx-auto px-4 py-4 flex justify-between items-center">
                    <div className="flex items-center">
                        <div className="w-10 h-10 bg-indigo-600 rounded-lg flex items-center justify-center mr-3">
                            <ClipboardCheck className="text-white" size={24} />
                        </div>
                        <span className="text-xl font-bold text-indigo-700">FSSM Stage</span>
                    </div>
                    <nav>
                        <ul className="flex space-x-8">
                            <li><a href="#" className="text-indigo-900 font-medium hover:text-indigo-600 transition-colors">Accueil</a></li>
                            <li><a href="#" className="text-gray-600 hover:text-indigo-600 transition-colors">À propos</a></li>
                            <li><a href="#" className="text-gray-600 hover:text-indigo-600 transition-colors">Contact</a></li>
                        </ul>
                    </nav>
                </div>
            </header>

            {/* Hero Section */}
            <section className="bg-gradient-to-r from-indigo-700 to-indigo-900 text-white py-16">
                <div className="container mx-auto px-4">
                    <div className="max-w-3xl mx-auto text-center">
                        <h1 className="text-4xl md:text-5xl font-bold mb-6">Système d'Évaluation des Stages</h1>
                        <p className="text-lg md:text-xl mb-10 text-indigo-100">
                            Une plateforme innovante pour l'évaluation efficace et précise des stagiaires
                            par leurs tuteurs professionnels.
                        </p>
                        <div className="flex flex-col sm:flex-row gap-4 justify-center">
                            <button
                                className="px-6 py-3 bg-white text-indigo-700 rounded-lg font-medium flex items-center justify-center hover:bg-gray-100 transition shadow-lg"
                                onClick={handleLoginClick}
                            >
                                <LogIn className="mr-2" size={20} />
                                Connexion Administration
                            </button>
                            <Link
                                to="/tutor-evaluation"
                                className="px-6 py-3 bg-indigo-800 text-white border border-indigo-300 rounded-lg font-medium flex items-center justify-center hover:bg-indigo-700 transition shadow-lg"
                            >
                                <ClipboardCheck className="mr-2" size={20} />
                                Formulaire du Tuteur
                            </Link>
                        </div>
                    </div>
                </div>
            </section>

            {/* Features Section with Cards */}
            <section className="py-16">
                <div className="container mx-auto px-4">
                    <h2 className="text-3xl font-bold text-center mb-12 text-gray-800">Fonctionnalités Principales</h2>
                    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
                        {features.map(feature => (
                            <div
                                key={feature.id}
                                className={`bg-white rounded-xl shadow-md p-6 transition-all duration-300 hover:shadow-lg ${
                                    hoverCard === feature.id ? 'transform -translate-y-2 border-l-4 border-indigo-500' : ''
                                }`}
                                onMouseEnter={() => setHoverCard(feature.id)}
                                onMouseLeave={() => setHoverCard(null)}
                            >
                                <div className="bg-indigo-100 p-3 rounded-lg inline-block mb-4">
                                    {feature.icon}
                                </div>
                                <h3 className="text-xl font-semibold mb-3 text-gray-800">{feature.title}</h3>
                                <p className="text-gray-600">{feature.description}</p>
                            </div>
                        ))}
                    </div>
                </div>
            </section>

            {/* Statistics Section */}
            <section className="bg-indigo-50 py-16">
                <div className="container mx-auto px-4">
                    <div className="text-center mb-12">
                        <h2 className="text-3xl font-bold text-gray-800 mb-4">Notre Plateforme en Chiffres</h2>
                        <p className="text-gray-600 max-w-2xl mx-auto">
                            Un outil complet qui facilite l'évaluation des stages et renforce la collaboration entre
                            l'université et les entreprises.
                        </p>
                    </div>

                    <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
                        <div className="bg-white p-6 rounded-xl shadow text-center">
                            <div className="inline-flex rounded-full bg-indigo-100 p-3 mb-4">
                                <Users className="h-8 w-8 text-indigo-600" />
                            </div>
                            <h3 className="text-4xl font-bold text-indigo-700 mb-2">500+</h3>
                            <p className="text-gray-600">Stagiaires Évalués</p>
                        </div>

                        <div className="bg-white p-6 rounded-xl shadow text-center">
                            <div className="inline-flex rounded-full bg-indigo-100 p-3 mb-4">
                                <Briefcase className="h-8 w-8 text-indigo-600" />
                            </div>
                            <h3 className="text-4xl font-bold text-indigo-700 mb-2">120+</h3>
                            <p className="text-gray-600">Entreprises Partenaires</p>
                        </div>

                        <div className="bg-white p-6 rounded-xl shadow text-center">
                            <div className="inline-flex rounded-full bg-indigo-100 p-3 mb-4">
                                <ClipboardCheck className="h-8 w-8 text-indigo-600" />
                            </div>
                            <h3 className="text-4xl font-bold text-indigo-700 mb-2">98%</h3>
                            <p className="text-gray-600">Taux de Satisfaction</p>
                        </div>
                    </div>
                </div>
            </section>

            {/* Call to Action */}
            <section className="py-16">
                <div className="container mx-auto px-4">
                    <div className="bg-gradient-to-r from-indigo-600 to-indigo-800 rounded-2xl shadow-xl p-8 md:p-12">
                        <div className="md:flex items-center justify-between">
                            <div className="mb-6 md:mb-0 md:mr-8">
                                <h2 className="text-2xl md:text-3xl font-bold text-white mb-4">Prêt à commencer ?</h2>
                                <p className="text-indigo-100 mb-0">
                                    Notre système d'évaluation de stages facilite le suivi des progrès et
                                    assure une communication fluide entre l'administration, les tuteurs et les stagiaires.
                                </p>
                            </div>
                            <div className="flex flex-shrink-0">
                                <button className="px-6 py-3 bg-white text-indigo-700 rounded-lg font-medium flex items-center shadow-lg hover:bg-indigo-50 transition">
                                    Découvrir la plateforme
                                    <ChevronRight className="ml-2" size={20} />
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            {/* Footer */}
            <footer className="bg-gray-800 text-white py-12">
                <div className="container mx-auto px-4">
                    <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
                        <div>
                            <div className="flex items-center mb-4">
                                <div className="w-8 h-8 bg-white rounded-md flex items-center justify-center mr-2">
                                    <ClipboardCheck className="text-indigo-700" size={18} />
                                </div>
                                <h3 className="text-xl font-bold">FSSM Stage</h3>
                            </div>
                            <p className="text-gray-300">
                                Un outil développé pour la Faculté des Sciences Semlalia Marrakech
                                pour l'évaluation efficace des stages.
                            </p>
                        </div>
                        <div>
                            <h3 className="text-lg font-semibold mb-4 border-b border-gray-700 pb-2">Liens Rapides</h3>
                            <ul className="space-y-2">
                                <li><a href="#" className="text-gray-300 hover:text-white transition-colors">Accueil</a></li>
                                <li><a href="#" className="text-gray-300 hover:text-white transition-colors">Connexion</a></li>
                                <li><a href="#" className="text-gray-300 hover:text-white transition-colors">Formulaire d'évaluation</a></li>
                                <li><a href="#" className="text-gray-300 hover:text-white transition-colors">Contact</a></li>
                            </ul>
                        </div>
                        <div>
                            <h3 className="text-lg font-semibold mb-4 border-b border-gray-700 pb-2">Contact</h3>
                            <address className="text-gray-300 not-italic">
                                Faculté des Sciences Semlalia<br />
                                Marrakech, Maroc<br />
                                <a href="mailto:contact@fssm-stage.ma" className="text-indigo-300 hover:text-indigo-200 transition-colors">contact@fssm-stage.ma</a>
                            </address>
                        </div>
                    </div>
                    <div className="border-t border-gray-700 mt-8 pt-8 text-center text-gray-400">
                        <p>&copy; {new Date().getFullYear()} FSSM Stage. Tous droits réservés.</p>
                    </div>
                </div>
            </footer>
        </div>
    );
}