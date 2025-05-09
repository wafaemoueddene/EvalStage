// components/layout/Sidebar.jsx
import React from 'react';
import {Link, useLocation} from 'react-router-dom';
import {useAuth} from '../../context/AuthContext';
import './Sidebar.css';

const Sidebar = ({collapsed}) => {
    const location = useLocation();
    const {currentUser} = useAuth();

    // Définir les routes en fonction du rôle d'utilisateur
    const adminRoutes = [
        {path: '/admin/dashboard', icon: 'fas fa-tachometer-alt', label: 'Dashboard'},
        {path: '/admin/evaluations', icon: 'fas fa-clipboard-list', label: 'Évaluations'},
        {path: '/admin/stagiaires', icon: 'fas fa-user-graduate', label: 'Stagiaires'},
        {path: '/admin/tuteurs', icon: 'fas fa-chalkboard-teacher', label: 'Tuteurs'},
        {path: '/admin/competences', icon: 'fas fa-cogs', label: 'Compétences'},
        {path: '/admin/settings', icon: 'fas fa-cog', label: 'Paramètres'}
    ];

    const tutorRoutes = [
        {path: '/tutor/dashboard', icon: 'fas fa-tachometer-alt', label: 'Dashboard'},
        {path: '/evaluation', icon: 'fas fa-edit', label: 'Nouvelle Évaluation'},
        {path: '/tutor/history', icon: 'fas fa-history', label: 'Historique'}
    ];

    // Si pas d'utilisateur, routes par défaut
    const defaultRoutes = [
        {path: '/', icon: 'fas fa-home', label: 'Accueil'},
        {path: '/about', icon: 'fas fa-info-circle', label: 'À propos'},
        {path: '/contact', icon: 'fas fa-envelope', label: 'Contact'}
    ];

    // Sélectionner les routes en fonction du rôle
    let routes = defaultRoutes;
    if (currentUser) {
        if (currentUser.role === 'admin') {
            routes = adminRoutes;
        } else if (currentUser.role === 'tutor') {
            routes = tutorRoutes;
        }
    }

    return (
        <div className={`sidebar ${collapsed ? 'collapsed' : ''}`}>
            <div className="sidebar-header">
                {!collapsed && <h3>Menu</h3>}
            </div>
            <ul className="nav flex-column">
                {routes.map((route) => (
                    <li className="nav-item" key={route.path}>
                        <Link
                            className={`nav-link ${location.pathname === route.path ? 'active' : ''}`}
                            to={route.path}
                        >
                            <i className={route.icon}></i>
                            {!collapsed && <span className="ms-2">{route.label}</span>}
                        </Link>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default Sidebar;