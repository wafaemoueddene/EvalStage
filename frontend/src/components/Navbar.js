import React from "react";

const Navbar = () => {
    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
            <div className="container">
                <a className="navbar-brand" href="/">Évaluation de Stage</a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <a className="nav-link active" href="/">Accueil</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/dashboard">Dashboard</a>
                        </li>
                        <li className="nav-item dropdown">
                            <a className="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                                Gestion
                            </a>
                            <ul className="dropdown-menu">
                                <li><a className="dropdown-item" href="/categories-view">Catégories</a></li>
                                <li><a className="dropdown-item" href="/competences-view">Compétences</a></li>
                                <li><a className="dropdown-item" href="/evaluations-view">Évaluations</a></li>
                                <li><a className="dropdown-item" href="/appreciations-view">Appréciations</a></li>
                                <li><a className="dropdown-item" href="/periodes-view">Périodes</a></li>
                                <li><a className="dropdown-item" href="/stages-view">Stages</a></li>
                                <li><a className="dropdown-item" href="/stagiaires-view">Stagiaires</a></li>
                                <li><a className="dropdown-item" href="/tuteurs-view">Tuteurs</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    );
};

export default Navbar;