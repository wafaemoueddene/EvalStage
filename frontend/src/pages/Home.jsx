import React, { useState } from 'react';
import { Camera, ClipboardCheck, BarChart2, Users, ArrowRight, LogIn } from 'lucide-react';
import './Home.css'; // Importation du fichier CSS
import { Link } from 'react-router-dom'; // Import Link from react-router-dom
import { useNavigate } from 'react-router-dom';

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
            icon: <ClipboardCheck size={48} />,
            title: "Évaluations Simplifiées",
            description: "Les tuteurs peuvent remplir rapidement des évaluations structurées pour chaque stagiaire. Facile, rapide, efficace."
        },
        {
            id: 2,
            icon: <BarChart2 size={48} />,
            title: "Suivi des Progrès",
            description: "Suivez les progrès des stagiaires à travers un tableau de bord interactif et des graphiques clairs."
        },
        {
            id: 3,
            icon: <Users size={48} />,
            title: "Gestion Centralisée",
            description: "Administrez stagiaires, tuteurs et évaluations de manière intuitive et centralisée."
        }
    ];

    return (
        <div className="min-h-screen">
            {/* Header avec nav */}
            <header>
                <div className="container">
                    <div className="logo-container">
                        <div className="logo-icon">
                        </div>
                        <span className="logo-text">FSSM Stage</span>
                    </div>
                    <nav>
                        <ul>
                            <li><a href="#">Accueil</a></li>
                            <li><a href="#">À propos</a></li>
                            <li><a href="#">Contact</a></li>
                        </ul>
                    </nav>
                </div>
            </header>

            {/* Hero Section */}
            <section className="hero">
                <div className="container">
                    <div className="hero-content">
                        <h1>Système d'Évaluation des Stages</h1>
                        <p>
                            Une plateforme innovante pour l'évaluation efficace et précise des stagiaires
                            par leurs tuteurs professionnels.
                        </p>
                        <div className="button-container">
                            <button className="btn btn-white" onClick={handleLoginClick}>
                                <LogIn className="btn-icon" size={20} />
                                Connexion
                            </button>
                            <button className="btn btn-dark">
                                <Link to="/tutor-evaluation" className="btn-link">
                                    <ClipboardCheck className="btn-icon" size={20} />
                                    Accéder au Formulaire du Tuteur
                                </Link>
                            </button>
                        </div>
                    </div>
                </div>
            </section>

            {/* Features Section */}
            <section className="features">
                <div className="container">
                    <h2>Fonctionnalités Principales</h2>
                    <div className="features-grid">
                        {features.map(feature => (
                            <div
                                key={feature.id}
                                className={`feature-card ${hoverCard === feature.id ? 'hover' : ''}`}
                                onMouseEnter={() => setHoverCard(feature.id)}
                                onMouseLeave={() => setHoverCard(null)}
                            >
                                <div className="feature-icon">
                                    {feature.icon}
                                </div>
                                <h3>{feature.title}</h3>
                                <p>{feature.description}</p>
                            </div>
                        ))}
                    </div>
                </div>
            </section>

            {/* Call to Action */}
            <section className="cta">
                <div className="container">
                    <h2>Prêt à commencer ?</h2>
                    <p>
                        Notre système d'évaluation de stages facilite le suivi des progrès et
                        assure une communication fluide entre l'administration, les tuteurs et les stagiaires.
                    </p>
                    <button className="btn btn-primary">
                        Découvrir la plateforme
                        <ArrowRight className="ml-2" size={20} />
                    </button>
                </div>
            </section>

            {/* Footer */}
            <footer>
                <div className="container">
                    <div className="footer-grid">
                        <div>
                            <h3>FSSM Stage</h3>
                            <p>
                                Un outil développé pour la Faculté des Sciences Semlalia Marrakech
                                pour l'évaluation efficace des stages.
                            </p>
                        </div>
                        <div>
                            <h3>Liens Rapides</h3>
                            <ul>
                                <li><a href="#">Accueil</a></li>
                                <li><a href="#">Connexion</a></li>
                                <li><a href="#">Formulaire d'évaluation</a></li>
                                <li><a href="#">Contact</a></li>
                            </ul>
                        </div>
                        <div>
                            <h3>Contact</h3>
                            <p>
                                Faculté des Sciences Semlalia<br />
                                Marrakech, Maroc<br />
                                contact@fssm-stage.ma
                            </p>
                        </div>
                    </div>
                    <div className="footer-bottom">
                        <p>&copy; {new Date().getFullYear()} FSSM Stage. Tous droits réservés.</p>
                    </div>
                </div>
            </footer>
        </div>
    );
}